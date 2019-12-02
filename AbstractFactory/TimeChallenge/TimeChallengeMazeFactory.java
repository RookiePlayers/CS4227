package AbstractFactory.TimeChallenge;

import AbstractFactory.*;
import Maze.Composite.Board;
import Maze.Composite.Player;
import Maze.UI.MazePreference;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TimeChallengeMazeFactory implements MazeFactory {
    @Override
    public MazeMenuBar createMenuBar(ArrayList<Player> players) {
        return new TimeChallengeMenuBar(players);
    }

    @Override
    public Board createBoard(double height, double width, int controllers,MazePreference preference) {
        return new TimeChallengeBoard(height,width,controllers,preference) ;
    }

    @Override
    public MazeWinScene createWinScene(Stage stageParent, Parent parent, long timeleft,ArrayList<Player>players) {
        return new TimeChallengeWinScene(stageParent,parent,timeleft,players);
    }

    @Override
    public MazeLoseScene createLoseScene(Stage stageParent, Parent parent, long timeleft) {
        return new TimeChallengeLoseScene(stageParent,parent,timeleft);
    }

    @Override
    public MazeScene createMazeScene(Stage stageParent, Parent parent) {
        return new TimeChallengeMazeScene(stageParent,parent);
    }
}
