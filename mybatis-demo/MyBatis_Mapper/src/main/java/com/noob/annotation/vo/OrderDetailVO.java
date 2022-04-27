package com.noob.annotation.vo;

import com.noob.annotation.model.Items;
import com.noob.annotation.model.OrderDetail;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OrderDetailVO extends OrderDetail {

    // 订单详情关联的订单项
    private Items items;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

}
