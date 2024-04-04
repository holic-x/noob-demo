package com.noob.annotation;

import com.noob.annotation.mapper.ManyToManyMapper;
import com.noob.annotation.mapper.OneToManyMapper;
import com.noob.annotation.mapper.OneToOneMapper;
import com.noob.annotation.mapper.UserMapper;
import com.noob.annotation.model.User;
import com.noob.annotation.vo.OrderDetailVO;
import com.noob.annotation.vo.OrderMoreVO;
import com.noob.annotation.vo.OrdersVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OrderTest {

    // 优化测试，将公共代码提取出来
    SqlSessionFactory sqlSessionFactory;

    @Before // 在所有测试代码执行之前执行该方法
    public void before() throws Exception {
        // a.定义mybatis配置文件,并得到配置文件的流
        String resource = "SqlMapConfig-annotation.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // b.根据流创建会话工厂,传入mybatis配置信息到会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testOneToOne() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取OneToOneMapper对象
        OneToOneMapper oneToOneMapper = sqlSession.getMapper(OneToOneMapper.class);

        // ------- 业务测试 -------
        List<OrderDetailVO> list = oneToOneMapper.selectAll();
//        list.forEach(System.out::println);
        for (OrderDetailVO orderDetailVO : list) {
            System.out.print(orderDetailVO.getId()+orderDetailVO.getItems().getDetail()+"\n");
        }

        // 关闭连接
        sqlSession.close();
    }

    @Test
    public void testOneToMany() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取OneToManyMapper对象
        OneToManyMapper oneToManyMapper = sqlSession.getMapper(OneToManyMapper.class);

        // ------- 业务测试 -------
        List<OrdersVO> list = oneToManyMapper.selectAll();
//        list.forEach(System.out::println);
        for (OrdersVO ordersVO : list) {
            System.out.println(ordersVO.getId()+"订单列表:");
            ordersVO.getOrderDetailList().forEach((od)->{
                System.out.println(od.getOrders_id()+":"+od.getItems_num()+"\t");
            });
        }

        // 关闭连接
        sqlSession.close();
    }

    @Test
    public void testManyToMany() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 通过SqlSession对象获取ManyToManyMapper对象
        ManyToManyMapper manyToManyMapper = sqlSession.getMapper(ManyToManyMapper.class);

        // ------- 业务测试 -------
        List<OrderMoreVO> list = manyToManyMapper.selectAll();
//        list.forEach(System.out::println);
        list.forEach((om)->{
            System.out.println(om.getId()+"订单列表:");
            om.getOrderDetailMoreVOList().forEach((od)->{
                System.out.println(od.getOrders_id()+":"+od.getItems_num()+"\t"
                        +od.getItems_price()+"\t"+od.getItems_detail()+"\t");
            });

        });

        // 关闭连接
        sqlSession.close();
    }

}
