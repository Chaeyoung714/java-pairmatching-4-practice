package pairmatching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.repository.CrewRepository;
import pairmatching.service.CrewService;

public class FunctionTest {
    private final CrewRepository crewRepository = new CrewRepository();
    private final CrewService crewService = new CrewService(crewRepository);

    @Test
    void 파일에서_크루이름_받아오기() {
        crewService.registerCrews();
        Assertions.assertThat(crewRepository.size()).isEqualTo(35);
    }
}
