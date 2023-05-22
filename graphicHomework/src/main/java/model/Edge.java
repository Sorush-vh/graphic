package model;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import view.ElementsRotationTransition;
import view.Game;

public class Edge extends Rectangle {


    public Edge(Ball ball){
        super(ball.getCenterX()-6/2, ball.getCenterY()-70, 4, 70);
        this.setFill(javafx.scene.paint.Color.BLACK);
    }
}
