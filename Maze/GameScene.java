package Maze;

import AbstractFactory.Maze;
import AbstractFactory.Multiplayer.MultiplayerMazeFactory;
import AbstractFactory.TimeChallenge.TimeChallengeMazeFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class GameScene  {
    private Scene scene;
    private final static String sceneID="GameScene01";
    private Stage parent;
    private Maze maze;
    private int controlers;
    public GameScene(Stage parent, GameModes modes){

    this.parent=parent;
    switch(modes){
        case MULTIPLAYER:
        {
            maze=new Maze(new MultiplayerMazeFactory(),parent,2);

        }break;
        default:  maze=new Maze(new TimeChallengeMazeFactory(),parent,1);

    }

        Thread t=new Thread(maze);
    maze.createMaze();
    Board board=new Board(maze.getGameBoard().getBoardWidth(),maze.getGameBoard().getBoardHeight(),maze.getGameBoard().getSizeFactor());
  //  board.setCellStack(maze.getGameBoard().getCellStack());
        String message="";
       message+=("\n");
       message+=("--------------------------------");
       message+=("\n");
       message+=(String.format("%-15s %-5s %-7s \n","Cat","AI Board","Game Board"));
       message+=("-------------------------------\n");
      message+=(String.format("%-15s %-5s %-7s \n","Height",board.getBoardHeight(),maze.getGameBoard().getBoardHeight()));
       message+=(String.format("%-15s %-5s %-7s \n","Width",board.getBoardWidth(),maze.getGameBoard().getBoardWidth()));
       message+=(String.format("%-15s %-5s %-7s \n","sizeFactor",board.getSizeFactor(),maze.getGameBoard().getSizeFactor()));
       message+=(String.format("%-15s %-5s %-7s \n","Cells",board.getCellStack().size(),maze.getGameBoard().getCellStack().size()));
       message+=(String.format("%-15s %-5s %-7s \n","Cols",board.getColumns(),maze.getGameBoard().getColumns()));
       message+=(String.format("%-15s %-5s %-7s \n","Rows",board.getRows(),maze.getGameBoard().getRows()));
       message+=("--------------------------------");
       message+=("\n");
        System.out.println(message);
       // JOptionPane.showMessageDialog(null,message,"AI Board vs Game Board",1);
    new TestMaze(board);

        maze.initGame();
    t.start();
    //getScene(h,w);
    }
   /* public HBox navigation(){
        HBox vbox=new HBox();
        javafx.scene.control.Button backBtbn=new Button("◀");
         Button nextButton=new Button("▶");
        vbox.setSpacing(10);
         vbox.getChildren().addAll(backBtbn,nextButton);
        backBtbn.setOnAction(e-> {
                    Navigation.previous();
                    parent.setScene(Navigation.ACTIVESCENE);

                });
            nextButton.setOnAction(e->{
                Navigation.forward();
                parent.setScene(Navigation.ACTIVESCENE);
            });
            return vbox;

    }*/
    public Scene getScene(double h,double w){


        double bh=700;
        double bw=700;


        Scenes.GAMESCENE=new Scene(maze,h,w);


    maze.setPlayerControls();
       return Scenes.GAMESCENE;

    }
}
