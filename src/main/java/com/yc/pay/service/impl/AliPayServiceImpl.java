package com.yc.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.yc.pay.config.BestPayConfig;
import com.yc.pay.config.constant.CommonEnum;
import com.yc.pay.config.global.error.Error;
import com.yc.pay.config.global.error.ErrorException;
import com.yc.pay.form.PayInfoForm;
import com.yc.pay.pojo.PayInfo;
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
        log.info("========= 支付宝PC支付响应信息========");
        log.info("response={}",response);
        log.info("========= 支付宝PC支付响应信息========");
        map.put("body",response.getBody());
        return map;
    }

    @Override
    public String asyncNotify(String notifyData) {
        log.info("========== 阿里PC支付回调响应信息 ===========");
        log.info("notifyData={}",notifyData);
        log.info("========== 阿里PC支付回调响应信息 ===========");
        // 1.签名校验
        BestPayService bestPayService = wxBestPayConfig.bestPayService();
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        // 2.金额校验
        PayInfo payInfo = this.payInfoService.getBaseMapper().selectOne(new LambdaQueryWrapper<PayInfo>()
                .eq(PayInfo::getOrderNo,payResponse.getOrderId())
        );
        if(ObjectUtils.isNull(payInfo)){
            throw new ErrorException(Error.OrderError);
        }
        if(!payInfo.getPayState().equals(CommonEnum.OrderStatus.SUCCESS)){
            if(payInfo.getPayAmount().compareTo(BigDecimal.valueOf(payResponse.getOrderAmount())) != 0){
                throw new ErrorException(Error.AmountError);
            }
            // 3.修改订单状态
            payInfo.setPayState(CommonEnum.OrderStatus.SUCCESS.getName());
            payInfo.setPlatformNumber(payResponse.getOutTradeNo());
            this.payInfoService.getBaseMapper().updateById(payInfo);
        }
        // 4.返回数据给支付宝
        return "success";
    }
}
