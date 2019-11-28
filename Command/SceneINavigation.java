package Command;

import Maze.Navigation;
import javafx.scene.Scene;

public class SceneINavigation implements INavigationControl {

    Scene newScene,initScene;

    @Override
    public void forward() {
        Navigation.forward();
    }

    @Override
    public void previous() {
        Navigation.previous();
    }

    @Override
    public void update() {
        Navigation.update(newScene);
    }

    @Override
    public void inittiate() {
        Navigation.initiate(initScene);
    }
}
