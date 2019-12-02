package AbstractFactory.TimeChallenge;

import Maze.Composite.Board;
import Maze.UI.MazePreference;

public class TimeChallengeBoard extends Board {

    public TimeChallengeBoard(double height, double width, int controllers, MazePreference preference) {
        super(height, width,preference);
        setMazePreference(preference);

        super.players=preference.getPlayerInGame();
        super.setPlayer(preference.getPlayerInGame());

    }





}
