package Command.PlayerCommand;

public class MoveDown implements Command {
    PlayerMovement playerMovement;

    public MoveDown(PlayerMovement playerMovement) {
        this.playerMovement = playerMovement;
    }

    @Override
    public void execute() {
        playerMovement.moveDown();
    }
}
