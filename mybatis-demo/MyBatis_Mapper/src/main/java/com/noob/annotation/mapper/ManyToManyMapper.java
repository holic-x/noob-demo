package com.noob.annotation.mapper;

import com.noob.annotation.vo.OrderDetailMoreVO;
import com.noob.annotation.vo.OrderMoreVO;
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
public interface ManyToManyMapper {

    // 根据orders_id筛选订单列表详情内容及关联的items详情
    @Select("SELECT od.*,it.price 'items_price',it.detail 'items_detail',it.pic 'items_pic',it.createtime 'items_createtime' FROM orderdetail od,items it WHERE od.items_id=it.id AND od.orders_id=#{ordersId}")
    public abstract List<OrderDetailMoreVO> selectOrderDetailMoreByOrdersId(Integer ordersId);


    // 查询全部订单内容
    @Select("SELECT os.*,od.* FROM orders os,orderdetail od WHERE os.id=od.orders_id")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "user_id",property = "user_id"),
            @Result(column = "number",property = "number"),
            @Result(column = "createtime",property = "createtime"),
            @Result(column = "note",property = "note"),
            @Result(
                    property = "orderDetailMoreVOList",  // 被包含对象的变量名
                    javaType = List.class,  // 被包含对象的实际数据类型
                    column = "id",          // 根据查询出的orders表的id字段来查询orderdetail表
                    /*
                        many、@Many 一对多查询的固定写法
                        select属性：指定调用哪个接口中的哪个查询方法
                     */
                    many = @Many(select = "com.noob.annotation.mapper.ManyToManyMapper.selectOrderDetailMoreByOrdersId")
            )
    })
    public abstract List<OrderMoreVO> selectAll();

}
