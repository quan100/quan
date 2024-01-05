package cn.javaquan.app.common.constant;

import lombok.Getter;

/**
 * 系统错误枚举
 * 格式：3位项目编码|3位模块编码|3位功能编码
 *
 * @author wangquan
 * @date 2021/12/23 00:01
 */
@Getter
public enum ErrorCodeEnum {

    // 系统通用  000
    SYSTEM_ERR(100000000, "系统错误"),
    PARAM_ERROR(100000001, "参数错误"),
    DATA_NOT_EXIST_ERROR(100000002, "数据不存在"),
    OPERATION_ERROR(100000003, "操作失败"),

    // 用户中心  001
    USER_LOGIN_PASSWORD_ERR(100001001, "用户名或密码错误"),
    USER_LOGIN_CAPTCHA_ERR(100001002, "验证码错误"),
    USER_LOGIN_FREEZE_ERR(100001003, "账号已冻结"),
    USER_LOGIN_LOGOFF_ERR(100001004, "账号已注销"),

    TRIPARTITE_NOT_BOUND_ERR(100001005, "账号未绑定"),
    TRIPARTITE_NOT_ACTIVATION_ERR(100001006, "账号未激活"),
    TRIPARTITE_NOT_FIND_ERR(100001007, "账号未注册"),
    TRIPARTITE_NOT_EXIST_ERR(100001008, "凭证信息无效，请重新登录"),
    TRIPARTITE_EXIST_BOUND_ERR(100001010, "账号正在申请中，请耐心等待管理员审核！"),
    TRIPARTITE_BOUND_ERR(100001011, "账号已绑定！"),

    LOGIN_ERR(100001009, "登录失败!"),

    // 管理后台
    PM_DICTIONARY_CODE_EXISTED_ERR(100002000, "字典编码已存在"),
    PM_DICTIONARY_NOT_FIND_ERR(100002001, "字典数据不存在"),

    PM_COMMON_LIST_NOT_FIND_ERR(100003001, "数据不存在"),

    PM_ROLE_CODE_EXISTED_ERR(100004000, "角色编码已存在"),

    PM_PERMISSION_PARENT_NOT_FOUND(100005000, "上级权限不存在"),

    PM_DICTIONARY_CODE_EXISTED(100006000, "字典编码已存在"),

    PM_USER_USERID_EXIST_ERR(100007000, "用户ID已被其它账号绑定！"),
    PM_USER_INFO_NOT_EXIST_ERR(100007001, "用户ID错误，信息不存在！"),
    PM_THIRD_ID_EXIST_ERR(100007002, "第三方ID已存在！"),
    ;

    Integer code;

    String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
