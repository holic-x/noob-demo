package com.noob.rpc.simple.compress;

import java.io.IOException;

/**
 * 压缩相关接口定义（压缩、解压）
 */
public interface Compressor {
    // 压缩对象
    byte[] compress(byte[] array) throws IOException;

    // 解压对象
    byte[] unCompress(byte[] array) throws IOException;
}
