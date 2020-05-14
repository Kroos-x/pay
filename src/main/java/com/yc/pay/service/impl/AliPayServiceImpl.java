package com.yc.pay.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.yc.pay.config.BestPayConfig;
import com.yc.pay.config.constant.CommonEnum;
import com.yc.pay.form.PayInfoForm;
import com.yc.pay.service.AliPayService;
import com.yc.pay.service.PayInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
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

    private final BestPayConfig wxBestPayConfig;
    private final PayInfoService payInfoService;

    @Autowired
    public AliPayServiceImpl(BestPayConfig wxBestPayConfig,PayInfoService payInfoService){
        this.wxBestPayConfig = wxBestPayConfig;
        this.payInfoService = payInfoService;
    }

    @Override
    public Map<String,String> pcPay(PayInfoForm payInfoForm) {
        payInfoForm.setPayPlatform(CommonEnum.PayPlatform.ALI.getCode());
        this.payInfoService.savePayInfo(payInfoForm);
        Map<String,String> map = new HashMap<>(16);
        BestPayService bestPayService = wxBestPayConfig.bestPayService();
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_PC);
        payRequest.setOrderId(payInfoForm.getOrderNo());
        payRequest.setOrderName("7223287-DeepLearning");
        // 固定0.01
        payRequest.setOrderAmount(BigDecimal.valueOf(0.01).doubleValue());
        PayResponse response = bestPayService.pay(payRequest);
        log.info("response={}",response);
        map.put("body",response.getBody());
        return map;
    }

    @Override
    public void asyncNotify(String notifyData) {
        log.info("回调数据");
        log.info("notifyData={}",notifyData);
        log.info("回调数据");
    }
}
