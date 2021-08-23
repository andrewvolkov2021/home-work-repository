package by.it_academy.jd2.Mk_JD2_82_21_chat.storage.model;

public class User {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String age;

    public User(String login, String password, String firstName, String lastName, String age) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }
}
