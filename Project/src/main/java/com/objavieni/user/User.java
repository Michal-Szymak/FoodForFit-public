package com.objavieni.user;

import com.objavieni.dto.UserDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;


    private String gender;
    private int age;

    @OneToOne(mappedBy = "user")
    private Preferences preferences;

    public User() {
    }

    public User(String name, String gender, int age, Preferences preferences) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.preferences = preferences;

    }


    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.gender = userDto.getGender();
        this.age = userDto.getAge();
        this.preferences = userDto.getPreferences();
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

    public void setGender(String gender) {
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
