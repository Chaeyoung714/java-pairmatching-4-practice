package pairmatching.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private FileReader() {
    }

    public static List<String> readCrew(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            List<String> fileBody = new ArrayList<>();
            while (scanner.hasNext()) {
                fileBody.add(scanner.next());
            }
            return fileBody;
        } catch (IOException e) {
            throw new IllegalStateException("[SYSTEM] File not found");
        }
    }



}
