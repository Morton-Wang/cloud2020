package com.wqm.springcloud.controller;

import com.wqm.springcloud.bean.po.Payment;
import com.wqm.springcloud.bean.vo.Result;
import com.wqm.springcloud.bean.vo.ResultEnum;
import com.wqm.springcloud.service.PaymentService8002;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wang
 */

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService8002 paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/insert")
    public Result createPayment(@RequestBody Payment po){
        int payment = paymentService.createPayment(po);
        log.info("插入结果="+payment);
        if (payment >0 ){
            return Result.success(payment+"端口号"+serverPort);
        }else {
            return Result.error(ResultEnum.EXCEPTION);
        }
    }

    @GetMapping("/payment/getInfo/{id}")
    public Result<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPayment(id);
        log.info("返回结果"+payment+"端口号"+serverPort);
        return Result.success(payment);
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****service*****"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }
}
