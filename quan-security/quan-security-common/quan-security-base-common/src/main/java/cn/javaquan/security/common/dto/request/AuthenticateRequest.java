package cn.javaquan.security.common.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wangquan
 * @date 2020/3/10 00:08
 */
@Data
public class AuthenticateRequest implements Serializable {

    private static final long serialVersionUID = 8785229043122502691L;
    /**
     * token令牌
     */
    private String token;

    /**
     * 认证类型，3：登录用户；2：带身份；1：匿名
     */
    @NotNull(message = "认证类型不可为空")
    @Range(min = 1, max = 3)
    private Integer type;
}
