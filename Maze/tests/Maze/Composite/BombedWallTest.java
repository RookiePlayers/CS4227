package Maze.Composite;

import Maze.UI.MazePreference;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombedWallTest {

    BombedWall bombedWall=new BombedWall();
    private MazePreference preference=new MazePreference();
    private Player player=new Player("",3,0,0,preference.getSizeFactor(),preference.getSizeFactor(),new Cell(0,0,0,0));

    @Test
    void getWall() {
        assertTrue(bombedWall != null);

    }

    @Test
    void hitWall() {
        player.setShielded(false);
        int h=player.getHealth();
        try{
            bombedWall.hitWall(player);
        }catch(NullPointerException e){
            assertEquals(h-1,player.getHealth());

        }
        assertEquals(h-1,player.getHealth());

    }
}