package Command.PlayerCommand;

import Maze.Actor;

public class ControllerCommand
{
    private PlayerMovement playerMovement;
    private Command movements[];

    public ControllerCommand(Actor target) {
        playerMovement= MovementType.getWASD(target);
        movements=new Command[]{
                new MoveUP(playerMovement),
                new MoveLeft(playerMovement),
                new MoveDown(playerMovement),
                new MoveRight(playerMovement)};
        this.target = target;
    }

    private Actor target;

    public PlayerMovement getPlayerMovement() {
        return playerMovement;
    }

    public void setPlayerMovement(PlayerMovement playerMovement) {
        this.playerMovement = playerMovement;
    }

    public Command[] getMovements() {
        return movements;
    }

    public void setMovements(Command[] movements) {
        this.movements = movements;
    }

    public Actor getTarget() {
        return target;
    }

    public void setTarget(Actor target) {
        this.target = target;
    }
}
