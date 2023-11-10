package cn.javaquan.tools.chat.core.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息接收者的信息
 *
 * @author javaquan
 */
@Data
public class To extends Mine implements Serializable {

    private static final long serialVersionUID = -6861097768180142019L;

    private String name;

    private String sign;

    private String type;

}
