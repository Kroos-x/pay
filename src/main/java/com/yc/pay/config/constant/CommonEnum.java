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

    enum DelFlag implements CommonEnum{
        /**
         * 删除状态
         */
        DEL(1,"已删除"),
        NO_DEL(0,"未删除")
        ;

        private Integer code;
        private String  name;

        DelFlag(Integer code,String name){
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

    enum State implements CommonEnum{
        /**
         * 冻结状态
         */
        Disabled(1,"冻结"),
        enabled(0,"正常")
        ;

        private Integer code;
        private String  name;

        State(Integer code,String name){
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

}
