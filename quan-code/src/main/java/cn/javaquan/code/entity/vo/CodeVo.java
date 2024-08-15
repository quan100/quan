/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the MIT License;
 * you may not use this file except in compliance with the License.
 */

package cn.javaquan.code.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 生成代码接口的请求参数.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Data
public class CodeVo implements Serializable {

    private static final long serialVersionUID = -4185290728526113744L;

    /**
     * 表名称.
     */
    private List<String> tableNames;

}
