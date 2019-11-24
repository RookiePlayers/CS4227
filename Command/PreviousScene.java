package Command;

import javafx.scene.control.Button;

public class PreviousScene extends Button implements Command {

    NavigationControl scene;

    public PreviousScene(NavigationControl scene) {
        this.scene = scene;
    }

    @Override
    public void execute() {
        scene.previous();

    }
}
