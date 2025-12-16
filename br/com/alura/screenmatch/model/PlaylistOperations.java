package br.com.alura.screenmatch.model;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaylistOperations {


    public static void createPlaylist(User user, String playlistName)  {
        FileOperations.createUserFolder(user.getUserName());
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


}
