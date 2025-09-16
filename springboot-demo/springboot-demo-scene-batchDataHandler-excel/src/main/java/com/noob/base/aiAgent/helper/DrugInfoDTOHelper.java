package com.noob.base.aiAgent.helper;

import com.noob.base.aiAgent.constant.PharmaceuticalTaskConstant;
import com.noob.base.aiAgent.drugInfoCrawl.entity.dto.DrugInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 药品信息处理辅助工具类
 */
@Slf4j
public class DrugInfoDTOHelper {

    /**
     * 从DrugInfo列表中收集所有非空的code
     *
     * @param drugInfoDTOList 药品信息列表
     * @return 非空code组成的字符串列表
     */
    public static List<String> collectValidCodes(List<DrugInfoDTO> drugInfoDTOList) {
        // 处理空列表情况，避免NPE
        if (drugInfoDTOList == null) {
            return new ArrayList<>();
        }

        // 使用Stream API进行过滤和转换
        return drugInfoDTOList.stream()
                // 过滤掉null的DrugInfo对象
                .filter(drug -> drug != null)
                // 提取code属性
                .map(DrugInfoDTO::getCode)
                // 过滤掉null、空字符串以及纯空格字符串
                .filter(code -> code != null && !code.trim().isEmpty())
                // 收集结果到List
                .collect(Collectors.toList());
    }


    // 校验任务处理结果
    public static TaskResultValidation<DrugInfoDTO> validDrugInfoList(List<DrugInfoDTO> drugInfoDTOList) {

        // 记录列表为空
        if (CollectionUtils.isEmpty(drugInfoDTOList)) {
            // log.info("获取药品信息为空，关联localTaskId=【{}】，fileKey=【{}】跟踪数据解析状态", fileKey, localTaskId);
            return TaskResultValidation.failure("解析获取的药品信息列表为空,无需数据处理");
        }

        // 记录不为空，进一步校验记录条目状态
        List<DrugInfoDTO> successList = new ArrayList<>();
        List<DrugInfoDTO> failList = new ArrayList<>();
        List<DrugInfoDTO> unknownStatusList = new ArrayList<>();

        // 校验任务处理结果
        for (DrugInfoDTO drugInfoDTO : drugInfoDTOList) {
            String itemStatus = drugInfoDTO.getItemStatus();
            if (PharmaceuticalTaskConstant.ITEM_STATUS_SUCCESS.equals(itemStatus)) {
                successList.add(drugInfoDTO);
            } else if (PharmaceuticalTaskConstant.ITEM_STATUS_FAIL.equals(itemStatus)) {
                failList.add(drugInfoDTO);
            } else {
                // log.warn("【{}】为非对接异常状态，不做数据处理，跳过当前记录", itemStatus);
                unknownStatusList.add(drugInfoDTO);
            }
        }

        // 处理校验结果
        StringBuffer validMessage = new StringBuffer();
        validMessage.append(String.format("本次爬取共有【%s】条记录，其中成功【%s】条，失败【%s】条，异常状态【%s】条。", drugInfoDTOList.size(), successList.size(), failList.size(), unknownStatusList.size()));
        // log.info("【RPA任务处理记录状态验证】localTaskId=【{}】，校验结果=【{}】", localTaskId, validMessage);

        if (failList.size() + unknownStatusList.size() == drugInfoDTOList.size()) {
            // 失败场景：所有记录都是失败或者状态未知(无法匹配对接处理)则认为任务处理失败
            return TaskResultValidation.failure(failList, validMessage.toString());
        } else if (successList.size() == drugInfoDTOList.size()) {
            // 成功场景：全部成功
            return TaskResultValidation.allSuccess(successList, validMessage.toString());
        } else {
            // 成功场景：部分成功

            // 执行失败的药品本位码
            String failCodes = String.join(",", collectValidCodes(failList));
            if (!StringUtils.isEmpty(failCodes)) {
                validMessage.append("失败状态记录【药品本位码】数据：").append(failCodes).append("；");
            }

            // 异常状态的药品本位码
            String unknownStatusCodes = String.join(",", collectValidCodes(unknownStatusList));
            if (!StringUtils.isEmpty(failCodes)) {
                validMessage.append("未知状态记录【药品本位码】数据：").append(unknownStatusCodes);
            }

            return TaskResultValidation.partialSuccess(successList, failList, validMessage.toString());
        }

    }

}
