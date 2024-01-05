package cn.javaquan.tools.mail;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * @author wangquan
 */
public class MailUtil implements InitializingBean {

    private static JavaMailSenderImpl sender;
    private static MailUtilProperties properties;

    public MailUtil(JavaMailSenderImpl sender, MailUtilProperties properties) {
        MailUtil.sender = sender;
        MailUtil.properties = properties;
    }

    public static boolean sendText(String to, String subject, String content) {
        return send(properties.determineDefaultAliasName(), to, subject, content, false);
    }

    public static boolean sendHtml(String to, String subject, String content) {
        return send(properties.determineDefaultAliasName(), to, subject, content, true);
    }

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
        } catch (Exception e) {
            e.printStackTrace();
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
