package Strategy;

import Maze.Composite.Cell;

public interface AIStrategy {
    public int execute(Cell current, Cell destination);
}
