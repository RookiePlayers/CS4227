package Strategy;

import Maze.Composite.Cell;

public class EuclideanDistance implements AIStrategy {
    public double D=1,D2=Math.sqrt(2);
    @Override
    public int execute(Cell current, Cell destination) {
        int dx = Math.abs(current.getI() - destination.getI());
        int dy = Math.abs(current.getJ() - destination.getJ());
        return (int)(D * Math.sqrt(dx * dx + dy * dy));
    }
}
