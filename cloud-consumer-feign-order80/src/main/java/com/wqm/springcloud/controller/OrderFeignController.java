package com.wqm.springcloud.controller;

import com.wqm.springcloud.bean.po.Payment;
import com.wqm.springcloud.bean.vo.Result;
import com.wqm.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/getInfo/{id}")
    public Result<Payment> getPaymentInfo(@PathVariable Long id){
        Result<Payment> payment = paymentFeignService.getPaymentById(id);
        return payment;
    }
}
