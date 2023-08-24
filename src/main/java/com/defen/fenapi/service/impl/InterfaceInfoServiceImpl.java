package com.defen.fenapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.defen.fenapi.exception.BusinessException;
import com.defen.fenapi.mapper.InterfaceInfoMapper;
import com.defen.fenapi.common.ErrorCode;
import com.defen.fenapi.service.InterfaceInfoService;
import com.defen.fenapicommon.model.entity.InterfaceInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author defen
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-02-11 19:42:29
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        String method = interfaceInfo.getMethod();

        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, description, url, requestHeader, responseHeader, method)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(description) && description.length() > 50){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口名称过长");
        }
    }
}




