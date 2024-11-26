package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Level;
import pairmatching.model.lesson.Mission;

public class MissionRespository {
    private final List<Mission> missions;

    public MissionRespository() {
        this.missions = new ArrayList<>();
    }

    public void save(String name, Level level) {
        missions.add(new Mission(name, level));
    }

    public List<Mission> findMissionsByLevel(Level level) {
        List<Mission> missionsByLevel = new ArrayList<>();
        for (Mission mission : missions) {
            if (mission.getLevel().equals(level)) {
                missionsByLevel.add(mission);
            }
        }
        return missionsByLevel;
    }

    public Mission findByNameAndLevel(String name, Level level) {
        for (Mission mission : missions) {
            if (mission.getName().equals(name)
                    && mission.getLevel().equals(level)) {
                return mission;
            }
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 이름 또는 레벨의 미션이 없습니다.");
    }
}
