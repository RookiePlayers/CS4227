package Maze;

import Maze.MazeSolver.MazeSolver;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TestMaze extends Stage {
    Board board;
    MazeSolver ai;
    public TestMaze(Board board){
        this.board=board;
        ai=new MazeSolver(board,board.getRows(),board.getColumns());

        board.getChildren().add(ai);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        BorderPane border=new BorderPane();

        border.setPrefWidth(bounds.getWidth());
        border.setPrefHeight(bounds.getHeight());

        BorderPane boardContainer=new BorderPane();
        double bh=600;
        double bw=600;


        boardContainer.setPrefHeight(600);
        boardContainer.setPrefWidth(600);


        board.setBoardHeight(bh);
        board.setBoardWidth(bw);
        boardContainer.setCenter(board);
        border.setCenter(boardContainer);

        setX(bounds.getMinX());
        setY(bounds.getMinY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        setTitle("Trapped AI"); // Set the stage title
        Scene scene= new Scene(border);
        Thread t1=new Thread(ai);

        setScene(scene);
        show();
        //initModality(Modality.APPLICATION_MODAL);
        //ai.findPathTo();
        t1.start();
    }
}
