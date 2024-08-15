package cn.javaquan.tools.chat.core.message;

import java.io.Serializable;

/**
 * 发送的消息格式定义.
 *
 * @author javaquan
 * @since 1.0.0
 */
@lombok.Data
public class Data implements Serializable {

    private static final long serialVersionUID = -5838017717755315386L;

    /**
     * 发送者信息.
     * <p>
     * 发送者的基本信息、发送内容等
     */
    private Mine mine;

    /**
     * 接受者信息.
     * <p>
     * 接受者的基本信息
     */
    private To to;

}
