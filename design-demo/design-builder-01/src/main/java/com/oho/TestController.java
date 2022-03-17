package com.oho;

import com.oho.builder.Builder;
import com.oho.simple.DecorationPackageController;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * 测试类
 */
public class TestController {

    @Test
    public void testSimpleMode() {

        DecorationPackageController decoration = new
                DecorationPackageController();
        // 豪华欧式
        System.out.println(decoration.getMatterList(new
                BigDecimal("132.52"), 1));
        // 轻奢⽥园
        System.out.println(decoration.getMatterList(new
                BigDecimal("98.25"), 2));
        // 现代简约
        System.out.println(decoration.getMatterList(new
                BigDecimal("85.43"), 3));

    }


    @Test
    public void testBuilderMode() {
        Builder builder = new Builder();
        // 豪华欧式
        System.out.println(builder.levelOne(132.52D).getDetail());
        // 轻奢⽥园
        System.out.println(builder.levelTwo(98.25D).getDetail());
        // 现代简约
        System.out.println(builder.levelThree(85.43D).getDetail());

    }


}
