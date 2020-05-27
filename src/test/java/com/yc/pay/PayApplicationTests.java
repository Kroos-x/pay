package com.yc.pay;

import com.alibaba.fastjson.JSONObject;
import com.yc.pay.config.constant.CommonConstant;
import com.yc.pay.config.propertie.EncodeProperties;
import com.yc.pay.config.propertie.OutSideUrlProperties;
import com.yc.pay.config.utils.EncoderUtil;
import com.yc.pay.config.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayApplicationTests {

    @Autowired
    private EncodeProperties encodeProperties;

    @Autowired
    private OutSideUrlProperties outSideUrlProperties;

    @Test
    public void contextLoads() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo","20200525214113278000001");
        jsonObject.put("payType","1");
        jsonObject.put("payTime","2020-05-26 12:12:12");
        jsonObject.put("sysUserId","3946d9f631e3cd4619afcb9512842435");
        // 签名
        String sign = EncoderUtil.md5(jsonObject.toJSONString()+encodeProperties.getSecretKey());
        // 密文
        String requestData = EncoderUtil.rsaEncrypt(jsonObject.toJSONString(), CommonConstant.RSA_PUBLIC_KEY);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("signData", sign);
        jsonObject1.put("requestData", requestData);
        String result = HttpClientUtil.doPostJson(outSideUrlProperties.getSyncCallPayUrl(),jsonObject1.toJSONString());
        log.info(result);
    }

}
