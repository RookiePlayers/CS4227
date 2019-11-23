package Maze;

import inventory.controls.Effects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public  class Cell extends Rectangle implements  Comparable{

    private int gCosts=10;
    private boolean isDoor=false;
    private boolean isTrap=false;
    private boolean entry=false;

    private double hCosts;
    private double fCosts;
    public Cell parent=null;

    private int i,j=0;
    protected double w,h;
    private boolean[] walls;
    private Wall[] specialWalls;
    protected int columns;
    private int rows;
    public String id;
    public boolean isEnd=false;
    public boolean isFirst=false;

    public boolean visited=false;
    private Color color=Color.BLUEVIOLET;
    protected GraphicsContext gc;

    public boolean isLife() {
        return life;
    }

    private boolean life=false;

    public Cell(int i,int j,double h,double w){
        this.i=i;
        this.j=j;
        this.w=w;
        this.h=h;
        setHeight(h);
        setWidth(w);
        walls= new boolean[]{true, true, true, true};//north,east,south,west
        specialWalls=new Wall[]{new NormalWall(),new NormalWall(),new NormalWall(),new NormalWall() };

    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public boolean isTrap() {
        return isTrap;
    }

    public void setTrap(boolean trap) {
        isTrap = trap;
    }

    public boolean isDoor() {
        return isDoor;
    }

    public void setDoor(boolean door,boolean entry) {
        isDoor = door;
        this.entry=entry;

    }

    public void setColumns(int columns){
        this.columns=columns;
    }
    public void setRows(int rows){
        this.rows=rows;
    }
    public void show(GraphicsContext gc) {
        double x=this.i*w;
        double y=this.j*w;
        this.gc=gc;
        //x=40.0;
        setX(x);
        setY(y);
        // System.out.println("x: "+x+", y: "+y);


            gc.setStroke(Color.WHITE);
        gc.strokeLine(getX(),getY(),x+w,y);
        gc.strokeLine(getX()+w,getY(),x+w,y+w);
        gc.strokeLine(getX()+w,getY()+w,x,y+w);
        gc.strokeLine(getX(),getY()+w,x,y);



        // gc.setStroke(Color.RED); gc.setLineWidth(2);*/
        //gc.setFill(Color.WHITESMOKE);

        // gc.strokeRect(getX(),
        //       getY(),
        //      getWidth(),
        //       getHeight());



    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;

    }

    public void changeColor(Color color) {
        this.color = color;
        gc.setFill(color);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());

    }
    public void drawCircle(GraphicsContext gc){
        double x=this.i*w;
        double y=this.j*w;
        //x=40.0;
        setX(x);
        setY(y);
     /*   gc.setFill(Color.TRANSPARENT);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());
*/

     gc.setStroke(Color.WHITE);
    gc.strokeText("Exit",getX(),getY());
        gc.setFill(color);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());
    }
    public void drawTrap(GraphicsContext gc){
        double x=this.i*w;
        double y=this.j*w;
        //x=40.0;
        setX(x);
        setY(y);
        Image img=new Image(getClass().getResourceAsStream("/Images/trap.png"));

        gc.drawImage(img,getX(),getY(),getWidth(),getHeight());
    }
    public void drawHp(GraphicsContext gc){
        double x=this.i*w;
        double y=this.j*w;
        //x=40.0;
        setX(x);
        setY(y);
        Image img=new Image(getClass().getResourceAsStream("/Images/heart.png"));


        gc.drawImage(img,getX(),getY(),getWidth()*.7,getHeight()*.7);
    }
    public void drawDoor(GraphicsContext gc){

        double x=this.i*w;
        double y=this.j*w;
        //x=40.0;
        setX(x);
        setY(y);
        // System.out.println("x: "+x+", y: "+y);
      //  gc.setEffect(Effects.Inner_Shadow());

        gc.setFill(color);
        gc.setStroke(color);
       // gc.fillText("••••••••••",getX(),getY());
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());

        Image img=new Image(getClass().getResourceAsStream(this.entry?"Images/exit.png":"Images/exit.png"));

        gc.drawImage(img,getX(),getY(),getWidth(),getHeight());
        gc.setEffect(null);
    }

    public void drawRectangle(GraphicsContext gc){
        gc.restore();
        this.gc=gc;

        double x=this.i*w;
        double y=this.j*w;
        //x=40.0;
        setX(x);
        setY(y);
       // System.out.println("x: "+x+", y: "+y);
        if(this.visited&&!isEnd){
            gc.setFill(color);
            gc.setStroke(Color.TRANSPARENT);
            gc.fillRect(getX(),
                    getY(),
                    getWidth(),
                    getHeight());
        //    Image img=new Image(getClass().getResourceAsStream("/Images/grass.jpg"));

        //    gc.drawImage(img,getX(),getY(),getWidth(),getHeight());

            ;
        }
        else if(this.visited&&isEnd){
            gc.setFill(Color.GREEN);
            gc.setStroke(Color.TRANSPARENT);
            gc.fillRect(getX(),
                    getY(),
                    getWidth(),
                    getHeight());

        }
         if(isFirst){
            gc.setFill(Color.RED);
            gc.setStroke(Color.TRANSPARENT);
            gc.fillRect(getX(),
                    getY(),
                    getWidth(),
                    getHeight());

        }

       // gc.setStroke(Color.RED); gc.setLineWidth(2);*/
        //gc.setFill(Color.WHITESMOKE);

       // gc.strokeRect(getX(),
         //       getY(),
          //      getWidth(),
         //       getHeight());
      //  System.out.println("N: "+this.walls[0]+"\nE: "+this.walls[1]+"\nS: "+this.walls[2]+"\nW: "+this.walls[3]);

        if(this.specialWalls[0]!=null)
        {

            gc.setStroke(this.specialWalls[0].getWallColor()); gc.setLineWidth(this.specialWalls[0].getWallWidth());
            gc.strokeLine(getX(),getY(),x+w,y);
            gc.setStroke(Color.TRANSPARENT);
        }else {

            gc.setStroke(Color.TRANSPARENT);
            gc.strokeLine(getX(),getY(),x+w,y);
        }
        if(this.specialWalls[1]!=null)
        {

            gc.setStroke(this.specialWalls[1].getWallColor()); gc.setLineWidth(this.specialWalls[1].getWallWidth());
            gc.strokeLine(getX()+w,getY(),x+w,y+w);
            gc.setStroke(Color.TRANSPARENT);
        }else {
            gc.setStroke(Color.TRANSPARENT);
            gc.strokeLine(getX()+w,getY(),x+w,y+w);
        }
        if(this.specialWalls[2]!=null)
        {
            gc.setStroke(this.specialWalls[2].getWallColor()); gc.setLineWidth(this.specialWalls[2].getWallWidth());
            gc.strokeLine(getX()+w,getY()+w,x,y+w);
            gc.setStroke(Color.TRANSPARENT);
        }else  {
            gc.setStroke(Color.TRANSPARENT);
            gc.strokeLine(getX()+w,getY()+w,x,y+w);
        }
        if(this.specialWalls[3]!=null)
        { gc.setStroke(this.specialWalls[3].getWallColor()); gc.setLineWidth(this.specialWalls[3].getWallWidth());
            gc.strokeLine(getX(),getY()+w,x,y);
            gc.setStroke(Color.TRANSPARENT);
        }else  {
            gc.setStroke(Color.TRANSPARENT);
            gc.strokeLine(getX(),getY()+w,x,y);
        }
        //gc.setStroke(Color.WHITE);
       // gc.strokeText(id, getX(), getY()+w);
       //
       //
       // gc.stroke();



    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    public Wall[] getSpecialWalls() {
        return specialWalls;
    }

    public void setSpecialWalls(Wall[] specialWalls) {
        this.specialWalls = specialWalls;
    }

    public void changeWallAt(int index, boolean value){
        this.walls[index]=value;
    }
    public void changeWallAt(int index, Wall wall){
        this.specialWalls[index]=wall;
    }
  /*  public boolean checkWallAt(String pos){
        switch(pos.toLowerCase()){
            case "n":return this.walls[0];
            case "e":return this.walls[1];
            case "s":return this.walls[2];
            case "w":return this.walls[3];
            default:return true;
        }

    }
    */public Wall checkWallAt(String pos){
        switch(pos.toLowerCase()){
            case "n":return this.specialWalls[0];
            case "e":return this.specialWalls[1];
            case "s":return this.specialWalls[2];
            case "w":return this.specialWalls[3];
            default:return new NormalWall();
        }

    }
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int index(int i, int j){
        System.out.println("i: "+i+" j: " + j+"\nCol: "+columns+", Row: "+rows);
        if(i<0||j<0||i>columns-1||j>rows-1)
            return -1;
        return i+j*columns;
    }
    public Map<String,Cell> checkPlayerNeighbours(Cell cell){return null;}
    public Cell checkNeighbours() {
     //   System.out.println("--> "+this+" <---");
      //  System.out.println("Checking neighbours");
       ArrayList<Cell>neighbours=new ArrayList<>();
       int e=index(i+1,j);
       int w=index(i-1,j);
       int n=index(i,j-1);
       int s=index(i,j+1);
        Cell east=e!=-1?BoardCells.cells.get(e):null;
        Cell west=w!=-1?BoardCells.cells.get(w):null;
        Cell north=n!=-1?BoardCells.cells.get(n):null;
        Cell south=s!=-1?BoardCells.cells.get(s):null;

        if(north!=null&&!north.visited)
        {
            neighbours.add(north);
        }
        if(south!=null&&!south.visited) {
            neighbours.add(south);
        }
        if(east!=null&&!east.visited) {
            neighbours.add(east);
        }
        if(west!=null&&!west.visited){
            neighbours.add(west);
        }
    if (neighbours.size()>0){
        System.out.println(id+" has "+neighbours.size()+"Free  Neighbours");
        for (Cell c:neighbours) {
            System.out.print(c.id+" -");

        }
        System.out.println();
        int random=(int)((Math.random()*neighbours.size()));
        String msg="";
        System.out.println("random: "+random);
        switch(random){
            case 0:msg="North";break;
            case 1:msg="East";break;
            case 2:msg="South";break;
            case 3:msg="West";break;
            default:break;
        }

        System.out.println(this+"\nMy Neighbour is: "+msg+"\n"+neighbours.get(random));
        return neighbours.get(random);
    }

        return null;
    }

    public void hightlight(GraphicsContext gc) {
        double x=this.i*w;
        double y=this.j*w;
        setX(x);
        setY(y);
        gc.setEffect(Effects.Inner_Shadow());

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());gc.setEffect(null);
    }
    public void hightlight(GraphicsContext gc,Color color) {
        double x=this.i*w;
        double y=this.j*w;
        setX(x);
        setY(y);


        gc.setFill(color);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());


    }


    public int getgCosts() {
        return gCosts;
    }

    public void setgCosts(int gCosts) {
        this.gCosts = gCosts;
    }

    public double gethCosts() {
        return hCosts;
    }

    public void sethCosts(double hCosts) {
        this.hCosts = hCosts;
    }
    public double getfCosts() {
        fCosts=gCosts + hCosts;
        return fCosts;
    }

    @Override
    public int compareTo(Object o) {
        Cell that = (Cell) o;
        return (int)((this.gCosts + this.hCosts) - (that.gCosts + that.hCosts));
    }



    @Override
    public String toString(){
        return "{Cell: "+id+"(x = "+i+",y = "+j+") -> Walls: "+ Arrays.toString(this.walls) +" Door: " + isDoor + " Trap: "+isTrap+"-> Neighbours: "+"}\n";
    }

    public void setParent(Cell parent) {
        this.parent=parent;
    }

    public void repaint() {

    }

    public void clear() {
        gc.clearRect(getX(),getY(),getWidth(),getHeight());
    }

    public void exitDoor(Cell player) {
    }

    public void triggerTrap(Player player) {
    }

    public void setLife(boolean life) {
        this.life=life;
    }
    public void triggerHealth(Player player){

    }
}