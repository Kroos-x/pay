package com.yc.pay;

import com.yc.pay.form.PayInfoForm;
import com.yc.pay.service.WxPayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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
public class WxPayTest extends PayApplicationTests{

    @Autowired
    private WxPayService wxPayService;

    @Test
    public void create (){
        PayInfoForm form = new PayInfoForm();
        form.setOrderNo("2020051201");
        form.setAmount(BigDecimal.valueOf(0.01));
        form.setSysUserId("jifsodjfiosd8908923");
        wxPayService.wxNativePay(form);
    }

}
