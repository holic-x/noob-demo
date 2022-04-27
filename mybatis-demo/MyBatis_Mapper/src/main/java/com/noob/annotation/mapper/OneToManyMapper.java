package com.noob.annotation.mapper;

import com.noob.annotation.model.OrderDetail;
import com.noob.annotation.model.Orders;
import com.noob.annotation.vo.OrdersVO;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public interface OneToManyMapper {

    //根据orders_id查询orderdetail表（一个订单列表对应多个订单详情）
    @Select("SELECT * FROM orderdetail WHERE orders_id = #{ordersId}")
    public abstract List<OrderDetail> selectByOrdersId(Integer ordersId);


    //查询全部
    @Select("SELECT * FROM orders")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "number",property = "number"),
            @Result(column = "createtime",property = "createtime"),
            @Result(column = "note",property = "note"),
            @Result(
                    property = "orderDetailList",  // 被包含对象的变量名
                    javaType = List.class,  // 被包含对象的实际数据类型
                    column = "id",          // 根据查询出的orders表的id字段来查询orderdetail表
                    /*
                        many、@Many 一对多查询的固定写法
                        select属性：指定调用哪个接口中的哪个查询方法
                     */
                    many = @Many(select = "com.noob.annotation.mapper.OneToManyMapper.selectByOrdersId")
            )
    })
    public abstract List<OrdersVO> selectAll();

}
