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

    public List<String> inputMissionChoice() {
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

    public String inputRematch() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?"
                + System.lineSeparator() + "네 | 아니오");
        String answer = Console.readLine();
        validateYesNoAnswer(answer);
        return answer;
    }

    private void validateYesNoAnswer(String answer) {
        if (answer != "네" && answer != "아니오") {
            throw new IllegalArgumentException();
        }
    }
}
