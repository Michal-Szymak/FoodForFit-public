package com.objavieni.dto;

import com.objavieni.user.Preferences;
import com.objavieni.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.objavieni.functions.PreferencesFunction.preferencesToDto;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String name;

    private String gender;
    private int age;

    private PreferencesDto preferencesDto = new PreferencesDto();

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.preferencesDto = preferencesToDto.apply(user.getPreferences());
    }

    public UserDto(long id, String name, String gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
}
