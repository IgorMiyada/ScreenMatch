package br.com.alura.screenmatch.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileOperations {

    private static final String FOLDER_PATH = "C:\\___\\___\\___\\ScreenMatchUsers\\";

    private static final Path USERS_DATA = Paths.get("usersData.json");

    private static  Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();


    public static boolean generateFile(String fileName, String userName) throws IOException{
        fileName = fileName+".json";
        Path filePath = Paths.get(FOLDER_PATH,userName,fileName);

        if(Files.exists(filePath)){
            return false;
        }
        Files.createFile(filePath);
        return true;

    }

    public static void createUserFolder(String userName){
        try{
            Path path = Paths.get(FOLDER_PATH+userName);
            if(Files.exists(path)){
                return;
            }
            Files.createDirectories(path);
        }catch (IOException e){
            System.err.println("Error trying to create folder. " + e.getMessage());
        }
    }

    public static void saveUserData(User user)throws IOException{
        JsonArray jsonArray;

        try(BufferedReader bf = new BufferedReader(new FileReader(USERS_DATA.toFile()))){
            jsonArray = JsonParser.parseReader(bf).getAsJsonArray();
        }catch (FileNotFoundException e){
            Files.createFile(USERS_DATA);
            jsonArray = new JsonArray();
        }catch (IllegalStateException e){
            jsonArray = new JsonArray();
        }

        JsonObject jsonObject = gson.toJsonTree(user).getAsJsonObject();
        jsonArray.add(jsonObject);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_DATA.toFile()))){
            bw.write(gson.toJson(jsonArray));
        }
    }

    public static boolean loginUser(String userName, String password)throws IOException{
        TypeToken<List<User>> userListType = new TypeToken<List<User>>() {};

        try(BufferedReader bf = new BufferedReader(new FileReader(USERS_DATA.toFile()))){
            List<User> users = gson.fromJson(bf,userListType);

            for(User user : users){
                if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                    System.out.println("User logged in");
                    user.setUserlogged(true);
                    Session.login(user);
                    FileWriter fileWriter = new FileWriter(USERS_DATA.toFile());
                    gson.toJson(users,fileWriter);
                    fileWriter.close();
                    return true;
                }
            }
        }catch (FileNotFoundException e){
            System.err.printf("There are no users created. " + e.getMessage());
        }
        return false;
    }


}
