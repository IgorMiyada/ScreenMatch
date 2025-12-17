package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        int number = 0;
        while (number!=8){
            System.out.println(menu);
            try{
                System.out.println("Enter the option number : ");
                number = sc.nextInt();
                sc.nextLine();
                menu.opcoesMenu(number);
                Thread.sleep(2000);
            }catch (InputMismatchException e) {
                System.err.println("Please, enter a number");
                sc.nextLine();
            } catch (InterruptedException e){
                System.err.println(e.getMessage());
            }

        }
    }
}
