package view;


import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import controller.UserComparator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;



public class ScoreboardMenu extends Application{
    
    public HBox sortingHbox;
    public CheckBox lol;
    public VBox rankingVbox;


    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage=stage;
        BorderPane borderPane = FXMLLoader.load(
            new URL(RegisterMenu.class.getResource("/FXML/MainMenu.fxml").toExternalForm()));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }


    private void initializerankVbox(){
        rankingVbox = new VBox(5);
        Collections.sort(User.getUsers(), new UserComparator());
        for (int i = 0; i < 10; i++) {
            HBox userInfo=new HBox(15);
            Text userRank=new Text();
            Text userName=new Text();
            Text userScore=new Text();
            Text userTime=new Text();
        }
    }
}
