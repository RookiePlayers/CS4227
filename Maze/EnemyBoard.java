package Maze;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Stack;

public class EnemyBoard extends Canvas implements Runnable{
    private Board b;
    public Cell currentPosition;
    //public Player player;
   public Enemy enemy;


    private GraphicsContext game;

    public EnemyBoard(Enemy enemy,Board board,Player player){
        this.enemy=enemy;
        this.b=board;
        this.setHeight(b.getBoardHeight());
        this.setWidth(b.getBoardWidth());

        game=this.getGraphicsContext2D();
        enemy.board=b;
        enemy.gc=game;
        drawEnemy(enemy);
        enemy.setRows(b.getRows());
        enemy.setColumns(b.getColumns());




    }
    public void drawEnemy(Enemy enemy){
        game.clearRect(0,0,getWidth(),getHeight());
        setTranslateX(10);
        setTranslateY(10);
        enemy.drawEnemy();



    }
    public void startAi(){
        System.out.println("Starting AI");
        enemy.path=enemy.trackPlayer();

    }
    @Override
    public void run(){
        int i=0;

        while(!enemy.caughtPlayer){

            try {
                System.out.println("Player Path? + -- "+enemy.path.size());
                System.out.println("Caught ? "+enemy.caughtPlayer);
                if(enemy.path.size()>0){
                    System.out.println("Before: "+enemy);
                    enemy=(Enemy)enemy.path.pop();
                    System.out.println("After: "+enemy);
                    enemy.drawEnemy();
                }


                Thread.sleep(enemy.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
