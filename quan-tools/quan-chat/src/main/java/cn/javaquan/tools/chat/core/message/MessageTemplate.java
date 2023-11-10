package cn.javaquan.tools.chat.core.message;

import java.io.Serializable;

/**
 * 按照layim消息格式定义
 *
 * @author javaquan
 */
@lombok.Data
public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 5501547129755684042L;

    private String type;
    private Data data;

}
