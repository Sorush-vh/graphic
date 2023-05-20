package model;

import javafx.util.Duration;
import view.ElementsRotationTransition;
import view.throwNeedleAnimation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;

public class Needle extends Group {
    public Edge edge;
    public Ball ball;

    private throwNeedleAnimation throwingAnimation;

    public Needle(){
        super();
        ball=new Ball(20);
        edge=new Edge(ball);
        this.getChildren().addAll(edge,ball.getBallGroup());
    }

    public void setThrowingAnimation(throwNeedleAnimation animation){
        this.throwingAnimation=animation;
    }

    public throwNeedleAnimation getThrowingAnimation(){
        return throwingAnimation;
    }

    public void throwNeedle(Pane pane){
        throwNeedleAnimation animation=new throwNeedleAnimation(pane, this);
        this.setThrowingAnimation(animation);
        animation.play();
    }

    public double getNeedleTopY(){
        return ball.getCenterY()-ball.getRadius()-edge.getHeight();
    }

    public static Needle createNewNeedle(){
        Needle newNeedle=new Needle();

        return newNeedle;
    }


}
