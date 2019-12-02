package Adapter;


import AbstractFactory.Survival.SurvivalMenuBar;
import Maze.Composite.Cell;
import Maze.Composite.Player;
import Maze.Persistance.MazeParts;
import Maze.controls.Effects;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class IceTrap extends Trap {
    int numTimersRunning=0;
    long freezeFor=3000;
    private Color o_color;
    public IceTrap(int i, int j, double h, double w) {
        super(i, j, h, w);
        setTrap(true);
        super.id= UUID.randomUUID().toString();
    }

    @Override
    public void triggerTrap(Player player) {
        int i = 3;
        o_color=player.getColor();
        player.setColor(Color.DARKBLUE.darker());
        player.setEffect(Effects.REFLECTION());
        player.drawPlayer();
        player.freezePlayer(true);
        numTimersRunning++;
        Timer timer = new Timer("Timer");
        TimerTask repeatedTask = new TimerTask() {

            public void run() {

                Platform.runLater(()->{
                    SurvivalMenuBar.FREEZETIME.setText(new SimpleDateFormat("mm:ss").format(freezeFor));

                });
               freezeFor-=1000;
               if(freezeFor<=0)
               {
                   player.freezePlayer(false);
                   player.setColor(o_color);
                   timer.cancel();
                   Platform.runLater(()->{
                       SurvivalMenuBar.FREEZETIME.setText("");

                   });
               }
            }
        };


        long delay  = 1000L;
        long period = 1000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
        numTimersRunning--;
    }
    @Override
    public void destroyTrap(GraphicsContext gc){
        clear();
        Cell c=new Cell(this.getI(),this.getJ(),this.getWidth(),this.getHeight());
        c.setWalls(this.getWalls());
        c.visited=true;
        c.drawRectangle(super.gc);
    }

    private void keyPressed(KeyEvent e) {
        if(numTimersRunning > 0)
            return;
    }
}
