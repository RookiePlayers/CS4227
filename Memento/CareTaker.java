package Memento;

import java.util.ArrayList;

public class CareTaker
{
    private ArrayList<Memento> history;
    private int currState =- 1;

    public CareTaker()
    {
        this.history = new ArrayList<>();
    }

    public void addMemento(Memento m)
    {
        this.history.add(m);
        currState = this.history.size() -1 ;
    }
    public Memento getMemento(int index)
    {
        return history.get(index);
    }
    public Memento undo()
    {
        System.out.println("Undoing State");
        if(currState <= 0)
        {
            currState = 0 ;
            return getMemento( 0);
        }

        currState--;
        return getMemento(currState);
    }
    public Memento redo()
    {
        System.out.println("Redoing State");
        if(currState >= history.size() -1)
        {
            currState = history.size() -1;
            return  getMemento(currState);
        }

        currState++;
        return getMemento(currState);
    }
}