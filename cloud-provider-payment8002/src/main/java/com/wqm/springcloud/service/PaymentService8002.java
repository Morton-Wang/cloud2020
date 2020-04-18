package com.wqm.springcloud.service;

import com.wqm.springcloud.bean.po.Payment;

public interface PaymentService8002 {
    int createPayment(Payment po);

    Payment getPayment(Long id);
}
