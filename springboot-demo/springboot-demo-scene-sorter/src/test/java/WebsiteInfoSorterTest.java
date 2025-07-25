import com.noob.base.sorter.WebsiteInfo;
import com.noob.base.sorter.WebsiteInfoSorter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for：WebsiteInfoSorter
 */
public class WebsiteInfoSorterTest {

    // 简单的测试实现（只需要覆盖webName验证）
    private static class TestWebsiteInfo extends WebsiteInfo {
        private String webName;

        public TestWebsiteInfo(String webName) {
            this.webName = webName;
        }

        @Override
        public String getWebName() {
            return webName;
        }

        @Override
        public void setWebName(String webName) {
            this.webName = webName;
        }
    }

    //================================================================================
    // Test cases for method
    //================================================================================

    /**
     * 测试：sortWebNameByPinyin 输入为null
     */
    @Test
    public void testSortWebNameByPinyin_NullList() {
        WebsiteInfoSorter.sortWebNameByPinyin(null);
        // 无异常即通过
    }

    /**
     * 测试：sortWebNameByPinyin 输入为空列表
     */
    @Test
    public void testSortWebNameByPinyin_EmptyList() {
        List<WebsiteInfo> list = new ArrayList<>();
        WebsiteInfoSorter.sortWebNameByPinyin(list);
        assertTrue(list.isEmpty());
    }

    /**
     * 测试：sortWebNameByPinyin 列表中有null webName
     */
    @Test
    public void testSortWebNameByPinyin_WithNullWebName() {
        List<WebsiteInfo> list = Arrays.asList(
                new TestWebsiteInfo("北京"),
                new TestWebsiteInfo(null),
                new TestWebsiteInfo("上海")
        );
        WebsiteInfoSorter.sortWebNameByPinyin(list);
        assertNull(list.get(0).getWebName());
        assertEquals("北京", list.get(1).getWebName());
        assertEquals("上海", list.get(2).getWebName());
    }

    /**
     * 测试：sortWebNameByPinyin 中文、英文、数字混合
     */
    @Test
    public void testSortWebNameByPinyin_Mixed() {
        List<WebsiteInfo> list = Arrays.asList(
                new TestWebsiteInfo("北京"),
                new TestWebsiteInfo("abc"),
                new TestWebsiteInfo("123"),
                new TestWebsiteInfo("上海")
        );
        WebsiteInfoSorter.sortWebNameByPinyin(list);
        // 按中文拼音和本地Collator排序，数字、英文、中文顺序
        assertEquals("123", list.get(0).getWebName());
        assertEquals("abc", list.get(1).getWebName());
        assertEquals("北京", list.get(2).getWebName());
        assertEquals("上海", list.get(3).getWebName());
    }

    /**
     * 测试：sortWebNameByPinyinInitial 输入为null
     */
    @Test
    public void testSortWebNameByPinyinInitial_NullList() {
        WebsiteInfoSorter.sortWebNameByPinyinInitial(null);
        // 无异常即通过
    }

    /**
     * 测试：sortWebNameByPinyinInitial 输入为空列表
     */
    @Test
    public void testSortWebNameByPinyinInitial_EmptyList() {
        List<WebsiteInfo> list = new ArrayList<>();
        WebsiteInfoSorter.sortWebNameByPinyinInitial(list);
        assertTrue(list.isEmpty());
    }

    /**
     * 测试：sortWebNameByPinyinInitial 列表中有null webName
     */
    @Test
    public void testSortWebNameByPinyinInitial_WithNullWebName() {
        List<WebsiteInfo> list = Arrays.asList(
                new TestWebsiteInfo("北京"),
                new TestWebsiteInfo(null),
                new TestWebsiteInfo("上海")
        );
        WebsiteInfoSorter.sortWebNameByPinyinInitial(list);
        assertNull(list.get(0).getWebName());
        // "北京"->"BJ", "上海"->"SH"，BJ在SH前
        assertEquals("北京", list.get(1).getWebName());
        assertEquals("上海", list.get(2).getWebName());
    }

    /**
     * 测试：sortWebNameByPinyinInitial 中文、英文、数字混合
     */
    @Test
    public void testSortWebNameByPinyinInitial_Mixed() {
        List<WebsiteInfo> list = Arrays.asList(
                new TestWebsiteInfo("北京"),
                new TestWebsiteInfo("abc"),
                new TestWebsiteInfo("123"),
                new TestWebsiteInfo("上海")
        );
        WebsiteInfoSorter.sortWebNameByPinyinInitial(list);
        // "123"->"123", "abc"->"ABC", "北京"->"BJ", "上海"->"SH"
        assertEquals("123", list.get(0).getWebName());
        assertEquals("abc", list.get(1).getWebName());
        assertEquals("北京", list.get(2).getWebName());
        assertEquals("上海", list.get(3).getWebName());
    }


    //================================================================================
    // Test cases for 业务需求（验证地方&非地方核查网站随机列表的排序规则）
    //================================================================================

    /**
     * 随机打乱生成的webName测试数据（全国性网站-非地方核查网站）
     */
    private static List<WebsiteInfo> getRandomWebsiteInfoListForNonLocal() {
        List<String> webNames = Arrays.asList(
                "百度",
                "发改委",
                "国家财政部",
                "国家农业农村部",
                "国家企业信用公示系统",
                "国家市场监督管理局",
                "国家税务总局",
                "国家统计局",
                "国家外汇管理局",
                "工信部",
                "海关总署网站中国海关企业进出口信用信息公示平台",
                "全国建筑市场监管公共服务平台",
                "人力资源和社会保障部",
                "人民法院公告网",
                "人民银行总行",
                "上交所",
                "深交所",
                "生态环境部",
                "特种设备安全监察局",
                "信用能源",
                "信用中国",
                "银保监会",
                "应急管理局",
                "政府采购严重违法失信行为信息记录",
                "住房和城乡建设部",
                "中国裁判文书网",
                "中国执行信息公开网",
                "中国执行信息公开网失信人",
                "中华人民共和国工业和信息化部",
                "证监会",
                "证券期货市场失信记录查询平台",
                "证券投资基金业协会",
                "自然资源部"
        );
        List<String> shuffled = new ArrayList<>(webNames);
        java.util.Collections.shuffle(shuffled);
        List<WebsiteInfo> result = new ArrayList<>();
        for (String name : shuffled) {
            // result.add(new TestWebsiteInfo(name));
            result.add(WebsiteInfo.builder().webName(name).build());
        }
        return result;
    }

    /**
     * 随机打乱生成的webName测试数据（地方核查网站）
     */
    private static List<WebsiteInfo> getRandomWebsiteInfoListForLocal() {
        List<String> webNames = Arrays.asList(
                "北京",
                "成都",
                "常州",
                "福州",
                "广州",
                "合肥",
                "杭州",
                "济南",
                "南京",
                "青岛",
                "上海",
                "石家庄",
                "厦门",
                "深圳",
                "苏州",
                "天津",
                "武汉",
                "无锡",
                "西安",
                "信阳",
                "长沙",
                "郑州"
        );
        List<String> shuffled = new ArrayList<>(webNames);
        java.util.Collections.shuffle(shuffled);
        List<WebsiteInfo> result = new ArrayList<>();
        for (String name : shuffled) {
            // result.add(new TestWebsiteInfo(name));
            result.add(WebsiteInfo.builder().webName(name).build());
        }
        return result;
    }


    /**
     * 测试：sortWebNameByPinyinInitial 中文、英文、数字混合
     * 验证非地方核查
     */
    @Test
    public void testSortWebNameByPinyinInitial_NonLocalWebsite() {
        List<WebsiteInfo> list = getRandomWebsiteInfoListForNonLocal();
        WebsiteInfoSorter.sortWebNameByPinyinInitial(list);

        // 预期排序结果
        list.forEach((item) -> {
            System.out.println(item.getWebName() + ":" + WebsiteInfoSorter.getPinyinInitials(item.getWebName()));
        });

        // Assert
        assertNotNull(list);

        /**
         * 百度:BD
         * 发改委:FGW
         * 国家财政部:GJCZB
         * 国家农业农村部:GJNYNCB
         * 国家企业信用公示系统:GJQYXYGSXT
         * 国家市场监督管理局:GJSCJDGLJ
         * 国家税务总局:GJSWZJ
         * 国家统计局:GJTJJ
         * 国家外汇管理局:GJWHGLJ
         * 工信部:GXB
         * 海关总署网站中国海关企业进出口信用信息公示平台:HGZSWZZGHGQYJCKXYXXGSPT
         * 全国建筑市场监管公共服务平台:QGJZSCJGGGFWPT
         * 人力资源和社会保障部:RLZYHSHBZB
         * 人民法院公告网:RMFYGGW
         * 人民银行总行:RMYXZX
         * 深交所:SJS
         * 上交所:SJS
         * 生态环境部:STHJB
         * 特种设备安全监察局:TZSBAQJCJ
         * 信用能源:XYNY
         * 信用中国:XYZG
         * 银保监会:YBJH
         * 应急管理局:YJGLJ
         * 政府采购严重违法失信行为信息记录:ZFCGYZWFSXXWXXJL
         * 住房和城乡建设部:ZFHCXJSB
         * 中国裁判文书网:ZGCPWSW
         * 中国执行信息公开网:ZGZXXXGKW
         * 中国执行信息公开网失信人:ZGZXXXGKWSXR
         * 中华人民共和国工业和信息化部:ZHRMGHGGYHXXHB
         * 证监会:ZJH
         * 证券期货市场失信记录查询平台:ZQQHSCSXJLCXPT
         * 证券投资基金业协会:ZQTZJJYXH
         * 自然资源部:ZRZYB
         */
    }


    /**
     * 测试：sortWebNameByPinyinInitial 中文、英文、数字混合
     * 验证地方核查
     */
    @Test
    public void testSortWebNameByPinyinInitial_LocalWebsite() {
        List<WebsiteInfo> list = getRandomWebsiteInfoListForLocal();
        WebsiteInfoSorter.sortWebNameByPinyinInitial(list);

        // 预期排序结果
        list.forEach((item) -> {
            System.out.println(item.getWebName() + ":" + WebsiteInfoSorter.getPinyinInitials(item.getWebName()));
        });

        // Assert
        assertNotNull(list);

        /**
         * fix 方向：长沙是多音字，在字母序中被识别为zhang读音，因此会被排在信阳后面，针对城市多音字首字母需要额外进行多音字的处理优化
         * 调整优化版本之后进一步确认生成的逻辑：例如长沙（CS）需要排在信阳（XY）前面，而不是因多音字导致（ZS在XY后面）
         */
        /**
         * 北京:BJ
         * 成都:CD
         * 常州:CZ
         * 福州:FZ
         * 广州:GZ
         * 合肥:HF
         * 杭州:HZ
         * 济南:JN
         * 南京:NJ
         * 青岛:QD
         * 上海:SH
         * 石家庄:SJZ
         * 厦门:SM
         * 深圳:SZ
         * 苏州:SZ
         * 天津:TJ
         * 武汉:WH
         * 无锡:WX
         * 西安:XA
         * 信阳:XY
         * 长沙:ZS
         * 郑州:ZZ
         */
    }


}
