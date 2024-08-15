package cn.javaquan.tools.mail;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
public class MailUtil implements InitializingBean {

    private static JavaMailSenderImpl sender;

    private static MailUtilProperties properties;

    /**
     * 构造方法注入实例.
     * @param sender 邮件发送实例
     * @param properties 邮件发送配置
     */
    public MailUtil(JavaMailSenderImpl sender, MailUtilProperties properties) {
        MailUtil.sender = sender;
        MailUtil.properties = properties;
    }

    /**
     * 发送文本邮件.
     * @param to 邮件收件人
     * @param subject 邮件主题
     * @param content 内容
     * @return 是否发送成功
     */
    public static boolean sendText(String to, String subject, String content) {
        return send(properties.determineDefaultAliasName(), to, subject, content, false);
    }

    /**
     * 发送html邮件.
     * @param to 邮件收件人
     * @param subject 邮件主题
     * @param content 内容
     * @return 是否发送成功
     */
    public static boolean sendHtml(String to, String subject, String content) {
        return send(properties.determineDefaultAliasName(), to, subject, content, true);
    }

    /**
     * 发送邮件.
     * @param aliasName 别名
     * @param to 邮件收件人
     * @param subject 邮件主题
     * @param content 内容
     * @param html 是否是html邮件
     * @return 是否发送成功
     */
    public static boolean send(String aliasName, String to, String subject, String content, boolean html) {
        StringBuffer from = new StringBuffer();
        from.append(aliasName).append("<").append(sender.getUsername()).append(">");
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from.toString());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, html);
            sender.send(message);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (sender == null) {
            throw new IllegalArgumentException("Property 'spring.mail' is required");
        }
    }

}
