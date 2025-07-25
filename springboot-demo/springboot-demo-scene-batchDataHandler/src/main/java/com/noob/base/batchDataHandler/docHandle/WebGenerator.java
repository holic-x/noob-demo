package com.noob.base.batchDataHandler.docHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebGenerator {
    // 存储webKey和webName的映射关系
    private static final List<String[]> WEB_MAPPINGS = new ArrayList<>();
    
    static {
        // 初始化映射关系
        WEB_MAPPINGS.add(new String[]{"baidu", "百度"});
        WEB_MAPPINGS.add(new String[]{"xiaohongshu", "小红书"});
        WEB_MAPPINGS.add(new String[]{"taobao", "淘宝"});
        WEB_MAPPINGS.add(new String[]{"weibo", "微博"});
        WEB_MAPPINGS.add(new String[]{"douyin", "抖音"});
        WEB_MAPPINGS.add(new String[]{"jingdong", "京东"});
        WEB_MAPPINGS.add(new String[]{"zhihu", "知乎"});
    }
    
    /**
     * 随机返回一组webKey和webName的映射
     * @return String数组，0位置为webKey，1位置为webName
     */
    public static String[] getRandomWebMapping() {
        Random random = new Random();
        int index = random.nextInt(WEB_MAPPINGS.size());
        return WEB_MAPPINGS.get(index);
    }
    
    public static void main(String[] args) {
        // 测试随机返回
        for (int i = 0; i < 5; i++) {
            String[] mapping = getRandomWebMapping();
            System.out.println(mapping[0] + "-" + mapping[1]);
        }
    }
}