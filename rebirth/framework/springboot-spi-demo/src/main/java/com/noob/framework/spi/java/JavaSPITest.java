package com.noob.framework.spi.java;

import java.util.ServiceLoader;

// JavaSPI机制测试
public class JavaSPITest {
    public static void main(String[] args) {
        // 加载所有的实现并执行
        ServiceLoader<Video> serviceLoader = ServiceLoader.load(Video.class);
        for (Video video : serviceLoader) {
            System.out.println(video);
            // 例如此处想要摘选TencentVideo
            if(TencentVideo.class.isInstance(video)){
                TencentVideo tencentVideo = (TencentVideo) video;
                tencentVideo.play();
            }
        }
    }
}
