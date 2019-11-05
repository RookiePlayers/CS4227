package AbstractFactory;

import javafx.scene.Parent;
import javafx.scene.Scene;
import  Maze.Player;

import java.util.ArrayList;

public abstract class MazeWinScene extends Scene {

    protected long timer=0;
    protected ArrayList<Player>players;
    protected Player Winner;
    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
    public MazeWinScene(Parent parent,ArrayList<Player>players) {
        super(parent);
        this.players=players;
    }
    public void updateUI(){}

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        Winner = winner;
    }
}
