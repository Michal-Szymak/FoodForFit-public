package com.objavieni.dto;

import com.objavieni.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import static com.objavieni.functions.PreferencesFunction.preferencesToDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String gender;
    private int age;
    private PreferencesDto preferencesDto = new PreferencesDto();

}
