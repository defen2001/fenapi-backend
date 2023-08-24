package com.defen.fenapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.defen.fenapicommon.model.entity.UserInterfaceInfo;

/**
* @author defen
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-07-30 23:25:07
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

}
