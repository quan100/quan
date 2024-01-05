package cn.javaquan.app.common.module.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户第三方账户
 *
 * @author wangquan
 * @date 2022/1/17 15:09
 */
@Data
public class TripartiteLoginEvent implements Serializable {

    private static final long serialVersionUID = 9129615075082380313L;

    /**
     * 第三方类型
     */
    @NotBlank(message = "第三方类型不能为空")
    private String thirdType;

    /**
     * 第三方ID
     */
    @NotBlank(message = "第三方ID不能为空")
    private String thirdId;

}
