package edu.alvin.struts2.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户VO类
 *
 * @author Alvin
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // 帐号
    private String account;
    // 密码
    private String password;
    // 密码确认
    private String passwordAck;
    // 姓名
    private String name;
    // 性别
    private String gender;
    // 生日
    private Date birthday;
    // 电话号码
    private String telephone;
    // 电子邮件
    private String email;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAck() {
        return passwordAck;
    }

    public void setPasswordAck(String passwordAck) {
        this.passwordAck = passwordAck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        long timespan = (Calendar.getInstance().getTimeInMillis() - birthday.getTime()) / 1000L;
        int year = (int) (timespan / 31558150 - 1);        // 计算年份
        if (timespan % 31558150 > 0) {
            year++;
        }
        return year;
    }
}
