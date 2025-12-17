package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Menu;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();

        int number = 0;
        while (number!=8){
            System.out.println(menu);
            System.out.println("Enter the option number : ");
            number = sc.nextInt();
            try{
                menu.opcoesMenu(number);
                Thread.sleep(2000);
            }catch (InterruptedException e){
                System.err.println(e.getMessage());
            }

        }
    }
}
