package Maze.MazeSolver;

import AbstractFactory.MazeWinScene;
import Maze.Board;
import Maze.BoardCells;
import Maze.Cell;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

/*Implementation of A* Algorithm*/
public class MazeSolver extends Canvas implements Runnable {
    public Board board;
    public Cell[][] cell;
    private List<Cell> openList=new ArrayList<>();
    private int columns,rows;
    private List<Cell> closedList=new ArrayList<>();
    private Stack<Cell>mazeGrid;
    private  Stack<Cell> path=new Stack<>();

    private Cell now;
    private Cell goal,start;
    static boolean additionalPath=false;
    private GraphicsContext gc;

    public MazeSolver(Board board,int columns,int rows){
        this.columns=columns;
        this.rows=rows;
        this.board=board;
        gc=this.getGraphicsContext2D();
        this.setHeight(board.getBoardHeight());
        this.setWidth(board.getBoardWidth());
        mazeGrid=board.getCellStack();
        goal=board.getCellStack().lastElement();
        start=board.getCellStack().firstElement();
        Thread t=new Thread(board);
        t.start();
        start.setgCosts(0);

        now=start;
        setHeuristics();
        System.out.println("Heustics Aplied");
        findPathTo();
        System.out.println(this.path);
       ;



    }


    public void setHeuristics(){
        for (Cell cell:mazeGrid) {
            cell.sethCosts(calcHeuristic(cell));
        }
    }
    public int calcHeuristic(Cell current){
        double h=Math.sqrt(Math.pow((goal.getI()-current.getI()),2)+Math.pow((goal.getJ()-current.getJ()),2));
        return (int)h;

    }
    private double manHanDistance(int dx, int dy) {

            return Math.abs(this.now.getI() + dx - goal.getI()) + Math.abs(this.now.getJ() + dy - goal.getJ()); // else return "Manhattan distance"

    }
    public ArrayList<ArrayList<Cell>> checkPlayerNeighbours(Cell thisCell){
        //Hold All neighbours both bad and Good Neighbours get added to open bad added to close
        ArrayList<ArrayList<Cell>> neighbours=new ArrayList<>();
        //Good Neighbours
        neighbours.add(new ArrayList<>());
        //Bad Neighbours
        neighbours.add(new ArrayList<>());
        //System.out.println(i+", "+j);

        int e=index(thisCell.getI()+1,thisCell.getJ());
        int w=index(thisCell.getI()-1,thisCell.getJ());
        int n=index(thisCell.getI(),thisCell.getJ()-1);
        int s=index(thisCell.getI(),thisCell.getJ()+1);
        // System.out.println(n+","+e+","+s+","+w);
        Cell east=e!=-1? BoardCells.cells.get(e):null;
        Cell west=w!=-1?BoardCells.cells.get(w):null;
        Cell north=n!=-1?BoardCells.cells.get(n):null;
        Cell south=s!=-1?BoardCells.cells.get(s):null;


        if(north!=null)
        {
            if(!isWallNorth(thisCell,north))
            neighbours.get(0).add(north);
            else neighbours.get(1).add(north);
        }
        if(south!=null) {
            if(!isWallSouth(thisCell,south))
            neighbours.get(0).add(south);
            else neighbours.get(1).add(south);
        }
        if(east!=null) {
            if(!isWallEast(thisCell,east))
            neighbours.get(0).add(east);
            else neighbours.get(1).add(east);
        }
        if(west!=null){
            if(!isWallWest(thisCell,west))
            neighbours.get(0).add(west);
            else neighbours.get(1).add(east);
        }
        System.out.println("Neighbours: "+neighbours);
        return neighbours;
    }

    public void addNeigborsToOpenList(){
        System.out.println("ADDING NEIGHBOURS");
        ArrayList<Cell>goodNeighbours=checkPlayerNeighbours(this.now).get(0);
        System.out.println("GOOD NEIGHBOURS: "+goodNeighbours);
        for(Cell c:goodNeighbours){
            if(true){//!isBlocked(c)
                System.out.println("Not blocked");
                c.setParent(this.now);
                c.setgCosts(this.now.getgCosts());
                c.sethCosts(calcHeuristic(c));
                if(!findNeighborInList(this.openList,c)&&!findNeighborInList(this.closedList,c)){
                    c.setgCosts(c.parent.getgCosts()+1);
                    openList.add(c);
                    Collections.sort(this.openList);
                }
            }
        }

       /* ArrayList<Cell>badNeighbours=checkPlayerNeighbours(this.now).get(1);
        for(Cell c:badNeighbours){
            if(isBlocked(c)){
                closedList.add(c);
            }
        }*/
        System.out.println("OPEN LIST: "+this.openList);
        System.out.println("Done ");
    }
    public List<Cell> findPathTo() {
        System.out.println("FIND PATH TO GOAL");
        this.closedList.add(this.now);
        System.out.println("CLOSED LIST: "+this.closedList);

        addNeigborsToOpenList();


        while (this.now.id != this.goal.id) {
            if (this.openList.isEmpty()) { // Nothing to examine
                return null;
            }
            this.now = this.openList.get(0); // get first node (lowest f score)
            this.openList.remove(0); // remove it
            this.closedList.add(this.now); // and add to the closed
            System.out.println("CLOSED LIST: "+this.closedList);
            addNeigborsToOpenList();
        }
        for (Cell c :
                closedList) {
            c.setColor(Color.GOLD);
            this.path.push(c);

        }
        return this.path;
    }
    private boolean findNeighborInList(List<Cell> array, Cell node) {
        return array.stream().anyMatch((n) -> (n.id == node.id));
    }
    public void addNeighboursToOpenList(){
        Cell node;
    }

    public boolean isValid(Cell current){
        return false;
    }

    public boolean isDestination(Cell current){
        return current==goal;
    }
    public boolean isBlocked(Cell next){
        boolean blocked=false;
        if(next.visited){
            blocked=true;
        }
        //else if(next.)
        //return next.
        return  blocked;
    }
    public int index(int i, int j){
        // System.out.println("i: "+i+" j: " + j+"\nCol: "+columns+", Row: "+rows);
        if(i<0||j<0||i>columns-1||j>rows-1)
            return -1;
        return i+j*columns;
    }
    public boolean isWallEast(Cell current,Cell next){
        System.out.println("CheckWallAt East: "+current.checkWallAt("E")+" - "+"CheckWallAt West: "+current.checkWallAt("W"));
        return (current.checkWallAt("E")&&next.checkWallAt("W"));
    }
    public boolean isWallNorth(Cell current,Cell next){
        return (current.checkWallAt("N")&&next.checkWallAt("S"));
    }
    public boolean isWallWest(Cell current,Cell next){
        return (current.checkWallAt("W")&&next.checkWallAt("E"));
    }public boolean isWallSouth(Cell current,Cell next){
        return (current.checkWallAt("S")&&next.checkWallAt("N"));
    }

    public void drawPath(int i){
        System.out.println(i+") Drawing "+path.size()+" Cells:  "+path.get(i));
        //cellStack.lastElement().hightlight(gc);
        //cellStack.firstElement().isFirst=true;
        //cellStack.lastElement().isEnd=true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                path.get(i).drawRectangle(gc);
                System.out.println(path);
            }
        });
    }
    @Override
    public void run() {
        int i=0;
        //i<cellStack.size()
        while(i<this.path.size()){

            try {
                drawPath(i);
                i++;

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
