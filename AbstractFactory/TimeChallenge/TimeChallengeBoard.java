package AbstractFactory.TimeChallenge;

import Maze.Board;
import Maze.Cell;
import Maze.Player;
import inventory.Models.Inventory;

public class TimeChallengeBoard extends Board {

    public TimeChallengeBoard(double height, double width,int controllers) {
        super(height, width);
        Player player=new Player("Player1",3,new Inventory(),0,0,60,60,new Cell(0,0,height,width));
        super.players.add(player);
        super.setPlayer(players);

    }





}
