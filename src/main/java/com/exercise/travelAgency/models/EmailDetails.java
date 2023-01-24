package com.exercise.travelAgency.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;

import java.util.Arrays;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmailDetails {
    @EqualsAndHashCode.Include
    private @Id@GeneratedValue Integer id;
    @EqualsAndHashCode.Include
    private String subject;
    private String text;

    @EqualsAndHashCode.Include
    @Column(name="send_address")
    private String from;

    @EqualsAndHashCode.Include
    @Column(name="destination")
    private String[] to;
    @Nullable
    private String pathToAttachment, attachmentName;
    @Nullable
    private String[] cc;
    public boolean hasCC, hasAtt;

    public EmailDetails() {}

    public EmailDetails(String from, String[] to, String subject, String text, String pathToAttachment, String attachmentName, String[] cc) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        if(!pathToAttachment.isEmpty()) {
            this.pathToAttachment = pathToAttachment;
            this.attachmentName = attachmentName;
            this.hasAtt=true;
        }
        else
            this.hasAtt=false;
        if(!(cc[0].isEmpty())) {
            this.hasCC=true;
            this.cc = cc;
        }
        else
            this.hasCC=false;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
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

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public boolean isHasCC() {
        return hasCC;
    }

    public void setHasCC(boolean hasCC) {
        this.hasCC = hasCC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String ret =  "EmailDetails{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", to=" + Arrays.toString(to);
        if(this.hasCC) {
            ret+=", cc=" + Arrays.toString(cc);
        }
        if(this.hasAtt)
            ret+=", pathToAttachment='" + pathToAttachment + '\'' +
                 ", attachmentName='" + attachmentName + '\'';

        ret += '}';
        return ret;
    }
}
