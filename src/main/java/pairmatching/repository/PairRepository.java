package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import pairmatching.model.Crew;
import pairmatching.model.Pair;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Level;
import pairmatching.model.lesson.Mission;

public class PairRepository {
    private final List<Pair> pairs;

    public PairRepository() {
        this.pairs = new ArrayList<>();
    }

    public void save(Course course, Mission mission, List<Crew> crews) {
        // 페어가 존재하는지 확인하고 넣어야함
        pairs.add(new Pair(course, mission, crews));
    }

    public boolean hasDuplicatedCrewPairInSameCourseAndLevel(List<Crew> crews, Course course, Level level) {
        for (Pair pair : pairs) {
            if (!pairMatchesCourseAndLevel(pair, course, level)) {
                continue;
            }
            int duplicatedCount = 0;
            List<Crew> targetCrews = pair.getCrews();
            for (Crew targetCrew : targetCrews) {
                if (crews.contains(targetCrew)) {
                    duplicatedCount++;
                }
            }
            //같은 과정과 레벨의 페어가 2명 이상 중복 시
            if (duplicatedCount > 1) {
                try {
                    return true;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }

    private boolean pairMatchesCourseAndLevel(Pair pair, Course course, Level level) {
        return pair.getCourse().equals(course) && pair.getMission().getLevel().equals(level);
    }

    public boolean hasPairWithCourseAndMission(Course course, Mission mission) {
        if (findByCourseAndMission(course, mission).isEmpty()) {
            return false;
        }
        return true;
    }

    public List<Pair> findByCourseAndMission(Course course, Mission mission) {
        List<Pair> matchedPairs = new ArrayList<>();
        for (Pair pair : pairs) {
            if (pair.getCourse().equals(course) && pair.getMission().equals(mission)) {
                matchedPairs.add(pair);
            }
        }
        return matchedPairs;
    }
}
