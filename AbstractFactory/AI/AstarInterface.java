package AbstractFactory.AI;

import Maze.Composite.Cell;

public interface AstarInterface {
    public int calcHeuristics(Cell current, Cell target);
    public Object checkNeighbours(Cell thisCell);


}
