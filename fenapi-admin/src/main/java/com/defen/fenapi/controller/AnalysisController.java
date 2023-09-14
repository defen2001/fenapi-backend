package com.defen.fenapi.controller;

import com.defen.fenapi.annotation.AuthCheck;
import com.defen.fenapicommon.common.BaseResponse;
import com.defen.fenapicommon.common.ErrorCode;
import com.defen.fenapicommon.common.ResultUtils;
import com.defen.fenapicommon.constant.UserConstant;
import com.defen.fenapi.exception.BusinessException;
import com.defen.fenapicommon.model.vo.InterfaceInfoVO;
import com.defen.fenapi.service.InterfaceInfoService;
import com.defen.fenapi.service.UserInterfaceInfoService;
import com.defen.fenapicommon.model.entity.InterfaceInfo;
import com.defen.fenapicommon.model.entity.UserInterfaceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统计分析接口
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        //1. 查询调用次数前三的接口
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoService.listTopInvokeInterfaceInfo(3);
        if (userInterfaceInfoList.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口信息不存在");
        }
        //2.根据接口id分组
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream().collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        List<InterfaceInfo> list = interfaceInfoService.lambdaQuery().in(InterfaceInfo::getId, interfaceInfoIdObjMap.keySet()).list();
        if (list.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口信息不存在");
        }
        //3. 组装返回结果
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }

}
