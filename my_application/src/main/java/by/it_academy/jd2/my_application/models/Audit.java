package by.it_academy.jd2.my_application.models;

import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audits")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @Column(name = "text_audit")
    private String text;

    @Column(name = "type_entity")
    private ETypeOfEntity typeOfEntity;

    @Column(name = "id_entity")
    private long idOfEntity;

    @OneToOne
    private User creator;

    @Column(name = "creation_time")
    private LocalDateTime creationDate;

    @Column(name = "update_time")
    private LocalDateTime updateDate;


    public Audit() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ETypeOfEntity getTypeOfEntity() {
        return typeOfEntity;
    }

    public void setTypeOfEntity(ETypeOfEntity typeOfEntity) {
        this.typeOfEntity = typeOfEntity;
    }

    public long getIdOfEntity() {
        return idOfEntity;
    }

    public void setIdOfEntity(long idOfEntity) {
        this.idOfEntity = idOfEntity;
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
