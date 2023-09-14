package com.defen.fenapicommon.model.dto.interfaceinfo;

import com.defen.fenapicommon.model.vo.RequestParamsRemarkVO;
import com.defen.fenapicommon.model.vo.ResponseParamsRemarkVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {

    /**
     * 接口名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 主机名
     */
    private String host;

    /**
     * 请求参数
     * [
     * {"name:: "username", "type": "string"}
     * ]
     */
    private String requestParams;

    /**
     * 请求参数说明
     */
    private List<RequestParamsRemarkVO> requestParamsRemark;

    /**
     * 响应参数说明
     */
    private List<ResponseParamsRemarkVO> responseParamsRemark;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 接口状态（0-关闭，1-开启）
     */
    private Integer status;

    /**
     * 请求类型
     */
    private String method;

    private static final long serialVersionUID = 1L;
}