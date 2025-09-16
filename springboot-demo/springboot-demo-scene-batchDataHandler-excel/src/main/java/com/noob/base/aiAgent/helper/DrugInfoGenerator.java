package com.noob.base.aiAgent.helper;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 药品信息随机生成器
 * - 生成指定条数的药品信息列表
 */
public class DrugInfoGenerator {
    // 药品相关随机数据
    private static final String[] DOSAGE_FORMS = {"片剂", "注射剂", "胶囊剂", "颗粒剂", "膏剂", "溶液剂"};
    private static final String[] SIZES = {"0.1g", "0.25g", "0.5g", "1g", "2g", "5ml", "10ml", "20ml"};
    private static final String[] HOLDER = {"张小白", "王小五", "里斯", "赵六", "神七"};
    private static final String[] HOLDER_ADDRESS = {"广东深圳", "广东广州", "上海浦东", "江西景德镇", "甘肃兰州", "浙江杭州", "北京"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        // 生成1000条测试数据并导出
        int count = 1000;
        String outputPath = "drug_info_list.json";

        List<DrugInfoDTO> drugList = generateDrugList(count);
        boolean success = exportToJson(drugList, outputPath);

        if (success) {
            System.out.println("成功生成" + count + "条数据，已保存至：" + new File(outputPath).getAbsolutePath());
        } else {
            System.err.println("数据导出失败");
        }
    }

    /**
     * 生成指定数量的DrugInfoDTO列表
     */
    public static List<DrugInfoDTO> generateDrugList(int count) {
        List<DrugInfoDTO> drugList = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            DrugInfoDTO drug = new DrugInfoDTO();
            // 生成模拟数据
            drug.setItemStatus("SUCCESS");
            drug.setApproveNum("国药准字H2023" + String.format("%04d", i + 1));
            drug.setPrdtName("测试药品" + (i + 1));
            drug.setEnName("Test Drug " + (i + 1));
            drug.setCommodityName("商品名" + (i + 1));
            // 随机选择剂型和规格
            drug.setDosageForm(DOSAGE_FORMS[random.nextInt(DOSAGE_FORMS.length)]);
            drug.setSize(SIZES[random.nextInt(SIZES.length)]);
            drug.setOwner(HOLDER[random.nextInt(HOLDER.length)]);
            drug.setOwnerLocation(HOLDER_ADDRESS[random.nextInt(HOLDER_ADDRESS.length)]);

            drugList.add(drug);
        }

        return drugList;
    }

    /**
     * 将DrugInfoDTO列表导出为JSON文件（UTF-8编码）
     */
    public static boolean exportToJson(List<DrugInfoDTO> drugList, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();

        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File(filePath), JsonEncoding.UTF8)) {
            // 格式化输出，便于阅读
            jsonGenerator.useDefaultPrettyPrinter();
            // 写入列表数据
            objectMapper.writeValue(jsonGenerator, drugList);
            return true;
        } catch (IOException e) {
            System.err.println("JSON导出错误：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
    