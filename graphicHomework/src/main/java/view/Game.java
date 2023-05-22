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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Ball;
import model.GameElements;
import model.GameVariables;
import model.Needle;

public class Game extends Application {

    public static boolean isGameGoingOn=true;
    public static int nextCheckpoint=2;
    public static int scoreValue;
    
    Pane gamePane;
    GameElements gameElements;
    ProgressBar freezeBar;
    Text freezePercentage;
    Text scoreText;
    HBox freezeHBox;
    Text OutputText;



 

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
        SetUserProgressData();
        initializeTexts();
        
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


    public void SetUserProgressData(){
        freezeHBox=new HBox(20);
        freezeHBox.setAlignment(Pos.CENTER_RIGHT);
        freezeHBox.setLayoutX(350);
        freezeHBox.setStyle("-fx-background-color: rgba(72, 70, 76, 0.306)");
        freezeBar=new ProgressBar(1);
        freezeBar.setProgress(0.2);
        freezeHBox.getChildren().add( freezeBar);

        freezePercentage= new Text(0, 0, "Freeze Progress: 0%");
        freezePercentage.setStyle("-fx-text-fill: rgb(22, 32, 9);");

        scoreText=new Text(0,0,"Your Score: "+scoreValue);
        scoreText.setStyle("-fx-text-fill: rgb(22, 32, 9);");

        freezeHBox.getChildren().addAll(freezePercentage,scoreText);

        gamePane.getChildren().addAll(freezeHBox);
        
    }

    public void addToFreezeProgress(double chargeValue){
        if(freezeBar.getProgress()+chargeValue<1)
            freezeBar.setProgress(freezeBar.getProgress()+chargeValue);

        else { 
            freezeBar.setProgress(1);
            OutputText.setText("Freeze loaded!: press "+"F"+" to freeze!");
        }
    }


    private void createNeedle(Pane gamePane) {
        Needle needle = Needle.createNewNeedle();
        gamePane.getChildren().addAll(needle);
        gamePane.getChildren().get(gamePane.getChildren().size()-1).requestFocus();

        needle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();

                if (keyName.equals("Space"))
                    handleNeedleThrow(needle);
                
                else if (keyName.equals("F"))
                    freezeElements();

                else if (keyEvent.getCode()==KeyCode.ESCAPE)
                    pauseGame(); 

                else if (keyName.equals("R"))
                    resizeBalls(); 

                else if (keyName.equals("A"))
                    fadeBalls(); 

                else if (keyName.equals("X"))
                    startDirectionChange();
            }
        });
    }


    public void pauseGame(){
        gameElements.rotateTransition.timeline.pause();
    }

    public void handleNeedleThrow(Needle needle){
        needle.throwNeedle(gamePane);
        addToFreezeProgress(0.1);
        createNeedle(gamePane);
        handleFreezePercentage();
        handleScoreChange();
    }

    private void handlePhaseChange(){

        int passedCheckpoint=nextCheckpoint-1;
        switch (passedCheckpoint) {
            case 1:
                startDirectionChange();
                resizeBalls();
                break;
            case 2:
                fadeBalls();
            default:
                break;
        }
        OutputText.setText("Entered Phase "+nextCheckpoint);
        nextCheckpoint++;
    }

    private void initializeTexts(){
        HBox hBox=new HBox();
        hBox.setStyle("-fx-background-color: rgba(72, 70, 76, 0.306)");

        OutputText=new Text();
        OutputText.setText("Phase 1: enjoy:)");
        OutputText.setStyle("-fx-text-fill: rgb(22, 32, 9);");

        hBox.getChildren().add(OutputText);
        hBox.setLayoutX(400);
        hBox.setLayoutY(450);

        gamePane.getChildren().add(hBox);
    }

    private void handleScoreChange(){
        scoreValue++;
        scoreText.setText("Your Score: "+scoreValue);

        if(scoreValue> ((nextCheckpoint-1)*GameVariables.numberOfBallsInGame)/4)
            handlePhaseChange();
    }

    private void handleFreezePercentage(){
        double progressInPercent=freezeBar.getProgress()*100;
        freezePercentage.setText("Percentage: "+(int)progressInPercent+"%");
    }

    private void freezeElements(){
        if(freezeBar.getProgress()<1) {
            OutputText.setText("You cant use Freeze right now!");
            return;
        }
        gameElements.rotateTransition.isOnFreeze=true;

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5000),
            actionEvent ->  gameElements.rotateTransition.isOnFreeze=false));

        timeline.play();
        freezeBar.setProgress(0);
        OutputText.setText("");
        handleFreezePercentage();
    }

    private void resizeBalls(){
        for ( Needle needle : gameElements.stickedNeedles ) 
            needle.ball.startBallResizing();
    }

    private void fadeBalls(){
        for ( Needle needle : gameElements.stickedNeedles ) {
                needle.setDisappearingTimeline();
                needle.disappearingTimeLine.play();
        }
    }

    private void startDirectionChange(){
        gameElements.rotateTransition.initializeDirectionChange();
    }

}
