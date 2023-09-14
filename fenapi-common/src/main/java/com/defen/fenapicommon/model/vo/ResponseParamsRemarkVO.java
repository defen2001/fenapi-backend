package com.defen.fenapicommon.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseParamsRemarkVO implements Serializable {

    private static final long serialVersionUID = -6549477882078242340L;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 说明
     */
    private String remark;
}
