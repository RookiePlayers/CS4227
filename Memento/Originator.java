package Memento;

import Maze.Enumerations.GameState;
import Maze.Persistance.MazeGameSettings;

public class Originator
{
    private GameState gameState ;

    public  Originator()
    {
    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState ;
        MazeGameSettings.GAMESTATE=gameState;

    }

    public GameState getGameState()
    {
        return this.gameState ;
    }

    public Memento save()
    {
        return new Memento(gameState);
    }

    public void restor(Memento m)
    {
        this.gameState = m.getState();
    }
}
