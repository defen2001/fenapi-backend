package com.defen.fenapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.defen.fenapicommon.common.ErrorCode;
import com.defen.fenapicommon.constant.CommonConstant;
import com.defen.fenapi.exception.BusinessException;
import com.defen.fenapi.mapper.UserInterfaceInfoMapper;
import com.defen.fenapicommon.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.defen.fenapi.service.UserInterfaceInfoService;
import com.defen.fenapi.utils.SqlUtils;
import com.defen.fenapicommon.model.entity.UserInterfaceInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author defen
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
 * @createDate 2023-07-30 23:25:07
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();

        List<UserInterfaceInfo> list = this.lambdaQuery()
                .eq(UserInterfaceInfo::getUserId, userId)
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .list();
        if (!list.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "该用户已经拥有该接口");
        }
        // 创建时，参数不能为空
        if (add) {
            if (userId == null || interfaceInfoId == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
    }

    /**
     * 获取查询包装类
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        if (interfaceInfoQueryRequest == null) {
            return queryWrapper;
        }

        String searchText = interfaceInfoQueryRequest.getSearchText();
        Long id = interfaceInfoQueryRequest.getId();
        Long userId = interfaceInfoQueryRequest.getUserId();
        Long interfaceInfoId = interfaceInfoQueryRequest.getInterfaceInfoId();
        Integer totalNum = interfaceInfoQueryRequest.getTotalNum();
        Integer leftNum = interfaceInfoQueryRequest.getLeftNum();
        Integer status = interfaceInfoQueryRequest.getStatus();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();

        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.like("name", searchText);
        }
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(totalNum), "totalNum", totalNum);
        queryWrapper.eq(ObjectUtils.isNotEmpty(leftNum), "leftNum", leftNum);
        queryWrapper.eq(ObjectUtils.isNotEmpty(interfaceInfoId), "interfaceInfoId", interfaceInfoId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status), "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    /**
     * 分页获取接口信息封装
     *
     * @param userInterfaceInfoPage
     * @param request
     * @return
     */
    @Override
    public Page<UserInterfaceInfo> getUserInterfaceInfoVOPage(Page<UserInterfaceInfo> userInterfaceInfoPage, HttpServletRequest request) {
        List<UserInterfaceInfo> interfaceInfoList = userInterfaceInfoPage.getRecords();
        Page<UserInterfaceInfo> interfaceInfoVOPage = new Page<>(userInterfaceInfoPage.getCurrent(), userInterfaceInfoPage.getSize(), userInterfaceInfoPage.getTotal());
        if (CollectionUtils.isEmpty(interfaceInfoList)) {
            return interfaceInfoVOPage;
        }
        interfaceInfoVOPage.setRecords(interfaceInfoList);
        return interfaceInfoVOPage;
    }

    /**
     * 获取接口调用排名前 n 的接口信息
     *
     * @param limit 前几名
     * @return List<InterfaceInfoVO>
     */
    @Override
    public List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit) {
        return userInterfaceInfoMapper.listTopInvokeInterfaceInfo(limit);
    }

//    public boolean invokeCount(long interfaceInfoId, long userId) {
//        // 判断
//        if (interfaceInfoId <= 0 || userId <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
//        updateWrapper.eq("userId", userId);
//
////        updateWrapper.gt("leftNum", 0);
//        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
//        return this.update(updateWrapper);
//    }
}




