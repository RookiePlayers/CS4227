package Command.PlayerCommand;

public class MoveRight implements Command {
    PlayerMovement playerMovement;

    public MoveRight(PlayerMovement playerMovement) {
        this.playerMovement = playerMovement;
    }

    @Override
    public void execute() {
        playerMovement.moveRight();
    }
}
