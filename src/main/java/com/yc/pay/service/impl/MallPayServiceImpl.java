package com.yc.pay.service.impl;

import com.yc.pay.pojo.MallPay;
import com.yc.pay.dao.MallPayMapper;
import com.yc.pay.service.MallPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MallPayServiceImpl extends ServiceImpl<MallPayMapper, MallPay> implements MallPayService {

}
