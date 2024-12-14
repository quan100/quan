package cn.javaquan.tools.sensitive;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 敏感数据替换规则枚举.
 *
 * @author javaquan
 * @since 2.3.2
 */
@Getter
@AllArgsConstructor
public enum SensitiveRuleEnum {

    /**
     * 全文替换规则.
     */
    ALL("(?<=.{0}).", "*"),
    /**
     * 文本替换规则.
     */
    TEXT("(?<=.{1}).(?=.{1})", "*"),
    /**
     * 姓名替换规则.
     */
    NAME("(?<=.{1}).", "*"),
    /**
     * 邮箱替换规则.
     */
    EMAIL("(^\\w)[^@]*(@.*$)", "$1****$2"),
    /**
     * 身份证替换规则.
     */
    ID_CARD("(?<=\\w{3})\\w(?=\\w{4})", "$1****$2"),
    /**
     * 银行卡号替换规则.
     */
    BANK_CARD("(\\d{6})\\d{9}(\\d{4})", "$1****$2"),
    /**
     * 手机号替换规则.
     */
    PHONE("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");

    /**
     * 正则.
     */
    private final String regex;

    /**
     * 替换符.
     */
    private final String replacement;

}
