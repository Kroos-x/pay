package com.yc.pay.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.yc.pay.config.constant.CommonConstant;
import com.yc.pay.config.propertie.AliParamProperties;
import com.yc.pay.config.propertie.WxParamProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
@Component
public class BestPayConfig {

    private final AliParamProperties aliParamProperties;
    private final WxParamProperties wxParamProperties;

    @Autowired
    public BestPayConfig(AliParamProperties aliParamProperties,WxParamProperties wxParamProperties){
        this.aliParamProperties = aliParamProperties;
        this.wxParamProperties = wxParamProperties;
    }

    @Bean
    public BestPayService bestPayService(WxPayConfig wxPayConfig){
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId(aliParamProperties.getAppId());
        aliPayConfig.setPrivateKey(CommonConstant.ALi_PC_PAY_PRIVATE_KEY);
        aliPayConfig.setAliPayPublicKey(CommonConstant.ALi_PC_PAY_PUBLIC_KEY);
        aliPayConfig.setNotifyUrl(aliParamProperties.getNotifyUrl());
        aliPayConfig.setReturnUrl(aliParamProperties.getReturnUrl());

        // 支付类, 所有方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        bestPayService.setAliPayConfig(aliPayConfig);
        return bestPayService;
    }

    @Bean
    public WxPayConfig wxPayConfig(){
        // 微信支付配置
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(wxParamProperties.getAppId());
        wxPayConfig.setMchId(wxParamProperties.getMchId());
        wxPayConfig.setMchKey(wxParamProperties.getMchKey());
        wxPayConfig.setNotifyUrl(wxParamProperties.getNotifyUrl());
        wxPayConfig.setReturnUrl(wxParamProperties.getReturnUrl());
        return wxPayConfig;
    }

}
