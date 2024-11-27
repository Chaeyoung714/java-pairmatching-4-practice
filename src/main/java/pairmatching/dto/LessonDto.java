package pairmatching.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Level;

public class LessonDto {
    private final List<String> courseNames;
    private final List<Level> levels;
    private final Map<Level, List<String>> missionNamesByLevel;

    public LessonDto(Map<Level, List<String>> missionNamesByLevel) {
        this.courseNames = Course.getAllNames();
        this.levels = new ArrayList<>(List.of(Level.values()));
        this.missionNamesByLevel = missionNamesByLevel;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public Map<Level, List<String>> getMissionNamesByLevel() {
        return missionNamesByLevel;
    }

    public List<Level> getLevels() {
        return levels;
    }
}
