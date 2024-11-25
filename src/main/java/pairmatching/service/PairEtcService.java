package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pairmatching.dto.LessonDto;
import pairmatching.dto.PairDto;
import pairmatching.model.Crew;
import pairmatching.model.CrewPair;
import pairmatching.model.Pair;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Mission;
import pairmatching.repository.PairRepository;

public class PairEtcService {
    private final PairRepository pairRepository;

    public PairEtcService(PairRepository pairRepository) {
        this.pairRepository = pairRepository;
    }

    public PairDto retrievePair(LessonDto lessonDto) {
        Course course = lessonDto.getCourse();
        Mission mission = lessonDto.getMission();
        Optional<Pair> pair = pairRepository.findByCourseAndMission(course, mission);
        if (pair.isEmpty()) {
            return new PairDto(new ArrayList<>(new ArrayList<>()));
        }
        return createPairsDto(pair.get());
    }

    private PairDto createPairsDto(Pair pair) {
        List<List<String>> pairCrewNames = new ArrayList<>();
        for (CrewPair crewPair : pair.getCrewPairs()) {
            List<String> onePairCrewNames = new ArrayList<>();
            for (Crew crew : crewPair.getCrews()) {
                onePairCrewNames.add(crew.getName());
            }
            pairCrewNames.add(onePairCrewNames);
        }
        return new PairDto(pairCrewNames);
    }

    public void initializePairs() {
        pairRepository.initializeAll();
    }
}
