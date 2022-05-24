package bowling.domain;

import static bowling.domain.Pins.START_PIN_COUNT;

import java.util.Objects;

public class Scores {
    private Score firstScore;
    private Score secondScore;

    public Scores() { }

    public Score first() {
        return firstScore;
    }

    public Score second() {
        return secondScore;
    }

    private boolean isHitFirst() {
        return firstScore == null;
    }

    public boolean isHitTwice() {
        return firstScore != null && secondScore != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scores scores = (Scores) o;
        return Objects.equals(firstScore, scores.firstScore) && Objects.equals(secondScore, scores.secondScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstScore, secondScore);
    }

    public void hit(int hitCount) {
        if (isHitFirst()) {
            firstScore = new Score(hitCount);
            return;
        }

        secondScore = new Score(hitCount);
    }

    public boolean isStrike() {
        return firstScore.get() == START_PIN_COUNT;
    }

    public boolean isSpare() {
        if (firstScore == null || secondScore == null)
            return false;

        return firstScore.get() + secondScore.get() == START_PIN_COUNT;
    }
}