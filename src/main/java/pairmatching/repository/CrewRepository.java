package pairmatching.repository;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pairmatching.model.lesson.Course;
import pairmatching.model.Crew;

public class CrewRepository {
    private final List<Crew> crews;

    public CrewRepository() {
        this.crews = new ArrayList<>();
    }

    public void save(String name, Course course) {
        crews.add(new Crew(name, course));
    }

    public List<String> getShuffledNames(Course course) {
        List<String> crewNames = new ArrayList<>();
        for (Crew crew : crews) {
            if (crew.getCourse().equals(course)) {
                crewNames.add(crew.getName());
            }
        }
        return Collections.unmodifiableList(Randoms.shuffle(crewNames));
    }

    public Crew findByNameAndCourse(String name, Course course) {
        for (Crew crew : crews) {
            if (crew.getName().equals(name)
                    && crew.getCourse().equals(course)) {
                return crew;
            }
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 이름 또는 과정의 크루가 없습니다.");
    }

    public int size() {
        return crews.size();
    }




}
