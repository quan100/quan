package cn.javaquan.tools.chat.core.message;

import java.io.Serializable;

/**
 * 按照layim消息格式定义
 *
 * @author javaquan
 */
@lombok.Data
public class Data implements Serializable {

    private static final long serialVersionUID = -5838017717755315386L;

    private Mine mine;
    private To to;

}
