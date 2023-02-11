package com.defen.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.defen.project.model.entity.InterfaceInfo;

/**
* @author defen
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-02-11 19:42:29
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
