package com.flab.yeogiseoja.domain.owner;

public interface EmailSender {
    void sendEmail(String to, String title, String content);
}
