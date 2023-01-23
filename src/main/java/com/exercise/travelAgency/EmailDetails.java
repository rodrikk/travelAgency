package com.exercise.travelAgency;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class EmailDetails {
    private @Id@GeneratedValue Integer id;
    private String subject, text;

    @Column(name="send_address")
    private String from;

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
    public boolean equals(Object obj) {
        if(!(obj instanceof EmailDetails))
            return false;
        else if(obj==this)
            return true;
        else {
            EmailDetails aux = (EmailDetails) obj;
            return this.id==aux.getId()&&this.getTo().equals(aux.getTo())&&this.getFrom().equals(aux.getFrom());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.to, this.from, this.subject);
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
