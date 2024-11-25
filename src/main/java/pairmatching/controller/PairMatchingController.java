package pairmatching.controller;

import java.util.List;
import pairmatching.dto.LessonDto;
import pairmatching.dto.MissionInfoDto;
import pairmatching.dto.PairDto;
import pairmatching.service.PairRetrieveService;
import pairmatching.util.PairExistsException;
import pairmatching.service.CrewService;
import pairmatching.service.MissionService;
import pairmatching.service.PairMatchService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CrewService crewService;
    private final MissionService missionService;
    private final PairMatchService pairMatchService;
    private final PairRetrieveService pairRetrieveService;

    public PairMatchingController(InputView inputView, OutputView outputView, CrewService crewService,
                                  MissionService missionService, PairMatchService pairMatchService,
                                  PairRetrieveService pairRetrieveService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.crewService = crewService;
        this.missionService = missionService;
        this.pairMatchService = pairMatchService;
        this.pairRetrieveService = pairRetrieveService;
    }

    public void run() {
        crewService.registerCrews();
        MissionInfoDto missionInfoDto = missionService.registerMissions();
        doOneFunction(missionInfoDto);
    }

    private void doOneFunction(MissionInfoDto missionInfoDto) {
        String functionInput = chooseFunction();
        performService(functionInput, missionInfoDto);
    }

    private String chooseFunction() {
        while(true) {
            try {
                String functionInput = inputView.inputFunctionChoice();
                return functionInput;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private void performService(String function, MissionInfoDto missionInfoDto) {
        if (continueService(function)) {
            if (function.equals("1")) {
                outputView.printMissions(missionInfoDto);
                PairDto pairDto = performMatchService();
                outputView.printMatchedPairs(pairDto);
            }
            if (function.equals("2")) {
                outputView.printMissions(missionInfoDto);
                PairDto pairDto = performRetrieveServie();
                outputView.printRetrievedPairs(pairDto);
            }
            if (function.equals("3")) {

            }
            doOneFunction(missionInfoDto);
        }
    }

    private PairDto performMatchService() {
        while (true) {
            try {
                List<String> lessonChoice = chooseLesson();
                LessonDto lessonDto = missionService.findMatchingLesson(lessonChoice);
                pairMatchService.checkExistingPair(lessonDto);
                return pairMatchService.matchPairs(lessonDto);
            } catch (PairExistsException e) {
                if (inputRematch()) {
                    pairMatchService.removePairs(e.getLessonOfExistingPair());
                    return pairMatchService.matchPairs(e.getLessonOfExistingPair());
                }
                return performMatchService();
            } catch (IllegalStateException | IllegalArgumentException e) {
                outputView.printErrorMessage("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private PairDto performRetrieveServie() {
        while (true) {
            try {
                List<String> lessonChoice = chooseLesson();
                LessonDto lessonDto = missionService.findMatchingLesson(lessonChoice);
                return pairRetrieveService.retrievePair(lessonDto);
            } catch (IllegalStateException | IllegalArgumentException e) {
                outputView.printErrorMessage("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private List<String> chooseLesson() {
        return inputView.inputLessonChoice();
    }

    private boolean inputRematch() {
        while (true) {
            try {
                String answer = inputView.inputRematch();
                return answer.equals("네");
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private boolean continueService(String function) {
        return !function.equals("Q");
    }
}
