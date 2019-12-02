package Maze.Composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
private Enemy enemy=new Enemy(0,0,0,0);
    private Cell c=new Cell(1,1,0,0);


    @Test
    void isWallEast() {
         Cell c2=new Cell(2,1,0,0);
         assertTrue(enemy.isWallEast(c,c2));
    }

    @Test
    void isWallNorth() {
        Cell c2=new Cell(1,0,0,0);
        assertTrue(enemy.isWallNorth(c,c2));
    }

    @Test
    void isWallWest() {
        Cell c2=new Cell(0,1,0,0);
        assertTrue(enemy.isWallEast(c,c2));
    }

    @Test
    void isWallSouth() {
        Cell c2=new Cell(0,1,0,0);
        assertTrue(enemy.isWallEast(c,c2));
    }
}