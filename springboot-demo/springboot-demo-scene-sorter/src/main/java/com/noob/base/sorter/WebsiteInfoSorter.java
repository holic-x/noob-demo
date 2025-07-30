package com.noob.base.sorter;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.text.Collator;
import java.util.*;

/**
 * 自定义排序规则辅助工具类（核查网站排序）
 */
public class WebsiteInfoSorter {

    // 补充：多音字特殊处理映射表（静态初始化）
    private static final Map<Character, String> POLYPHONE_MAPPING = initPolyphoneMap();

    private static Map<Character, String> initPolyphoneMap() {
        Map<Character, String> map = new HashMap<>();
        // 补充常见多音字映射（示例），解决由于中文多音字排序导致的字母序列问题

        // 城市/地名相关）(此处针对地名场景补充多音字，其他场景的补充需要关注额外的多音字字典补充和自定义规则)
        map.put('长', "C"); // 长沙(C)、长春(C)
        map.put('重', "C"); // 重庆(C)
        map.put('厦', "X"); // 厦门(X)
        map.put('蚌', "B"); // 蚌埠(B)
        map.put('泌', "B"); // 泌阳(B)
        map.put('六', "L"); // 六安(L)、六合(L)
        map.put('涡', "G"); // 涡阳(G)
        map.put('莞', "G"); // 东莞(G)
        map.put('丽', "L"); // 丽水(L)
        map.put('台', "T"); // 台州(T)
        map.put('阿', "A"); // 阿城(A)、阿克苏(A)

        // 常用多音字（按使用频率排序）
        map.put('的', "D"); // 的确(D)
        map.put('了', "L"); // 了解(L)
        map.put('和', "H"); // 和平(H)
        map.put('中', "Z"); // 中国(Z)
        map.put('为', "W"); // 为了(W)
        map.put('大', "D"); // 大家(D)
        map.put('地', "D"); // 地方(D)
        map.put('要', "Y"); // 要求(Y)
        map.put('会', "H"); // 会议(H)
        map.put('行', "X"); // 行为(X)

        // 常见姓氏多音字
        map.put('仇', "Q"); // 姓氏
        map.put('解', "X"); // 姓氏
        map.put('单', "S"); // 姓氏
        map.put('查', "Z"); // 姓氏
        map.put('区', "O"); // 姓氏
        map.put('朴', "P"); // 姓氏
        map.put('繁', "P"); // 姓氏
        map.put('翟', "Z"); // 姓氏

        // 其他高频多音字
        map.put('率', "L"); // 效率(L)
        map.put('参', "C"); // 参加(C)
        map.put('差', "C"); // 差别(C)
        map.put('传', "C"); // 传播(C)
        map.put('调', "D"); // 调查(D)
        map.put('度', "D"); // 度过(D)
        map.put('分', "F"); // 分别(F)
        map.put('更', "G"); // 更加(G)
        map.put('好', "H"); // 好人(H)
        map.put('还', "H"); // 还有(H)
        map.put('几', "J"); // 几个(J)
        map.put('假', "J"); // 假期(J)
        map.put('空', "K"); // 空气(K)
        map.put('量', "L"); // 测量(L)
        map.put('难', "N"); // 困难(N)
        map.put('强', "Q"); // 强大(Q)
        map.put('少', "S"); // 少年(S)
        map.put('数', "S"); // 数学(S)
        map.put('体', "T"); // 体育(T)
        map.put('相', "X"); // 相信(X)
        map.put('应', "Y"); // 应该(Y)
        map.put('正', "Z"); // 正确(Z)

        return map;
    }

    /**
     * 根据中文名称的全拼音对核查网站数据列表进行排序
     *
     * @param list
     */
    public static void sortWebNameByPinyin(List<WebsiteInfo> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        // 指定排序规则（对中文字符进行全拼音排序）
        Collator collator = Collator.getInstance(Locale.CHINA);

        list.sort((o1, o2) -> {
            String name1 = o1.getWebName();
            String name2 = o2.getWebName();

            if (name1 == null && name2 == null) return 0;
            if (name1 == null) return -1;
            if (name2 == null) return 1;

            return collator.compare(name1, name2);
        });
    }

    /**
     * 根据中文名称的首字母拼音对核查网站数据列表进行排序
     *
     * @param list
     */
    public static void sortWebNameByPinyinInitial(List<WebsiteInfo> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        Collections.sort(list, new Comparator<WebsiteInfo>() {
            @Override
            public int compare(WebsiteInfo o1, WebsiteInfo o2) {
                String name1 = o1.getWebName();
                String name2 = o2.getWebName();

                // 处理null值
                if (name1 == null && name2 == null) return 0;
                if (name1 == null) return -1;
                if (name2 == null) return 1;

                // 获取拼音首字母缩写（补充多音字处理）
                String initial1 = getPinyinInitials(name1);
                String initial2 = getPinyinInitials(name2);

                return initial1.compareTo(initial2);
            }
        });
    }

    /**
     * 获取中文的拼音首字母缩写
     * 北京 → BJ
     * 山东 → SD
     * 补充说明：自动处理常见多音字（如长沙→CS）
     */
    public static String getPinyinInitials(String chinese) {
        if (chinese == null || chinese.isEmpty()) {
            return "";
        }

        StringBuilder initials = new StringBuilder();
        char[] chars = chinese.toCharArray();

        for (char c : chars) {
            // 补充：优先检查多音字映射（兼容多音字导致的排序问题场景，按照业务场景处理多音字映射，以适配业务需求）
            if (POLYPHONE_MAPPING.containsKey(c)) {
                initials.append(POLYPHONE_MAPPING.get(c));
                continue;
            }

            // 中文字符处理
            if (isChineseCharacter(c)) {
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
                if (pinyinArray != null && pinyinArray.length > 0) {
                    // 取拼音的首字母并转为大写
                    initials.append(Character.toUpperCase(pinyinArray[0].charAt(0)));
                }
            } else {
                // 非中文字符直接保留原字符（转为大写）
                initials.append(Character.toUpperCase(c));
            }
        }

        return initials.toString();
    }

    /**
     * 判断是否为中文字符
     */
    private static boolean isChineseCharacter(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
    }
}