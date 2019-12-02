package AbstractFactory.Multiplayer;

import Maze.Composite.Board;
import Maze.UI.MazePreference;

public class MultiplayerBoard extends Board {


    public MultiplayerBoard(double height, double width, int controllers, MazePreference preference) {


        super(height, width,preference);
        super.setPlayer(preference.getPlayerInGame());
        setMazePreference(preference); System.out.println(preference);
    }

}
