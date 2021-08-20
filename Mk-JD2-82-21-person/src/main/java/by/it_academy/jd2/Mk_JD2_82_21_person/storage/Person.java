package by.it_academy.jd2.Mk_JD2_82_21_person.storage;

public class Person {
    private final String firstName;
    private final String lastName;
    private final String age;

    public Person(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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
