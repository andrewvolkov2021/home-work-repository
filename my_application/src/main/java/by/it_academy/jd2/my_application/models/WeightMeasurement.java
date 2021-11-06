package by.it_academy.jd2.my_application.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class WeightMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Profile profile;

    @Column(name = "weight_measurement")
    private double weight;

    @OneToOne
    private User creator;

    @Column(name = "creation_time")
    private LocalDateTime creationDate;

    @Column(name = "update_time")
    private LocalDateTime updateDate;

    public WeightMeasurement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
