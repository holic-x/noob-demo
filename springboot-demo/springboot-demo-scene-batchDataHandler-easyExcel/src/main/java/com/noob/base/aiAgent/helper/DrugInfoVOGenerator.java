package com.noob.base.aiAgent.helper;

import com.noob.base.aiAgent.drugInfoCrawl.entity.vo.DrugInfoVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 药品数据生成器，用于生成测试数据
 */
public class DrugInfoVOGenerator {

    private static final Random random = new Random();
    private static final String[] PRDT_NAMES = {"阿司匹林肠溶片", "布洛芬缓释胶囊", "盐酸氨溴索口服溶液", 
                                              "头孢克肟分散片", "阿莫西林胶囊", "盐酸左西替利嗪片"};
    private static final String[] EN_NAMES = {"Aspirin Enteric-coated Tablets", "Ibuprofen Sustained-release Capsules",
                                             "Ambroxol Hydrochloride Oral Solution", "Cefixime Dispersible Tablets",
                                             "Amoxicillin Capsules", "Levocetirizine Hydrochloride Tablets"};
    private static final String[] MANUFACTURERS = {"北京同仁堂制药有限公司", "上海医药集团股份有限公司",
                                                  "广州白云山医药集团股份有限公司", "扬子江药业集团有限公司"};
    private static final String[] DOSAGE_FORMS = {"片剂", "胶囊剂", "口服溶液", "分散片", "注射剂"};

    /**
     * 生成指定数量的药品信息列表
     */
    public static List<DrugInfoVO> generateDrugList(int count) {
        List<DrugInfoVO> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            DrugInfoVO dto = new DrugInfoVO();
            dto.setId(i + 1);
            dto.setPrdtName(PRDT_NAMES[random.nextInt(PRDT_NAMES.length)]);
            dto.setEnName(EN_NAMES[random.nextInt(EN_NAMES.length)]);
            dto.setCommodityName("商品名" + (i + 1));
            dto.setApproveNum("国药准字H" + (20000000 + i));
            dto.setDosageForm(DOSAGE_FORMS[random.nextInt(DOSAGE_FORMS.length)]);
            dto.setSpec((random.nextInt(10) + 1) + "mg*" + (random.nextInt(30) + 10) + "片");
            dto.setManufacturer(MANUFACTURERS[random.nextInt(MANUFACTURERS.length)]);
            dto.setApproveDate(new Date(System.currentTimeMillis() - random.nextInt(365 * 24 * 60 * 60 * 1000)));
            list.add(dto);
        }
        return list;
    }
}
    