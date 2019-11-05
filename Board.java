import javafx.application.Application;
import javafx.stage.Stage;

public class Board extends Stage
{
    private double height=500;
    private double width=500;
    public Board(double height,double width){
        this.width=width;
        this.height=height;
        this.setHeight(height);
        this.setWidth(width);


    }
} 