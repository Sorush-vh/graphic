package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import controller.JsonConverter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import view.RegisterMenuController;

public class User {
    
    private String username;
    private String password;
    private Image avatar;
    private int[] difficultyScores;
    private int[] missionTimes;
    private int[] missionScores;
    private KeyCode[] userKeyBinds;

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
        this.difficultyScores=new int[3];
        this.userKeyBinds=new KeyCode[3];
        this.missionScores=new int[3];
        this.missionTimes=new int[3];
    }

    public int[] getMissionTimes(){
        return missionTimes;
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

    public String getUsername(){
        return username;
    }

    public int[] getDifficultyScores(){
        return difficultyScores;
    }

    public int[] getMissionScores(){
        return missionScores;
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

    public void storeMissionScore(int score, int mission, int difficulty,int timeTaken){
        if(score>missionScores[mission-1]){
            difficultyScores[difficulty-1]+=score;
            missionScores[mission-1]=score;
            missionTimes[mission-1]=timeTaken;
        }
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
            JsonConverter.putUserDataInFile(username, password,temp.difficultyScores,temp.missionTimes,temp.getMissionScores() ,"src/main/resources/data/users.json");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
