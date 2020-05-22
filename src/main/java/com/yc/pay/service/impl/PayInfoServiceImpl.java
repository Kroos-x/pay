package com.yc.pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.pay.config.constant.CommonEnum;
import com.yc.pay.config.global.error.Error;
import com.yc.pay.config.global.error.ErrorException;
import com.yc.pay.dao.PayInfoMapper;
import com.yc.pay.form.PayInfoForm;
import com.yc.pay.pojo.PayInfo;
import com.yc.pay.service.PayInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述：
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
@Service
@Transactional(rollbackFor = Exception.class)
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements PayInfoService {

    @Override
    public void savePayInfo(PayInfoForm form) {
        int count = this.baseMapper.selectCount(new LambdaQueryWrapper<PayInfo>()
                .eq(PayInfo::getOrderNo,form.getOrderNo())
        );
        if(count > 0){
            throw new ErrorException(Error.OrderDuplicate);
        }
        PayInfo payInfo = new PayInfo();
        payInfo.setSysUserId(form.getSysUserId());
        payInfo.setOrderNo(form.getOrderNo());
        payInfo.setPayPlatform(form.getPayPlatform());
        payInfo.setPayAmount(form.getAmount());
        payInfo.setPayState(CommonEnum.OrderStatus.NOTPAY.getCode());
        this.baseMapper.insert(payInfo);
    }

    @Override
    public PayInfo payInfo(String orderNo) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<PayInfo>()
            .eq(PayInfo::getOrderNo,orderNo)
        );
    }


}
