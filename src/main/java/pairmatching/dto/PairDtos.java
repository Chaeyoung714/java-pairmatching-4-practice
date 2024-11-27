package pairmatching.dto;

import java.util.ArrayList;
import java.util.List;

public class PairDtos {
    private final List<PairDto> pairDtos;

    public PairDtos(List<List<String>> pairCrewNames) {
        List<PairDto> dtos = new ArrayList<>();
        for (List<String> onePairCrewNames : pairCrewNames) {
            dtos.add(new PairDto(onePairCrewNames));
        }
        this.pairDtos = dtos;
    }

    public List<PairDto> getPairDtos() {
        return pairDtos;
    }

    public class PairDto {
        private final List<String> crewNames;

        public PairDto(List<String> crewNames) {
            this.crewNames = crewNames;
        }

        public List<String> getCrewNames() {
            return crewNames;
        }
    }
}
