package com.yc.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.yc.pay.config.constant.CommonConstant;
import com.yc.pay.config.constant.CommonEnum;
import com.yc.pay.config.global.error.Error;
import com.yc.pay.config.global.error.ErrorException;
import com.yc.pay.config.propertie.EncodeProperties;
import com.yc.pay.config.propertie.OutSideUrlProperties;
import com.yc.pay.config.utils.EncoderUtil;
import com.yc.pay.config.utils.HttpClientUtil;
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

    private final BestPayService bestPayService;
    private final PayInfoService payInfoService;
    private final EncodeProperties encodeProperties;
    private final OutSideUrlProperties outSideUrlProperties;

    @Autowired
    public AliPayServiceImpl(PayInfoService payInfoService,BestPayService bestPayService,
                             EncodeProperties encodeProperties,OutSideUrlProperties outSideUrlProperties){
        this.bestPayService = bestPayService;
        this.payInfoService = payInfoService;
        this.encodeProperties = encodeProperties;
        this.outSideUrlProperties = outSideUrlProperties;
    }

    @Override
    public Map<String,String> pcPay(PayInfoForm payInfoForm) {
        payInfoForm.setPayPlatform(CommonEnum.PayPlatform.ALI.getCode());
        this.payInfoService.savePayInfo(payInfoForm);
        Map<String,String> map = new HashMap<>(16);
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
        /**
         *  response=PayResponse(prePayParams=null, payUri=null, appId=null, timeStamp=null, nonceStr=null, packAge=null, signType=null, paySign=null, orderAmount=null, orderId=null, outTradeNo=null, mwebUrl=null, body=<form id='bestPayForm' name="punchout_form" method="post" action="https://openapi.alipay.com//gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=Oh4Jf94NhfuUauFRX2xu59Hy4fbRjRTLwTIfVoEBD%2FPFp6s7X6%2BEiYIxsCeHynHTEZWtNHH57r56m%2BCEAkUPzcmD9fphdfW9oqvo6FXWZm8EH%2BkeHbzjqOqUWvIL1DZtntMrmRm4D37%2B2m5ZIJjfehkddSt6EMtBh53VR3JvRC%2FmOpTlcVaezi6JdLg968YNjJd0RKXK5GOCP6O7%2FgTKPA95otK6pG6Oh2eFP4V8c9TxT%2FiogpEGdfdmAmcIjFCudf6oz%2FQf9uhY9DLjD%2BVC4kW68SillzGf%2BMVG2hwdf7IR7R2RlAOeHDz5Mu8nJxK5KW6W7QtpyHaWDPaVYEaNmw%3D%3D&return_url=http%3A%2F%2F127.0.0.1&notify_url=+http%3A%2F%2Fkcrzqz.natappfree.cc%2FaliPay%2Fnotify&app_id=2018062960540016&sign_type=RSA2&version=1.0&timestamp=2020-05-14+21%3A02%3A26">
         * <input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;2020051402&quot;,&quot;total_amount&quot;:&quot;0.01&quot;,&quot;subject&quot;:&quot;7223287-DeepLearning&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
         * <input type="submit" value="立即支付" style="display:none" >
         * </form>
         * <script>document.getElementById('bestPayForm').submit();</script>, codeUrl=null, attach=null, payPlatformEnum=null)
         */
    }

    @Override
    public String asyncNotify(String notifyData) {
        log.info("========== 阿里PC支付回调响应信息 ===========");
        log.info("notifyData={}",notifyData);
        /**
         * notifyData=gmt_create=2020-05-14+21%3A03%3A28&charset=utf-8&gmt_payment=2020-05-14+21%3A03%3A35&notify_time=2020-05-14+21%3A03%3A36&subject=7223287-DeepLearning&sign=SeXoCNSohtuJdNBAT3g%2FUjCZNOcZMUTmQW7PCZ2DG6wv0Tb6AeVVE%2F38jD%2FMt85k1XfyBVckwR6zunG9s81j8drbyhkfg2SCwVSknZtRqLkfrEUKfnqgLSHDW3BZdUBUMHaRLj5qAYRKeNCrvtjFmSGKB7LTPYR2fchhftCE34XnuiZ5Lx9gMw24CqgLg0s%2FohuCBihj00z0hHHnHutINAavIbWBGhytaCuoZr2g6RZTLGecpYoBeQ%2FZcyhnLVxciYsj4SKhdgvz14VkJafZtwXV6lQ0ikFydLLcpH4rcFCW2mtk9sgYzzW3GeRS0ysEd6UPedSVePcbUTu0qu%2B8Yw%3D%3D&buyer_id=2088912639047236&invoice_amount=0.01&version=1.0&notify_id=2020051400222210335047231430560377&fund_bill_list=%5B%7B%22amount%22%3A%220.01%22%2C%22fundChannel%22%3A%22PCREDIT%22%7D%5D&notify_type=trade_status_sync&out_trade_no=2020051402&total_amount=0.01&trade_status=TRADE_SUCCESS&trade_no=2020051422001447231451835318&auth_app_id=2018062960540016&receipt_amount=0.01&point_amount=0.00&buyer_pay_amount=0.01&app_id=2018062960540016&sign_type=RSA2&seller_id=2088721207163940
         */
        log.info("========== 阿里PC支付回调响应信息 ===========");
        // 1.签名校验
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
            // 4.推送消息给业务系统
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNo",payInfo.getOrderNo());
            jsonObject.put("payType","0");
            jsonObject.put("payTime",payInfo.getCreateTime());
            jsonObject.put("sysUserId",payInfo.getSysUserId());
            // 签名
            String sign = EncoderUtil.md5(jsonObject.toJSONString()+encodeProperties.getSecretKey());
            // 密文
            String requestData = EncoderUtil.rsaEncrypt(jsonObject.toJSONString(), CommonConstant.RSA_PUBLIC_KEY);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("signData", sign);
            jsonObject1.put("requestData", requestData);
            HttpClientUtil.doPostJson(outSideUrlProperties.getSyncCallPayUrl(),jsonObject1.toJSONString());
        }
        // 4.返回数据给支付宝
        return "success";
    }
}
