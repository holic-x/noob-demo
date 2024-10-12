package com.noob.design.decorate;

/**
 * 装饰者模式：流的扩展
 */
public class DecorateWriteDemo {
    public static void main(String[] args) {
        // 定义装饰目标
        TextBaseWriter writer = new TextBaseWriter();
        // 定义装饰类
        BufferTextBaseWriter bufferTextBaseWriter = new BufferTextBaseWriter(writer);
        bufferTextBaseWriter.write();
    }
}

// 定义流（接口规范）
interface BaseWriter{
    public void write();
}

// 定义不同的流及操作（例如文本、图片、影像等）
class TextBaseWriter implements BaseWriter{
    @Override
    public void write() {
        System.out.println("i am TextBaseWriter");
    }
}
class PictureBaseWriter implements BaseWriter{
    @Override
    public void write() {
        System.out.println("i am PictureBaseWriter");
    }
}
class VideoBaseWriter implements BaseWriter{
    @Override
    public void write() {
        System.out.println("i am VideoBaseWriter");
    }
}

// 流扩展（在原有的不同处理流基础上需要做功能增强），例如引入buffer（缓冲流）概念等，因此引入装饰者模式进行装饰
class BufferTextBaseWriter implements BaseWriter{
    // 定义装饰目标
    private TextBaseWriter textBaseWriter;

    // 构造函数初始化（接收装饰目标用于初始化）
    public BufferTextBaseWriter(TextBaseWriter textBaseWriter) {
        this.textBaseWriter = textBaseWriter;
    }

    @Override
    public void write() {
        System.out.println("i am decorate start");
        textBaseWriter.write();
        System.out.println("i am decorate end");
    }
}
// 类似地，以此类推，其他的装饰类也是通过接收被装饰对象来实现装饰动作
