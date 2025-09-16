package com.noob.base.aiAgent.helper;

import com.noob.base.aiAgent.constant.PharmaceuticalTaskConstant;
import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(JUnit4ClassRunner.class)
public class DrugInfoDTOHelperTest {

    // 生成DTO
    public DrugInfoDTO createDrugInfoDTO(String code, String itemStatus) {
        DrugInfoDTO dto = new DrugInfoDTO();
        dto.setItemStatus(itemStatus);
        dto.setCode(code);
        return dto;
    }


    // --- 覆盖 collectValidCodes ---

    @Test
    public void testCollectValidCodes_nullList() {
        List<String> result = DrugInfoDTOHelper.collectValidCodes(null);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testCollectValidCodes_emptyList() {
        List<String> result = DrugInfoDTOHelper.collectValidCodes(new ArrayList<DrugInfoDTO>());
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testCollectValidCodes_variousCases() {
        List<DrugInfoDTO> list = Arrays.asList(
                null,
                createDrugInfoDTO(null, null),
                createDrugInfoDTO("", null),
                createDrugInfoDTO("   ", null),
                createDrugInfoDTO("abc", null),
                createDrugInfoDTO("def", null)
        );
        List<String> result = DrugInfoDTOHelper.collectValidCodes(list);
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains("abc"));
        Assert.assertTrue(result.contains("def"));
    }

    // --- 覆盖 validDrugInfoList ---

    @Test
    public void testValidDrugInfoList_nullOrEmpty() {
        TaskResultValidation<DrugInfoDTO> result1 = DrugInfoDTOHelper.validDrugInfoList(null);
        Assert.assertFalse(result1.isSuccess());
        Assert.assertTrue(result1.getMessage().contains("解析获取的药品信息列表为空"));

        TaskResultValidation<DrugInfoDTO> result2 = DrugInfoDTOHelper.validDrugInfoList(Collections.<DrugInfoDTO>emptyList());
        Assert.assertFalse(result2.isSuccess());
        Assert.assertTrue(result2.getMessage().contains("解析获取的药品信息列表为空"));
    }

    @Test
    public void testValidDrugInfoList_allSuccess() {
        List<DrugInfoDTO> list = Arrays.asList(
                createDrugInfoDTO("c1", PharmaceuticalTaskConstant.ITEM_STATUS_SUCCESS),
                createDrugInfoDTO("c2", PharmaceuticalTaskConstant.ITEM_STATUS_SUCCESS)
        );
        TaskResultValidation<DrugInfoDTO> result = DrugInfoDTOHelper.validDrugInfoList(list);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(2, result.getSuccessList().size());
        Assert.assertTrue(result.getMessage().contains("成功【2】条"));
    }

    @Test
    public void testValidDrugInfoList_allFail() {
        List<DrugInfoDTO> list = Arrays.asList(
                createDrugInfoDTO("c1", PharmaceuticalTaskConstant.ITEM_STATUS_FAIL),
                createDrugInfoDTO("c2", PharmaceuticalTaskConstant.ITEM_STATUS_FAIL)
        );
        TaskResultValidation<DrugInfoDTO> result = DrugInfoDTOHelper.validDrugInfoList(list);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals(2, result.getFailList().size());
        Assert.assertTrue(result.getMessage().contains("失败【2】条"));
    }

    @Test
    public void testValidDrugInfoList_allUnknown() {
        List<DrugInfoDTO> list = Arrays.asList(
                createDrugInfoDTO("c1", "UNKNOWN"),
                createDrugInfoDTO("c2", "???")
        );
        TaskResultValidation<DrugInfoDTO> result = DrugInfoDTOHelper.validDrugInfoList(list);
        Assert.assertFalse(result.isSuccess());
        Assert.assertTrue(result.getMessage().contains("异常状态【2】条"));
    }

    @Test
    public void testValidDrugInfoList_partialSuccess() {
        List<DrugInfoDTO> list = Arrays.asList(
                createDrugInfoDTO("c1", PharmaceuticalTaskConstant.ITEM_STATUS_SUCCESS),
                createDrugInfoDTO("c2", PharmaceuticalTaskConstant.ITEM_STATUS_FAIL),
                createDrugInfoDTO("c3", "UNKNOWN")
        );
        TaskResultValidation<DrugInfoDTO> result = DrugInfoDTOHelper.validDrugInfoList(list);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(1, result.getSuccessList().size());
        Assert.assertEquals(1, result.getFailList().size());
        Assert.assertTrue(result.getMessage().contains("失败状态记录【药品本位码】数据：c2"));
        Assert.assertTrue(result.getMessage().contains("未知状态记录【药品本位码】数据：c3"));
    }

    @Test
    public void testValidDrugInfoList_partialSuccess_noCodes() {
        // fail/unknown 没有有效code
        List<DrugInfoDTO> list = Arrays.asList(
                createDrugInfoDTO("c1", PharmaceuticalTaskConstant.ITEM_STATUS_SUCCESS),
                createDrugInfoDTO("   ", PharmaceuticalTaskConstant.ITEM_STATUS_FAIL),
                createDrugInfoDTO(null, "UNKNOWN")
        );
        TaskResultValidation<DrugInfoDTO> result = DrugInfoDTOHelper.validDrugInfoList(list);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(1, result.getSuccessList().size());
        Assert.assertEquals(1, result.getFailList().size());
        Assert.assertFalse(result.getMessage().contains("失败状态记录【药品本位码】数据："));
        Assert.assertFalse(result.getMessage().contains("未知状态记录【药品本位码】数据："));
    }
}