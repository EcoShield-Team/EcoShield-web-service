package com.api.ecoshieldwebservice.interfaces;

public interface IEmailService {
    void sendPasswordReset(String toEmail, String resetLink, String verificationCode);
}

