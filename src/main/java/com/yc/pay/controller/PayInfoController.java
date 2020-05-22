package com.yc.pay.controller;

import com.yc.pay.form.PayInfoForm;
import com.yc.pay.pojo.PayInfo;
import com.yc.pay.service.AliPayService;
import com.yc.pay.service.PayInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述：支付信息控制层
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
@RequestMapping("/payInfo")
public class PayInfoController {

    private final PayInfoService payInfoService;

    @Autowired
    public PayInfoController(PayInfoService payInfoService){
        this.payInfoService = payInfoService;
    }

    /**
     * 查询订单信息
     * @param orderNo 订单ID
     * @return 订单信息
     */
    @GetMapping("/payInfo")
    @ResponseBody
    public PayInfo payInfo(@RequestParam String orderNo){
        log.info("查询订单信息...");
        return this.payInfoService.payInfo(orderNo);
    }

}
