package Memento;
import Maze.*;
import Maze.Enumerations.GameState;
import Maze.Persistance.MazeGameSettings;
import Maze.Persistance.Navigation;
import Maze.UI.PlayMenu;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PauseMenu extends Stage
{

    private Scene scene;
    private Stage parent;
    private final static String sceneID="Pause Menu";
    private Originator originator = new Originator();
    private CareTaker careTaker = new CareTaker();
    public PauseMenu(Stage parent) {

        this.parent=parent;

        originator.setGameState(GameState.PAUSED);
        careTaker.addMemento(originator.save());
        MazeGameSettings.GAMESTATE=originator.getGameState();


        setWidth(500.0);
        setHeight(500.0);
        initModality(Modality.APPLICATION_MODAL);
        setScene(getScene(500.0,500.0));

    }

    public static  void printState(Originator o)
    {

        System.out.println("Current State: " + o.getGameState().name());
    }

    public Scene getScene(double h, double w){
        BorderPane borderPane=new BorderPane();

        VBox vbox =new VBox();
        Button pauseBtn = new Button("Pause");
        Button playBtn=new Button("Resume");
        Button quit=new Button("Quit");
        vbox.setSpacing(10.0);
        vbox.setAlignment(Pos.CENTER);

        playBtn.setOnAction(e->{
            originator.setGameState(GameState.PLAYING);
            careTaker.addMemento(originator.save());
            close();
        });
        quit.setOnAction(e->{

           parent.setScene(Navigation.HOME);
           close();
        });

        vbox.getChildren().addAll(new Label("Game is Paused"),playBtn,quit);
        borderPane.setCenter(vbox);




        scene=new Scene(borderPane,h,w, Color.web("#111"));
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        scene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());
        borderPane.getStyleClass().add("leaderboard");
        Navigation.HOME=scene;
        return scene;
    }

}

