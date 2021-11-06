package by.it_academy.jd2.my_application.models;

import by.it_academy.jd2.my_application.models.api.EActivity;
import by.it_academy.jd2.my_application.models.api.ESex;
import by.it_academy.jd2.my_application.models.api.ETarget;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @Column(name = "height_profile")
    private double height;

    @Column(name = "weight_profile")
    private double weight;

    @Column(name = "birthday_profile")
    private LocalDateTime birthdayDate;

    @Column(name = "sex_profile")
    private ESex sex;

    @Column(name = "activity_profile")
    private EActivity activity;

    @Column(name = "target_profile")
    private ETarget target;

    @OneToOne
    private User creator;

    @Column(name = "creation_time")
    private LocalDateTime creationDate;

    @Column(name = "update_time")
    private LocalDateTime updateDate;

    public Profile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDateTime getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDateTime birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public ESex getSex() {
        return sex;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public EActivity getActivity() {
        return activity;
    }

    public void setActivity(EActivity activity) {
        this.activity = activity;
    }

    public ETarget getTarget() {
        return target;
    }

    public void setTarget(ETarget target) {
        this.target = target;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
