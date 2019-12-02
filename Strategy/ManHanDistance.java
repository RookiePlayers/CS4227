package Strategy;

import Maze.Composite.Cell;

public class ManHanDistance implements AIStrategy {
    @Override
    public int execute(Cell current, Cell destination) {
        return Math.abs(current.getI() - destination.getI()) + Math.abs(current.getJ() - destination.getJ()); // else return "Manhattan distance"

    }
}
