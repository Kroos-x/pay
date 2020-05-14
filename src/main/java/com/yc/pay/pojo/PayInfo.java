package com.yc.pay.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 功能描述：支付信息
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-05-13
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "pay_info_id", type = IdType.UUID)
    private String PayInfoId;
    /**
     * 用户id
     */
    private String sysUserId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付平台:0-支付宝,1-微信
     */
    private Integer payPlatform;
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    /**
     * 平台支付流水号
     */
    private String platformNumber;
    /**
     * 支付状态
     */
    private String platformState;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;



}
