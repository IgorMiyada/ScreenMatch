package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Menu;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();

        int number = 0;
        while (number!=7){
            System.out.println(menu);
            System.out.println("Enter the option number : ");
            number = sc.nextInt();
            menu.opcoesMenu(number);
        }
    }
}
