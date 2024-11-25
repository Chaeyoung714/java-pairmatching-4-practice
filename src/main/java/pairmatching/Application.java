package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.repository.CrewRepository;
import pairmatching.service.CrewService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PairMatchingController controller = new PairMatchingController(
                new InputView()
                , new OutputView()
                , new CrewService(new CrewRepository())
        );
        controller.run();
    }
}
