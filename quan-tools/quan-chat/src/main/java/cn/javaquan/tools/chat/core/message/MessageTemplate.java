package cn.javaquan.tools.chat.core.message;

import java.io.Serializable;

/**
 * 消息模版定义，即 quan-chat 组件接收的客户端消息格式
 * <p>
 * 任何通过 quan-chat 组件发送的消息必须按照消息模版定义的格式
 *
 * @author javaquan
 */
@lombok.Data
public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 5501547129755684042L;

    private String type;
    private Data data;

}
