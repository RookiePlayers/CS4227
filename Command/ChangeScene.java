package Command;

public class ChangeScene {
    Command command;
    public  ChangeScene(Command newCommand){
        command=newCommand;
    }
    public void press(){
        command.execute();
    }
}
