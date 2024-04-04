package com.noob.annotation.vo;

import com.noob.annotation.model.OrderDetail;
import com.noob.annotation.model.Orders;

import java.util.List;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OrdersVO extends Orders {

    private List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
