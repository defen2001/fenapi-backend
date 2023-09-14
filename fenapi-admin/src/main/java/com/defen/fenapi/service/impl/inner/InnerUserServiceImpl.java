package com.defen.fenapi.service.impl.inner;

import com.defen.fenapi.service.UserService;
import com.defen.fenapicommon.model.entity.User;
import com.defen.fenapicommon.service.InnerUserService;
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
    private UserService userService;


    @Override
    public User getInvokeUser(String accessKey) {
        return userService.query()
                .eq("accessKey", accessKey)
                .one();
    }
}
