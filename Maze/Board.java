package Maze;

import inventory.Models.Inventory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.canvas.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

public class Board extends StackPane implements Runnable
{
    private Cell current;
    private Stack<Cell> cellStack,virtualStack;
    private double height=500;
    private double width=500;
    private int sizeFactor=100;
    private int columns,rows;
    private Canvas canvas;
    private boolean goal;
    //private ArrayList<Cell>cells=new ArrayList<>();
    private GraphicsContext gc;
    protected ArrayList<PlayerBoard> playerBoard=new ArrayList<>();
    protected ArrayList<Player> players=new ArrayList<>();

    public Board(double height,double width,int sizeFactor){
        this.height=height;
        this.width=width;
        this.sizeFactor=sizeFactor;
        cellStack=new Stack<>();
        virtualStack=new Stack<>();
        virtualStack.addAll(cellStack);
        canvas=new Canvas(height,height);


        this.setHeight(height);
        this.setWidth(height);
        gc= canvas.getGraphicsContext2D();

        this.columns=(int)Math.floor(width/this.sizeFactor);
        this.rows=(int)Math.floor(height/this.sizeFactor);
        System.out.println(getHeight()+"-***-"+getWidth());
        setStyle("-fx-background-color:#111;-fx-margin:20px");
        initiate();
        current=BoardCells.cells.get(0);

        // playerBoard=new PlayerBoard(player,this);

        getChildren().addAll(canvas);



        current.visited=true;

        BoardCells.emptyCells=BoardCells.cells.size();
        BoardCells.emptyCells--;


       /* while(cellStack.size()<=BoardCells.cells.size()){
            fillStack();
        }*/
        for(int i=0;i<BoardCells.cells.size();i++){
            draw(i);
        }
        fillStack();


    }

    public Board(double height,double width){
        this.height=height;
        this.width=width;
        cellStack=new Stack<>();
        virtualStack=new Stack<>();
        virtualStack.addAll(cellStack);
        canvas=new Canvas(height,height);

        this.width=width+20;
        this.height=height+20;
        this.setHeight(height);
        this.setWidth(height);
        gc= canvas.getGraphicsContext2D();

        this.columns=(int)Math.floor(width/sizeFactor);
        this.rows=(int)Math.floor(height/sizeFactor);
      //  System.out.println(getHeight()+"--"+getWidth());
        setStyle("-fx-background-color:#111;-fx-margin:20px");
        initiate();
        current=BoardCells.cells.get(0);

        // playerBoard=new PlayerBoard(player,this);

        getChildren().addAll(canvas);



        current.visited=true;

        BoardCells.emptyCells=BoardCells.cells.size();
        BoardCells.emptyCells--;


       /* while(cellStack.size()<=BoardCells.cells.size()){
            fillStack();
        }*/
        for(int i=0;i<BoardCells.cells.size();i++){
            draw(i);
        }
        fillStack();


    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void isGoodMaze(){
        if(cellStack.lastElement().getI()>=(rows-1)/2&&cellStack.lastElement().getJ()>=(columns-1)/2){

        }
        else{
            cellStack.clear();
            virtualStack.clear();
            initiate();
            current=BoardCells.cells.get(0);
            BoardCells.emptyCells=BoardCells.cells.size();
            current.visited=true;
            BoardCells.emptyCells--;
            for(int i=0;i<BoardCells.cells.size();i++){
                draw(i);
            }
            fillStack();

        }
    }
    public void setPlayer(ArrayList<Player>players){
        this.players=players;
        for(Player p:players){
            p.setColumns(columns);
            p.setRows(rows);


        }
        PlayerBoard pb=new PlayerBoard(players,this);
        playerBoard.add(pb);
        getChildren().add(pb);

    }

    public Stack<Cell> getCellStack() {
        return cellStack;
    }

    public void setCellStack(Stack<Cell> cellStack) {
        this.cellStack = cellStack;
    }

    public ArrayList<PlayerBoard> getPlayerBoard() {
        return this.playerBoard;
    }



    public boolean isGoal() {
        return this.goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public double getBoardHeight() {
        return height;
    }


    public void setBoardHeight(double height) {
        this.height = height;
    }


    public double getBoardWidth() {
        return width;
    }


    public void setBoardWidth(double width) {
        this.width = width;
    }

    public void initiate(){
        BoardCells.cells=new ArrayList<>();
        String[][]ids={{"A","B","C"},{"D","E","F"},{"G","H","I"}};
        for(int j=0;j<rows;j++){
            for(int i=0;i<columns;i++){
               // System.out.println("i: "+i+" j: "+j);
                Cell cell=new Cell(i,j,sizeFactor,sizeFactor);

                cell.id=UUID.randomUUID().toString();//ids[i][j];//
                cell.setColumns(columns);
                cell.setRows(rows);
                BoardCells.cells.add(cell);

            }
        }

    }

    public void draw(int i){

        BoardCells.cells.get(i).show(gc);
        System.out.println("drawing..");




    }
    public void fillStack(){
        current.visited=true;
      //  System.out.println("cellStack: "+cellStack.size());
      //  System.out.println("board: "+(BoardCells.cells.size()-1));
      //  System.out.println(BoardCells.emptyCells+" Empty cells left");

      //  System.out.println("current: "+current);
        Cell next=current.checkNeighbours();
      //  System.out.println("Next: "+next);

        if(!cellExists(cellStack,current))
        {BoardCells.emptyCells--;}
        if(!cellExists(cellStack,current))
            cellStack.push(current);
        if(next!=null){
            next.visited=true;

            virtualStack.push(current);
            if(!cellExists(cellStack,current))
            cellStack.push(current);

            removeWalls( current, next);
            current=next;


        }else if(virtualStack.size()>0){
        //    System.out.println("Current: "+current);
            current=virtualStack.pop();



        }
       // if(cellStack.size()<BoardCells.cells.size()-1){

        if(BoardCells.emptyCells>=0) {
            fillStack();

        }else{
          //  System.out.println("No More empty cells");
            isGoodMaze();
        }

    }
    public void drawPath(int i){
       // System.out.println(i+") Drawing "+cellStack.size()+" Cells: CellId - "+cellStack.get(i).id);
        cellStack.lastElement().hightlight(gc);
        cellStack.firstElement().isFirst=true;
        cellStack.lastElement().isEnd=true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cellStack.get(i).drawRectangle(gc);
                System.out.println(cellStack);
            }
        });
    }

    public boolean cellExists(Stack<Cell> list,Cell cell ){
        boolean oldCell=false;
        for (Cell c:list) {
            if(c.id==cell.id)
                oldCell=true;

        }
        return oldCell;
    }
    public void removeWalls(Cell a,Cell b){
        int x=a.getI()-b.getI();
       // System.out.println("Remove wall at: "+x);
        //eastern neighbours
        if(x==1){
            a.changeWallAt(3,false);
            b.changeWallAt(1,false);
        }
        //western neighbours
        else if(x==-1){
            a.changeWallAt(1,false);
            b.changeWallAt(3,false);
        }
        int y=a.getJ()-b.getJ();
        //northern
        if(y==1){
            a.changeWallAt(0,false);
            b.changeWallAt(2,false);
        }
        //southern
        else if(y==-1){
            a.changeWallAt(2,false);
            b.changeWallAt(0,false);
        }
    }
    public void setSizeFactor(int size){
        if(size>=40){
            this.sizeFactor=size;
        }

    }

    @Override
    public void run() {
        int i=0;
        //i<cellStack.size()
        while(i<cellStack.size()){

            try {
                drawPath(i);
                i++;

                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public void unvisitNodes(){
        for(Cell c:cellStack){
            c.visited=false;
        }
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getSizeFactor() {
        return sizeFactor;
    }
}