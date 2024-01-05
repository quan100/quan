package com.quan.tools.notice;

/**
 * 消息通知执行器
 *
 * @author javaquan
 * @since 2.2.0
 */
public interface INoticeExecutor {

    /**
     * 推送消息
     *
     * @param e 异常信息
     */
    void push(Throwable e);
}
