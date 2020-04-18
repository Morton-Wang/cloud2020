package com.wqm.springcloud.service;

import com.wqm.springcloud.bean.po.Payment;
import com.wqm.springcloud.bean.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/getInfo/{id}")    //哪个地址
    Result<Payment> getPaymentById(@PathVariable("id") Long id);
}
