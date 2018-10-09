import java.util.stream.IntStream;

public class Frame {
    private boolean lastFrame;

    private int[] rolls;
    private int rollIndex = 0;
    private boolean spare;
    private boolean strike;
    private boolean finished;
    public Frame() {
        this(false);
    }

    public Frame(boolean lastFrame) {
        this.rolls = lastFrame ? new int[3] : new int[2];
        this.lastFrame = lastFrame;
    }

    public void roll(Integer pinsHit) {
        rolls[rollIndex] = pinsHit;
        boolean firstRoll = rollIndex == 0;
        boolean lastRoll = rollIndex == (this.rolls.length - 1);

        this.strike = firstRoll && getScore() == 10;
        this.spare = lastRoll && getScore() == 10;
        if (lastFrame) {
            this.finished = lastRoll;
        } else {
            this.finished = this.strike || this.spare || lastRoll;
        }

        rollIndex++;
    }

    public int getScore() {
        return IntStream.of(this.rolls).sum();
    }

    public boolean isLastFrame() {
        return lastFrame;
    }

    public int getRollHits(int rolls) {
        return IntStream.of(this.rolls).limit(rolls).sum();
    }

    public boolean isSpare() {
        return spare;
    }

    public boolean isStrike() {
        return strike;
    }

    public boolean isFinished() {
        return finished;
    }
}
