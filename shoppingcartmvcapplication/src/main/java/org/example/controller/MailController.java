package org.example.controller;

import org.example.Dao.MailDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

    @Autowired
    private MailDao mailDao;


    @PostMapping("/register")
    @ResponseBody
    public String sendConfirmationMail(@RequestBody User user) {

        String toEmail = user.getEmail();
        String subject = "Registration Confirmation";
        String text = "Thank you for registering on our platform. Your registration is successful!";
        mailDao.sendMail(toEmail, subject, text);
        return "Registration successful. Confirmation email sent!";
    }
}
