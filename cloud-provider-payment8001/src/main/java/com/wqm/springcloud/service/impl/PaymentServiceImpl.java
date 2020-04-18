package com.wqm.springcloud.service.impl;

import com.wqm.springcloud.bean.po.Payment;
import com.wqm.springcloud.dao.PaymentDao;
import com.wqm.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int createPayment(Payment po) {
       return paymentDao.create(po);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
