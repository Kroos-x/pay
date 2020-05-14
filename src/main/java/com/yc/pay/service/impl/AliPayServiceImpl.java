package com.yc.pay.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.yc.pay.config.WxBestPayConfig;
import com.yc.pay.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AliPayServiceImpl implements AliPayService {

    private final WxBestPayConfig wxBestPayConfig;

    @Autowired
    public AliPayServiceImpl(WxBestPayConfig wxBestPayConfig){
        this.wxBestPayConfig = wxBestPayConfig;
    }

    @Override
    public PayResponse pcPay(String orderId, BigDecimal amount) {
        // TODO: 2020/5/13 订单信息入库
        // TODO: 2020/5/13 重复订单号生成支付要有校验
        BestPayService bestPayService = wxBestPayConfig.bestPayService();
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_PC);
        payRequest.setOrderId(orderId);
        payRequest.setOrderName("7223287-DeepLearning");
        payRequest.setOrderAmount(amount.doubleValue());
        PayResponse response = bestPayService.pay(payRequest);
        log.info("response={}",response);
        return response;
    }

    @Override
    public void asyncNotify(String notifyData) {
        log.info("回调数据");
        log.info("notifyData={}",notifyData);
        log.info("回调数据");
    }
}
