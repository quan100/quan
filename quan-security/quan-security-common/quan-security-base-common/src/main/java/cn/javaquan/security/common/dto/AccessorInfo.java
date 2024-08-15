package cn.javaquan.security.common.dto;

import cn.javaquan.security.common.AccessorTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 访问者信息.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Getter
@Setter
public class AccessorInfo implements Serializable {

    private static final long serialVersionUID = -6088100665238645056L;

    /**
     * 访问者身份类型.
     *
     * @see AccessorTypeEnum
     */
    private Integer type;

    /**
     * 凭证ID 访问者凭证，一般为访问者唯一身份标识，如：userId.
     */
    private String authId;

    /**
     * 拥有的角色.
     */
    private List<?> roles;

    /**
     * 保存到凭证的数据.
     */
    private Object data;

}
