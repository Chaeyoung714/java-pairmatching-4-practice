package pairmatching.controller;

import java.util.List;
import pairmatching.dto.LessonDto;
import pairmatching.dto.PairDtos;
import pairmatching.util.PairExistsException;
import pairmatching.service.CrewService;
import pairmatching.service.MissionService;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CrewService crewService;
    private final MissionService missionService;
    private final PairService pairService;

    public PairMatchingController(InputView inputView, OutputView outputView, CrewService crewService,
                                  MissionService missionService, PairService pairService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.crewService = crewService;
        this.missionService = missionService;
        this.pairService = pairService;
    }

    public void run() {
        crewService.registerCrews();
        LessonDto lessonDto = missionService.registerMissions();
        String functionInput = chooseFunction();
        performService(functionInput, lessonDto);
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

    private void performService(String function, LessonDto lessonDto) {
        if (continueService(function)) {
            if (function.equals("1")) {
                PairDtos pairDtos = performMatchService(lessonDto);
                outputView.printMatchedPairs(pairDtos);
            }
            if (function.equals("2")) {

            }
            if (function.equals("3")) {

            }
            chooseFunction();
        }
    }

    private PairDtos performMatchService(LessonDto lessonDto) {
        while (true) {
            try {
                outputView.printMissions(lessonDto);
                List<String> missionChoice = chooseMission();
                return pairService.matchPairs(missionChoice);
            } catch (PairExistsException e) {
                // 페어 재매칭 물어봐야 함
                if (inputRematch()) {
                    chooseMission();
                    return performMatchService(lessonDto);
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                outputView.printErrorMessage("[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private List<String> chooseMission() {
        return inputView.inputMissionChoice();
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
        return function != "Q";
    }
}
