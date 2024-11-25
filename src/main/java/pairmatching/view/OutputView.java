package pairmatching.view;

import java.util.List;
import pairmatching.dto.PairDtos;
import pairmatching.dto.PairDtos.PairDto;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
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
            printDelimiter(i, crewNamesAmount);
        }
        System.out.println();
    }

    private void printDelimiter(int i, int crewNamesAmount) {
        if (i < crewNamesAmount - 1) {
            System.out.print(" : ");
        }
    }
}
