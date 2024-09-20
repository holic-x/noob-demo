package com.noob.decorator.io;

public class MediaWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("media writer");
    }
}
