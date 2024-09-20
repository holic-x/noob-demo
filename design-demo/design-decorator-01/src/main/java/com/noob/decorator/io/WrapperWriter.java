package com.noob.decorator.io;

public abstract class WrapperWriter implements Writer {

    private Writer writer;

    public WrapperWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void write(String str) {
        // 调用原方法
        this.writer.write(str);
        // 扩展方法
        System.out.println("decorate write " + str);
    }

}
