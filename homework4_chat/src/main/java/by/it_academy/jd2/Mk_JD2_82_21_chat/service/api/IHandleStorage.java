package by.it_academy.jd2.Mk_JD2_82_21_chat.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.Text;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import java.util.LinkedList;

public interface IHandleStorage {
    void saveNewUser(User user);

    User getUser(String login);

    void setMessage(User user, Text text);

    LinkedList<Text> getMessages(User user);
}
