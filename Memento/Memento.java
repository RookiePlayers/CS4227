package Memento;

import Maze.Enumerations.GameState;

public class Memento
{

    private GameState state;

    public Memento(GameState state)
    {
        this.state = state ;
    }

    public GameState getState()
    {
        return this.state;
    }

    public void setState()
    {
        this.state = state ;
    }
}
