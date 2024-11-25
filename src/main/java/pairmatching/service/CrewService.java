package pairmatching.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pairmatching.model.lesson.Course;
import pairmatching.util.FileReader;
import pairmatching.repository.CrewRepository;

public class CrewService {
    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public void registerCrews() {
//        List<String> backendCrewNames = FileReader.readCrew("./src/main/resources/backend-crew.md");
//        List<String> frontEndCrewNames = FileReader.readCrew("./src/main/resources/frontend-crew.md");
        List<String> backendCrewNames = FileReader.readCrew("./java-pairmatching-precourse/src/main/resources/backend-crew.md");
        List<String> frontEndCrewNames = FileReader.readCrew("./java-pairmatching-precourse/src/main/resources/frontend-crew.md");

        List<String> totalCrewNames = Stream.of(backendCrewNames, frontEndCrewNames)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        validateDuplication(totalCrewNames);

        saveCrewsByCourse(Course.BACKEND, backendCrewNames);
        saveCrewsByCourse(Course.FRONTEND, frontEndCrewNames);
    }

    private void saveCrewsByCourse(Course course, List<String> crewNames) {
        for (String name : crewNames) {
            crewRepository.save(name, course);
        }
    }

    private void validateDuplication(List<String> crewNames) {
        Set<String> uniqueNames = new HashSet<>(crewNames);
        if (uniqueNames.size() != crewNames.size()) {
            throw new IllegalStateException("[SYSTEM] 크루 이름 간 중복이 발생했습니다.");
        }
    }
}
