package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.decorate;

/**
 * 进阶版装饰者模式（基于流的装饰思路）
 */
public class AdvanceDecorateDemo {


    // ① 定义流接口（接口定义）
    static interface BaseWriter {
        public void write();
    }

    // ② 定义具体流（不同的流：例如字节流、字符流 | 文本流、图像流、视频流）
    static class TextWriter implements BaseWriter {
        @Override
        public void write() {
            System.out.println("text write......");
        }
    }

    static class PictureWrite implements BaseWriter {
        @Override
        public void write() {
            System.out.println("picture write......");
        }
    }

    static class VideoWrite implements BaseWriter {
        @Override
        public void write() {
            System.out.println("video write");
        }
    }

    // ③ 定义装饰类（对流进行装饰，例如引入Buffer概念）
    static class TextBufferWriter implements BaseWriter {

        TextWriter textWriter;

        TextBufferWriter(TextWriter textWriter) {
            this.textWriter = textWriter;
        }

        @Override
        public void write() {
            // 装饰处理
            System.out.println("buffer decorate");
            textWriter.write();
        }
    }

    public static void main(String[] args) {
        TextWriter textWriter = new TextWriter();
        TextBufferWriter textBufferWriter = new TextBufferWriter(textWriter);
        textBufferWriter.write();
    }
}
