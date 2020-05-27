package com.yc.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lly835.bestpay.config.WxPayConfig;
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

    private final BestPayService bestPayService;
    private final PayInfoService payInfoService;
    private final WxPayConfig wxPayConfig;
    private final EncodeProperties encodeProperties;
    private final OutSideUrlProperties outSideUrlProperties;

    @Autowired
    public WxPayServiceImpl(BestPayService bestPayService,PayInfoService payInfoService,
                            WxPayConfig wxPayConfig,EncodeProperties encodeProperties,OutSideUrlProperties outSideUrlProperties){
        this.bestPayService = bestPayService;
        this.outSideUrlProperties = outSideUrlProperties;
        this.payInfoService = payInfoService;
        this.wxPayConfig = wxPayConfig;
        this.encodeProperties = encodeProperties;
    }

    @Override
    public Map<String,String> wxNativePay(PayInfoForm form) {
        // 支付信息入库
        form.setPayPlatform(CommonEnum.PayPlatform.WX.getCode());
        this.payInfoService.savePayInfo(form);
        Map map = new HashMap(16);
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);
        payRequest.setOrderId(form.getOrderNo());
        payRequest.setOrderName("7223287-DeepLearning");
        // 固定支付0.01
        payRequest.setOrderAmount(BigDecimal.valueOf(0.01).doubleValue());
        PayResponse response = bestPayService.pay(payRequest);
        log.info("========= 微信native支付响应信息========");
        log.info("response={}",response);
        /**
         * response=PayResponse(prePayParams=null, payUri=null, appId=wxd898fcb01713c658, timeStamp=1589460872, nonceStr=CSeA8C4kRjojv3YZ, packAge=prepay_id=wx1420543176719937376c7ac81219085700, signType=MD5, paySign=E17564BE304EDBD0300D67D8106235B6, orderAmount=null, orderId=null, outTradeNo=null, mwebUrl=null, body=null, codeUrl=weixin://wxpay/bizpayurl?pr=IZwJlEO, attach=null, payPlatformEnum=null)
         */
        log.info("========= 微信native支付响应信息========");
        map.put("codeUrl",response.getCodeUrl());
        map.put("orderNo", form.getOrderNo());
        map.put("returnUrl", wxPayConfig.getReturnUrl());
        return map;
        /**
         * <return_msg><![CDATA[OK]]></return_msg>
         * <appid><![CDATA[wxd898fcb01713c658]]></appid>
         * <mch_id><![CDATA[1483469312]]></mch_id>
         * <nonce_str><![CDATA[c8uUFfSWFgepeyGP]]></nonce_str>
         * <sign><![CDATA[C21725E811AA20A11C935DC0295D02AF]]></sign>
         * <result_code><![CDATA[SUCCESS]]></result_code>
         * <prepay_id><![CDATA[wx1420543176719937376c7ac81219085700]]></prepay_id>
         * <trade_type><![CDATA[NATIVE]]></trade_type>
         * <code_url><![CDATA[weixin://wxpay/bizpayurl?pr=IZwJlEO]]></code_url>
         * </xml>
         *
         */
    }

    @Override
    public String asyncNotify(String notifyData) {
        log.info("========== 微信native支付回调响应信息 ===========");
        log.info("notifyData={}",notifyData);
        log.info("========== 微信native支付回调响应信息 ===========");
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
            jsonObject.put("payType","1");
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
        // 4.返回数据给微信
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
        /**
         * <bank_type><![CDATA[OTHERS]]></bank_type>
         * <cash_fee><![CDATA[1]]></cash_fee>
         * <fee_type><![CDATA[CNY]]></fee_type>
         * <is_subscribe><![CDATA[Y]]></is_subscribe>
         * <mch_id><![CDATA[1483469312]]></mch_id>
         * <nonce_str><![CDATA[GrPn5A7Zaxw81xnN]]></nonce_str>
         * <openid><![CDATA[oTgZpwX_ElaAqWKIG6X76n4oxLeY]]></openid>
         * <out_trade_no><![CDATA[2020051401]]></out_trade_no>
         * <result_code><![CDATA[SUCCESS]]></result_code>
         * <return_code><![CDATA[SUCCESS]]></return_code>
         * <sign><![CDATA[E84B346265C74BECAD2B8A51255D1BA6]]></sign>
         * <time_end><![CDATA[20200514205851]]></time_end>
         * <total_fee>1</total_fee>
         * <trade_type><![CDATA[NATIVE]]></trade_type>
         * <transaction_id><![CDATA[4200000557202005142138256998]]></transaction_id>
         * </xml>
         */
    }

}
