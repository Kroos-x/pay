package com.yc.pay.service;

import com.yc.pay.form.PayInfoForm;
import com.yc.pay.pojo.PayInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 功能描述：支付信息
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-05-13
 * @Version: 1.0.0
 *
 */
public interface PayInfoService extends IService<PayInfo> {

    /**
     * 保存支付信息
     * @param form 请求信息
     */
    void savePayInfo(PayInfoForm form);
}
