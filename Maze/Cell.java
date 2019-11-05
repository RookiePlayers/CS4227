package Maze;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public  class Cell extends Rectangle implements  Comparable{

    private int gCosts=10;

    private double hCosts;
    private double fCosts;
    public Cell parent=null;

    private int i,j=0;
    protected double w,h;
    private boolean[] walls;
    protected int columns;
    private int rows;
    public String id;
    public boolean isEnd=false;
    public boolean isFirst=false;

    public boolean visited=false;
    private Color color=Color.BLUEVIOLET;

    public Cell(int i,int j,double h,double w){
        this.i=i;
        this.j=j;
        this.w=w;
        this.h=h;
        setHeight(h);
        setWidth(w);
        walls= new boolean[]{true, true, true, true};//north,east,south,west


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

    public void drawRectangle(GraphicsContext gc){

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
        if(this.walls[0])
        {
            gc.setStroke(Color.WHITE); gc.setLineWidth(2);
            gc.strokeLine(getX(),getY(),x+w,y);
            gc.setStroke(Color.TRANSPARENT);
        }else {
            gc.setStroke(Color.TRANSPARENT);
            gc.strokeLine(getX(),getY(),x+w,y);
        }

        if(this.walls[1])
        { gc.setStroke(Color.WHITE); gc.setLineWidth(2);
            gc.strokeLine(getX()+w,getY(),x+w,y+w);
            gc.setStroke(Color.TRANSPARENT);
        }else {
            gc.setStroke(Color.TRANSPARENT);
            gc.strokeLine(getX()+w,getY(),x+w,y+w);
        }
        if(this.walls[2])
        {
            gc.setStroke(Color.WHITE); gc.setLineWidth(2);
            gc.strokeLine(getX()+w,getY()+w,x,y+w);
            gc.setStroke(Color.TRANSPARENT);
        }else  {
            gc.setStroke(Color.TRANSPARENT);
            gc.strokeLine(getX()+w,getY()+w,x,y+w);
        }
        if(this.walls[3])
        { gc.setStroke(Color.WHITE); gc.setLineWidth(2);
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
    public void changeWallAt(int index,boolean value){
        this.walls[index]=value;
    }
    public boolean checkWallAt(String pos){
        switch(pos.toLowerCase()){
            case "n":return this.walls[0];
            case "e":return this.walls[1];
            case "s":return this.walls[2];
            case "w":return this.walls[3];
            default:return true;
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
       // System.out.println("i: "+i+" j: " + j+"\nCol: "+columns+", Row: "+rows);
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
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());
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
        return "{Cell: "+id+"(x = "+i+",y = "+j+") -> Walls: "+ Arrays.toString(this.walls) +" -> Neighbours: "+"}\n";
    }

    public void setParent(Cell parent) {
        this.parent=parent;
    }
}