package com.yc.pay.controller;

import com.yc.pay.form.PayInfoForm;
import com.yc.pay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
     * 微信native支付预订单
     * @param form 请求信息
     * @return 支付页面
     */
    @GetMapping("/createForWxNative")
    public ModelAndView create(PayInfoForm form){
        return new ModelAndView ("createForWxNative",wxPayService.wxNativePay(form));
    }

    /**
     * 微信native支付异步通知
     * @param notifyData 请求数据
     */
    @PostMapping("/notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData){
        return wxPayService.asyncNotify(notifyData);
    }

}
