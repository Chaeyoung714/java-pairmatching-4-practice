package pairmatching.model.lesson;

public class Mission {
    private final String name;
    private final Level level;

    public Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }
}
