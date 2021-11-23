package by.it_academy.jd2.my_application.models;

import by.it_academy.jd2.my_application.models.api.ETypeOfEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

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
    private long entityId;

    @Column(name = "creation_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creationDate;

    public Audit() {
    }

    public long getId() {
        return id;
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

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
