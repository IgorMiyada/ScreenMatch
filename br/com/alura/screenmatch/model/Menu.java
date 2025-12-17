package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.excession.MovieNotFoundException;
import br.com.alura.screenmatch.excession.PasswordException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    @Override
    public String toString() {
        return """
                ######################
                Select one of the options :
                1.Create Account
                2.Login
                3.Change save local for playlists(It saves in the project directory by default)
                4.Search for a movie
                5.Register a movie list
                6.Add a movie to a playlist
                7.See a playlist
                8.End application""";
    }

    public void opcoesMenu(int opcaoMenu){
        Scanner sc = new Scanner(System.in);

        try{
            switch (opcaoMenu){
                case 1:
                    System.out.println("Enter the user name : ");
                    String userName = sc.nextLine();
                    System.out.println("Enter your email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password = sc.nextLine();
                    System.out.println("Confirm your password : ");
                    String confirmPassword = sc.nextLine();
                    User user = new User(userName.trim(),email,password.trim(),confirmPassword.trim());
                    FileOperations.createUserFolder(user.getUserName());
                    FileOperations.saveUserData(user);
                    System.out.println("User Created");
                    break;
                case 2:
                    System.out.println("Enter the user name : ");
                    String loginUserName = sc.nextLine();
                    System.out.println("Enter the userPassword : ");
                    String loginPassword = sc.nextLine();
                    if(FileOperations.loginUser(loginUserName.trim(),loginPassword.trim())){
                        System.out.println("Login successful");
                    }else{
                        System.out.println("User don't exist");
                    }
                    break;
                case 3:
                    System.out.println("Enter the folders path you want to save : ");
                    String folderPath = sc.nextLine();
                    SystemVariables.setFolderPath(folderPath);
                    break;
                case 4:
                    Gson gson = new GsonBuilder()
                            .setPrettyPrinting()
                            .create();

                    System.out.println("Enter the wished movie: ");
                    String titleName = sc.nextLine();
                    String show = SearchOmdbApi.searchTitle(titleName);
                    JsonElement jsonElement = JsonParser.parseString(show);
                    System.out.println(gson.toJson(jsonElement));
                    break;
                case 5:
                    if(Session.isUserLogged()){
                        System.out.println("Insert the playlist name : ");
                        String playlistToBeCreated = sc.nextLine();
                        PlaylistOperations.createPlaylist(Session.getUserLogged(),playlistToBeCreated);
                    }

                    break;
                case 6:
                    if(Session.isUserLogged()){
                        System.out.println("Enter the playlist name : ");
                        String playlistName = sc.nextLine();
                        System.out.println("Enter the movie name : ");
                        String movieName = sc.nextLine();
                        PlaylistOperations.addMovieToPlaylist(playlistName,movieName,Session.getUserLogged());
                        System.out.println("Movie added to the playlist");
                    }
                    break;
                case 7:
                    if(Session.isUserLogged()){
                        System.out.println("Enter the playlist you want to see : ");
                        List<String> fileLines =  FileOperations.readFile(sc.nextLine(),Session.getUserLogged());
                        for(String textLine : fileLines){
                            System.out.println(textLine);
                        }
                    }
                    break;
                case 8:
                    System.out.println("End application");break;
                default :
                    System.out.println("Invalid option! Enter other number ");
                    break;
            }
        } catch(PasswordException | IOException | MovieNotFoundException e){
            System.err.println(e.getMessage());
        }

    }
}
