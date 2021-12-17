package bowling.domain;

import bowling.domain.state.Start;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

public abstract class TemplateFrame implements Frame {
    protected final FrameInfo frameInfo;
    protected State state;

    protected TemplateFrame() {
        this(FrameInfo.init(), new Start());
    }

    protected TemplateFrame(FrameInfo frameInfo, State state) {
        this.frameInfo = frameInfo;
        this.state = state;
    }

    @Override
    public void run(PitchNumberStrategy numberStrategy) {
        Pitch pitch = Pitch.first();
        while (progressing()) {
            int fallDownCount = numberStrategy.generate(pitch.pinsSize());
            Pins fallDownPins = Pins.create(fallDownCount);
            Pins existPins = pitch.run(fallDownPins);
            frameInfo.addPitch(pitch);
            state.pitch(existPins, fallDownPins, this);
            pitch = pitch.next();
        }
    }

    private boolean progressing() {
        return state.progressing();
    }

    public abstract FrameInfo info();
    public abstract void changeState(State state);
    public abstract State state();
}
