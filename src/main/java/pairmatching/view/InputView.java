package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pairmatching.dto.LessonDto;
import pairmatching.model.lesson.Level;

public class InputView {
    public String inputFunctionChoice() {
        System.out.println("기능을 선택하세요." + System.lineSeparator()
                + "1. 페어 매칭" + System.lineSeparator()
                + "2. 페어 조회" + System.lineSeparator()
                + "3. 페어 초기화" + System.lineSeparator()
                + "Q. 종료");
        String choice = Console.readLine();
        validateFunctionChoiceInput(choice);
        return choice;
    }

    private void validateFunctionChoiceInput(String choice) {
        if (!choice.equals("1")
                && !choice.equals("2")
                && !choice.equals("3")
                && !choice.equals("Q")) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> inputMissionChoice(LessonDto lessonDto) {
        System.out.println(System.lineSeparator() + "#############################################");
        printCourseNames(lessonDto);
        printMissionNames(lessonDto);
        System.out.println("############################################");
        System.out.println("과정, 레벨, 미션을 선택하세요." + System.lineSeparator()
                + "ex) 백엔드, 레벨1, 자동차경주");
        String choices = Console.readLine();
        validateMissionChoiceInput(choices);
        return new ArrayList<>(List.of(choices.split(", ")));
    }

    private void validateMissionChoiceInput(String choices) {
        String[] parsedChoices = choices.split(", ", -1);
        if (parsedChoices.length != 3) {
            throw new IllegalArgumentException();
        }
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
                printDelimiter(i, namesAmount);
            }
            System.out.println();
        }
    }

    private void printDelimiter(int i, int namesAmount) {
        if (i < namesAmount - 1) {
            System.out.print(" | ");
        }
    }
}
