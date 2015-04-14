package edu.alvin.struts2.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * �û�VO��
 *
 * @author Alvin
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // �ʺ�
    private String account;
    // ����
    private String password;
    // ����ȷ��
    private String passwordAck;
    // ����
    private String name;
    // �Ա�
    private String gender;
    // ����
    private Date birthday;
    // �绰����
    private String telephone;
    // �����ʼ�
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
        int year = (int) (timespan / 31558150 - 1);        // �������
        if (timespan % 31558150 > 0) {
            year++;
        }
        return year;
    }
}
