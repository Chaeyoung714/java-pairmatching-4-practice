package pairmatching.model;

import java.util.Collections;
import java.util.List;

public class CrewPair {
    private final List<Crew> crews;

    public CrewPair(List<Crew> crews) {
        validateSize(crews);
        this.crews = crews;
    }

    private void validateSize(List<Crew> crews) {
        if (crews.size() != 2 && crews.size() != 3) {
            throw new IllegalStateException("[SYSTEM] 페어 매칭 수가 알맞지 않습니다.");
        }
    }

    public List<Crew> getCrews() {
        return Collections.unmodifiableList(crews);
    }
}
