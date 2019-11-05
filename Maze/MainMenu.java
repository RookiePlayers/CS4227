package Maze;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainMenu {
private Scene scene;
private Stage parent;
private final static String sceneID="MainMenu01";
 public MainMenu(Stage parent){
    this.parent=parent;
 }
 public Scene getScene(double h,double w){
     BorderPane borderPane=new BorderPane();

     VBox vbox =new VBox();
     Button playBtn=new Button("Play");
     Button LeaderboardBtn=new Button("Leaderboard");
     Button Help=new Button("Help");
     vbox.setSpacing(10.0);
     vbox.setAlignment(Pos.CENTER);

     playBtn.setOnAction(e->{
         Scene playMenuScene=new PlayMenu(this.parent).getScene(h,w);
         Navigation.update(playMenuScene);
         this.parent.setScene(playMenuScene);
     });

     vbox.getChildren().addAll(playBtn,LeaderboardBtn,Help);
     borderPane.setCenter(vbox);



    scene=new Scene(borderPane,h,w, Color.WHITE);borderPane.prefHeightProperty().bind(scene.heightProperty());
     borderPane.prefWidthProperty().bind(scene.widthProperty());
    Navigation.HOME=scene;
     return scene;
 }
}
