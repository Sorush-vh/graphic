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
import model.Edge;
import model.GameElements;
import model.Needle;

public class throwNeedleAnimation extends Transition{

    public Needle needle;
    public Pane pane;
    private int windDegree;
    private double minDistance;
    private static double mainBallCenterX,mainBallCenterY;

    public static GameElements gameElements;
    public static int i=0;

    public throwNeedleAnimation(Pane pane, Needle needle, int windDegree){
        this.windDegree=windDegree;
        this.needle=needle;
        this.pane=pane;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));
        this.minDistance=gameElements.getMainCircle().getRadius();
        this.mainBallCenterX=gameElements.getMainCircle().getCenterX();
        this.mainBallCenterY=gameElements.getMainCircle().getCenterY();
    }
    // needle.getBoundsInParent().intersects(gameElements.getMainCircle().getBoundsInParent())

    @Override
    protected void interpolate(double v) {
    
        moveNeedleComponents(10, needle);
        
        if (   needle.edge.getDistanceFromCoord(mainBallCenterX, mainBallCenterY) < minDistance) {
            collision(needle);
        }

        if(needle.getNeedleTopY()<gameElements.getMainCircle().getCenterY()+50){
            needle.getThrowingAnimation().stop();
            needle.setThrowingAnimation(null);
            this.stop();
            pane.getChildren().remove(needle);
        }
    }

    public void collision(Needle needle){
        needle.getThrowingAnimation().stop();
        needle.setThrowingAnimation(null);
       
        changeEdge(needle);
        gameElements.getChildren().add(needle);
        gameElements.stickedNeedles.add(needle);
        rotateNeedleToMerge(needle, gameElements.getMainCircle());
        addPhaseEffectsToNeedle();
    }

    private void moveNeedleComponents(int deltaY, Needle needle){
        double deltaX=deltaY*Math.tan((windDegree*Math.PI)/180);
        needle.ball.setCenterY(needle.ball.getCenterY()-deltaY);
        needle.ball.text.setY(needle.ball.text.getY()-deltaY);
        needle.edge.setY(needle.edge.getY()-deltaY);

        needle.ball.setCenterX(needle.ball.getCenterX()+deltaX);
        needle.ball.text.setX(needle.ball.text.getX()+deltaX);
        needle.edge.setX(needle.edge.getX()+deltaX);
    }

    private void addPhaseEffectsToNeedle(){
        if(Game.nextCheckpoint-1>=2)
            needle.ball.startBallResizing();
        
        if(Game.nextCheckpoint-1>=3){
            needle.setDisappearingTimeline();
            needle.disappearingTimeLine.play();
        }
    }

    private void rotateNeedleToMerge(Needle needle , Circle mainCircle){
        Rotate rotate= gameElements.rotateTransition.CreateRotate(
                -gameElements.rotateTransition.getAngle(), mainCircle.getCenterX(), mainCircle.getCenterY());
        needle.getTransforms().add(rotate);
    }
    

    private void changeEdge(Needle needle){
        // double thetaOfMerge;
        // double deltaX=-mainBallCenterX+needle.edge.getX();
        // double deltaY=-mainBallCenterY+needle.edge.getY();
        // thetaOfMerge=Math.atan(deltaX/deltaY);
        // System.out.println(deltaX);
        // System.out.println(deltaY);

        // double edgeTopX=needle.edge.getX();
        // double edgeTopY=needle.edge.getY();

        // Rotate edgeRotate=new Rotate(-thetaOfMerge*180/Math.PI, edgeTopX, edgeTopY);
        // needle.getTransforms().add(edgeRotate);
    }
}
