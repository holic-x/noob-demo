package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.strategy;

public class Calculator {

    // ① 定义抽象策略
    static interface  Operator{
        public int oper(int a,int b);
    }

    // ② 定义具体策略（例如此处设定加减乘除算法策略）
    static class OperatorAdd implements Operator{
        @Override
        public int oper(int a, int b) {
            return a+b;
        }
    }
    static class OperatorSub implements Operator{
        @Override
        public int oper(int a, int b) {
            return a-b;
        }
    }
    static class OperatorMul implements Operator{
        @Override
        public int oper(int a, int b) {
            return a*b;
        }
    }
    static class OperatorDiv implements Operator{
        @Override
        public int oper(int a, int b) {
            return a/b;
        }
    }

    // ③ 定义环境(根据不同的环境调用不同的算法策略)
    static class Context{
        // 定义策略
        private Operator operator;

        // 初始化策略（构造器）
        Context(Operator operator){
            this.operator = operator;
        }

        // 执行实际逻辑（调用策略）
        public int doOperator(int a,int b){
            return operator.oper(a,b);
        }
    }

    public static void main(String[] args) {
        // 初始化算法策略、环境
        OperatorAdd add = new OperatorAdd();
        Context context = new Context(add);
        System.out.println(context.doOperator(1,1));
    }

}
