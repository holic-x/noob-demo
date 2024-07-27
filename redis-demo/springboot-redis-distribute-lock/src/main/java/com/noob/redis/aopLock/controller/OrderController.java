package com.noob.redis.aopLock.controller;

import com.noob.redis.aopLock.model.Resp;
import com.noob.redis.aopLock.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/test")
public class OrderController {

    @Autowired
    IOrderService orderService;

    /*
    // Swagger 配置
    @ApiOperation(value = "修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode", value = "订单编号", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "订单状态 1：未发货 2：已发货 3：完成", paramType = "query"),
    })
     */
    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.PUT)
    public Resp updateOrderStatus(@RequestParam(value = "orderCode")String orderCode,
                                  @RequestParam(value = "userId")Integer userId,
                                  @RequestParam(value = "status")Integer status){
        log.info("updateOrderStatus reqParam:orderCode:{},userId:{},status:{}",orderCode,userId,status);
        return orderService.updateOrder(orderCode,userId,status);
    }

}
