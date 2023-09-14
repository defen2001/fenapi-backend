package com.defen.fenapicommon.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 *
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 用户请求参数
     */
    private String requestParams;

    /**
     * 主机号
     */
    private String host;

}