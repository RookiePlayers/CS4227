package Strategy;

import Maze.Composite.Cell;

public class AIContext {

   public  AIStrategy aiStrategy;
    public void setStrategy(AIStrategy aiStrategy) {
        this.aiStrategy=aiStrategy;

    }
    public int executeStrategy(Cell now,Cell goal){
        return this.aiStrategy.execute(now,goal);
    }
}
