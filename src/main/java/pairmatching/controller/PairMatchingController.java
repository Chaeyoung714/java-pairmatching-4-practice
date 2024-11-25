package pairmatching.controller;

import pairmatching.service.CrewService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CrewService crewService;

    public PairMatchingController(InputView inputView, OutputView outputView, CrewService crewService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.crewService = crewService;
    }

    public void run() {
        crewService.registerCrews();
        String functionInput = chooseFunction();
        performService(functionInput);
    }

    private String chooseFunction() {
        while(true) {
            try {
                String functionInput = inputView.inputFunctionChoice();
                return functionInput;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void performService(String function) {
        if (continueService(function)) {
            if (function.equals("1")) {

            }
            if (function.equals("2")) {

            }
            if (function.equals("3")) {

            }
            chooseFunction();
        }
    }

    private boolean continueService(String function) {
        return function != "Q";
    }


}
