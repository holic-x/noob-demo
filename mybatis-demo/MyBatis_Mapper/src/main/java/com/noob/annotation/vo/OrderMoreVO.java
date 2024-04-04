package com.noob.annotation.vo;

import com.noob.annotation.model.Orders;

import java.util.List;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OrderMoreVO extends Orders{

    private List<OrderDetailMoreVO> orderDetailMoreVOList;

    public List<OrderDetailMoreVO> getOrderDetailMoreVOList() {
        return orderDetailMoreVOList;
    }

    public void setOrderDetailMoreVOList(List<OrderDetailMoreVO> orderDetailMoreVOList) {
        this.orderDetailMoreVOList = orderDetailMoreVOList;
    }
}
