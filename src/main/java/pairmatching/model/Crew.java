package pairmatching.model;

import pairmatching.model.lesson.Course;

public class Crew {
    private final String name;
    private final Course course;

    public Crew(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }
}
