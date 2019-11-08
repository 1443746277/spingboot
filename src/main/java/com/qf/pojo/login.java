package com.qf.pojo;

public class login {
    private String username;
    private String password;
private String rolename;

    public login(String username, String password, String rolename) {
        this.username = username;
        this.password = password;
        this.rolename = rolename;
    }

    public login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
