package Command.PlayerCommand;

public class MoveLeft implements Command {
    PlayerMovement playerMovement;

    public MoveLeft(PlayerMovement playerMovement) {
        this.playerMovement = playerMovement;
    }

    @Override
    public void execute() {
        playerMovement.moveLeft();
    }
}
