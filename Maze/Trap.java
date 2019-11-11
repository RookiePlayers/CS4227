package Maze;

import javafx.scene.canvas.GraphicsContext;

import java.util.UUID;

public class Trap extends Cell {
    private int damage=1;
    public Trap(int i, int j, double h, double w) {

        super(i, j, h, w);
        super.id= UUID.randomUUID().toString();
        setTrap(true);

    }

    public void destroyTrap(GraphicsContext gc){
        clear();
        Cell c=new Cell(this.getI(),this.getJ(),this.getWidth(),this.getHeight());
        c.setWalls(this.getWalls());
        c.visited=true;
        c.drawRectangle(super.gc);


    }
    @Override
    public void triggerTrap(Player player)
    {
        player.takeDamage(damage);
        destroyTrap(player.getGc());
    }
}
