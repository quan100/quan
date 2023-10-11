package com.quan.app.common.module.file;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传返回参数
 *
 * @author JavaQuan
 * @since 2023-04-04 10:38:39
 */
@Data
public class FileVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    private String name;

    /**
     * 文件访问地址
     */
    private String url;
}
