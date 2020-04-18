package com.wqm.springcloud.lb.impl;

import com.wqm.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public  final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= 2147483647?0:current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
