package cn.javaquan.app.common.module.file;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传返回参数.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class FileVO implements Serializable {

    private static final long serialVersionUID = -137770378920503836L;

    /**
     * 文件名称.
     */
    private String name;

    /**
     * 文件访问地址.
     */
    private String url;

}
