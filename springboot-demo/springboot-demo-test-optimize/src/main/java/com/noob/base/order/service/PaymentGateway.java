
package com.noob.base.order.service;

import com.noob.base.order.model.request.PaymentRequest;
import com.noob.base.order.model.vo.PaymentResult;

// PaymentGateway.java
public interface PaymentGateway {
    PaymentResult processPayment(PaymentRequest request);
}