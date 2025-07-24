package com.noob.base.batchDataHandler.docHandle.v2_optimize_batchGet;

import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import com.noob.base.batchDataHandler.docHandle.mock.MockHandleTimeHelper;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟文件服务: 文件批量处理服务
 * - 1.根据文件名称列表批量获取文件数据，封装为Map<String uniqueKey,byte[] bytes>
 * - 2.根据文件名获取文件数据
 */
public class MockBatchFileService {

    private String baseDir;

    public MockBatchFileService(String baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * 根据fileInfo获取图片输入流
     *
     * @param fileInfo
     * @return
     * @throws Exception
     */
    public InputStream getFileStreamByFileInfo(FileInfo fileInfo) throws Exception {
        // 模拟服务请求耗时场景
        // MockHandleTimeHelper.mockHandleTime(500); // 设定固定耗时验证性能优化场景:单个文件模拟500ms处理

        // 文件信息
        String fileKey = fileInfo.getFileKey();
        String fileName = fileInfo.getFileName();

        // 获取文件流(根据文件名称定位)
        String filePath = baseDir + File.separator + fileName;
        System.out.println("模拟文件处理服务：当前处理图片：" + fileName);
        return new FileInputStream(filePath);
    }


    /**
     * 模拟服务请求耗时场景：根据批次数量mock操作耗时响应
     * 典型场景耗时估算（单线程）：
     * - 500个截图‌：SSD约1-3秒，HDD约5-10秒
     * - 1000个截图‌：SSD约2-5秒，HDD约10-20秒
     */
    private void mockHandleTime(List<FileInfo> fileInfoList) {
        int size = CollectionUtils.isEmpty(fileInfoList) ? 0 : fileInfoList.size();
        // 设定固定耗时验证性能优化场景:批量文件模拟处理（根据文件数来模拟耗时）
        if (size > 0 && size <= 500) {
            MockHandleTimeHelper.mockHandleTime(3000);
        } else if (size > 500 && size <= 1000) {
            MockHandleTimeHelper.mockHandleTime(5000);
        } else if (size > 1000 && size <= 2000) {
            MockHandleTimeHelper.mockHandleTime(20000);
        } else {
            throw new RuntimeException("批次个数耗时处理mock异常，请结合实际场景补充设定");
        }
    }

    /**
     * 根据fileInfo列表获取图片输入流数据
     *
     * @param fileInfoList
     * @return
     * @throws Exception
     */
    public Map<String, InputStream> getFileStreamByFileInfo_forBatch(List<FileInfo> fileInfoList) throws Exception {

        // 模拟服务请求耗时场景
        // mockHandleTime();

        // 文件信息批量处理
        Map<String, InputStream> map = new HashMap<>();
        for (FileInfo fileInfo : fileInfoList) {
            String fileKey = fileInfo.getFileKey();
            String fileName = fileInfo.getFileName();
            // 获取文件流(根据文件名称定位)
            String filePath = baseDir + File.separator + fileName;
            System.out.println("模拟文件处理服务：当前处理图片：" + fileName);
            FileInputStream fis = new FileInputStream(filePath);
            // 封装处理结果集
            map.put(fileKey, fis);
        }

        // 返回模拟的文件服务批量处理结果
        return map;
    }

}