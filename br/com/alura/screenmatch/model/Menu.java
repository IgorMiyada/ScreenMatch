package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.excession.MovieNotFoundException;
import br.com.alura.screenmatch.excession.PasswordException;

import java.io.IOException;
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
                    User user = new User(userName,email,password,confirmPassword);
                    FileOperations.createUserFolder(user.getUserName());
                    FileOperations.saveUserData(user);
                    System.out.println("User Created");
                    break;
                case 2:
                    System.out.println("Enter the user name : ");
                    String loginUserName = sc.nextLine();
                    System.out.println("Enter the userPassword : ");
                    String loginPassword = sc.nextLine();
                    if(FileOperations.loginUser(loginUserName,loginPassword)){
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
                    System.out.println("Enter the wished movie: ");
                    String titleName = sc.nextLine();
                    SearchOmdbApi.searchTitle(titleName);
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

                    break;
                case 8:
                    System.out.println("End application");break;
                default :
                    System.out.println("Invalida option! Enter other number ");
                    break;
            }
        }catch(PasswordException | IOException | MovieNotFoundException e){
            System.err.println(e.getMessage());
        }

    }
}
