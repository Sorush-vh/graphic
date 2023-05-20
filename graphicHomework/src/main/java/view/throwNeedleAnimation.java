package view;

import javafx.scene.layout.Pane;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.GameElements;
import model.Needle;

public class throwNeedleAnimation extends Transition{

    public Needle needle;
    public Pane pane;
    public static GameElements gameElements;

    public throwNeedleAnimation(Pane pane, Needle needle){
        this.needle=needle;
        this.pane=pane;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));
    }


    @Override
    protected void interpolate(double v) {
    
        moveNeedleComponents(10, needle);
        
        if (needle.getBoundsInParent().intersects(gameElements.getMainCircle().getBoundsInParent())) {
            collision(needle, gameElements.getMainCircle());
        }

        if(needle.getNeedleTopY()<gameElements.getMainCircle().getCenterY()+50){
            needle.getThrowingAnimation().stop();
            needle.setThrowingAnimation(null);
            this.stop();
        }
    }

    public void collision(Needle needle, Circle mainCircle){
        needle.getThrowingAnimation().stop();
        needle.setThrowingAnimation(null);
       
        gameElements.getChildren().add(needle);
        rotateNeedleToMerge(needle);
    }

    private void moveNeedleComponents(int deltaY, Needle needle){
        needle.ball.setCenterY(needle.ball.getCenterY()-deltaY);
        needle.ball.text.setY(needle.ball.text.getY()-deltaY);
        needle.edge.setY(needle.edge.getY()-deltaY);
    }



    private void rotateNeedleToMerge(Needle needle){
        Rotate rotate=new Rotate();
        rotate.setPivotX(gameElements.getMainCircle().getCenterX());
        rotate.setPivotY(gameElements.getMainCircle().getCenterY());
        rotate.setAngle(-gameElements.rotateTransition.theta);
        needle.getTransforms().add(rotate);
    }
    
}
