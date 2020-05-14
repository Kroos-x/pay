package com.yc.pay.controller;

import com.lly835.bestpay.model.PayResponse;
import com.yc.pay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：微信支付 控制层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-11
 * @Version: 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/wxPay")
public class WxPayController {

    private final WxPayService wxPayService;

    @Autowired
    public WxPayController (WxPayService wxPayService){
        this.wxPayService = wxPayService;
    }

    /**
     * 微信支付预订单
     * @param orderId 订单号
     * @param amount 支付金额
     * @return 支付页面
     */
    @GetMapping("/createForWxNative")
    public ModelAndView create(@RequestParam("orderId")String orderId, @RequestParam("amount") BigDecimal amount){
        PayResponse response = wxPayService.wxNativePay(orderId, amount);
        Map map = new HashMap();
        map.put("codeUrl",response.getCodeUrl());
        return new ModelAndView ("createForWxNative",map);
    }

    /**
     * 微信native支付异步通知
     * @param notifyData 请求数据
     */
    @PostMapping("/notify")
    @ResponseBody
    public void asyncNotify(@RequestBody String notifyData){
        // TODO: 2020/5/13 接收参数方式
        log.info("================异步回调================");
        log.info("================异步回调================");
        wxPayService.asyncNotify(notifyData);
    }


}
