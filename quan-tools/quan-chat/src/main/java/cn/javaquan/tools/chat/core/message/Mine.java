package cn.javaquan.tools.chat.core.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息发送者的信息结构.
 *
 * @author javaquan
 * @since 1.0.0
 */
@Data
public class Mine implements Serializable {

    private static final long serialVersionUID = -6861097768180142019L;

    /**
     * 我的id.
     */
    private String id;

    /**
     * 我的昵称.
     */
    private String username;

    /**
     * 我的头像.
     */
    private String avatar;

    /**
     * 消息内容.
     */
    private String content;

    /**
     * 是否我发送的消息.
     */
    private Boolean mine;

}
