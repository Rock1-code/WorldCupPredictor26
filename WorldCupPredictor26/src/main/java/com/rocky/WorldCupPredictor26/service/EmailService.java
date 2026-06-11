package com.rocky.WorldCupPredictor26.service;

import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailSender mailSender;

    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendPredictionConfirmation(String toEmail, String userName, String teamName) {

        try {
            System.out.println("EMAIL SERVICE CALLED");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("World Cup Prediction Confirmation");

            message.setText(
                    "Hello " + userName + ",\n\n"
                    + "Your FIFA World Cup 2026 prediction has been recorded.\n\n"
                    + "Selected Team: " + teamName + "\n\n"
                    + "Login here to update your prediction:\n"
                    + "https://worldcuppredictor26-production.up.railway.app\n\n"
                    + "Good luck!\n\n"
                    + "World Cup Predictor 2026\n"
                    + "© 2026 Rocky"
            );

            mailSender.send(message);

            System.out.println("EMAIL SENT SUCCESSFULLY");

        } catch (Exception ex) {
            System.out.println("EMAIL FAILED");
            ex.printStackTrace();
        }
    }
}