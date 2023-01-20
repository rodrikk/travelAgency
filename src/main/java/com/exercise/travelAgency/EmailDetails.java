package com.exercise.travelAgency;

import jakarta.annotation.Nullable;

public class EmailDetails {
    private String from, to, subject, text, pathToAttachment, attachmentName;

    @Nullable
    private String cc;
    public boolean hasCC;

    public EmailDetails(String from, String to, String subject, String text, String pathToAttachment, String attachmentName) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.pathToAttachment = pathToAttachment;
        this.attachmentName = attachmentName;
        this.hasCC = false;
    }

    public EmailDetails(String from, String to, String subject, String text, String pathToAttachment, String attachmentName, String cc) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.pathToAttachment = pathToAttachment;
        this.attachmentName = attachmentName;
        this.cc = cc;
        this.hasCC = true;
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

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public boolean isHasCC() {
        return hasCC;
    }

    public void setHasCC(boolean hasCC) {
        this.hasCC = hasCC;
    }
}
