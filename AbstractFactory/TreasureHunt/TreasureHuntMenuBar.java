package AbstractFactory.TreasureHunt;

import AbstractFactory.MazeMenuBar;
import Maze.Heartbox;
import Maze.MazeGameSettings;
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
import javafx.scene.shape.Rectangle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TreasureHuntMenuBar extends MazeMenuBar {
    public static Button pointsBtn,goal;
    public static  Label message;


    private ArrayList<Heartbox> heartBox = new ArrayList<>();
    public TreasureHuntMenuBar(ArrayList<Player>players){
        super(players);
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Label lbl=new Label("Treasure Hunt  Maze");
        lbl.setStyle("-fx-text-fill:white");
        vbox.getChildren().addAll(lbl,optionsBar());
        getChildren().addAll(vbox);
        setAlignment(Pos.CENTER);
    }

    public Pane optionsBar(){
        VBox bar=new VBox();
        bar.setAlignment(Pos.CENTER);

        bar.setSpacing(20);


        this.pointsBtn=new Button(points+"");
        pointsBtn.setAlignment(Pos.CENTER);
        this.goal=new Button(points+"");
        this.message=new Label("Can't Leave without reaching target point");

        HBox items=new HBox();
        items.setAlignment(Pos.CENTER);
        items.setSpacing(30);
        VBox item1=new VBox();
        VBox item2=new VBox();
        VBox item3=new VBox();
        VBox item4=new VBox();
        VBox item5=new VBox();
        item1.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("/Images/coin.png"),50, 50, false, false)),new Label("50 points"));
        item2.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("/Images/pile.gif"),50, 50, false, false)),new Label("400 points"));
        item3.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("/Images/iphone.png"),50, 50, false, false)),new Label("1200 points"));
        item4.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("/Images/diamond.png"),50, 50, false, false)),new Label("1600 points"));
        item5.getChildren().addAll(new ImageView(new Image(getClass().getResourceAsStream("/Images/crown.png"),50, 50, false, false)),new Label("2500 points"));

        items.getChildren().addAll(playerBox(),item1,item2,item3,item4,item5);
        VBox b=new VBox();
        b.setAlignment(Pos.CENTER);

        b.setSpacing(20);
        b.getChildren().addAll(this.pointsBtn,new Label("Points to gather: "+ MazeGameSettings.preference.getTreasureGoal()));
        bar.getChildren().addAll(items,b,message);


        return bar;

    }
    public HBox playerBox(){
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
    public void checkHealth(){

    }

    @Override
    public void updateUI() {
        Platform.runLater(()->{
            pointsBtn.setText(new SimpleDateFormat("mm:ss").format(timer));
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
