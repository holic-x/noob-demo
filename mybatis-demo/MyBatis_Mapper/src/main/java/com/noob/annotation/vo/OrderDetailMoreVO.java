package com.noob.annotation.vo;

import com.noob.annotation.model.Items;
import com.noob.annotation.model.OrderDetail;

import java.util.Date;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/8/10
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class OrderDetailMoreVO extends OrderDetail {

    // 订单详情关联的订单项相关内容
    private float items_price;
    private String items_detail;
    private String items_pic;
    private Date items_createtime;

    public float getItems_price() {
        return items_price;
    }

    public void setItems_price(float items_price) {
        this.items_price = items_price;
    }

    public String getItems_detail() {
        return items_detail;
    }

    public void setItems_detail(String items_detail) {
        this.items_detail = items_detail;
    }

    public String getItems_pic() {
        return items_pic;
    }

    public void setItems_pic(String items_pic) {
        this.items_pic = items_pic;
    }

    public Date getItems_createtime() {
        return items_createtime;
    }

    public void setItems_createtime(Date items_createtime) {
        this.items_createtime = items_createtime;
    }
}
