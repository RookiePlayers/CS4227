package Maze.Composite;

import Maze.Composite.Item;

public class Crown extends Item {
    public Crown(int i, int j, double width, double height) {
        super(i,j,width,height);
        super.url="crown.png";
        super.value=2500;
    }
}
