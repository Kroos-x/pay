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
        ALI(0,"支付宝"),
        WX(1,"微信")

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
        SUCCESS("SUCCESS","支付成功"),
        REFUND("REFUND","转入退款"),
        NOTPAY("NOTPAY","未支付"),
        CLOSED("CLOSED","已关闭"),
        REVOKED("REVOKED","已撤销(刷卡支付）"),
        USERPAYING("USERPAYING","用户支付中"),
        PAYERROR("PAYERROR","支付失败"),
        UNKNOW("UNKNOW","未知状态")
        ;

        private String code;
        private String name;

        OrderStatus(String code,String name){
            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return code;
        }

        public String getName(){
            return name;
        }
    }

}
