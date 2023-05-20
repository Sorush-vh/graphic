package view;



import javafx.scene.layout.Pane;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.transform.Rotate;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.GameElements;
import model.Needle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

public class ElementsRotationTransition extends Rotate{
    
    public static GameElements gameElements;


    
    private Pane pane;
    public Timeline timeline;
    public double theta=0;

    public ElementsRotationTransition(Pane pane){
        super();
        this.setPivotX(gameElements.getMainCircle().getCenterX());
        this.setPivotY(gameElements.getMainCircle().getCenterY());
        this.setAngle(1);
        
        timeline=createTimeline();
    }

    public Timeline createTimeline(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50),
                actionEvent -> executeRotate()));

        this.timeline=timeline;
        timeline.setCycleCount(-1);
        timeline.setDelay(Duration.millis(0));
        return timeline;
    }

    private void executeRotate(){
        theta=(theta+this.getAngle())%360;
        gameElements.getTransforms().add(this);
    }
}
