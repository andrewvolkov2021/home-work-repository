package by.it_academy.jd2.Mk_JD2_82_21_chat.storage.storage;

import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.Text;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MessageStorage {
    private Map<User, LinkedList<Text>> storage;

    private static final MessageStorage instance = new MessageStorage();

    private MessageStorage() {
        this.storage = new HashMap<>();
    }

    public static MessageStorage getInstance() {
        return instance;
    }

    public Map<User, LinkedList<Text>> getStorage() {
        return storage;
    }
}
