package com.noob.decorator.io;

public class WrapperTextWriter extends WrapperWriter {

    public WrapperTextWriter(Writer writer) {
        super(writer);
    }

    @Override
    public void write(String str) {
        super.write(str);
    }
}
