package com.defen.fenapi.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.defen.fenapi.common.ErrorCode;
import com.defen.fenapi.exception.BusinessException;
import com.defen.fenapi.mapper.UserMapper;
import com.defen.fenapicommon.model.entity.User;
import com.defen.fenapicommon.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部接口服务实现类
 *
 * @author defen
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;

    // [编程知识星球](https://yupi.icu) 零基础快速入门编程，不走弯路！30+ 原创学习路线和专栏、1000+ 编程精华文章、500+ 编程学习指南、20T+ 编程资源汇总

    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);
        return userMapper.selectOne(queryWrapper);
    }
}
