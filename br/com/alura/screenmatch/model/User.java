package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.excession.PasswordException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String userName;
    private String password;
    private String email;
    private List<TitleList> titleLists;
    private boolean isUserlogged;

    public User(String userName, String email, String password, String confirmPassword) {
        this.userName = userName;
        this.email = email;
        if(!password.equals(confirmPassword)){
            throw new PasswordException("Passwords are not equals");
        }
        checkPassword(password);
        this.password = password;
    }

    public static void checkPassword(String password){
        Pattern specialCharacters = Pattern.compile("[!@#$%&*+/-_]");
        Matcher matcher = specialCharacters.matcher(password);

        if(password.length()<8){
            throw new PasswordException("Password must have at least 8 characters");
        }
        else if(!matcher.find() || password.contains(" ")){
            throw new PasswordException("Password must have special characters and can not have blank spaces");
        }
    }

    public void verifyOldPassword(String password){
        if(!this.password.equals(password)){
            throw new PasswordException("Wrong password, please try again");
        }
    }

    public void addShowList(String listName){
        titleLists.add(new TitleList(listName));
    }

    public String getUserName() {
        return userName;
    }

    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void resetPassword(String password) {
        verifyOldPassword(password);
        checkPassword(password);
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUserlogged() {
        return isUserlogged;
    }
    public void setUserlogged(boolean userlogged) {
        isUserlogged = userlogged;
    }
}
