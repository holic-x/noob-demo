package com.noob.decorator.io;

public class TextWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("text writer");
    }
}
