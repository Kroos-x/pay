package com.yc.pay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.pay.pojo.PayInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
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

    @GetMapping("/test1")
    @ResponseBody
    public PayInfo test1(PayInfo form){
        PayInfo payInfo = new PayInfo();
        payInfo.setSysUserId("124321423");
        payInfo.setOrderNo("23434");
        payInfo.setPayPlatform(1);
        payInfo.setUpdateTime(LocalDateTime.now());
        return payInfo;
    }

    @PostMapping("/test2")
    @ResponseBody
    public PayInfo test2(PayInfo form){
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
    public String test3(HttpServletRequest request){
        Map<String, String> tranMap = new HashMap<>();
        try {
            request.setCharacterEncoding("UTF-8");
            // 获取请求数据
            String signData = request.getParameter("signData");
            String transData = request.getParameter("transData");
            //将报文中%2B转换为+
            transData = transData.replaceAll("%2B", "+");
            //解析请求数据map
            ObjectMapper mapper = new ObjectMapper();
            tranMap = mapper.readValue(transData, Map.class);
            //获取报文密文信息
            String notifyData = tranMap.get("notifyData");
            //获取签名数据密文信息
            String signMsg = tranMap.get("signData");
            //报文解密
            // byte[] bSrc = cn.com.infosec.icbc.ReturnValue.base64dec(notifyData.getBytes());
            // //签名数据解密
            // byte[] bSign = cn.com.infosec.icbc.ReturnValue.base64dec(signMsg.getBytes());
            // //获取验签公钥
            // // File file = new File(ICBCConstant.cert_path);
            // File file = new File(ResourceUtils.getURL("classpath:templates/").getPath() + File.separator + "pay2cf.dccnet.com.cn.cer");
            // byte[] tempByte = new byte[100];
            // if (file.exists()) {
            //     int byteread = 0;
            //     FileInputStream in = new FileInputStream(file);
            //     tempByte = new byte[in.available()];
            //     in.read(tempByte);
            // }
            // //验签公钥解密
            // byte[] EncCert = cn.com.infosec.icbc.ReturnValue.base64enc(tempByte);
            // //验签 0为验签通过  其余为验签失败
            // int verifyResult = cn.com.infosec.icbc.ReturnValue.verifySign(bSrc, bSrc.length, tempByte, bSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
