package AbstractFactory.Multiplayer;

import AbstractFactory.MazeMenuBar;
import Command.ChangeScene;
import Command.INavigationControl;
import Command.NavigationController;
import Command.PreviousScene;
import Maze.Heartbox;
import Maze.Navigation;
import Maze.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MultiplayerMenuBar extends MazeMenuBar {
    Button time;
    //Button solutionBtn;
    private ArrayList<Heartbox> heartBox = new ArrayList<>();


    public MultiplayerMenuBar(ArrayList<Player>players){
        super(players);
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Label lbl=new Label("Multiplayer  Maze");
        lbl.setStyle("-fx-text-fill:white");
        vbox.getChildren().addAll(lbl,optionsBar(),playersBox());
        getChildren().addAll(vbox);
        setAlignment(Pos.CENTER);
    }
    public Pane optionsBar(){
        HBox bar=new HBox();
        bar.setAlignment(Pos.CENTER);

        bar.setSpacing(20);
        INavigationControl nControl= NavigationController.getNavigation();
        PreviousScene previousScene=new PreviousScene(nControl);
        previousScene.setText("Exit");
        ChangeScene changeScene=new ChangeScene(previousScene);
        previousScene.setOnAction(e->{changeScene.press();});

        this.time=new Button(new SimpleDateFormat("mm:ss").format(timer));
        time.setAlignment(Pos.CENTER);
         solutionBtn=new Button("Solution");
        solutionBtn.setAlignment(Pos.CENTER_RIGHT);
        bar.getChildren().addAll(time,solutionBtn);



        return bar;

    }
    public HBox playersBox(){
        HBox hbox=new HBox();
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);
        int i=0;
        for(Player p:players){
            heartBox.add(new Heartbox(p.getHealth()));
            hbox.getChildren().add(playerModule(p,heartBox.get(i)));
            i++;

        }
        return hbox;

    }
    public VBox playerModule(Player player,Heartbox hbox){
        VBox vbox=new VBox();
        Label name=new Label(player.getName());

        Rectangle rectangle=new Rectangle();
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setFill(player.getColor());


        vbox.getChildren().addAll(name,rectangle,hbox);

        return vbox;
    }

    @Override
    public void updateUI() {
        Platform.runLater(()->{
            time.setText(new SimpleDateFormat("mm:ss").format(timer));
        });

        super.updateUI();

    }
    @Override
    public void updateHealth(Player p){
        int pi=players.indexOf(p);
        if(pi>-1)
            heartBox.get(pi).updateHearts(p.getHealth());

        super.updateHealth(p);
    }
}
