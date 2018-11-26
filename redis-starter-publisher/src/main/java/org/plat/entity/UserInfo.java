package org.plat.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String moblie;
    private String username;
    private Integer age;
    private String pid;

    public UserInfo(){}

    public UserInfo(String moblie, String username, Integer age, String pid) {
        this.moblie = moblie;
        this.username = username;
        this.age = age;
        this.pid = pid;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "moblie='" + moblie + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", pid='" + pid + '\'' +
                '}';
    }
}
