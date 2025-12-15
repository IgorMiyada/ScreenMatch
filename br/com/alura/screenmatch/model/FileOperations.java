package br.com.alura.screenmatch.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileOperations {

    private static final String FOLDER_PATH = "C:\\Users\\igorm\\Documents\\ScreenMatchUsers\\";

    private static final Path USERS_DATA = Paths.get("usersData.json");

    private static  Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();


    public static void generateFile(String fileName, int fileType, List<Title> titleList) throws IOException {
        String fileExtension =  fileType == 1 ? ".txt" : ".json";
        fileName = fileName+fileExtension;

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            bw.write(gson.toJson(titleList));
        }
    }

    public static void createUserFolder(String userName) throws IOException{
        Path path = Paths.get(FOLDER_PATH+userName);
        Files.createDirectories(path);
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

    public void searchUser(String userName){
        Path path = Paths.get(FOLDER_PATH+userName);

    }


}
