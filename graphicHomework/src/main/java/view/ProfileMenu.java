package view;

import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

import org.json.simple.parser.ParseException;

import controller.JsonConverter;


public class ProfileMenu extends Application {
    public static Stage stage;

    public PasswordField password;
    public TextField username;
    public Text output_text;

    @Override
    public void start(Stage stage) throws Exception {
        ProfileMenu.stage=stage;
        BorderPane borderPane = FXMLLoader.load(
            new URL(RegisterMenu.class.getResource("/FXML/ProfileMenu.fxml").toExternalForm()));
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        username.setPromptText(User.getCurrentUser().getUsername());
        username.setOpacity(0.2);

        username.textProperty().addListener((observable, oldText, newText)->{
            username.setPromptText(newText);
            username.setOpacity(1);
        });

        password.textProperty().addListener((observable, oldText, newText)->{
            password.setOpacity(1);
            password.setPromptText(newText);
        });
    }

    public void usernameChange() throws ParseException{
        String newUsername=username.getText();
        if(User.getUserById(newUsername) != null)
            output_text.setText("Error: username already in use");
        else{
            User.getCurrentUser().setUsername(newUsername);
            JsonConverter.putUserDataInFile(newUsername, User.getCurrentUser().getPassword(),User.getCurrentUser().getDifficultyScores(),
                    User.getCurrentUser().getMissionTimes(),User.getCurrentUser().getMissionScores(),"src/main/resources/data/users.json");
        }
    }

    public void passwordChange(){

    }
}
