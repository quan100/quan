package cn.javaquan.tools.chat.core.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息接收者的信息.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class To extends Mine implements Serializable {

    private static final long serialVersionUID = -6861097768180142019L;

    /**
     * 接收者名称.
     */
    private String name;

    /**
     * 接收者签名.
     */
    private String sign;

    /**
     * 接收者类型.
     */
    private String type;

}
