package Adapter;


import Maze.Composite.Player;
import javafx.scene.canvas.GraphicsContext;

public class IceTrapAdapter implements ITrap {
    private IceTrap icetrap;
    public IceTrapAdapter(IceTrap newIceTrap){
        icetrap = newIceTrap;
    }

    @Override
    public void destroyTrap(GraphicsContext gc) {
        icetrap.destroyTrap(gc);
    }

    @Override
    public void triggerTrap(Player player) {
        icetrap.triggerTrap(player);
        icetrap.destroyTrap(player.getGc());
    }

    public IceTrap getIcetrap() {
        return icetrap;
    }

    public void setIcetrap(IceTrap icetrap) {
        this.icetrap = icetrap;
    }
}
