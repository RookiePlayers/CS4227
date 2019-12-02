package Maze.Composite;

import Maze.UI.MazePreference;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {
    private MazePreference preference=new MazePreference();
    private Player player=new Player("",3,0,0,preference.getSizeFactor(),preference.getSizeFactor(),new Cell(0,0,0,0));

    @Test
    void exitDoor() {
        Cell c=new Cell(1,2,0,0);
        Door d=new Door(0,0,0,0,c, Color.BEIGE);
        try{
            d.exitDoor(player);
        }catch (NullPointerException n){
            assertEquals(c.getI(),player.getI());
            assertEquals(c.getJ(),player.getJ());
        }
        assertEquals(c.getI(),player.getI());
        assertEquals(c.getJ(),player.getJ());
    }
}