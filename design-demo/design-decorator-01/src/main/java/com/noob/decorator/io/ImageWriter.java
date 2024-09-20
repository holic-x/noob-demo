package com.noob.decorator.io;

public class ImageWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("image writer");
    }
}
