package com.noob.base.springbootdemobatchhandler;

import com.noob.base.batchHandle.entity.model.TLimit;
import com.noob.base.batchHandle.entity.model.TUserBatch;
import com.noob.base.batchHandle.util.RandomGenEntityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GenDataTest {

    /**
     * 模拟生成数据（生成SQL文件）
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
        FileWriter writer = new FileWriter(new File("D:\\Desktop\\tmp\\insert.txt"));
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
     */
    @Test
    public void batchGenTUserBatchToSQL() throws Exception {
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
        FileWriter writer = new FileWriter(new File("D:\\Desktop\\tmp\\insert.sql"));
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
