package cn.javaquan.tools.notice;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;


/**
 * 消息推送执行器
 *
 * @author javaquan
 * @since 2.2.0
 */
@Slf4j
public class NoticeExecutor implements InitializingBean {

    @Setter
    private INoticeExecutor noticeExecutor;
    @Setter
    private boolean printStackTrace;

    public NoticeExecutor() {
    }

    /**
     * 推送消息
     *
     * @param e 异常信息
     */
    public void push(Throwable e) {
        if (null != noticeExecutor) {
            noticeExecutor.push(e);
        }
        if (printStackTrace) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.noticeExecutor == null) {
            log.warn("noticeExecutor is null");
        }
    }
}
