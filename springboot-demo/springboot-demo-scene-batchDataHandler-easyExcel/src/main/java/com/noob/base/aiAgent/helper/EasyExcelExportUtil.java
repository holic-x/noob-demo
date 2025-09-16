package com.noob.base.aiAgent.helper;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.util.List;

/**
 * EasyExcel导出工具类
 */
public class EasyExcelExportUtil {

    /**
     * 导出Excel到本地文件
     *
     * @param dataList  数据列表
     * @param filePath  导出文件路径
     * @param clazz     数据模型类
     * @param sheetName 工作表名称
     * @param <T>       泛型
     */
    public static <T> void exportToLocalFile(List<T> dataList, String filePath, Class<T> clazz, String sheetName) {
        // 设置表头和内容样式策略
        HorizontalCellStyleStrategy styleStrategy = getStyleStrategy();

        // 执行导出
        EasyExcel.write(filePath, clazz)
                .sheet(sheetName)
                .registerWriteHandler(styleStrategy)  // 应用样式
                .doWrite(dataList);
    }

    /**
     * 创建Excel样式策略（表头和内容样式）
     */
    private static HorizontalCellStyleStrategy getStyleStrategy() {
        // 表头样式
        WriteCellStyle headCellStyle = new WriteCellStyle();
        // 设置表头水平居中
        headCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置表头垂直居中
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 表头字体
        WriteFont headFont = new WriteFont();
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBold(true);  // 加粗
        headCellStyle.setWriteFont(headFont);

        // 内容样式
        WriteCellStyle contentCellStyle = new WriteCellStyle();
        // 内容水平居中
        contentCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 内容垂直居中
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 内容字体
        WriteFont contentFont = new WriteFont();
        contentFont.setFontHeightInPoints((short) 11);
        contentCellStyle.setWriteFont(contentFont);

        // 返回样式策略
        return new HorizontalCellStyleStrategy(headCellStyle, contentCellStyle);
    }
}
    