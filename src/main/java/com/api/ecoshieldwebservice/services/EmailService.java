package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    @Autowired
    JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordReset(String toEmail, String resetLink) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject("EcoShield – Restablecer contraseña");
        msg.setText("""
                Solicitaste restablecer tu contraseña.
                Usa este enlace (válido por 24 horas):

                %s

                Si no fuiste tú, ignora este correo.
                """.formatted(resetLink));
        mailSender.send(msg);
    }
}
