package view;


import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;



public class MainMenu extends Application {

    public static Stage stage;
    public Button profileButton;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage=stage;
        BorderPane borderPane = FXMLLoader.load(
            new URL(RegisterMenu.class.getResource("/FXML/MainMenu.fxml").toExternalForm()));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public void startGame() throws Exception{
        new Game().start(stage);
    }

    private void setProfileButtonStyle(){
        if(User.getCurrentUser()==null)
            profileButton.setOpacity(0.3);
    }

    public void profile() throws Exception{
        setProfileButtonStyle();

        if(User.getCurrentUser()!=null)
            new ProfileMenu().start(stage);
    }

    public void setting(){
        
    }

    public void scoreBoard(){
        
    }

    public void logout() throws Exception{
        User.setCurrentUser(null);
        new LoginMenu().start(stage);
    }

    public void restartGame(){
        
    }
}
