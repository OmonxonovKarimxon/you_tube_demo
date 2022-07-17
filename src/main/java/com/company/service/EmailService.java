package com.company.service;

import com.company.entity.EmailHistoryEntity;
import com.company.repository.EmailHistoryRepository;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    EmailHistoryRepository emailHistoryRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromAccount;

    @Value("${server.url}")
    private String serverUrl;


    public void sendRegistrationEmail(String toAccount, Integer id) {


        StringBuilder builder = new StringBuilder();
        builder.append("<h1 style='align-text:center'>Salom Jiga alo </h1>");
        builder.append("<b>Mazgi </b>");
        builder.append(JwtUtil.encode(id));
        builder.append("<p>");
        builder.append("<p> <a href='http://10.10.3.40:8080/auth/public/email/verification/" +
                JwtUtil.encode(id)+"'>Link verification</a> </p>");
        builder.append("</p>");
        System.out.println(JwtUtil.encode(id));
        sendEmail(toAccount, "Registration", builder.toString());

    }

    private void sendEmail(String toAccount, String subject, String text) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom("omonxonovsss@mail.ru");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(msg);

            EmailHistoryEntity entity  =new EmailHistoryEntity();
            entity.setEmail(toAccount);
            emailHistoryRepository.save(entity);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendSimpleEmail(String toAccount, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAccount);
        msg.setSubject(subject);
        msg.setText(text);
        msg.setFrom(fromAccount);
        javaMailSender.send(msg);
    }

    public Long getEmailCount(String email) {
        return emailHistoryRepository.getEmailCount(email);
    }

    public PageImpl<EmailHistoryEntity> pegination(Integer size, Integer page) {

        Sort sort= Sort.by(Sort.Direction.ASC, "id");

        Pageable pegeable = PageRequest.of(page,size,sort);
        Page<EmailHistoryEntity> all = emailHistoryRepository.findAll(pegeable);
        List<EmailHistoryEntity>list = all.getContent();

        return  new PageImpl(list, pegeable, all.getTotalElements());


    }


}
