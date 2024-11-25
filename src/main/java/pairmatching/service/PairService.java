package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pairmatching.dto.LessonDto;
import pairmatching.dto.PairDto;
import pairmatching.model.CrewPair;
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

    public void checkExistingPair(LessonDto lessonChoice) {
        if (pairRepository.findByCourseAndMission(lessonChoice.getCourse(), lessonChoice.getMission()).isPresent()) {
            throw new PairExistsException(lessonChoice);
        }
    }

    public PairDto matchPairs(LessonDto lessonChoice) {
        Course course = lessonChoice.getCourse();
        Level level = lessonChoice.getLevel();
        Mission mission = lessonChoice.getMission();

        int tryCount = 0;
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

    private PairDto createPairsDto(Course course, Mission mission) {
        Optional<Pair> pair = pairRepository.findByCourseAndMission(course, mission);
        List<List<String>> pairCrewNames = new ArrayList<>();
        for (CrewPair crewPair : pair.orElseThrow().getCrewPairs()) {
            List<String> onePairCrewNames = new ArrayList<>();
            for (Crew crew : crewPair.getCrews()) {
                onePairCrewNames.add(crew.getName());
            }
            pairCrewNames.add(onePairCrewNames);
        }
        return new PairDto(pairCrewNames);
    }

    public void removePairs(LessonDto lessonChoice) {
        pairRepository.removePair(lessonChoice.getCourse(), lessonChoice.getMission());
    }

    private void matchEveryPair(List<String> crewNames, Course course, Level level, Mission mission) {
        int i = 0;
        List<CrewPair> pair = new ArrayList<>();
        while (i < crewNames.size()) {
            if (i == crewNames.size() - 2) {
                pair.add(new CrewPair(matchOnePair(i, crewNames, 2, course, level)));
                break;
            }
            if (i == crewNames.size() - 3) {
                pair.add(new CrewPair(matchOnePair(i, crewNames, 3, course, level)));
                break;
            }
            pair.add(new CrewPair(matchOnePair(i, crewNames, 2, course, level)));
            i += 2;
        }
        pairRepository.save(course, mission, pair);
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
