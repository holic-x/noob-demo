package com.noob.annotation.mapper;

import com.noob.annotation.model.Items;
import com.noob.annotation.vo.OrderDetailVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public interface OneToOneMapper {

    // 根据item_id查找订单项
    @Select("SELECT * FROM items WHERE id=#{id}")
    public abstract Items selectItemsById(Integer id);

    // 根据id查找订单详情以及关联的订单项内容
    @Select("SELECT * FROM orderdetail")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "orders_id",property = "orders_id"),
            @Result(
                    property = "items",      // 被包含对象的变量名
                    javaType = Items.class,  // 被包含对象的实际数据类型
                    column = "items_id",     // 根据查询出的orderdetails表的items_id字段来查询items表
                    /*
                        one、@One(一对一)
                        select属性：指定调用哪个接口中的哪个方法
                     */
                    one = @One(select = "com.noob.annotation.mapper.OneToOneMapper.selectItemsById")
            )
    })
    public abstract List<OrderDetailVO> selectAll();

}
