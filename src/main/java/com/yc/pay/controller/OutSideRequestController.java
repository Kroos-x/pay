package com.yc.pay.controller;

import com.alibaba.fastjson.JSON;
import com.yc.pay.config.constant.CommonConstant;
import com.yc.pay.config.propertie.EncodeProperties;
import com.yc.pay.config.utils.EncoderUtil;
import com.yc.pay.pojo.PayInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：站外接口对接
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-23
 * @Version: 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/sideRequest")
public class OutSideRequestController {

    private final EncodeProperties encodeProperties;

    @Autowired
    public OutSideRequestController(EncodeProperties encodeProperties){
        this.encodeProperties = encodeProperties;
    }

    @GetMapping("/test1")
    @ResponseBody
    public PayInfo test1(PayInfo form) {
        PayInfo payInfo = new PayInfo();
        payInfo.setSysUserId("124321423");
        payInfo.setOrderNo("23434");
        payInfo.setPayPlatform(1);
        payInfo.setUpdateTime(LocalDateTime.now());
        return payInfo;
    }

    @PostMapping("/test2")
    @ResponseBody
    public PayInfo test2(PayInfo form) {
        PayInfo payInfo = new PayInfo();
        payInfo.setPayInfoId("12312312");
        payInfo.setSysUserId("124321423");
        payInfo.setOrderNo("23434");
        payInfo.setPayPlatform(1);
        payInfo.setUpdateTime(LocalDateTime.now());
        return payInfo;
    }

    @PostMapping("/test3")
    @ResponseBody
    public String test3(HttpServletRequest request) {
        try {
            String aesKey = encodeProperties.getAesKey();
            request.setCharacterEncoding(CommonConstant.CHARSET_UTF_8);
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
	        while ((inputStr = streamReader.readLine()) != null){
                responseStrBuilder.append(inputStr);
            }
            Map<String, String> map = JSON.parseObject(responseStrBuilder.toString(), Map.class);
            //获取报文密文信息
            String notifyData = map.get("requestData");
            //报文解密
            String deStr = EncoderUtil.aesDecrypt(notifyData,aesKey);
            // 验签
            String sign = EncoderUtil.md5(deStr+encodeProperties.getSecretKey());
            //获取签名数据密文信息
            String signMsg = map.get("signData");
            if(StringUtils.equals(signMsg,sign)){
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }



}
