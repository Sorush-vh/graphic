package model;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Ball extends Circle {

    public static double XofBalls;
    public static double YofBalls;
    public static int ballNumber=10;

    public Text text;
    public Group group;

    public Ball(double radius){
        super(XofBalls,YofBalls, radius);
        this.setFill(javafx.scene.paint.Color.WHITE);
        this.setStroke(javafx.scene.paint.Color.BLACK);
        this.setStrokeWidth(2);
        createTextOfBall();

        group= new Group();
        group.getChildren().addAll(this,text);
        ballNumber++;
    }

    private void createTextOfBall(){
        
        int xModifier=getNumberXmodifier();
        Text text = new Text(XofBalls+xModifier, YofBalls+4,"");
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-text-alignment: center; -fx-alignment: center;");
        text.setText(""+ballNumber);
        this.text=text;
    }

    private int getNumberXmodifier(){
        int xModifier;
        if(ballNumber>=10) xModifier=-8;
        else xModifier=-5;
        return xModifier;
    }

    public Group getBallGroup(){
        return group;
    }

}
