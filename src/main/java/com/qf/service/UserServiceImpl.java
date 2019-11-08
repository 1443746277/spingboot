package com.qf.service;

import com.qf.mapper.UserMapper;
import com.qf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int deleteUser(int uid) {
        return userMapper.deleteUser(uid);
    }

    @Override
    public List<User> getuserList() {
        return userMapper.getuserList();
    }

    @Override
    public User getUserById(int uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public String seletUserByName(String username) {
        return userMapper.seletUserByName(username);
    }

    @Override
    public String getRoleByUsername(String username) {
        return userMapper.getRoleByUsername(username);
    }
}
