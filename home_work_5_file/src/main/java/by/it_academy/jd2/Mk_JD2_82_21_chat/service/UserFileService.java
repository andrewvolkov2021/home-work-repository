package by.it_academy.jd2.Mk_JD2_82_21_chat.service;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleInfo;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UserFileService implements IHandleInfo {
    private static final String PATH_TO_FILE_WITH_USERS = "users.txt";
    private static final UserFileService instance = new UserFileService();

    private UserFileService(){
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> set = new HashSet<>();
        String line = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE_WITH_USERS))){
            while ((line = bufferedReader.readLine()) != null){
                String[] array = line.split(" ");
                String login = array[0];
                String password = array[1];
                String firstName = array[2];
                String lastName = array[3];
                String date = array[4];

                set.add(new User(login, password, firstName, lastName, date));
            }
        } catch (IOException ex){
        }
        return set;
    }

    public static UserFileService getInstance(){
        return instance;
    }
}
