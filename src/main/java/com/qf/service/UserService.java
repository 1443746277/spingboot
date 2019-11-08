package com.qf.service;

import com.qf.pojo.User;

import java.util.List;

public interface UserService {
    public int deleteUser(int uid);
    public List<User> getuserList();
    public User getUserById(int uid);
    public int updateUser(User user);
    public int addUser(User user);
    public String seletUserByName(String username);
    public String getRoleByUsername(String username);

}
