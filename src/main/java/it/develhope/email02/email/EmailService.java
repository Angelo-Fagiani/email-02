package it.develhope.email02.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTo(String email, String title,String text)throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
        String htmlMsg = "<h1>Hello World!</h1>" +
                    "<h2>You have a new message</h2>" +
                    "img src='https://cdn.artphotolimited.com/images/5cdad65d9c936e3bb1f33485/700x700/lac-de-landro.jpg' alt='Alternative text' height='200'>" +
                    "<h3>" + text + "</h3>";
        helper.setText(htmlMsg,true);
        helper.setTo(email);
        helper.setSubject(title);
        helper.setFrom("angelo.develhope@gmail.com");
        mailSender.send(mimeMessage);
    }
}
