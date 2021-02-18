package com.objavieni.service;

import com.objavieni.dto.PreferencesDto;
import com.objavieni.dto.UserDto;
import com.objavieni.error.UserNotFoundException;
import com.objavieni.repository.UserRepository;
import com.objavieni.user.Preferences;
import com.objavieni.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static com.objavieni.functions.PreferencesFunction.dtoToPreferences;
import static com.objavieni.functions.UserFunction.dtoToUser;
import static com.objavieni.functions.UserFunction.userToDto;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    public final UserRepository userRepository;

    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = userRepository.save(dtoToUser.apply(userDto));
        return userToDto.apply(user);
    }

    @Transactional
    public UserDto updateUser(UUID userId, PreferencesDto preferencesDto) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setPreferences(dtoToPreferences.apply(preferencesDto));
        user = userRepository.save(user);

        return userToDto.apply(user);
    }

    @Transactional
    public UserDto findByName(String name) {
        User user = userRepository.findByName(name);
        if (user.getPreferences() == null) {
            user.setPreferences(new Preferences());
        }
        return userToDto.apply(user);
    }
}
