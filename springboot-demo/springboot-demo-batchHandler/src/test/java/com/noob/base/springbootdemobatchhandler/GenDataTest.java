package com.noob.base.springbootdemobatchhandler;

import com.noob.base.batchHandle.entity.model.TUserBatch;
import com.noob.base.batchHandle.util.RandomGenEntityUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 模拟数据生成测试（单次处理，生成单个文件）
 */
@SpringBootTest
class GenDataTest {

    private static final Logger log = LoggerFactory.getLogger(GenDataTest.class);

    // 文件目标生成路径
    private String TARGET_PATH  = "D:\\Desktop\\tmp\\";

    /**
     * 模拟生成数据（生成TXT文件）
     * 生成数据格式：按照指定分隔符（','）按行生成数据文本
     */
    @Test
    public void batchGenTUserBatchToTxt() throws Exception {
        List<TUserBatch> tUserBatchList = RandomGenEntityUtil.genTUserBatch(1000000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 封装SQL文本信息
        List<String> insertList = new ArrayList<>();
        String split = ",";
        tUserBatchList.forEach(tUserBatch -> {
            // 拼接SQL语句
            StringBuffer insertSql = new StringBuffer();
            // 主键自增
            insertSql.append(tUserBatch.getAccount() + split)
                    .append(tUserBatch.getMobile() + split)
                    .append(sdf.format(tUserBatch.getAdd_time()));
            insertList.add(insertSql.toString());
        });

        // 写入文件
        FileWriter writer = new FileWriter(new File(TARGET_PATH + "insert.txt"));
        insertList.forEach(insertSql -> {
            try {
                writer.write(insertSql + "\n");
            } catch (IOException e) {
                System.out.println("数据写入异常" + insertSql);
                throw new RuntimeException(e);
            }
        });
        // 关闭写入流
        writer.close();
    }

    /**
     * 模拟生成数据（生成SQL文件）
     * 生成数据格式：insert 类型的SQL语句
     */
    @Test
    public void batchGenTUserBatchToSQL() throws Exception {
        // 100w 数据处理正常，如果直接生成一个5000w的大数据量文件程序会卡死，抛出OOM错误
        List<TUserBatch> tUserBatchList = RandomGenEntityUtil.genTUserBatch(1000000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 封装SQL文本信息
        List<String> insertList = new ArrayList<>();
        tUserBatchList.forEach(tUserBatch -> {
            // 拼接SQL语句
            StringBuffer insertSql = new StringBuffer();
            // 主键自增
            insertSql.append("insert into t_user_batch (account,mobile,add_time) values( ")
                    .append("'" + tUserBatch.getAccount() + "',")
                    .append("'" + tUserBatch.getMobile() + "',")
                    .append("'" + sdf.format(tUserBatch.getAdd_time()) + "');");
            insertList.add(insertSql.toString());
        });

        // 写入文件
        FileWriter writer = new FileWriter(new File(TARGET_PATH + "insertUserBatch001.sql"));
        insertList.forEach(insertSql -> {
            try {
                writer.write(insertSql + "\n");
            } catch (IOException e) {
                System.out.println("数据写入异常" + insertSql);
                throw new RuntimeException(e);
            }
        });
        // 关闭写入流
        writer.close();
    }

}
