package AbstractFactory.Multiplayer;

import AbstractFactory.*;
import AbstractFactory.Multiplayer.*;
import Maze.Board;
import Maze.Player;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MultiplayerMazeFactory implements MazeFactory {

        @Override
        public MazeMenuBar createMenuBar(ArrayList<Player>players) {
            return new MultiplayerMenuBar(players);
        }

        @Override
        public Board createBoard(double height, double width,int controllers) {
            return new MultiplayerBoard(height,width,controllers) ;
        }

        @Override
        public MazeWinScene createWinScene(Stage stageParent, Parent parent, long timeleft, ArrayList<Player> players) {
            return new MultiplayerWinScene(stageParent,parent,timeleft,players);
        }

        @Override
        public MazeLoseScene createLoseScene(Stage stageParent, Parent parent, long timeleft) {
            return new MultiplayerLoseScene(stageParent,parent,timeleft);
        }

        @Override
        public MazeScene createMazeScene(Stage stageParent, Parent parent) {
            return new MultiplayerMazeScene(stageParent,parent);
        }


}
