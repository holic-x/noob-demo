package com.noob.base.springbootdemobatchhandler;

import com.noob.base.batchHandle.entity.model.TUserBatch;
import com.noob.base.batchHandle.util.RandomGenEntityUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 模拟数据生成测试（分批处理，生成多个文件）
 */
@SpringBootTest
class GenBatchDataTest {

    private static final Logger log = LoggerFactory.getLogger(GenBatchDataTest.class);

    // 文件目标生成路径
//    private String TARGET_PATH  = "D:\\Desktop\\tmp\\";
    private String TARGET_PATH  = "/Users/holic-x/tmp/";

    /**
     * 模拟生成数据（生成SQL文件） 分批处理 普通测试
     */
    @Test
    public void test() throws Exception {
        int num = 5000020;
        // 定义分批处理的数量
        int batchSize = 1000000;
        // 获取需要分批的次数和余数
        int operNum = num / batchSize;
        int remainNum = num % batchSize;
        System.out.println(operNum);
        System.out.println(remainNum);
    }

    /**
     * 指定生成文件名称、导出数量大小，完成批量生成操作
     * @param fileName
     * @param size
     * @throws Exception
     */
    private void batchGenTUserBatchToSQLByParam(String fileName,int size) throws Exception {
        List<TUserBatch> tUserBatchList = RandomGenEntityUtil.genTUserBatch(size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 封装SQL文本信息
        List<String> insertList = new ArrayList<>();
        tUserBatchList.forEach(tUserBatch -> {
            // 拼接SQL语句
            StringBuffer insertSql = new StringBuffer();
            // 主键自增
            String split = ",";
            insertSql.append(tUserBatch.getAccount() + split)
                    .append(tUserBatch.getMobile() + split)
                    .append(sdf.format(tUserBatch.getAdd_time()));
            insertList.add(insertSql.toString());
        });

        // 写入文件
        FileWriter writer = new FileWriter(new File(TARGET_PATH + fileName));
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
    public void batchGenTUserBatchToSQL() throws Exception {
        long start = System.currentTimeMillis();
        // 定义模拟生成数据总数
        int totalSize = 5000 * 10000;
        // 定义分批处理的数量(测试参考：100w、200w、500w、1000w)
        int batchSize = 500 * 10000;
        // 获取分批次数和余数
        int operNum = totalSize / batchSize;
        int remainNum = totalSize % batchSize;
        // 生成文件的随机名称前缀
        String randomName = UUID.randomUUID().toString().replaceAll("-","").substring(0,6);
        for (int i = 1; i <= operNum; i++) {
            System.out.println("第" + i + "批数据开始处理");
            String fileName = randomName + "-insertUserBatch" + i + ".sql";
            // 调用方法批量生成数据
            batchGenTUserBatchToSQLByParam(fileName,batchSize);
            System.out.println("第" + i + "批数据处理完成");
        }
        // 对余数进行导出
        batchGenTUserBatchToSQLByParam("remain" + remainNum + ".sql",remainNum);
        long end = System.currentTimeMillis();
        System.out.println("批量导出" + totalSize + "条数据总耗时：" + (end - start)/1000 + "s   单批处理数量为:" + batchSize);
    }

}
