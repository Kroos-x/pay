package com.yc.pay.config.global.error;

/**
 * 功能描述：异常代码枚举
 *  [throw new ErrorException(DlError.RoleNoDelete);]
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-03-22
 * @Version: 1.0.0
 */
public enum Error implements IError {

    /**
     * 订单异常
     */
    OrderDuplicate(500, 50100, "订单号已存在，请勿重复提交!"),
    OrderError(500, 50101, "订单号错误!"),
    AmountError(500, 50102, "异步通知中的订单金额与实际支付金额不同!"),
    ;

    /**
     * HTTP状态码
     */
    private int httpStatusCode;
    /**
     * 自定义错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String msg;

    Error(int httpStatusCode, int code, String msg) {
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
