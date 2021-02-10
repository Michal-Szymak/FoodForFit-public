package com.objavieni.service;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.dto.UserDto;
import com.objavieni.error.UserNotFoundException;
import com.objavieni.repository.UserRepository;
import com.objavieni.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    User user;
//
//    @Mock
//    PreferencesDto preferencesDto;
//
//
//
//
//    @InjectMocks
//    UserService userService;
//
//    @Test
//    void updateUser_updatedUser_givenIdAndPreferences() throws UserNotFoundException {
//        //given
//        UUID userId = null;
//        given(userRepository.findById(userId))
//                .willReturn(java.util.Optional.of(new User()));
//        given(userRepository.save(user)).willReturn(new User());
//        //when
//        UserDto actualUser = userService.updateUser(userId, preferencesDto);
//        //then
//        assertEquals(actualUser != null,true);
//    }

}