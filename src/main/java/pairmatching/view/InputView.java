package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputFunctionChoice() {
        System.out.println("기능을 선택하세요." + System.lineSeparator()
                + "1. 페어 매칭" + System.lineSeparator()
                + "2. 페어 조회" + System.lineSeparator()
                + "3. 페어 초기화" + System.lineSeparator()
                + "Q. 종료");
        String choice = Console.readLine();
        validateChoiceInput(choice);
        return choice;
    }

    private void validateChoiceInput(String choice) {
        if (!choice.equals("1")
                && !choice.equals("2")
                && !choice.equals("3")
                && !choice.equals("Q")) {
            throw new IllegalArgumentException();
        }
    }
}
