package com.noob.rpc.simple.compress;

/**
 * 对象工厂方法：根据指定类型返回压缩机制
 */
public class CompressorFactory {
    public static Compressor get(byte extraInfo) {
        switch (extraInfo & 24) {
            case 0x0:
                return new SnappyCompressor();
            default:
                return new SnappyCompressor();
        }
    }
}
