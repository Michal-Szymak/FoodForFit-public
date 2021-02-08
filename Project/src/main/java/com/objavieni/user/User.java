package com.objavieni.user;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Gender gender;
    private int age;

    @OneToOne(mappedBy = "preferences")
    private Preferences preferences;

    public User() {
    }

    public User(String name, Gender gender, int age, Preferences preferences) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}
