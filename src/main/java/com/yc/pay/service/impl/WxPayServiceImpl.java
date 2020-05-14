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
    public String asyncNotify(String notifyData) {
        log.info("========== 微信native支付回调响应信息 ===========");
        log.info("notifyData={}",notifyData);
        /**
         * notifyData=gmt_create=2020-05-13+22%3A40%3A21&charset=utf-8&gmt_payment=2020-05-13+22%3A40%3A34&notify_time=2020-05-13+22%3A40%3A35&subject=7223287-DeepLearning&sign=bXLQfUXcIEVTCfcn1gWMfUd%2BBUu1EtLpBdeGMnu8XGnmNQX27miXsik2Y7qm2Pnj83faLkiYIB4725EeccbF0cf2uEZJW11DVdBHmPIVdebF4Rln%2BdPer9EMpNlFWsw1Q%2FwVC3G2ndh4zOVVcYS%2BhEIcs8n4dMWm4zPrKvwOzgBDJXol01je6wVBn9D5safHMG1ndcN%2B7KHDegLMVdMA9%2BF5QPIGC0HgVWvZ8q1%2BPaP%2B8dAzPrgwKAyuYNMu6Xwj6vhQIDeCFPYpH%2BUV5p1RY7MjIVkeL153%2Be4lsbO8aV0ciq7L13bINKqPnfp3BJI%2BnG3fpioV0ks8p0HMUIrvCA%3D%3D&buyer_id=2088812404543308&invoice_amount=0.01&version=1.0&notify_id=2020051300222224034043301420428141&fund_bill_list=%5B%7B%22amount%22%3A%220.01%22%2C%22fundChannel%22%3A%22ALIPAYACCOUNT%22%7D%5D&notify_type=trade_status_sync&out_trade_no=2020051302&total_amount=0.01&trade_status=TRADE_SUCCESS&trade_no=2020051322001443301423546798&auth_app_id=2018062960540016&receipt_amount=0.01&point_amount=0.00&buyer_pay_amount=0.01&app_id=2018062960540016&sign_type=RSA2&seller_id=2088721207163940
         */
        log.info("========== 微信native支付回调响应信息 ===========");
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
        // 4.返回数据给微信
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }


}
