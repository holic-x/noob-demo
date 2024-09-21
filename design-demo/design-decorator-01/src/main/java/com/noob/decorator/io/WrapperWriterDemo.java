package com.noob.decorator.io;

public class WrapperWriterDemo {
    public static void main(String[] args) {
        WrapperTextWriter wtw = new WrapperTextWriter(new TextWriter());
        wtw.write("Hello World");
    }
}
