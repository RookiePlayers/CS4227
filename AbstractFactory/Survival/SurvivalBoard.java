package AbstractFactory.Survival;

import Maze.*;
import Maze.ButtonFactory.Door;
import inventory.Models.Inventory;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class SurvivalBoard extends Board {
Player player;
    public SurvivalBoard(double height, double width, int controllers) {
        super(height, width);
        this.player=new Player("Player1",3,new Inventory(),0,0,60,60,new Cell(0,0,height,width));
        super.players.add(player);
        super.setPlayer(players);
        setTraps(5);
        setHPs(3);
        setEnemy();

    }
    public void setTraps(int traps){
        while (traps>0){
            replaceForTrap();
            traps--;
        }
    }
    public void setHPs(int hp){
        while (hp>0){
            replaceForHealth();
            hp--;
        }
    }
    public void setEnemy(){
        System.out.println("Setting enemy");
      // Enemy enemy=new Enemy((int)(Math.random()*12),(int)(Math.random()*12),getSizeFactor(),getSizeFactor());
        Enemy enemy=new Enemy(3,3,getSizeFactor(),getSizeFactor());


        enemy.player=player;
        EnemyBoard eb=new EnemyBoard(enemy,this,player);
        enemy.setHeuristics();
        System.out.println("Setting enemy: "+enemy);
        getChildren().add(eb);
        Thread et=new Thread(eb);
        eb.startAi();
        et.start();
    }
    public void replaceForTrap(){
        int entry=(int)(Math.random()*BoardCells.cells.size());
        System.out.println(entry+"--"+BoardCells.cells.size());
        Cell c= BoardCells.cells.get(entry);
        if(!c.isDoor()){
            c.setTrap(true);
            BoardCells.cells.get(entry).changeColor(Color.RED);
            Trap trap=new Trap(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            trap.setGc(c.getGc());
            trap.setWalls(c.getWalls());
            BoardCells.cells.set(entry,trap);
        }
        else replaceForTrap();


    }
    public void replaceForHealth(){
        int entry=(int)(Math.random()*BoardCells.cells.size());
        System.out.println(entry+"--"+BoardCells.cells.size());
        Cell c= BoardCells.cells.get(entry);
        if(!c.isDoor()){
            c.setLife(true);
            BoardCells.cells.get(entry).changeColor(Color.LIGHTGREEN);
            Health hp=new Health(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            hp.setGc(c.getGc());
            hp.setWalls(c.getWalls());
            BoardCells.cells.set(entry,hp);
        }
        else replaceForTrap();


    }

    @Override
    public void drawPath(int i){
        System.out.println(i+") Drawing "+cellStack.size()+" Cells: CellId - "+cellStack.get(i).id);
        cellStack.lastElement().hightlight(gc);
        cellStack.firstElement().isFirst=true;
        cellStack.lastElement().isEnd=true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(cellStack.get(i).isDoor()){
                    cellStack.get(i).drawDoor(gc);
                }else if(cellStack.get(i).isTrap()){
                    cellStack.get(i).drawTrap(gc);
                }
                else if(cellStack.get(i).isLife()){
                    cellStack.get(i).drawHp(gc);
                }else
                    cellStack.get(i).drawRectangle(gc);
               // System.out.println(cellStack);
            }
        });
    }





}
