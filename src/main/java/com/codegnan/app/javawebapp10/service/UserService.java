package com.codegnan.app.javawebapp10.service;

import com.codegnan.app.javawebapp10.dto.CredentialsDto;
import com.codegnan.app.javawebapp10.dto.UserDto;

import java.util.List;

public interface UserService {

    boolean signUp(UserDto userDto, CredentialsDto credentialsDto);

    List<UserDto> getAllUsers();

    UserDto signIn(String username, String loginPassword);

    boolean updateLoginPasswordByUsername(String username, String newPassword);

    boolean deleteByUserId(int userId);
}