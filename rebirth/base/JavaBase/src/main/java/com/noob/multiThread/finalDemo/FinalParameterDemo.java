package com.noob.multiThread.finalDemo;




// final
public class FinalParameterDemo {

    interface Printer{
        void print();
    }
    
    // 定义信息打印方法
    public static void printMessage(final String message){
        Printer printer = new Printer() {
            @Override
            public void print() {
                // 访问final参数
                System.out.println(message);
            }
        };
        // 执行方法
        printer.print();
    }

    public static void main(String[] args) {
        printMessage("Hello World");
    }

}
