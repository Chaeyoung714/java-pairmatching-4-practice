package pairmatching.util;

import pairmatching.dto.LessonDto;

public class PairExistsException extends RuntimeException{
    private final LessonDto lessonOfExistingPair;

    public PairExistsException(LessonDto lessonOfExistingPair) {
        this.lessonOfExistingPair = lessonOfExistingPair;
    }

    public LessonDto getLessonOfExistingPair() {
        return lessonOfExistingPair;
    }
}
