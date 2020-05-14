package com.yc.pay.config.constant;

/**
 * 功能描述：公用枚举参数
 *  枚举充当常量
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-04-16
 * @Version: 1.0.0
 */
public interface CommonEnum {

    enum PayPlatform implements CommonEnum{
        /**
         * 支付平台
         */
        WX(0,"微信"),
        ALI(1,"支付宝")
        ;

        private Integer code;
        private String  name;

        PayPlatform(Integer code,String name){
            this.code = code;
            this.name = name;
        }

        public Integer getCode(){
            return code;
        }

        public String getName(){
            return name;
        }
    }

    enum OrderStatus implements CommonEnum{
        /**
         * 支付平台
         */
        SUCCESS("支付成功"),
        REFUND("转入退款"),
        NOTPAY("未支付"),
        CLOSED("已关闭"),
        REVOKED("已撤销（刷卡支付）"),
        USERPAYING("用户支付中"),
        PAYERROR("支付失败"),
        UNKNOW("未知状态")
        ;
        private String  name;

        OrderStatus(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

}
