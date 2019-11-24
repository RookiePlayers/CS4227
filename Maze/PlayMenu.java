package Maze;

import Command.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.border.Border;

public class PlayMenu {
    private Scene scene;
    private Stage parent;


    public PlayMenu(Stage parent){
        this.parent=parent;
    }

    public HBox navigation(){
        NavigationControl nControl= NavigationController.getNavigation();
        PreviousScene previousScene=new PreviousScene(nControl);
        previousScene.setText("◀");
        NextScene nextScene=new NextScene(nControl);
        nextScene.setText("▶");
        ChangeScene changeScene=new ChangeScene(previousScene);
        previousScene.setOnAction(e->{changeScene.press();parent.setScene(Navigation.ACTIVESCENE);});
        nextScene.setOnAction(e->{changeScene.press();parent.setScene(Navigation.ACTIVESCENE);});

        HBox vbox=new HBox();
        Button backBtbn=new Button("◀");
        Button nextButton=new Button("▶");
        vbox.setSpacing(10);
       vbox.getChildren().addAll(previousScene,nextScene);
        backBtbn.setOnAction(e->{
            Navigation.previous();
            parent.setScene(Navigation.ACTIVESCENE);

        });
        nextButton.setOnAction(e->{
            Navigation.forward();
            parent.setScene(Navigation.ACTIVESCENE);
        });
        return vbox;

    }
    public Scene getScene(double h, double w){
        GridPane gridPane = new GridPane();


        VBox vbox =new VBox();
        Button timeChallenge=new Button("Time Challenge");
        timeChallenge.setOnAction(e->{
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            GameScene game=new GameScene(parent,GameModes.TIMECHALLENGE);
            Scene gameScene=game.getScene(bounds.getHeight(),bounds.getWidth());
            Navigation.update(gameScene);
            parent.setScene(gameScene);
        });
        Button treasureHunt=new Button("Treasure Hunt");
        Button multiplayer=new Button("Multiplayer");
        multiplayer.setOnAction(e->{
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            GameScene game=new GameScene(parent,GameModes.MULTIPLAYER);
            Scene gameScene=game.getScene(bounds.getHeight(),bounds.getWidth());
            Navigation.update(gameScene);
            parent.setScene(gameScene);
        });
        Button survival=new Button("Survival");
        survival.setOnAction(e->{
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            GameScene game=new GameScene(parent,GameModes.SURVIVAL);
            Scene gameScene=game.getScene(bounds.getHeight(),bounds.getWidth());
            Navigation.update(gameScene);
            parent.setScene(gameScene);
        });
        vbox.setSpacing(10.0);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderPane=new BorderPane();


        gridPane.add(timeChallenge, 0, 0, 1, 1);
        gridPane.add(treasureHunt, 1, 0, 1, 1);
        gridPane.add(multiplayer,0,1,1,1);
        gridPane.add(survival,1,1,1,1);
        vbox.getChildren().addAll(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setTop(navigation());
        borderPane.setCenter(vbox);

        scene=new Scene(borderPane,h,w, Color.WHITE); borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        return scene;
    }
}
