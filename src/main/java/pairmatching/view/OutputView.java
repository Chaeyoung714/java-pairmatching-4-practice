package pairmatching.view;

import java.util.List;
import java.util.Map;
import pairmatching.dto.LessonDto;
import pairmatching.dto.PairDtos;
import pairmatching.dto.PairDtos.PairDto;
import pairmatching.model.lesson.Level;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMissions(LessonDto lessonDto) {
        System.out.println(System.lineSeparator() + "#############################################");
        printCourseNames(lessonDto);
        printMissionNames(lessonDto);
        System.out.println("############################################");
    }

    private void printCourseNames(LessonDto lessonDto) {
        List<String> courseNames = lessonDto.getCourseNames();
        System.out.println(String.format("과정: %s | %s", courseNames.get(0), courseNames.get(1)));
    }

    private void printMissionNames(LessonDto lessonDto) {
        Map<Level, List<String>> missionNames = lessonDto.getMissionNamesByLevel();
        System.out.println(String.format("미션:"));
        for (Level level : lessonDto.getLevels()) {
            System.out.printf("\t- %s: ", level.getName());
            List<String> missionNamesByLevel = missionNames.get(level);
            int namesAmount = missionNamesByLevel.size();
            if (missionNamesByLevel.isEmpty()) {
                System.out.println();
                continue;
            }
            for (int i = 0; i < namesAmount; i++) {
                System.out.print(missionNamesByLevel.get(i));
                printMissionDelimiter(i, namesAmount);
            }
            System.out.println();
        }
    }

    private void printMissionDelimiter(int i, int namesAmount) {
        if (i < namesAmount - 1) {
            System.out.print(" | ");
        }
    }

    public void printMatchedPairs(PairDtos pairDtos) {
        System.out.println(System.lineSeparator() + "페어 매칭 결과입니다.");
        for (PairDto pairDto : pairDtos.getPairDtos()) {
            printOneMatchedPair(pairDto.getCrewNames());
        }
        System.out.println();
    }

    private void printOneMatchedPair(List<String> crewNames) {
        int crewNamesAmount = crewNames.size();
        for (int i = 0; i < crewNamesAmount; i++) {
            System.out.print(crewNames.get(i));
            printCrewDelimiter(i, crewNamesAmount);
        }
        System.out.println();
    }

    private void printCrewDelimiter(int i, int crewNamesAmount) {
        if (i < crewNamesAmount - 1) {
            System.out.print(" : ");
        }
    }
}
