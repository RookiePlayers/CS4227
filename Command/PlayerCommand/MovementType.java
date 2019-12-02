package Command.PlayerCommand;

import Maze.Composite.Actor;

public class MovementType {
    public static IPlayerMovement getWASD(Actor actor){
        return new WASDMovementI(actor);
    }
}
