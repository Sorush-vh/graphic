package model;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import controller.JsonConverter;
import javafx.scene.image.Image;
import view.RegisterMenuController;

public class User {
    
    private String username;
    private String password;
    private Image avatar;

    private static final ArrayList<User> users=new ArrayList<User>();
    private static User activeUser;

    static{
        try {
            JsonConverter.fillFormerUsersDatabase("src/main/resources/data/users.json");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Image getAvatar(){
        return avatar;
    }

    public void setAvatar(Image avatar){
        this.avatar=avatar;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public static void addUser(User user){
        users.add( user);
    }

    public static User getUserById(String username){
        for (User user : users) 
            if(user.getUsername().equals(username)) return user;
        
        return null;    
    }

    public static User getCurrentUser(){
        return activeUser;
    }
    
    public static void setCurrentUser(User user){
        activeUser=user;
    }

    public static void createUser(String username, String password){
        User temp=new User(username, password);
        addUser(temp);
        try {
            JsonConverter.putUserDataInFile(username, password, "src/main/resources/data/users.json");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
