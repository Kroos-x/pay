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
    public void asyncNotify(@RequestBody String notifyData){
        // TODO: 2020/5/13 接收参数方式
        log.info("================异步回调================");
        /**
         * notifyData=gmt_create=2020-05-13+22%3A40%3A21&charset=utf-8&gmt_payment=2020-05-13+22%3A40%3A34&notify_time=2020-05-13+22%3A40%3A35&subject=7223287-DeepLearning&sign=bXLQfUXcIEVTCfcn1gWMfUd%2BBUu1EtLpBdeGMnu8XGnmNQX27miXsik2Y7qm2Pnj83faLkiYIB4725EeccbF0cf2uEZJW11DVdBHmPIVdebF4Rln%2BdPer9EMpNlFWsw1Q%2FwVC3G2ndh4zOVVcYS%2BhEIcs8n4dMWm4zPrKvwOzgBDJXol01je6wVBn9D5safHMG1ndcN%2B7KHDegLMVdMA9%2BF5QPIGC0HgVWvZ8q1%2BPaP%2B8dAzPrgwKAyuYNMu6Xwj6vhQIDeCFPYpH%2BUV5p1RY7MjIVkeL153%2Be4lsbO8aV0ciq7L13bINKqPnfp3BJI%2BnG3fpioV0ks8p0HMUIrvCA%3D%3D&buyer_id=2088812404543308&invoice_amount=0.01&version=1.0&notify_id=2020051300222224034043301420428141&fund_bill_list=%5B%7B%22amount%22%3A%220.01%22%2C%22fundChannel%22%3A%22ALIPAYACCOUNT%22%7D%5D&notify_type=trade_status_sync&out_trade_no=2020051302&total_amount=0.01&trade_status=TRADE_SUCCESS&trade_no=2020051322001443301423546798&auth_app_id=2018062960540016&receipt_amount=0.01&point_amount=0.00&buyer_pay_amount=0.01&app_id=2018062960540016&sign_type=RSA2&seller_id=2088721207163940
         */
        log.info("================异步回调================");
        aliPayService.asyncNotify(notifyData);
    }

}
