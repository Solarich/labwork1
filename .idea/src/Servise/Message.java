package Servise;

import java.io.Serializable;
import java.util.Date; // test change
import java.util.Objects;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String text;
    private Date departmentTime;
    private String sender;

    public Message(String text, Date departmentTime, String sender) {
        this.text = text;
        this.departmentTime = departmentTime;
        this.sender = sender;
    }

    public Message() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDepartmentTime() {
        return departmentTime;
    }

    public void setDepartmentTime(Date departmentTime) {
        this.departmentTime = departmentTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(text, message.text) &&
                Objects.equals(departmentTime, message.departmentTime) &&
                Objects.equals(sender, message.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, departmentTime, sender);
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", departmentTime=" + departmentTime +
                ", sender='" + sender + '\'' +
                '}';
    }
}
