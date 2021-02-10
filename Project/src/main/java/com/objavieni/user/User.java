package com.objavieni.user;

import com.objavieni.dto.UserDto;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.util.UUID;

import static com.objavieni.functions.PreferencesFunction.dtoToPreferences;

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

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "preferences_id", referencedColumnName = "id")
//    private Preferences preferences = new Preferences();

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Preferences preferences = new Preferences();

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "preferences_id", referencedColumnName = "id")
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


    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.name = userDto.getName();
        this.gender = userDto.getGender();
        this.age = userDto.getAge();
        this.preferences = dtoToPreferences.apply(userDto.getPreferencesDto());
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
