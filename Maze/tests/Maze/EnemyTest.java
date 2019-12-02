package Maze;

import Maze.Composite.Board;
import Maze.Composite.Cell;
import Maze.Composite.Enemy;
import Maze.Composite.Player;
import Maze.Persistance.BoardCells;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.paint.Color;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    public Player player;
    public Board board;
    public EnemyTest current;
    public Enemy enemy;
    public Stack<Cell> path=new Stack<>();
    private List<Cell> closedList=new ArrayList<>();
    private List<Cell> openList=new ArrayList<>();
    private  Cell c=new Cell(0,0,0,0);
    private int i;
    private int j;

    @Test
    void getDamage() {
        int damage = (int) enemy.getDamage();
        assertEquals(damage,enemy.getSpeed());

    }

    @Test
    void setDamage() {

        int damage  = 5;
        enemy.setDamage(damage);
    }

    @Test
    void setSpeed() {
        int speed = 10;
        enemy.setSpeed(speed);
    }



    @Test
    void calcHeuristic(Cell cell) {


        double x =  Math.sqrt(Math.pow((player.getI()-current.getI()),2)+Math.pow((player.getJ()-current.getJ()),2));

    }

    private int getJ() {
        return j;
    }


    @Test
    void checkEnemyNeighbours() {

        Assertions.assertTrue(BoardCells.cells.get(0).getColumns() > 0);
        Assertions.assertTrue(BoardCells.cells.get(0).getRows() > 0);
    }

    @Test
    void resetCurrentPath() {

        path.clear();
        closedList.clear();
        current=this;
        openList.clear();
    }

    @Test
    void drawEnemy() {

        String file="/Images/danger.jpg";
        assertEquals(file,c.getGrassImg(),"danger.png missing");
    }

    @Test
    void getSpeed() {

        int speed = (int) enemy.getSpeed();
        assertEquals(speed,enemy.getSpeed());
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}