package Maze;

import Command.PlayerCommand.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Stack;

public class PlayerBoard extends Canvas{
    private Board b;
    public Cell currentPosition;
    //public Player player;
    public ArrayList<Player>players;
    public ArrayList<ControllerCommand>controllerCommands=new ArrayList<>();


    private GraphicsContext game;

    public PlayerBoard(ArrayList<Player> player,Board board){
        this.players=player;

        this.b=board;
        this.setHeight(b.getBoardHeight());
        this.setWidth(b.getBoardWidth());

        game=this.getGraphicsContext2D();

        for(Player p:players){
            p.gc=game;
            drawPlayer(p);
            controllerCommands.add(new ControllerCommand(p));

        }


    }
    public void drawPlayer(Player player){
        game.clearRect(0,0,getWidth(),getHeight());
        setTranslateX(10);
        setTranslateY(10);
        player.drawPlayer();



    }
    public void setupControllers(){
        switch(players.size()){
            case 2:{
                Scenes.GAMESCENE.setOnKeyPressed(e->{ System.out.println(Scenes.GAMESCENE);
                    setupP1Controls(players.get(0),e);
                setupP2Controls(players.get(1),e);
                });
            }break;
            default: Scenes.GAMESCENE.setOnKeyPressed(e->{ System.out.println(Scenes.GAMESCENE);
                setupP1Controls(players.get(0),e);});break;
        }
    }
    public void setupP1Controls(Player player, KeyEvent e){

            Command commandPlayer=null;
            controllerCommands.get(0).setTarget(player);


            switch (e.getCode()) {
                case D: {
                    System.out.println("RIGHT");
                   // player.moveRight();
                    commandPlayer=controllerCommands.get(0).getMovements()[3];


                }
                break;
                case W: {
                    System.out.println("UP");
                   // player.moveUp();
                    commandPlayer=controllerCommands.get(0).getMovements()[0];
                }
                break;
                case A: {
                    System.out.println("LEFT");
                   // player.moveLeft();
                    commandPlayer=controllerCommands.get(0).getMovements()[1];
                }
                break;
                case S: {
                    System.out.println("DOWN");
                   // player.moveDown();
                    commandPlayer=controllerCommands.get(0).getMovements()[2];
                }
                break;
            }
            PlayerTrigger playerTrigger;
            if(commandPlayer!=null)
            {
                playerTrigger=new PlayerTrigger(commandPlayer);
                playerTrigger.move();
            }

            if(player.current==b.getCellStack().lastElement()){
                b.setGoal(true);
                player.setDone(true);
                player.hightlight(game, Color.GOLD);
                System.out.println("Finished!!!");
            }
    }
    public void setupP2Controls(Player player, KeyEvent e){


        Command commandPlayer=null;
        controllerCommands.get(1).setTarget(player);


        switch (e.getCode()) {
            case L: {
                System.out.println("RIGHT");
                // player.moveRight();
                commandPlayer=controllerCommands.get(1).getMovements()[3];


            }
            break;
            case I: {
                System.out.println("UP");
                // player.moveUp();
                commandPlayer=controllerCommands.get(1).getMovements()[0];
            }
            break;
            case K: {
                System.out.println("LEFT");
                // player.moveLeft();
                commandPlayer=controllerCommands.get(1).getMovements()[1];
            }
            break;
            case J: {
                System.out.println("DOWN");
                // player.moveDown();
                commandPlayer=controllerCommands.get(1).getMovements()[2];
            }
            break;
        }
        PlayerTrigger playerTrigger;
        if(commandPlayer!=null)
        {
            playerTrigger=new PlayerTrigger(commandPlayer);
            playerTrigger.move();
        }

        if(player.current==b.getCellStack().lastElement()){
                b.setGoal(true);
                player.setDone(true);
                player.hightlight(game, Color.GOLD);
                System.out.println("Finished!!!");
            }

    }
}
