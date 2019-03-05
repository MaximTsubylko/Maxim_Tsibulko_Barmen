package com.tsibulko.finaltask.service;

public class CustomMessage {
    private String subject;
    private String text;

    public CustomMessage() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public CustomMessage(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }
}
