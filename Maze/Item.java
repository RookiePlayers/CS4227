package Maze;

import inventory.Models.ItemStatus;

import java.time.Duration;
import java.time.Instant;

public class Item extends Cell {
    protected String type;
    protected String name;//eg Gold Key
    protected String ID = "gold_key";
    protected String description;//eg key for opening gold door01
    protected Instant timePickedUp = Instant.now();//e.g this - current time gives the duration
    protected Duration timeElapsedSincePickedUp;
    protected ItemStatus status;
    public Item(int i, int j, double h, double w) {
        super(i, j, h, w);
    }

}
