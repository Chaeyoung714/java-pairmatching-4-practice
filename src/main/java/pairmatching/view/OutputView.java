package pairmatching.view;

import java.util.List;
import java.util.Map;
import pairmatching.dto.MissionInfoDto;
import pairmatching.dto.PairDto;
import pairmatching.dto.PairDto.CrewPairDto;
import pairmatching.model.lesson.Level;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMissions(MissionInfoDto missionInfoDto) {
        System.out.println(System.lineSeparator() + "#############################################");
        printCourseNames(missionInfoDto);
        printMissionNames(missionInfoDto);
        System.out.println("############################################");
    }

    private void printCourseNames(MissionInfoDto missionInfoDto) {
        List<String> courseNames = missionInfoDto.getCourseNames();
        System.out.println(String.format("과정: %s | %s", courseNames.get(0), courseNames.get(1)));
    }

    private void printMissionNames(MissionInfoDto missionInfoDto) {
        Map<Level, List<String>> missionNames = missionInfoDto.getMissionNamesByLevel();
        System.out.println(String.format("미션:"));
        for (Level level : missionInfoDto.getLevels()) {
            System.out.printf("\t- %s: ", level.getName());
            List<String> missionNamesByLevel = missionNames.get(level);
            int namesAmount = missionNamesByLevel.size();
            if (missionNamesByLevel.isEmpty()) {
                System.out.println();
                continue;
            }
            for (int i = 0; i < namesAmount; i++) {
                System.out.print(missionNamesByLevel.get(i));
                printMissionDelimiter(i, namesAmount);
            }
            System.out.println();
        }
    }

    private void printMissionDelimiter(int i, int namesAmount) {
        if (i < namesAmount - 1) {
            System.out.print(" | ");
        }
    }

    public void printMatchedPairs(PairDto pairDtos) {
        System.out.println(System.lineSeparator() + "페어 매칭 결과입니다.");
        for (CrewPairDto crewPairDto : pairDtos.getDto()) {
            printOneMatchedPair(crewPairDto.getCrewNames());
        }
        System.out.println();
    }

    private void printOneMatchedPair(List<String> crewNames) {
        int crewNamesAmount = crewNames.size();
        for (int i = 0; i < crewNamesAmount; i++) {
            System.out.print(crewNames.get(i));
            printCrewDelimiter(i, crewNamesAmount);
        }
        System.out.println();
    }

    private void printCrewDelimiter(int i, int crewNamesAmount) {
        if (i < crewNamesAmount - 1) {
            System.out.print(" : ");
        }
    }

    public void printRetrievedPairs(PairDto pairDto) {
        if (pairDto.getDto().isEmpty()) {
            System.out.println("[ERROR] 매칭 이력이 없습니다." + System.lineSeparator());
            return;
        }
        printMatchedPairs(pairDto);
    }

    public void printInitialization() {
        System.out.println(System.lineSeparator() + "초기화 되었습니다." + System.lineSeparator());
    }
}
