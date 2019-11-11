package AbstractFactory.TimeChallenge;

import AbstractFactory.MazeMenuBar;
import Maze.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TimeChallengeMenuBar extends MazeMenuBar {
    Button time;
    public TimeChallengeMenuBar(ArrayList<Player>players){
        super(players);
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        Label lbl=new Label("Time Challenge Maze");
        lbl.setStyle("-fx-text-fill:white");
        vbox.getChildren().addAll(lbl,optionsBar());
        getChildren().addAll(vbox);
        setAlignment(Pos.CENTER);
    }
    public Pane optionsBar(){
        HBox bar=new HBox();
        bar.setAlignment(Pos.CENTER);

        bar.setSpacing(20);
        Button inventoryBtn=new Button("Inventory");
        inventoryBtn.setAlignment(Pos.CENTER_LEFT);

          this.time=new Button(new SimpleDateFormat("mm:ss").format(timer));
         time.setAlignment(Pos.CENTER);
        Button equipBtn=new Button("Equip");
        equipBtn.setAlignment(Pos.CENTER_RIGHT);
        bar.getChildren().addAll(inventoryBtn,time,equipBtn);


        return bar;

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
          //  heartBox.get(pi).updateHearts(p.getHealth());

        super.updateHealth(p);
    }
}
