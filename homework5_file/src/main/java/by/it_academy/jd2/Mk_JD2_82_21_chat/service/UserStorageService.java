package by.it_academy.jd2.Mk_JD2_82_21_chat.service;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleInfo;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.storage.MessageStorage;

import java.util.Set;

public class UserStorageService implements IHandleInfo {

    private static final UserStorageService instance = new UserStorageService();

    private UserStorageService(){
    }

    @Override
    public Set<User> getAllUsers() {
        return MessageStorage.getInstance().getStorage().keySet();
    }

    public static UserStorageService getInstance(){
        return instance;
    }

}
