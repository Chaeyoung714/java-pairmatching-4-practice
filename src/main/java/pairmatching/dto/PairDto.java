package pairmatching.dto;

import java.util.ArrayList;
import java.util.List;

public class PairDto {
    private final List<CrewPairDto> dto;

    public PairDto(List<List<String>> pairCrewNames) {
        List<CrewPairDto> dtos = new ArrayList<>();
        for (List<String> onePairCrewNames : pairCrewNames) {
            dtos.add(new CrewPairDto(onePairCrewNames));
        }
        this.dto = dtos;
    }

    public List<CrewPairDto> getDto() {
        return dto;
    }

    public class CrewPairDto {
        private final List<String> crewNames;

        public CrewPairDto(List<String> crewNames) {
            this.crewNames = crewNames;
        }

        public List<String> getCrewNames() {
            return crewNames;
        }
    }
}
