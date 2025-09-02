package com.noob.base.aiAgent.drugInfoCrawl.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 药品信息数据传输对象
 * 注解说明：
 * - @ExcelProperty：指定Excel列名和顺序
 * - @ColumnWidth：设置列宽
 * - @HeadRowHeight：表头行高
 * - @ContentRowHeight：内容行高
 * - @DateTimeFormat：日期格式化
 */
@Data
@HeadRowHeight(20)  // 表头行高
@ContentRowHeight(18)  // 内容行高
public class DrugInfoVO {

    @ExcelProperty(value = "序号", index = 0)
    @ColumnWidth(10)
    private Integer id;

    @ExcelProperty(value = "药品名称", index = 1)
    @ColumnWidth(30)
    private String prdtName;

    @ExcelProperty(value = "英文名称", index = 2)
    @ColumnWidth(30)
    private String enName;

    @ExcelProperty(value = "商品名称", index = 3)
    @ColumnWidth(25)
    private String commodityName;

    @ExcelProperty(value = "批准文号", index = 4)
    @ColumnWidth(20)
    private String approveNum;

    @ExcelProperty(value = "剂型", index = 5)
    @ColumnWidth(15)
    private String dosageForm;

    @ExcelProperty(value = "规格", index = 6)
    @ColumnWidth(20)
    private String spec;

    @ExcelProperty(value = "生产企业", index = 7)
    @ColumnWidth(30)
    private String manufacturer;

    @ExcelProperty(value = "批准日期", index = 8)
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")  // 日期格式化
    private Date approveDate;

}
    