package Strategy;

import Maze.Composite.Cell;

public class DiagonalDistance implements AIStrategy {
    public double D=1,D2=Math.sqrt(2);
    @Override
    public int execute(Cell current, Cell destination) {
        int dx = Math.abs(current.getI() - destination.getI());
        int dy = Math.abs(current.getJ() - destination.getJ());
        return (int)(D * (dx + dy) + (D2 - 2 * D) * Math.min(dx, dy));
    }
}
