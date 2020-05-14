package com.yc.pay.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能描述：微信native支付请求信息
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-14
 * @Version: 1.0.0
 */
@Data
public class PayInfoForm {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 用户标识
     */
    private String sysUserId;

    // ========== 非请求参数 ============
    /**
     * 支付平台
     */
    private Integer payPlatform;


}
