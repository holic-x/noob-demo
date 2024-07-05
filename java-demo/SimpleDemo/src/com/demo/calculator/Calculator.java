package com.demo.calculator;


import java.util.Scanner;

/**
 * @author Xiao long Zu
 * Calculator
 */
public class Calculator {
    /**
     * method used to print a welcome message on the screen
     */
    public static void welcome() {
        System.out.println("Welcome to Xiao long Zu Calculator~");
    }

    /**
     * method to print a menu on the screen that reads the menu item input from the user and returns the user's choice
     */
    public static int menu() {
        // scanner object to take the user input
        Scanner scanner = new Scanner(System.in);

        // showing the menu
        System.out.println("1. +");
        System.out.println("2. -");
        System.out.println("3. /");
        System.out.println("4. *");
        System.out.println("5. %");
        System.out.println("6. Exit");

        // taking the user choice
        System.out.print("==> ");
        int choice = Integer.parseInt(scanner.nextLine());

        // returning the choice
        return choice;
    }

    public static void main(String[] args) {
        // print welcome msg
        Calculator.welcome();

        // scanner object
        Scanner scanner = new Scanner(System.in);

        // taking the inputs in a loop
        while (true) {
            // asking for the menu choice
            int choice = menu();

            // if menu choice is invalid then break
            if (choice < 1 || choice > 5) {
                break;
            }

            // asking for the first number
            System.out.print("Enter first number: ");
            int num1 = Integer.parseInt(scanner.nextLine());

            // asking for the second number
            System.out.print("Enter second number: ");
            int num2 = Integer.parseInt(scanner.nextLine());

            // this will hold the result
            int result = 0;

            // printing the result by validating the operator
            switch (choice) {
                case 1:
                    result = num1 + num2;
                    break;
                case 2:
                    result = num1 - num2;
                    break;
                case 3:
                    result = num1 / num2;
                    break;
                case 4:
                    result = num1 * num2;
                    break;
                case 5:
                    result = num1 % num2;
                    break;
            }

            // printing the result
            System.out.println("Result: " + result);

            // printing a new line
            System.out.println();
        }
    }
}