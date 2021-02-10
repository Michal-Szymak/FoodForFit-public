package com.objavieni.functions;

import com.objavieni.dto.UserDto;
import com.objavieni.user.User;
import org.junit.jupiter.api.Test;

import static com.objavieni.functions.UserFunction.dtoToUser;
import static com.objavieni.functions.UserFunction.userToDto;
import static org.junit.jupiter.api.Assertions.*;

class UserFunctionTest {

    @Test
    void userToDtoTest(){
        UserDto userDto = userToDto.apply(new User());
        assertEquals(userDto.getClass(),UserDto.class);
    }

    @Test
    void dtoToUserTest(){
        User user = dtoToUser.apply(new UserDto());
        assertEquals(user.getClass(),User.class);
    }

}