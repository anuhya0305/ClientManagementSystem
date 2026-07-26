package com.codegnan.app.javawebapp10.service;

import com.codegnan.app.javawebapp10.dao.UserDao;
import com.codegnan.app.javawebapp10.dao.UserDaoImpl;
import com.codegnan.app.javawebapp10.dto.CredentialsDto;
import com.codegnan.app.javawebapp10.dto.UserDto;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean signUp(UserDto userDto, CredentialsDto credentialsDto) {
        return userDao.save(userDto, credentialsDto);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserDto signIn(String username, String loginPassword) {
        return userDao.findByUsernameAndLoginPassword(username, loginPassword);
    }

    @Override
    public boolean updateLoginPasswordByUsername(String username, String newPassword) {
        return userDao.updateLoginPasswordByUsername(username, newPassword);
    }

    @Override
    public boolean deleteByUserId(int userId) {
        return userDao.deleteByUserId(userId);
    }
}