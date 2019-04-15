package nju.edu.mycourse.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MailUtil {
    private static Map<String, Long> unusedCode = new HashMap<>();
    private static Map<String, String> codeEmail = new HashMap<>();

    static {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            Set<String> toBeRemoved = new HashSet<>();
            final int TEN_MIN_MILLIS = 10 * 60 * 1000;
            for (Map.Entry<String, Long> entry : unusedCode.entrySet()) {
                if (entry.getValue() > TEN_MIN_MILLIS) {
                    toBeRemoved.add(entry.getKey());
                }
            }
            for (String code : toBeRemoved) {
                unusedCode.remove(code);
                codeEmail.remove(code);
            }
        }, 10, 10, TimeUnit.MINUTES);
    }

    /**
     * 发送验证码
     *
     * @param email 用户的邮箱
     * @return 是否成功发送邮件
     */
    public static synchronized boolean sendVerificationCode(String email) {
        final String SENDER_USERNAME = "1140481216@qq.com";
        final String SENDER_PASSWORD = "etmxbaldfakphjhj";
        final String HOST = "smtp.qq.com";
        final String PORT = "465";
        final String AUTH = "true";

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", HOST);
        prop.setProperty("mail.smtp.port", PORT);
        prop.setProperty("mail.smtp.auth", AUTH);
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_USERNAME, SENDER_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SENDER_USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("MyCourse 注册验证码");
            String code = generateVerificationCode();
            codeEmail.put(code, email);
            String content = "您的验证码为：<b>" + code
                    + "</b>，验证码有效时间为<b>10</b>分钟，请尽快输入。";
            message.setContent(content, "text/html;charset=UTF-8");
            Transport.send(message);
            System.out.println("测试邮件已发送。");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean sendEmail(String email, String author, String receiver, String content) {
        final String SENDER_USERNAME = "1140481216@qq.com";
        final String SENDER_PASSWORD = "etmxbaldfakphjhj";
        final String HOST = "smtp.qq.com";
        final String PORT = "465";
        final String AUTH = "true";

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", HOST);
        prop.setProperty("mail.smtp.port", PORT);
        prop.setProperty("mail.smtp.auth", AUTH);
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_USERNAME, SENDER_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(SENDER_USERNAME));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("MyCourse 邮件通知");
            String sb = receiver + "同学：<br>"
                    + content + "<br>"
                    + author + "老师";
            message.setContent(sb, "text/html;charset=UTF-8");
            Transport.send(message);
            System.out.println("邮件已发送。");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
            return false;
        }
    }


    /**
     * 验证，验证码有效时间 10 分钟
     *
     * @param code 前端传回的验证码
     * @return 如果验证码未被使用，返回 true 表示验证成功
     *         否则返回 false 表示验证失败
     */
    public static synchronized boolean verify(String code) {
        if(code==null) {
            return false;
        }
        final int TEN_MIN_MILLIS = 10 * 60 * 1000;
        boolean succeeded = false;
        String verifiedEmail = "";
        if (unusedCode.containsKey(code)) {
            if (System.currentTimeMillis() - unusedCode.get(code) <= TEN_MIN_MILLIS) {
                succeeded = true;
                verifiedEmail = codeEmail.remove(code);
            } else {
                codeEmail.remove(code);
            }
            unusedCode.remove(code);
        }
        return succeeded;
    }

    /**
     * 生成验证码
     *
     * @return 六位数字验证码
     */
    private static synchronized String generateVerificationCode() {
        StringBuilder generated;
        do {
            int rand = new Random().nextInt(1000000);
            generated = new StringBuilder(String.valueOf(rand));
            int diff = 6 - generated.length();
            for (int i = 0; i < diff; i++) {
                generated.insert(0, "0");
            }
        } while (unusedCode.containsKey(generated.toString()));
        unusedCode.put(generated.toString(), System.currentTimeMillis());

        return generated.toString();
    }
}
