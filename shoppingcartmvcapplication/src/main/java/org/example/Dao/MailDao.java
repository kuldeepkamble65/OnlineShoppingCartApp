package org.example.Dao;

public interface MailDao {
    void sendMail(String to, String subject, String text);
}
