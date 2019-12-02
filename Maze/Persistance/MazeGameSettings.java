package Maze.Persistance;

import Maze.Enumerations.GameModes;
import Maze.Enumerations.GameState;
import Maze.UI.MazePreference;

public abstract class MazeGameSettings {
    public static MazePreference preference=new MazePreference();
    public static GameModes currentMode=GameModes.TIMECHALLENGE;
    public static boolean MAINTHREADON=true;
    public static boolean ENEMYTHREADON=false;
    public static GameState GAMESTATE= GameState.OFF;
    public static boolean AITHREAD=false;
    public static boolean BOARDTHREAD=false;
}
