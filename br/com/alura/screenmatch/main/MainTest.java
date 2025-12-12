package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Menu;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();

        System.out.println(menu);
        System.out.println("Enter the option number : ");
        int number = sc.nextInt();
        menu.opcoesMenu(number);
    }
}
