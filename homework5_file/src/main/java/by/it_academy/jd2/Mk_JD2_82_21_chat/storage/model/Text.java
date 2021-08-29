package by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Text {
    private String text;
    private User sender;
    private String date;

    public Text(String text, User sender, String date) {
        this.text = text;
        this.sender = sender;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public User getSender() {
        return sender;
    }

    public String getDate() {
        return date;
    }
}
