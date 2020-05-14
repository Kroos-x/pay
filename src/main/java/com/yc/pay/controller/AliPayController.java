package com.yc.pay.controller;

import com.yc.pay.form.PayInfoForm;
import com.yc.pay.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述：支付宝支付控制层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-13
 * @Version: 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/aliPay")
public class AliPayController {

    private final AliPayService aliPayService;

    @Autowired
    public AliPayController(AliPayService aliPayService){
        this.aliPayService = aliPayService;
    }
    /**
     * 支付宝支付预订单
     * @param payInfoForm 请求信息
     * @return 支付页面
     */
    @GetMapping("/createForAliNative")
    public ModelAndView create(PayInfoForm payInfoForm){
        return new ModelAndView ("createForAliPayPc",aliPayService.pcPay(payInfoForm));
    }

    /**
     * 支付异步通知
     * @param notifyData 请求数据
     */
    @PostMapping("/notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData){
        // TODO: 2020/5/13 接收参数方式
        return aliPayService.asyncNotify(notifyData);
    }

}
