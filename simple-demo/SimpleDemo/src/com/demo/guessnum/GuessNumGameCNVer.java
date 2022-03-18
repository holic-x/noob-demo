package com.demo.guessnum;

import java.util.Random;
import java.util.Scanner;

/**
 * 猜数游戏
 */
public class GuessNumGameCNVer {

    /**
     * 随即生成整型数据
     * @param bound
     * @return
     */
    private static int randomNum(int bound){
        Random random = new Random();
        // 随即生成指定范围的整型数据
        return random.nextInt(100);
    }

    /**
     * 验证参数正确性
     * @param guessNum
     * @param resNum
     * @return
     */
    private static boolean vaildGuess(int guessNum,int resNum){
        // 比较数据大小进行提示
        if(guessNum==resNum){
            System.out.println("You win!!");
            return true;
        }else if(guessNum<resNum){
            System.out.println("Too low! Try again:");
        }else if(guessNum>resNum){
            System.out.println("Too high! Try again:");
        }
        // 其余情况异常
        return false;
    }

    private static void playGame(){
        Scanner scanner = new Scanner(System.in);
//        System.out.println("请指定用户尝试次数");
//        int playTimes = scanner.nextInt();
        int playTimes = 5; // 默认尝试5次
        // 调用方法随机生成数据
        int resNum = randomNum(100);
        boolean vaildRes = false;
        System.out.println("I'm thinking of number between 1 and 100. Guess what it is:");
        for (int i = 1; i <= playTimes; i++) {
//            System.out.println("当前第"+i+"次尝试，请输入你选择的数字");
            int guessNum = scanner.nextInt();
            // 调用方法验证
            if(vaildGuess(guessNum,resNum)){
                // 猜数正确
                vaildRes = true;
                break;
            }
        }
        // 验证结果正确性
        if(!vaildRes){
            System.out.println("You lost! The number was "+resNum+".");
        }

    }

    public static void main(String[] args) {
        GuessNumGameCNVer.playGame();
    }

}
