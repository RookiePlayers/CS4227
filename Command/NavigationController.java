package Command;

public class NavigationController {
    public static NavigationControl getNavigation(){
        return  new SceneNavigation();
    }
}
