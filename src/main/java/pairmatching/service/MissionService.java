package pairmatching.service;

import static pairmatching.model.lesson.Level.LEVEL1;
import static pairmatching.model.lesson.Level.LEVEL2;
import static pairmatching.model.lesson.Level.LEVEL4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.dto.LessonDto;
import pairmatching.dto.MissionInfoDto;
import pairmatching.model.lesson.Course;
import pairmatching.model.lesson.Level;
import pairmatching.model.lesson.Mission;
import pairmatching.repository.MissionRespository;

public class MissionService {
    private final MissionRespository missionRespository;

    public MissionService(MissionRespository missionRespository) {
        this.missionRespository = missionRespository;
    }

    public MissionInfoDto registerMissions() {
        missionRespository.save("자동차경주", LEVEL1);
        missionRespository.save("로또", LEVEL1);
        missionRespository.save("숫자야구게임", LEVEL1);
        missionRespository.save("장바구니", LEVEL2);
        missionRespository.save("결제", LEVEL2);
        missionRespository.save("지하철노선도", LEVEL2);
        missionRespository.save("성능개선", LEVEL4);
        missionRespository.save("배포", LEVEL4);

        return createMissionsDto();
    }

    private MissionInfoDto createMissionsDto() {
        Map<Level, List<String>> missionNamesByLevel = new HashMap<>();
        for (Level level : Level.values()) {
            List<String> missionNames = new ArrayList<>();
            for (Mission mission : missionRespository.findMissionsByLevel(level)) {
                missionNames.add(mission.getName());
            }
            missionNamesByLevel.put(level, missionNames);
        }
        return new MissionInfoDto(missionNamesByLevel);
    }

    public LessonDto findMatchingLesson(List<String> lessonChoice) {
        Course course = Course.findByName(lessonChoice.get(0));
        Level level = Level.findByName(lessonChoice.get(1));
        Mission mission = missionRespository.findByNameAndLevel(lessonChoice.get(2), level);
        validateLesson(level, mission);
        return new LessonDto(course, level, mission);
    }

    private void validateLesson(Level level, Mission mission) {
        if (!mission.getLevel().equals(level)) {
            throw new IllegalArgumentException();
        }
    }
}
