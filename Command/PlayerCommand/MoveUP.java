package Command.PlayerCommand;

public class MoveUP implements Command {
    PlayerMovement playerMovement;

    public MoveUP(PlayerMovement playerMovement) {
        this.playerMovement = playerMovement;
    }

    @Override
    public void execute() {
        playerMovement.moveUp();
    }
}
