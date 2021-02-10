package com.objavieni.functions;

import com.objavieni.dto.UserDto;
import com.objavieni.user.User;
import java.util.function.Function;
import static com.objavieni.functions.PreferencesFunction.dtoToPreferences;
import static com.objavieni.functions.PreferencesFunction.preferencesToDto;

public class UserFunction {
    public static final Function<User,UserDto> userToDto = user -> new UserDto(
            user.getId(),
            user.getName(),
            user.getGender(),
            user.getAge(),
            preferencesToDto.apply(user.getPreferences())
    );
    public static final Function<UserDto,User> dtoToUser = userDto -> new User(
            userDto.getName(),
            userDto.getGender(),
            userDto.getAge(),
            dtoToPreferences.apply(userDto.getPreferencesDto())
    );
}
