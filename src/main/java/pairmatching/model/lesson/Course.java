package pairmatching.model.lesson;

import java.util.ArrayList;
import java.util.List;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        for (Course course : Course.values()) {
            names.add(course.name);
        }
        return names;
    }

    public static Course findByName(String name) {
        for (Course course : Course.values()) {
            if (course.name.equals(name)) {
                return course;
            }
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 이름의 과정이 없습니다.");
    }
}
