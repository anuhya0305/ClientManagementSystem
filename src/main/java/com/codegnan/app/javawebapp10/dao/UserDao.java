package com.codegnan.app.javawebapp10.dao;

import com.codegnan.app.javawebapp10.dto.CredentialsDto;
import com.codegnan.app.javawebapp10.dto.UserDto;

import java.util.List;

public interface UserDao {
    boolean save(UserDto userDto, CredentialsDto credentialsDto);

    List<UserDto> getAllUsers();

    UserDto findByUsernameAndLoginPassword(String username,String loginPassword);

    boolean updateLoginPasswordByUsername(String username,String newPassword);

    boolean deleteByUserId(int userId);

}