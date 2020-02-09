package clinic.centersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendMailTo(String email, String subject, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);


        System.out.println("Mail sent!");
    }

    public void sendSyncMailTo(String email, String subject, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);


        System.out.println("Mail sent!");
    }

}
