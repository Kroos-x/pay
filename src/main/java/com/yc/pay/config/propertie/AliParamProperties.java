package com.yc.pay.config.propertie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述：微信认证参数配置
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-05-14
 * @Version: 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay.auth.ali")
public class AliParamProperties {

    /**
     * AppId
     */
    private String appId;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 返回地址
     */
    private String returnUrl;

}
