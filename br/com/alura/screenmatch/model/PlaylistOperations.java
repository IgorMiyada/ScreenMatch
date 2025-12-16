package br.com.alura.screenmatch.model;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaylistOperations {


    public static void createPlaylist(User user, String playlistName)  {
        FileOperations.createUserFolder(user.getUserName());
        if(FileOperations.generateFile(playlistName, user.getUserName())){
            System.out.println("Playlist created");
        }else{
            System.out.println("This playlist already exists");
        }
    }

    public void addMovieToPlaylist(String movieName){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
//        JsonArray jsonArray;
//
//        try(BufferedReader bf = new BufferedReader(new FileReader())){
//            jsonArray = JsonParser.parseReader(bf).getAsJsonArray();
//        }catch (FileNotFoundException e){
//            Files.createFile();
//            jsonArray = new JsonArray();
//        }catch (IllegalStateException e){
//            jsonArray = new JsonArray();
//        }
//
//        JsonObject jsonObject = gson.toJsonTree().getAsJsonObject();
//        jsonArray.add(jsonObject);
//
//        try(BufferedWriter bw = new BufferedWriter(new FileWriter())){
//            bw.write(gson.toJson(jsonArray));
//        }


        try{
            String json = SearchOmdbApi.searchTitle(movieName);
            OmdbTitle omdbTitle = gson.fromJson(json, OmdbTitle.class);


        }catch (InterruptedException e) {
            System.out.println("Error trying to send the requisition. " + e.getMessage());
        } catch (IOException e){
            System.err.println("Ocurried an error trying to read the file " + e.getMessage());
        }

    }
}
