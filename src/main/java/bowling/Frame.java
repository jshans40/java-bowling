package bowling;

import bowling.domain.scores.Scores;
import java.util.Objects;

public class Frame {

    private final Scores scores;
    private final Score sumScore;

    public Frame(Scores scores) {
        this(scores, Score.of(scores.sumScore()));
    }


    private Frame(Scores scores, Score sumScore) {
        this.scores = scores;
        this.sumScore = sumScore;
    }

    public boolean isClosedStroke() {
        return scores.isClosed();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(scores, frame.scores) && Objects.equals(sumScore,
            frame.sumScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, sumScore);
    }
}
