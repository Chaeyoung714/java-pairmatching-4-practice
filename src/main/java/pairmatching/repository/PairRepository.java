package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pairmatching.model.Crew;
import pairmatching.model.CrewPair;
import pairmatching.model.Pair;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Level;
import pairmatching.model.lesson.Mission;

public class PairRepository {
    private final List<Pair> pairs;

    public PairRepository() {
        this.pairs = new ArrayList<>();
    }

    public void save(Course course, Mission mission, List<CrewPair> crews) {
        pairs.add(new Pair(course, mission, crews));
    }

    public boolean hasDuplicatedCrewPairInSameCourseAndLevel(List<Crew> crews, Course course, Level level) {
        for (Pair pair : pairs) {
            if (!pairMatchesCourseAndLevel(pair, course, level)) {
                continue;
            }
            List<CrewPair> targetCrewPairs = pair.getCrewPairs();
            for (CrewPair crewPair : targetCrewPairs) {
                int duplicatedCount = 0;
                for (Crew targetCrew : crewPair.getCrews()) {
                    if (crews.contains(targetCrew)) {
                        duplicatedCount++;
                    }
                }
                //같은 과정과 레벨의 페어가 2명 이상 중복 시
                if (duplicatedCount > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pairMatchesCourseAndLevel(Pair pair, Course course, Level level) {
        return pair.getCourse().equals(course) && pair.getMission().getLevel().equals(level);
    }

    public Optional<Pair> findByCourseAndMission(Course course, Mission mission) {
        for (Pair pair : pairs) {
            if (pair.getCourse().equals(course) && pair.getMission().equals(mission)) {
                return Optional.of(pair);
            }
        }
        return Optional.empty();
    }

    public void removePair(Course course, Mission mission) {
        Optional<Pair> removingPair = findByCourseAndMission(course, mission);
        if (removingPair.isPresent()) {
            pairs.remove(removingPair.orElseThrow());
            return;
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 과정과 미션의 페어가 없습니다.");
    }
}
