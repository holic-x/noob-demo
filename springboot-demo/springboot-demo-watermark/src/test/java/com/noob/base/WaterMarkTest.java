package com.noob.base;

import com.noob.base.bak.ImageMarkLogoUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

//@SpringBootTest
public class WaterMarkTest {

    private List<String> imageTypeList = Arrays.asList("png", "jpg", "jpeg", "gif","bmp");

    @Test
    public void test() {
        String sourcePath = "D:\\Desktop\\test\\watermark\\";
        String targetPath = "D:\\Desktop\\test\\watermark\\target\\";
        // 设定要加水印的源文件
        String srcImgPath = sourcePath + "test.png";
        // 设定水印文字
        String logoText = "[hello noob]";
        // 设定水印图片文件
        String iconPath = sourcePath + "icon.png";

        // 设定生成文字水印的目标文件
        String targetTextPath = targetPath + "test_genText1.png";
        String targetTextPath2 = targetPath + "test_genText2.png";

        // 设定生成图片水印的目标文件
        String targetIconPath = targetPath + "test_genIcon1.png";
        String targetIconPath2 = targetPath + "test_genIcon2.png";
        System.out.println("给图片添加水印文字开始...");
        // 给图片添加水印文字

        ImageMarkLogoUtil.markImageByText(logoText, srcImgPath, targetTextPath);

        // 给图片添加水印文字,水印文字旋转-45
        ImageMarkLogoUtil.markImageByText(logoText, srcImgPath, targetTextPath2, -45);

        System.out.println("给图片添加水印文字结束...");
        System.out.println("给图片添加水印图片开始...");
        ImageMarkLogoUtil.setImageMarkOptions(0.3f, 3, 1, null, null);

        // 给图片添加水印图片
        ImageMarkLogoUtil.markImageByIcon(iconPath, srcImgPath, targetIconPath);
        // 给图片添加水印图片,水印图片旋转-45
        ImageMarkLogoUtil.markImageByIcon(iconPath, srcImgPath, targetIconPath2, -45);
        System.out.println("给图片添加水印图片结束...");
    }

}
