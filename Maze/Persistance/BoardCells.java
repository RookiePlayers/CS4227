package Maze.Persistance;

import Maze.Composite.Cell;
import Maze.Composite.Enemy;

import java.util.ArrayList;

public abstract class BoardCells {
    public static ArrayList<Cell> cells=new ArrayList<>();
    public static ArrayList<Cell> path=new ArrayList<>();
    public static ArrayList<Enemy>currentEnimies=new ArrayList<>();
    public static int cols,rows;

    public static int emptyCells =0;
}
