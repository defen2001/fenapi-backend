package com.defen.fenapi.service.impl.inner;

import com.defen.fenapicommon.common.ErrorCode;
import com.defen.fenapi.exception.BusinessException;
import com.defen.fenapi.service.UserInterfaceInfoService;
import com.defen.fenapicommon.model.entity.UserInterfaceInfo;
import com.defen.fenapicommon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部接口服务实现类
 *
 * @author defen
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    /**
     * 调用接口统计
     *
     * @param interfaceInfoId 接口ID
     * @param userId          用户ID
     * @return boolean 是否执行成功
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 查询接口是否存在
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.lambdaQuery().eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId).eq(UserInterfaceInfo::getUserId, userId).one();
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口不存在");
        }
        // 修改调用次数
        return userInterfaceInfoService.lambdaUpdate()
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .eq(UserInterfaceInfo::getUserId, userId)
                .set(UserInterfaceInfo::getTotalNum, userInterfaceInfo.getTotalNum() + 1)
                .set(UserInterfaceInfo::getLeftNum, userInterfaceInfo.getLeftNum() - 1).update();
    }

    /**
     * 是否还有调用次数
     *
     * @param interfaceId 接口id
     * @param userId      用户id
     * @return UserInterfaceInfo 用户接口信息
     */
    @Override
    public UserInterfaceInfo hasLeftNum(Long interfaceId, Long userId) {
        return userInterfaceInfoService.lambdaQuery().eq(UserInterfaceInfo::getInterfaceInfoId, interfaceId).eq(UserInterfaceInfo::getUserId, userId).one();
    }

    /**
     * 添加默认的用户接口信息
     *
     * @param interfaceId 接口id
     * @param userId      用户id
     * @return Boolean 是否添加成功
     */
    @Override
    public Boolean addDefaultUserInterfaceInfo(Long interfaceId, Long userId) {
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        userInterfaceInfo.setUserId(userId);
        userInterfaceInfo.setInterfaceInfoId(interfaceId);
        userInterfaceInfo.setLeftNum(99999999);

        return userInterfaceInfoService.save(userInterfaceInfo);
    }

    /**
     * 检查用户是否有接口
     *
     * @param interfaceId 接口id
     * @param userId      用户id
     * @return UserInterfaceInfo 用户接口信息
     */
    @Override
    public UserInterfaceInfo checkUserHasInterface(long interfaceId, long userId) {
        if (interfaceId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return userInterfaceInfoService.lambdaQuery().eq(UserInterfaceInfo::getUserId, userId).eq(UserInterfaceInfo::getInterfaceInfoId, interfaceId).one();
    }

}
