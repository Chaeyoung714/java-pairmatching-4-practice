package pairmatching.model;

import java.util.Collections;
import java.util.List;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Mission;

public class Pair {
    private final Course course;
    private final Mission mission;
    private final List<Crew> crews;

    public Pair(Course course, Mission mission, List<Crew> crews) {
        validateSize(crews);
        this.course = course;
        this.mission = mission;
        this.crews = crews;
    }

    private void validateSize(List<Crew> crews) {
        if (crews.size() != 2 && crews.size() != 3) {
            throw new IllegalStateException("[SYSTEM] 페어 매칭 수가 알맞지 않습니다.");
        }
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }

    public Mission getMission() {
        return mission;
    }

    public Course getCourse() {
        return course;
    }
}
