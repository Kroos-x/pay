package com.yc.pay;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yc.pay.config.constant.CommonConstant;
import com.yc.pay.config.propertie.EncodeProperties;
import com.yc.pay.config.propertie.OutSideUrlProperties;
import com.yc.pay.config.utils.EncoderUtil;
import com.yc.pay.config.utils.HttpClientUtil;
import com.yc.pay.dao.PayInfoMapper;
import com.yc.pay.pojo.PayInfo;
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

    @Autowired
    private PayInfoMapper payInfoMapper;

    @Test
    public void contextLoads() {
        PayInfo payInfo =this.payInfoMapper.selectOne(new LambdaQueryWrapper<PayInfo>()
            .eq(PayInfo::getOrderNo,"20200527205527643000001")
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo",payInfo.getOrderNo());
        jsonObject.put("payType","1");
        jsonObject.put("payTime",payInfo.getCreateTime().toString().replace("T"," "));
        jsonObject.put("sysUserId",payInfo.getSysUserId());
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
