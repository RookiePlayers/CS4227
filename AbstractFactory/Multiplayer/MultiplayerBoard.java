package AbstractFactory.Multiplayer;

import Maze.Board;
import Maze.Cell;
import Maze.Player;
import inventory.Models.Inventory;
import javafx.scene.paint.Color;

public class MultiplayerBoard extends Board {


    public MultiplayerBoard(double height, double width,int controllers) {
        super(height, width);
        Player player=new Player("Player1",3,new Inventory(),0,0,60,60,new Cell(0,0,height,width));
        player.setColor(Color.BROWN);
        Player player2=new Player("Player2",3,new Inventory(),0,0,60,60,new Cell(0,0,height,width));
        player2.setColor(Color.PINK);



        super.players.add(player);
        super.players.add(player2);
        super.setPlayer(players);
    }

}
