package pairmatching.controller;

import java.util.List;
import pairmatching.service.CrewService;

public class PairMatchingController {
    private final CrewService crewService;

    public PairMatchingController(CrewService crewService) {
        this.crewService = crewService;
    }

    public void run() {
        crewService.registerCrews();

    }



}
