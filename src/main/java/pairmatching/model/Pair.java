package pairmatching.model;

import java.util.Collections;
import java.util.List;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Mission;

public class Pair {
    private final Course course;
    private final Mission mission;
    private final List<CrewPair> crewPairs;

    public Pair(Course course, Mission mission, List<CrewPair> crewPairs) {
        this.course = course;
        this.mission = mission;
        this.crewPairs = crewPairs;
    }

    public List<CrewPair> getCrewPairs() {
        return crewPairs;
    }

    public Mission getMission() {
        return mission;
    }

    public Course getCourse() {
        return course;
    }
}
