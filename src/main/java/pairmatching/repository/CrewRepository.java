package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import pairmatching.model.Course;
import pairmatching.model.Crew;

public class CrewRepository {
    private final List<Crew> crews;

    public CrewRepository() {
        this.crews = new ArrayList<>();
    }

    public void save(String name, Course course) {
        crews.add(new Crew(name, course));
    }

    public int size() {
        return crews.size();
    }




}
