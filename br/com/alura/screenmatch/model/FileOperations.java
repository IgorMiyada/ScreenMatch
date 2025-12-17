package br.com.alura.screenmatch.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileOperations {



    private static  Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();


    public static boolean generateFile(String fileName, String userName) throws IOException{
        fileName = fileName+".json";
        Path filePath = Paths.get(SystemVariables.getFolderPath(),userName,fileName);

        if(Files.exists(filePath)){
            return false;
        }else{
            createUserFolder(userName);
        }
        Files.createFile(filePath);
        return true;

    }

    public static void createUserFolder(String userName){
        try{
            Path path = Paths.get(SystemVariables.getFolderPath()+userName);
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

        try(BufferedReader bf = new BufferedReader(new FileReader(SystemVariables.getUsersData().toFile()))){
            jsonArray = JsonParser.parseReader(bf).getAsJsonArray();
        }catch (FileNotFoundException e){
            Files.createFile(SystemVariables.getUsersData());
            jsonArray = new JsonArray();
        }catch (IllegalStateException e){
            jsonArray = new JsonArray();
        }

        JsonObject jsonObject = gson.toJsonTree(user).getAsJsonObject();
        jsonArray.add(jsonObject);

        BufferedWriter bw = new BufferedWriter(new FileWriter(SystemVariables.getUsersData().toFile()));
        bw.write(gson.toJson(jsonArray));
        bw.close();

    }

    public static boolean loginUser(String userName, String password)throws IOException{
        TypeToken<List<User>> userListType = new TypeToken<List<User>>() {};

        try(BufferedReader bf = new BufferedReader(new FileReader(SystemVariables.getUsersData().toFile()))){
            List<User> users = gson.fromJson(bf,userListType);

            for(User user : users){
                if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                    user.setUserlogged(true);
                    Session.login(user);
                    FileWriter fileWriter = new FileWriter(SystemVariables.getUsersData().toFile());
                    gson.toJson(users,fileWriter);
                    fileWriter.close();
                    return true;
                }
            }
            return false;
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("There are no users created. ");
        }
    }

    public static List<String> readFile(String playlistName,User user)throws IOException{
        Path path = Paths.get(SystemVariables.FOLDER_PATH,user.getUserName(),playlistName+".json");
        if(!Files.exists(path)){
            throw new FileNotFoundException("Could not find the playlist");
        }

        return Files.readAllLines(path);

    }



}
