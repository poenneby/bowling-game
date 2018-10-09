public class BowlingGame {
    private Frame[] frames = {
            new Frame(),
            new Frame(),
            new Frame(),
            new Frame(),
            new Frame(),
            new Frame(),
            new Frame(),
            new Frame(),
            new Frame(),
            new Frame(true)
    };
    private int frameIndex = 0;

    public int getScore() {
        int score = 0;
        for (int i = 0; i < frames.length; i++) {
            int bonus = 0;
            if (frames[i].isLastFrame() && frames[i - 1].isStrike()) {
                return score += frames[i].getScore();
            } else if (frames[i].isStrike()) {
                bonus = addBonusForStrike(i);
            } else if (frames[i].isSpare()) {
                bonus = addBonusForSpare(i);
            }
            score += frames[i].getScore() + bonus;
        }
        return score;
    }

    private int addBonusForSpare(int currentFrameIndex) {
        return frames[currentFrameIndex + 1].getRollHits(1);
    }

    private int addBonusForStrike(int currentFrameIndex) {
        int bonus = 0;
        Frame nextFrame = frames[currentFrameIndex + 1];
        if (nextFrame.isLastFrame()) {
            bonus = nextFrame.getRollHits(2);
        } else if (nextFrame.isStrike()) {
            Frame frameAfterNextFrame = frames[currentFrameIndex + 2];
            bonus = frameAfterNextFrame.getRollHits(1);
            bonus += nextFrame.getScore();
        } else {
            bonus = nextFrame.getScore();
        }
        return bonus;
    }

    public void roll(Integer pinsHit) {
        if (frames[frameIndex].isFinished()) {
            frameIndex++;
        }
        frames[frameIndex].roll(pinsHit);
    }
}
