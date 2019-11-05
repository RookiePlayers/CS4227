package AbstractFactory;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class MazeScene extends Scene {
    protected boolean timed=true;
    protected long timer=30000;
    public MazeScene(Parent parent) {
        super(parent);
    }

    public boolean isTimed() {
        return timed;
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
    }

    public long getTime() {
        return timer;
    }

    public void setTime(long time) {
        this.timer = time;
    }
}
