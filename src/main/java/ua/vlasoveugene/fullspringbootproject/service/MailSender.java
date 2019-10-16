package ua.vlasoveugene.fullspringbootproject.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ua.vlasoveugene.fullspringbootproject.config.MailConfig;

@Service
public class MailSender {
    @Value("${spring.mail.username}")
    private String user;

    private JavaMailSender sender;

    public MailSender(@Qualifier("Sender")JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendCode(String mailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(user);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        sender.send(mailMessage);
    }
}
