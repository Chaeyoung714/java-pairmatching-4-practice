package pairmatching;

import pairmatching.controller.PairMatchingController;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MissionRespository;
import pairmatching.repository.PairRepository;
import pairmatching.service.CrewService;
import pairmatching.service.MissionService;
import pairmatching.service.PairMatchService;
import pairmatching.service.PairEtcService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        CrewRepository crewRepository = new CrewRepository();
        MissionRespository missionRespository = new MissionRespository();
        PairRepository pairRepository = new PairRepository();

        PairMatchingController controller = new PairMatchingController(
                new InputView()
                , new OutputView()
                , new CrewService(crewRepository)
                , new MissionService(missionRespository)
                , new PairMatchService(missionRespository, crewRepository, pairRepository)
                , new PairEtcService(pairRepository)
        );
        controller.run();
    }
}
