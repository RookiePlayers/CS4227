package Command.PlayerCommand;

public class PlayerTrigger {
    Command command;

    public PlayerTrigger(Command command) {
        this.command = command;
    }
    public void move(){
        this.command.execute();
    }
}
