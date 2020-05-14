package com.yc.pay.service;

import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-13
 * @Version: 1.0.0
 */
public interface AliPayService {

    /**
     * native 支付
     * @param orderId 订单ID
     * @param amount 支付金额
     * @return response
     */
    PayResponse pcPay(String orderId, BigDecimal amount);

    /**
     *  阿里PC支付异步通知
     * @param notifyData 请求数据
     */
    void asyncNotify(String notifyData);
}
