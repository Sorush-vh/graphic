package view;

import javafx.application.Application;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.GameElements;
import model.Needle;

public class Game extends Application {

    Pane gamePane;
    GameElements gameElements;

 

    @Override
    public void start(Stage stage) throws Exception {
        Pane gamePane = FXMLLoader.load(Game.class.getResource("/fxml/Game.fxml"));
        this.gamePane=gamePane;
        setNewNeedlesStartingPos(180, 500);
        initializeInGameElements();
        gameElements.initializeRotation();

        Scene scene = new Scene(gamePane);
        stage.setScene(scene);
        createNeedle(gamePane);

        
        stage.show();
    }

    private void initializeInGameElements(){
        Circle mainCircle= new Circle(180, 180, 80, javafx.scene.paint.Color.BLACK);
        gameElements= new GameElements(mainCircle, gamePane);
        throwNeedleAnimation.gameElements=gameElements;
        gamePane.getChildren().add( gameElements);
        ElementsRotationTransition.gameElements=gameElements;
    }

    private void setNewNeedlesStartingPos(int ballX, int ballY){
        Ball.XofBalls=ballX;
        Ball.YofBalls=ballY;
    }


    private void createNeedle(Pane gamePane) {
        Needle needle = Needle.createNewNeedle();
        gamePane.getChildren().addAll(needle);
        gamePane.getChildren().get(gamePane.getChildren().size()-1).requestFocus();

        needle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                 if (keyName.equals("Space")){
                    needle.throwNeedle(gamePane);
                    createNeedle(gamePane);
                 }
            }
        });
    }
}
