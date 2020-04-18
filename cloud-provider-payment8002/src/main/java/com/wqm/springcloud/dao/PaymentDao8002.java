package com.wqm.springcloud.dao;

import com.wqm.springcloud.bean.po.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wang
 */
@Mapper
public interface PaymentDao8002 {

    int create(@Param("po") Payment po);

    Payment getPaymentById(@Param("id") Long id);
}
