package Maze;

import inventory.Models.Inventory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Cell {
    private int health = 3;
    private long speed = 2000;
    private String name="Player 1";
    private Color color=Color.WHITE;
    private int i,j=0;
    public GraphicsContext gc;
    public  Cell current;
    private boolean done=false;


    private Inventory inventory;


    public Player(String name, int health, Inventory inventory,int i, int j, double h,double w,Cell initPos) {
    super(i,j,h,w);
        this.i=initPos.getI();
        this.j=initPos.getJ();
        this.name = name;
        this.health = health;
        this.inventory = inventory;
        this.current=initPos;
       // setHeight(h/2);
       // setWidth(w/2);

    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public void drawPlayer(){
        System.out.println("Current-> "+current);
        double x=super.getI()*super.w;
        double y=super.getJ()*super.w;
        //x=40.0;
        setX(x);
        setY(y);
        gc.setFill(this.color);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());

    }
    public void moveRight(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("E");
        System.out.println("Neighbour is at: "+next);

        if(next!=null){
            System.out.println("From: ("+this.i+", "+this.j+") to: ("+next.getI()+", "+next.getJ()+")");
            if(!isWallEast(next)){
                gc.clearRect(getX(),getY(),getWidth(),getHeight());
                setI(next.getI());
                setJ(next.getJ());
                current=next;
                drawPlayer();
            }
        }
    }
    public void moveLeft(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("W");
        System.out.println("Neighbour is at: "+next);
        if(next!=null){
            System.out.println("From: ("+this.i+", "+this.j+") to: ("+next.getI()+", "+next.getJ()+")");
            if(!isWallWest(next)){
                gc.clearRect(getX(),getY(),getWidth(),getHeight());
                setI(next.getI());
                setJ(next.getJ());
                current=next;
                drawPlayer();
            }
        }
    }
    public void moveUp(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("N");
        System.out.println("Neighbour is at: "+next);
        if(next!=null){
            System.out.println("From: ("+this.i+", "+this.j+") to: ("+next.getI()+", "+next.getJ()+")");
            if(!isWallNorth(next)){
                gc.clearRect(getX(),getY(),getWidth(),getHeight());
                setI(next.getI());
                setJ(next.getJ());
                current=next;
                drawPlayer();
            }
        }
    }
    public void moveDown(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("S");
        System.out.println("Neighbour is at: "+next);
        if(next!=null){
            System.out.println("From: ("+this.i+", "+this.j+") to: ("+next.getI()+", "+next.getJ()+")");
            if(!isWallSouth(next)){
                gc.clearRect(getX(),getY(),getWidth(),getHeight());
                setI(next.getI());
                setJ(next.getJ());
                current=next;
                drawPlayer();
            }
        }
    }


    @Override
    public void show(GraphicsContext gc) {

    }

    @Override
    public void drawRectangle(GraphicsContext gc) {

    }
    @Override
    public  Map<String,Cell> checkPlayerNeighbours(Cell thisCell){
        Map<String,Cell> neighbours=new HashMap<>();
        //System.out.println(i+", "+j);

        int e=index(thisCell.getI()+1,thisCell.getJ());
        int w=index(thisCell.getI()-1,thisCell.getJ());
        int n=index(thisCell.getI(),thisCell.getJ()-1);
        int s=index(thisCell.getI(),thisCell.getJ()+1);
       // System.out.println(n+","+e+","+s+","+w);
        Cell east=e!=-1?BoardCells.cells.get(e):null;
        Cell west=w!=-1?BoardCells.cells.get(w):null;
        Cell north=n!=-1?BoardCells.cells.get(n):null;
        Cell south=s!=-1?BoardCells.cells.get(s):null;


        if(north!=null)
        {
            neighbours.put("N",north);
        }
        if(south!=null) {
            neighbours.put("S",south);
        }
        if(east!=null) {
            neighbours.put("E",east);
        }
        if(west!=null){
            neighbours.put("W",west);
        }
        System.out.println("Neighbours: "+neighbours);
        return neighbours;
    }
    public boolean isWallEast(Cell next){
        System.out.println("CheckWallAt East: "+current.checkWallAt("E")+" - "+"CheckWallAt West: "+current.checkWallAt("W"));
        return (current.checkWallAt("E")&&next.checkWallAt("W"));
    }
    public boolean isWallNorth(Cell next){
        return (current.checkWallAt("N")&&next.checkWallAt("S"));
    }
    public boolean isWallWest(Cell next){
        return (current.checkWallAt("W")&&next.checkWallAt("E"));
    }public boolean isWallSouth(Cell next){
        return (current.checkWallAt("S")&&next.checkWallAt("N"));
    }
}
