package com.defen.fenapi.service.impl.inner;

import com.defen.fenapi.service.InterfaceInfoService;
import com.defen.fenapicommon.model.entity.InterfaceInfo;
import com.defen.fenapicommon.service.InnerInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部接口服务实现类
 *
 * @author defen
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public InterfaceInfo getInterfaceInfo(String path, String method) {
        return interfaceInfoService.query()
                .eq("url", path)
                .eq("method", method)
                .one();
    }

}
