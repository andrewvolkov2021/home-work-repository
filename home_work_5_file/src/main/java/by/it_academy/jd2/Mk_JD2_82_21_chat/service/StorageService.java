package by.it_academy.jd2.Mk_JD2_82_21_chat.service;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleStorage;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.Text;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.storage.MessageStorage;

import java.util.LinkedList;
import java.util.Map;

public class StorageService implements IHandleStorage {
    private static final StorageService instance = new StorageService();

    private StorageService() {
    }

    @Override
    public void saveNewUser(User user) {
        getMapWithUsers().put(user, new LinkedList<Text>());
    }

    /**
     * Внимание!!! Данный метод в качестве результата может возвращать null
     */
    @Override
    public User getUser(String login) {
        User user = getMapWithUsers()
                .keySet().stream()
                .filter(x -> x.getLogin().equals(login))
                .findFirst()
                .orElse(null);
        return user;
    }

    @Override
    public void setMessage(User user, Text text) {
        getMapWithUsers().get(user).add(text);
    }

    @Override
    public LinkedList<Text> getMessages(User user) {
        return getMapWithUsers().get(user);
    }

    private Map<User, LinkedList<Text>> getMapWithUsers() {
        return MessageStorage.getInstance().getStorage();
    }

    public static StorageService getInstance() {
        return instance;
    }
}
