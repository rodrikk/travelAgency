package com.exercise.travelAgency;

public class EmailDetails {
    private String from, to, subject, text, pathToAttachment, attachmentName;

    public EmailDetails(String from, String to, String subject, String text, String pathToAttachment, String attachmentName) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.pathToAttachment = pathToAttachment;
        this.attachmentName = attachmentName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public String getPathToAttachment() {
        return pathToAttachment;
    }

    public void setPathToAttachment(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
