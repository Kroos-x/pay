package com.yc.pay.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.yc.pay.config.BestPayConfig;
import com.yc.pay.config.constant.CommonEnum;
import com.yc.pay.form.PayInfoForm;
import com.yc.pay.service.PayInfoService;
import com.yc.pay.service.WxPayService;
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
 * @Datetime: 2020-05-11
 * @Version: 1.0.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WxPayServiceImpl implements WxPayService{

    private final BestPayConfig wxBestPayConfig;
    private final PayInfoService payInfoService;

    @Autowired
    public WxPayServiceImpl(BestPayConfig wxBestPayConfig,PayInfoService payInfoService){
        this.wxBestPayConfig = wxBestPayConfig;
        this.payInfoService = payInfoService;
    }

    @Override
    public Map<String,String> wxNativePay(PayInfoForm form) {
        // 支付信息入库
        form.setPayPlatform(CommonEnum.PayPlatform.WX.getCode());
        this.payInfoService.savePayInfo(form);
        Map map = new HashMap(16);
        BestPayService bestPayService = wxBestPayConfig.bestPayService();
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);
        payRequest.setOrderId(form.getOrderNo());
        payRequest.setOrderName("7223287-DeepLearning");
        // 固定支付0.01
        payRequest.setOrderAmount(BigDecimal.valueOf(0.01).doubleValue());
        PayResponse response = bestPayService.pay(payRequest);
        log.info("========= 微信native支付响应信息========");
        log.info("response={}",response);
        log.info("========= 微信native支付响应信息========");
        map.put("codeUrl",response.getCodeUrl());
        return map;
    }

    @Override
    public void asyncNotify(String notifyData) {
        // TODO: 2020/5/13
        /**
         * 1.签名校验
         * 2.金额校验
         * 3.修改订单状态
         * 4.返回数据给微信
         */
        BestPayService bestPayService = wxBestPayConfig.bestPayService();
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("payResponse={}",payResponse);
    }

    // =============== 子方法 ==================

}
