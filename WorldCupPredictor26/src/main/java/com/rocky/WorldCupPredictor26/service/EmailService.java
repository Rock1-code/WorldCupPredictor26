package com.rocky.WorldCupPredictor26.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final MailSender mailSender;

    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPredictionConfirmation(String toEmail, String userName, String teamName) {

        System.out.println("========== EMAIL SERVICE CALLED ==========");
        System.out.println("To: " + toEmail);
        System.out.println("Name: " + userName);
        System.out.println("Team: " + teamName);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("World Cup Prediction Confirmation");
            message.setText(
                    "Hello " + userName + ",\n\n"
                    + "Your FIFA World Cup 2026 prediction has been recorded.\n\n"
                    + "Selected Team: " + teamName + "\n\n"
                    + "If you want to make changes, please login again using the link below:\n\n"
                    + "https://scope-applause-each.ngrok-free.dev\n\n"
                    + "Good luck!\n\n"
                    + "World Cup Predictor 2026\n"
                    + "© 2026 Rocky"
            );

            mailSender.send(message);

            System.out.println("========== EMAIL SENT SUCCESSFULLY ==========");

        } catch (MailException ex) {
            System.out.println("========== EMAIL FAILED ==========");
            ex.printStackTrace();
        }
    }
}