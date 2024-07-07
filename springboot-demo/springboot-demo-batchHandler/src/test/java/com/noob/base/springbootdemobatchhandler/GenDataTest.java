package com.noob.base.springbootdemobatchhandler;

import com.noob.base.batchHandle.entity.model.TLimit;
import com.noob.base.batchHandle.entity.model.TUserBatch;
import com.noob.base.batchHandle.util.RandomGenEntityUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class GenDataTest {

    private static final Logger log = LoggerFactory.getLogger(GenDataTest.class);

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
    public void batchGenTUserBatchToSQL1() throws Exception {
        List<TUserBatch> tUserBatchList = RandomGenEntityUtil.genTUserBatch(5000000);// 1000000
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
        FileWriter writer = new FileWriter(new File("D:\\Desktop\\tmp\\insertUserBatch01.sql"));
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
     * 模拟生成数据（生成SQL文件） 分批处理
     */
    @Test
    public void test() throws Exception {
        int num = 5000020;
        // 定义分批处理的数量
        int batchSize = 1000000;
        // 获取分批次数和余数
        int operNum = num / batchSize;
        int remainNum = num % batchSize;
        System.out.println(operNum);
        System.out.println(remainNum);
    }

    /**
     * 执行生成文件名称的导出数量大小
     * @param fileName
     * @param size
     * @throws Exception
     */
    private void batchGenTUserBatchToSQLBySize(String fileName,int size) throws Exception {
        List<TUserBatch> tUserBatchList = RandomGenEntityUtil.genTUserBatch(size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 封装SQL文本信息
        List<String> insertList = new ArrayList<>();
        tUserBatchList.forEach(tUserBatch -> {
            // 拼接SQL语句
            StringBuffer insertSql = new StringBuffer();
            // 主键自增
            /*
            insertSql.append("insert into t_user_batch (account,mobile,add_time) values( ")
                    .append("'" + tUserBatch.getAccount() + "',")
                    .append("'" + tUserBatch.getMobile() + "',")
                    .append("'" + sdf.format(tUserBatch.getAdd_time()) + "');");
             */
            String split = ",";
            insertSql.append(tUserBatch.getAccount() + split)
                    .append(tUserBatch.getMobile() + split)
                    .append(sdf.format(tUserBatch.getAdd_time()));
            insertList.add(insertSql.toString());
        });

        // 写入文件
        FileWriter writer = new FileWriter(new File("D:\\Desktop\\tmp\\" + fileName));
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
     * 模拟生成数据（生成SQL文件） 分批处理
     */
    @Test
    public void batchGenTUserBatchToSQL2() throws Exception {
        long start = System.currentTimeMillis();
        int num = 50000000;
        // 定义分批处理的数量
        int batchSize = 10000000;
        // 获取分批次数和余数
        int operNum = num / batchSize;
        int remainNum = num % batchSize;
        String randomName = UUID.randomUUID().toString().replaceAll("-","");
        for (int i = 1; i <= operNum; i++) {
            System.out.println("第" + i + "批数据开始处理");
            String fileName = randomName + "-insertUserBatch" + i + ".sql";
            // 调用方法批量生成数据
            batchGenTUserBatchToSQLBySize(fileName,batchSize);
            System.out.println("第" + i + "批数据处理完成");
        }
        // 对余数进行导出
        batchGenTUserBatchToSQLBySize("remain" + remainNum + ".sql",remainNum);
        long end = System.currentTimeMillis();
        System.out.println("批量导出" + num + "条数据耗时：" + (end - start)/1000 + "s   每批处理数量为:" + batchSize);
    }

}
