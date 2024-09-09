package com.example.devopapi.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailUtil {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Meta<T>{
        private String to;
        private String from;
        private String subject;
        private String templateUrl;
        private T data;
    }

    public void sendMail(Meta<?> meta) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        //1.prepare template
        Context context = new Context();
        context.setVariable("data", meta.getData());
        String htmlTemplate = templateEngine.process(meta.templateUrl, context);
        helper.setText(htmlTemplate, true);

        //2. Prepare email information
        helper.setTo(meta.getTo());
        helper.setFrom(meta.getFrom());
        helper.setSubject(meta.getSubject());

        //3.send email
        javaMailSender.send(mimeMessage);

    }
}
