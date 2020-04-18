package com.wqm.springcloud.controller;

import com.wqm.springcloud.bean.po.Payment;
import com.wqm.springcloud.bean.vo.Result;
import com.wqm.springcloud.bean.vo.ResultEnum;
import com.wqm.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author Wang
 */
@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/insert")
    public Result<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/insert",payment,Result.class);
    }

    @GetMapping("/consumer/payment/getInfo/{id}")
    public Result<Payment> getInfo(@PathVariable  Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getInfo/"+id,Result.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public Result<Payment> getInfo2(@PathVariable  Long id){
        ResponseEntity<Result> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getInfo/" + id, Result.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getStatusCode().toString());
            return entity.getBody();
        }else {
            return Result.error(ResultEnum.EXCEPTION);
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null || instances.size()<=0){
            return null;
        }else {
            ServiceInstance instance = loadBalancer.instance(instances);
            URI uri = instance.getUri();
            return restTemplate.getForObject(uri+"/payment/lb",String.class);
        }

    }
}
