package bowling.domain;

import bowling.domain.bonusScore.BonusScore;
import bowling.domain.exceptions.InvalidScoreValueException;
import bowling.domain.exceptions.InvalidTryOfApplyBonusException;

import java.util.Objects;

public class FrameScore {
    private final FrameScoreStatus frameScoreStatus;
    private final Integer score;

    public FrameScore(FrameScoreStatus frameScoreStatus, int score) {
        validate(score);
        this.frameScoreStatus = frameScoreStatus;
        this.score = score;
    }

    private void validate(int score) {
        if (score < 0 ) {
            throw new InvalidScoreValueException("Score value cannot be negative value");
        }
    }

    public boolean isComplete() {
        return this.frameScoreStatus == FrameScoreStatus.COMPLETE;
    }

    public FrameScore applySpareBonus(int spareBonus) {
        validateApplyBonus();

        return new FrameScore(FrameScoreStatus.COMPLETE, this.score + spareBonus);
    }

    public FrameScore applyStrikeBonus(BonusScore bonusScore) {
        if (bonusScore.isStrikeBonus()) {
            return new FrameScore(FrameScoreStatus.COMPLETE, this.score + bonusScore.getStrikeBonus());
        }
        return new FrameScore(FrameScoreStatus.NOT_READY, this.score + bonusScore.getStrikeBonus());
    }

    public FrameScore applySpecialStrikeBonus(int specialStrikeBonus) {
        validateApplyBonus();

        return new FrameScore(FrameScoreStatus.COMPLETE, this.score + 10 + specialStrikeBonus);
    }

    public Integer getScore() {
        return this.score;
    }

    private void validateApplyBonus() {
        if (this.frameScoreStatus == FrameScoreStatus.COMPLETE) {
            throw new InvalidTryOfApplyBonusException("이미 계산된 점수에 보너스를 적용할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameScore that = (FrameScore) o;
        return score == that.score &&
                frameScoreStatus == that.frameScoreStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameScoreStatus, score);
    }

    @Override
    public String toString() {
        return "FrameScore{" +
                "frameScoreStatus=" + frameScoreStatus +
                ", score=" + score +
                '}';
    }
}