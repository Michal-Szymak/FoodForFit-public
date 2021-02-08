package com.objavieni.dto;

import com.objavieni.user.Preferences;
import com.objavieni.user.User;
import lombok.Data;


@Data

public class UserDto {
    private long id;
    private String name;

    private String gender;
    private int age;

    private Preferences preferences;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.preferences = user.getPreferences();
    }

    public UserDto(String name, String gender, int age, Preferences preferences) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.preferences = preferences;
    }
}
