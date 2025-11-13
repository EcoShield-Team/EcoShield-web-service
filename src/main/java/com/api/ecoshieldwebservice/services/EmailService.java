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
    public void sendPasswordReset(String toEmail, String resetLink, String verificationCode) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject("EcoShield – Restablecer contraseña");

        msg.setText("""
            Solicitaste restablecer tu contraseña en EcoShield.

            Código de verificación (6 dígitos):
            %s

            También puedes usar este enlace (válido por 15 minutos):
            %s


            Si no fuiste tú, ignora este correo.
            """.formatted(verificationCode, resetLink));

        mailSender.send(msg);
    }
}
