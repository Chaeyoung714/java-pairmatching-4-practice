package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import pairmatching.dto.PairDtos;
import pairmatching.model.Pair;
import pairmatching.util.PairDuplicationException;
import pairmatching.util.PairExistsException;
import pairmatching.model.Crew;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Level;
import pairmatching.model.lesson.Mission;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MissionRespository;
import pairmatching.repository.PairRepository;

public class PairService {
    private final MissionRespository missionRespository;
    private final CrewRepository crewRepository;
    private final PairRepository pairRepository;

    public PairService(MissionRespository missionRespository, CrewRepository crewRepository,
                       PairRepository pairRepository) {
        this.missionRespository = missionRespository;
        this.crewRepository = crewRepository;
        this.pairRepository = pairRepository;
    }

    public PairDtos matchPairs(List<String> choice) {
        int tryCount = 0;
        Course course = Course.findByName(choice.get(0));
        Level level = Level.findByName(choice.get(1));
        Mission mission = missionRespository.findByNameAndLevel(choice.get(2), level);

        //페어 존재하는지 확인
        checkExistingPair(course, mission);

        //페어 매칭
        List<String> crewNames = crewRepository.getShuffledNames(course);
        while (true) {
            try {
                tryCount++;
                matchEveryPair(crewNames, course, level, mission);
                break;
            } catch (PairDuplicationException e) {
                if (tryCount == 3) {
                    throw new PairDuplicationException("[SYSTEM] 페어를 매칭할 수 없습니다.");
                }
            }
        }

        return createPairsDto(course, mission);
    }

    private PairDtos createPairsDto(Course course, Mission mission) {
        List<Pair> pairs = pairRepository.findByCourseAndMission(course, mission);
        List<List<String>> pairCrewNames = new ArrayList<>();
        for (Pair pair : pairs) {
            List<String> onePairCrewNames = new ArrayList<>();
            for (Crew crew : pair.getCrews()) {
                onePairCrewNames.add(crew.getName());
            }
            pairCrewNames.add(onePairCrewNames);
        }
        return new PairDtos(pairCrewNames);
    }

    private void checkExistingPair(Course course, Mission mission) {
        if (pairRepository.hasPairWithCourseAndMission(course, mission)) {
            throw new PairExistsException();
        }
    }

    private void matchEveryPair(List<String> crewNames, Course course, Level level, Mission mission) {
        int i = 0;
        while (i < crewNames.size()) {
            if (i == crewNames.size() - 2) {
                pairRepository.save(course, mission, matchOnePair(i, crewNames, 2, course, level));
                break;
            }
            if (i == crewNames.size() - 3) {
                pairRepository.save(course, mission, matchOnePair(i, crewNames, 3, course, level));
                break;
            }
            pairRepository.save(course, mission, matchOnePair(i, crewNames, 2, course, level));
            i += 2;
        }
    }

    private List<Crew> matchOnePair(int indexOfCrewNames, List<String> crewNames, int pairSize, Course course, Level level) {
        List<Crew> matchedCrews = new ArrayList<>();
        for (int i = 0; i < pairSize; i++) {
            String crewName = crewNames.get(indexOfCrewNames + i);
            matchedCrews.add(crewRepository.findByNameAndCourse(crewName, course));
        }
        validatePairDuplication(matchedCrews, course, level);
        return matchedCrews;
    }

    private void validatePairDuplication(List<Crew> crews, Course course, Level level) {
        //같은 과정(은 확인할 필요 없음), 레벨 내에 2명 이상 겹치는 페어가 있는지 확인
        if (pairRepository.hasDuplicatedCrewPairInSameCourseAndLevel(crews, course, level)) {
            throw new PairDuplicationException();
        }
    }
}
