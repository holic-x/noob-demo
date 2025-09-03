package com.noob.base.batchDataHandler.poiDoc;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.util.ArrayList;

/**
 * 业务数据处理类
 * 职责：生成模拟业务数据、读取图片（含占位图逻辑）、处理数据转换
 * 特点：连接业务逻辑和底层工具，不直接操作Word文档
 */
public class BusinessDataProcessor {

    /**
     * 生成模拟业务数据（含测试图片）
     *
     * @param totalGroups 总业务组数（每个组对应一个二级标题下的三级核查项集合）
     * @param imageDir    图片存放目录
     * @param imgPrefix   图片文件名前缀
     * @param imgSuffix   图片文件后缀
     * @return 业务数据列表（BusinessImageDTO集合）
     */
    public static List<BusinessImageDTO> generateMockData(int totalGroups, String imageDir,
                                                          String imgPrefix, String imgSuffix) {
        // 每组二级标题下有3个三级核查项，每个核查项3张图，计算总图片数
        int totalCheckItems = totalGroups * 3; // 总三级核查项数
        int totalImages = totalCheckItems * 3; // 总图片数（每个核查项3张）
        generateTestImages(imageDir, imgPrefix, imgSuffix, totalImages);

        // 生成DTO数据列表（按一级→二级→三级结构填充）
        return IntStream.range(0, totalGroups)
                .mapToObj(secondLevelIndex -> {
                    BusinessImageDTO dto = new BusinessImageDTO();

                    // 一级标题：每10个二级标题递增一级（如"1-1...", "1-2...", "2-1..."）
                    int firstLevel = secondLevelIndex / 10 + 1;
                    int secondLevelInFirst = secondLevelIndex % 10 + 1;
                    /*
                    dto.setFirstLevelTitle(String.format(
                            "%d-%d 持续督导工作计划及实施方案",
                            firstLevel,
                            secondLevelInFirst
                    ));

                     */

                    // 二级标题：网站名称（循环使用4个网站）
                    String[] websites = {"百度", "谷歌", "必应", "搜狗"};
                    int websiteIndex = secondLevelIndex % 4;
                    dto.setWebsiteName(String.format(
                            "%d.%s",
                            websiteIndex + 1,
                            websites[websiteIndex]
                    ));

                    // 生成当前二级标题下的3个三级核查项
                    List<CheckItem> checkItems = new ArrayList<>();
                    for (int checkItemIdx = 0; checkItemIdx < 3; checkItemIdx++) {
                        // 三级核查项标题（如"1.1.1 【测试】-【测试内容1】"）
                        String checkItemTitle = String.format(
                                "%d.%d.%d 【测试】-【测试内容%d】",
                                websiteIndex + 1,
                                secondLevelInFirst,
                                checkItemIdx + 1,
                                secondLevelIndex * 3 + checkItemIdx + 1
                        );
                        CheckItem checkItem = new CheckItem(checkItemTitle);

                        // 核查详情子项（3条示例）
                        /*
                        checkItem.setCheckDetails(Arrays.asList(
                                String.format("核查项1：网站备案信息完整（%d）", checkItemIdx + 1),
                                String.format("核查项2：ICP许可证在有效期内（%d）", checkItemIdx + 1),
                                String.format("核查项3：联系方式与备案一致（%d）", checkItemIdx + 1)
                        ));
                         */

                        // 图片路径列表（每个三级核查项3张图）
                        int imageStartIdx = (secondLevelIndex * 3 + checkItemIdx) * 3 + 1;
                        checkItem.setImagePaths(Arrays.asList(
                                imageDir + imgPrefix + imageStartIdx + imgSuffix,
                                imageDir + imgPrefix + (imageStartIdx + 1) + imgSuffix,
                                imageDir + imgPrefix + (imageStartIdx + 2) + imgSuffix
                        ));

                        checkItems.add(checkItem);
                    }

                    dto.setCheckItems(checkItems);
                    dto.setProcessSuccess(false); // 初始未处理
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * 生成测试用的空白图片（用于测试环境）
     * （保持不变，兼容多图片场景）
     */
    private static void generateTestImages(String imageDir, String prefix, String suffix, int count) {
        try {
            java.io.File dir = new java.io.File(imageDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 1x1像素空白PNG字节数组（固定格式）
            byte[] blankImage = {
                    (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A, 0x00, 0x00, 0x00, 0x0D,
                    0x49, 0x48, 0x44, 0x52, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x01,
                    0x08, 0x06, 0x00, 0x00, 0x00, 0x1F, 0x15, (byte) 0xC4, (byte) 0x89, 0x00, 0x00, 0x00,
                    0x0A, 0x49, 0x44, 0x41, 0x54, 0x78, (byte) 0x9C, 0x63, 0x00, 0x01, 0x00, 0x00,
                    0x05, 0x00, 0x01, 0x0D, 0x0A, 0x2D, (byte) 0xB4, 0x00, 0x00, 0x00, 0x00, 0x49,
                    0x45, 0x4E, 0x44, (byte) 0xAE, 0x42, 0x60, (byte) 0x82
            };

            for (int i = 1; i <= count; i++) {
                java.io.File imgFile = new java.io.File(imageDir + prefix + i + suffix);
                if (!imgFile.exists()) {
                    try (java.io.FileOutputStream fos = new java.io.FileOutputStream(imgFile)) {
                        fos.write(blankImage);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("生成测试图片失败：" + e.getMessage());
        }
    }

    /**
     * 批量读取图片字节数组（适配三级核查项结构，带红底占位图逻辑）
     *
     * @param dtoList DTO对象列表
     * @param width   占位图宽度（像素）
     * @param height  占位图高度（像素）
     */
    public static void batchReadImageWithPlaceholder(List<BusinessImageDTO> dtoList, int width, int height) {
        for (BusinessImageDTO dto : dtoList) {
            try {
                // 校验三级核查项列表
                if (dto.getCheckItems() == null || dto.getCheckItems().isEmpty()) {
                    throw new IllegalArgumentException("三级核查项列表为空");
                }

                // 遍历每个三级核查项，读取其关联的图片
                for (CheckItem checkItem : dto.getCheckItems()) {
                    List<String> imagePaths = checkItem.getImagePaths();
                    if (imagePaths == null || imagePaths.isEmpty()) {
                        throw new IllegalArgumentException("核查项图片路径为空：" + checkItem.getTitle());
                    }

                    // 读取每张图片（失败则用占位图）
                    List<byte[]> imageBytesList = imagePaths.stream()
                            .map(imgPath -> {
                                try {
                                    java.io.File imgFile = new java.io.File(imgPath);
                                    if (!imgFile.exists() || !imgFile.isFile()) {
                                        throw new java.io.FileNotFoundException("图片不存在：" + imgPath);
                                    }
                                    return Files.readAllBytes(Paths.get(imgPath));
                                } catch (Exception e) {
                                    // 单张图片失败→生成占位图
                                    try {
                                        System.err.println("图片处理失败（" + imgPath + "）：" + e.getMessage() + "，使用占位图");
                                        return ImagePlaceholderUtil.generateRedPlaceholder(width, height);
                                    } catch (Exception ex) {
                                        throw new RuntimeException("占位图生成失败：" + ex.getMessage());
                                    }
                                }
                            })
                            .collect(Collectors.toList());

                    checkItem.setImageBytesList(imageBytesList);
                }

                // 所有核查项处理完成（含占位图）
                dto.setProcessSuccess(true);
            } catch (Exception e) {
                dto.setProcessSuccess(false);
                System.err.println("DTO处理失败（网站：" + dto.getWebsiteName() + "）：" + e.getMessage());
            }
        }
    }
}