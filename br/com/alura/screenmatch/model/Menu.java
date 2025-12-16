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
                3.Search for a movie
                4.Register a movie list
                5.Register a movie
                6.End application""";
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
                    break;
                case 2:
                    System.out.println("Enter the user name : ");
                    String loginUserName = sc.nextLine();
                    System.out.println("Enter the userPassword : ");
                    String loginPassword = sc.nextLine();
                    FileOperations.loginUser(loginUserName,loginPassword);
                    break;
                case 3:
                    System.out.println("Enter the wished movie: ");
                    String titleName = sc.nextLine();
                    SearchOmdbApi.searchTitle(titleName);
                    break;
                case 4:
                    try{
                        if(Session.isUserLogged()){
                            System.out.println("Insert the playlist name : ");
                            PlaylistOperations.createPlaylist(Session.getUserLogged(),"teste");
                        }
                    }catch (NullPointerException e){
                        System.out.println("There is no user logged.");
                    }
                    break;
                case 5:
                    System.out.printf("Add a movie to playlist");
                    break;
                case 6:
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
