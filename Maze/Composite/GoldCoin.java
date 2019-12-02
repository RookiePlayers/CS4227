package Maze.Composite;

import Maze.Composite.Item;

public class GoldCoin extends Item {
    public GoldCoin(int i, int j, double width, double height) {
        super(i,j,width,height);
        super.url="coin.png";
        super.value=50;

    }
}
