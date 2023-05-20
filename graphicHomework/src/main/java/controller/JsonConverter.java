package controller;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.User;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonConverter {
    public static void putUserDataInFile(String username, String password, String dirFromSrc) throws ParseException {

            JSONObject newUser= new JSONObject();
            String loginValue;

            newUser.put("username", username);
            newUser.put("password", password);
        JSONArray userData= null;
        try {
            userData = getUsersDataInJson(dirFromSrc);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int userIndexInData=getUserIndexInJsonArray(username, dirFromSrc);
            
            
            if(userIndexInData==-1)
                userData.add(newUser);
            else{
                userData.remove(userIndexInData);
                userData.add(newUser);
            }

            try{
                File file=new File(dirFromSrc);
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(userData.toJSONString());  
                fileWriter.flush();  
                fileWriter.close();  
            } catch ( IOException e) {  
                e.printStackTrace();  
            }
    }

    public static void removeUsernameJsonData(String username, String password, String dirFromSrc) throws ParseException{
        JSONArray userData=getUsersDataInJson(dirFromSrc);
        int index=getUserIndexInJsonArray(username, password);
        userData.remove(index);
        try{
            File file=new File(dirFromSrc);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(userData.toJSONString());  
            fileWriter.flush();  
            fileWriter.close();  
        } catch ( IOException e) {  
            e.printStackTrace();  
        }
    }

    public static void fillFormerUsersDatabase(String dirFromSrc) throws ParseException {
        JSONArray usersJsonArray=getUsersDataInJson(dirFromSrc);

        for (int i = 0; i < usersJsonArray.size(); i++) {
            User userUnderRestoration=new User(null, null);
            fillUserInfo(userUnderRestoration, i, usersJsonArray);
            User.addUser(userUnderRestoration);
        }

    }
   
    private static void fillUserInfo(User user,int userIndex, JSONArray jsonData){
        JSONObject UserInJson=(JSONObject) jsonData.get(userIndex);
        user.setUsername(getJsonKeyValue("username", UserInJson));
        String Password=getJsonKeyValue("password", UserInJson);
        user.setPassword(Password);

    }

    private static String getJsonKeyValue(String key, JSONObject jsonObject){
        if(jsonObject.get(key)!=null)
            return jsonObject.get(key).toString();
        else return null;
    }

    private static JSONArray getUsersDataInJson(String dirFromSrc) throws org.json.simple.parser.ParseException {
        JSONArray formerData=new JSONArray();
        try {  
            
            JSONParser jsonParser = new JSONParser();
            Object objjj = jsonParser.parse(new FileReader(dirFromSrc));
            formerData=(JSONArray) objjj;

        } catch (  IOException e) {
            e.printStackTrace();  
        }
        return formerData;
    }

    private static int getUserIndexInJsonArray(String username, String dirFromSrc)  {
        JSONArray jsonDataArray=null;
        try {
            jsonDataArray = getUsersDataInJson(dirFromSrc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonUser;

        for (int i = 0; i < jsonDataArray.size(); i++) {
            jsonUser= (JSONObject)jsonDataArray.get(i);
            if(jsonUser.get("username").toString().equals(username))
                return i;
        }
        return -1;
    }

}

