package com.objavieni.user;

import lombok.Data;
import org.hibernate.annotations.Type;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(unique = true)
    private String name;


    private String gender;
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_preferences",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "preferences_id", referencedColumnName = "id") }
    )
    private Preferences preferences;

    public User() {
        this.preferences = new Preferences();
    }

    public User(String name, String gender, int age, Preferences preferences) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}
