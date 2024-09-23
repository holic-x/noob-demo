package com.noob.design.strategy.computer;

public class MyComputer {
    public static void main(String[] args) {
        int a=10,b=2;
        String operator = "+";
        if(operator.equals("+")){
            System.out.println(a+b);
        }else if(operator.equals("-")){
            System.out.println(a-b);
        }else if(operator.equals("*")){
            System.out.println(a*b);
        }else if(operator.equals("%")){
            System.out.println(a%b);
        }else{
            throw new RuntimeException("指定操作符异常");
        }
    }
}
