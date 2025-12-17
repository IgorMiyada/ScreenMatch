package br.com.alura.screenmatch.model;

public class Session {
    private static User userLogged = null;

    public static void login(User user){
        Session.userLogged = user;
    }

    public static User getUserLogged() {
        return userLogged;
    }

    public static boolean isUserLogged(){
        if(userLogged!=null){
            return userLogged.isUserlogged();
        }else{
            System.out.println("No user is logged");
            return false;
        }
    }
}
