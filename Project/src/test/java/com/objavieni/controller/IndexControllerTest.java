package com.objavieni.controller;

import com.objavieni.dto.UserDto;
import com.objavieni.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    UserService userService;

    @Mock
    UserDto userDto;

    @InjectMocks
    IndexController indexController;

    @Test
    void loginP_redirectToProfile_givenPropperName(){
        //given
        given(userService.findByName(userDto.getName())).willReturn(new UserDto());
        //when
        String s = indexController.loginP(userDto);
        //than
        assertEquals("redirect:profile",s);
    }

    @Test
    void loginP_redirectToLogin_givenWrongName(){
        //given
        given(userService.findByName(userDto.getName())).willReturn(null);
        //when
        String s = indexController.loginP(userDto);
        //than
        assertEquals("login",s);
    }

    @Test
    void registerUser_redirectToLogin_whenSuccessfulSave(){
        //given
        given(userService.saveUser(userDto)).willReturn(new UserDto());
        //when
        String actual = indexController.registerUser(userDto);
        //than
        assertEquals("redirect:login",actual);
    }

    @Test
    void registerUser_redirectToRegister_whenUnsuccessfulSave(){
        //given
        given(userService.saveUser(userDto)).willReturn(null);
        //when
        String actual = indexController.registerUser(userDto);
        //than
        assertEquals("redirect:register",actual);
    }
}