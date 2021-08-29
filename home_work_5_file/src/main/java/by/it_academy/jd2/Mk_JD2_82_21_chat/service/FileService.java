package by.it_academy.jd2.Mk_JD2_82_21_chat.service;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.api.IHandleStorage;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.Text;
import by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model.User;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class FileService implements IHandleStorage {

    private static final String DIRECTORY_PARAM_NAME = "../conf/file";
    private static final String PATH_TO_FILE_WITH_USERS = "../conf/file/users.txt";
    private static final String PATH_TO_FILE_WITH_MESSAGES = "../conf/file/messages.txt";
    private static final FileService instance = new FileService();

    private FileService(){
    }

    @Override
    public void saveNewUser(User user) {

        File directory = new File(DIRECTORY_PARAM_NAME);
        File file = new File(PATH_TO_FILE_WITH_USERS);

        if (!directory.exists()) {
            directory.mkdir();
        }

        String login = user.getLogin();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String date = user.getAge();

         try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))){
            String userForFile = login + " " + password + " " + firstName + " " + lastName + " " + date + "\n";
            bufferedWriter.write(userForFile);
        } catch (IOException ex) {
        }
    }

    /**
     * Внимание!!! Данный метод в качестве результата может возвращать null
     */
    @Override
    public User getUser(String login) {
        String line = null;
        User user = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE_WITH_USERS))){
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(login.toLowerCase())) {
                    String[] array = line.split(" ");
                    String password = array[1];
                    String firstName = array[2];
                    String lastName = array[3];
                    String date = array[4];

                    user = new User(login, password, firstName, lastName, date);
                    break;
                }
            }
        } catch (IOException ex) {
        }
        return user;
    }

    @Override
    public void setMessage(User user, Text text) {

        File directory = new File(DIRECTORY_PARAM_NAME);
        File file = new File(PATH_TO_FILE_WITH_MESSAGES);

        if (!directory.exists()) {
            directory.mkdir();
        }

        String login = user.getLogin();
        String sender = text.getSender().getLogin();
        String date = text.getDate();
        String message = text.getText();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_TO_FILE_WITH_MESSAGES, true))){
            String messageForFile = login + " " + sender + " " + date + " " + message  + "\n";
            bufferedWriter.write(messageForFile);
        } catch (IOException ex) {
        }
    }

    @Override
    public LinkedList<Text> getMessages(User user) {
        LinkedList<Text> list = new LinkedList<>();
        String line = null;
        String login = user.getLogin();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE_WITH_MESSAGES))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(login.toLowerCase())) {
                    String[] array = line.split(" ");
                    String senderLogin = array[1];

                    String[] arrayDate = Arrays.copyOfRange(array, 2, 6);
                    String date = "";
                    for (String item : arrayDate) {
                        date = date + item + " ";
                    }

                    String[] arrayMessage = Arrays.copyOfRange(array, 6, array.length);
                    String message = "";
                    for (String item : arrayMessage) {
                        message = message + item + " ";
                    }

                    User sender = getUser(senderLogin);

                    Text text = new Text(message, sender, date);
                    list.add(text);
                }
            }
        } catch (IOException ex) {
        }
        return list;
    }

    public static FileService getInstance(){
        return instance;
    }

}
