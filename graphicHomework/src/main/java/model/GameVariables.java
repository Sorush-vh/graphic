package model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import view.Game;

public class GameVariables {
    public static boolean IsGameLaunched=false;
    public static int numberOfBallsInGame;
    public static int totalBallNumber;
    public static int missionNumber;
    public static int missionDifficulty=1;
    public static double edgeHeight;
    public static int missionTime;
    public static double freezeTimer;
    public static int needleSpeed;

    private static void setMission1(Game game){
        game.createNeedle(game.gamePane);
            Timeline timelinez = new Timeline(new KeyFrame(Duration.millis(250),
                actionEvent -> setDefaultNeedles(game)));
            timelinez.setDelay(Duration.millis(0));
            timelinez.setCycleCount(3);
            timelinez.play();
            timelinez.setOnFinished(event -> timeLineEndEvent(game));
    }
    
    private static void timeLineEndEvent(Game game){
        IsGameLaunched=true;
        game.gamePane.getChildren().remove(game.preparedNeedle);
        setNeedlesCount();
        game.createNeedle(game.gamePane);
    }

    private static void setMission2(Game game){
        game.createNeedle(game.gamePane);
        Timeline timelinez = new Timeline(new KeyFrame(Duration.millis(400),
                actionEvent -> setDefaultNeedles(game)));
            timelinez.setDelay(Duration.millis(0));
            timelinez.setCycleCount(4);
            timelinez.play();
            timelinez.setOnFinished(event -> timeLineEndEvent(game));
    }

    private static void setMission3(Game game){
        game.createNeedle(game.gamePane);
        Timeline timelinez = new Timeline(new KeyFrame(Duration.millis(500),
                actionEvent -> setDefaultNeedles(game)));
            timelinez.setDelay(Duration.millis(0));
            timelinez.setCycleCount(5);
            timelinez.play();
            timelinez.setOnFinished(event -> timeLineEndEvent(game));
    }

    public static void runMission(int missionNumber, Game game){
        GameVariables.missionNumber=missionNumber;
        edgeHeight=70;
        setMissionsTime();
        applyDifficulty();
        switch (missionNumber) {
            case 1:
                setMission1(game);
                break;
            case 2:
                setMission2(game);
                break;
            case 3:
                setMission3(game);
                break;
            default:
                break;
        }
    }

    private static void setDefaultNeedles(Game game){
        game.handleNeedleThrow(game.preparedNeedle);
    }

    private static void setNeedlesCount(){
        switch (missionNumber) {
            case 1:
                numberOfBallsInGame=10;
                break;
            case 2:
                numberOfBallsInGame=13;
                break;
            case 3:
                numberOfBallsInGame=16;
                break;
            default:
                break;
        }
        totalBallNumber=numberOfBallsInGame;
    }

    private static void applyDifficulty(){
        switch (missionDifficulty) {
            case 1:
                freezeTimer=7;
                needleSpeed=13;
                edgeHeight+=20;
            case 2:
                freezeTimer=5;
                needleSpeed=10;
                edgeHeight+=10;
                missionTime-=15;
                break;
            case 3:
                freezeTimer=3;
                needleSpeed=7;
                missionTime-=30;
                break;
            default:
                break;
        }
    }

    private static void setMissionsTime(){
        switch (missionNumber) {
            case 1:
                missionTime=60;
                break;
            case 2:
                missionTime=75;
                break;
            case 3:
                missionTime=90;
                break;
            default:
                break;
        }
    }


}
