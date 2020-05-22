package com.yc.pay.service;

import com.yc.pay.form.PayInfoForm;

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
public interface WxPayService {

    /**
     * native 支付
     * @param form 请求信息
     * @return response
     */
    Map<String,String> wxNativePay(PayInfoForm form);

    /**
     *  微信native支付异步通知
     * @param notifyData 请求数据
     * @return 响应信息
     */
    String asyncNotify(String notifyData);

}
