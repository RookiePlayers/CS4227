package Command.PlayerCommand;

import Maze.Actor;

public class MovementType {
    public static PlayerMovement getWASD(Actor actor){
        return new WASDMovement(actor);
    }
}
