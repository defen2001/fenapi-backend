package com.defen.fenapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.defen.fenapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author defen
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2023-07-30 23:25:07
* @Entity com.defen.fenapi.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    /**
     * 获取接口调用排名前 n 的接口信息
     *
     * @param limit 前几名
     * @return List<InterfaceInfoVO>
     */
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




