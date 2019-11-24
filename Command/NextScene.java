package Command;

import javafx.scene.control.Button;

public class NextScene extends Button implements Command {
    NavigationControl scene;

    public NextScene(NavigationControl scene) {
        this.scene = scene;
    }

    @Override
    public void execute() {
        scene.previous();

    }
}
