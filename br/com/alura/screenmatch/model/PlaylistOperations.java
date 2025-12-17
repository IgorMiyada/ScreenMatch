package br.com.alura.screenmatch.model;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaylistOperations {


    public static void createPlaylist(User user, String playlistName)  {
        try{
            if(FileOperations.generateFile(playlistName, user.getUserName())){
                System.out.println("Playlist created");
            }else{
                System.out.println("This playlist already exists");
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

    public static void addMovieToPlaylist(String playlistName, String movieName, User user) throws IOException {
        Path fileName = Paths.get(SystemVariables.getFolderPath(),user.getUserName(),playlistName+".json");

        if(!Files.exists(fileName)){
            throw new FileNotFoundException("There is no playlist created");
        }

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        String json = SearchOmdbApi.searchTitle(movieName);
        OmdbTitle omdbTitle = gson.fromJson(json, OmdbTitle.class);

        JsonArray jsonArray;

        try(BufferedReader bf = new BufferedReader(new FileReader(fileName.toFile()))){
            jsonArray = JsonParser.parseReader(bf).getAsJsonArray();
        }catch (IllegalStateException e){
            jsonArray = new JsonArray();
        }

        JsonObject jsonObject = gson.toJsonTree(omdbTitle).getAsJsonObject();
        jsonArray.add(jsonObject);

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.toFile()));
        bw.write(gson.toJson(jsonArray));
        bw.close();

    }


}
