package com.demo.guessnum;

import java.util.Random;
import java.util.Scanner;

/**
 * Guess Number Game
 */
public class GuessNumGame {

    /**
     * random gen number
     * @param bound
     * @return
     */
    private static int randomNum(int bound){
        Random random = new Random();
        // Randomly generates integer data in the specified range
        return random.nextInt(100);
    }

    /**
     * Verify the correctness of parameters
     * @param guessNum
     * @param resNum
     * @return
     */
    private static boolean vaildGuess(int guessNum,int resNum){
        // Compare data size to prompt
        if(guessNum==resNum){
            System.out.println("You win!!");
            return true;
        }else if(guessNum<resNum){
            System.out.println("Too low! Try again:");
        }else if(guessNum>resNum){
            System.out.println("Too high! Try again:");
        }
        // Other conditions are abnormal
        return false;
    }

    private static void playGame(){
        Scanner scanner = new Scanner(System.in);
        int playTimes = 5; // The default is 5 attempts
        // Call method to generate data randomly
        int resNum = randomNum(100);
        boolean vaildRes = false;
        System.out.println("I'm thinking of number between 1 and 100. Guess what it is:");
        for (int i = 1; i <= playTimes; i++) {
            int guessNum = scanner.nextInt();
            // Call method validation
            if(vaildGuess(guessNum,resNum)){
                // Guess the number correctly
                vaildRes = true;
                break;
            }
        }
        // Verify the correctness of the results
        if(!vaildRes){
            System.out.println("You lost! The number was "+resNum+".");
        }
    }

    public static void main(String[] args) {
        GuessNumGame.playGame();
    }

}
