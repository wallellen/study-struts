package edu.alvin.struts.form;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * 用户信息表单
 *
 * @author Alvin
 */
public class UserForm2 extends ValidatorActionForm {
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

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = super.validate(mapping, request);
        if (errors.isEmpty()) {
            // 验证生日
            Calendar minDate = Calendar.getInstance();
            minDate.add(Calendar.YEAR, -90);
            Calendar maxDate = Calendar.getInstance();
            maxDate.add(Calendar.YEAR, -18);
            if (birthday == null || birthday.getTime() < minDate.getTimeInMillis() || birthday.getTime() > maxDate.getTimeInMillis()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
                errors.add("birthday", new ActionMessage("error.form.birthday", dateFormat.format(minDate.getTime()), dateFormat.format(maxDate.getTime())));
            }
        }
        return errors;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account.trim();
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
        this.name = name.trim();
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

    public static long getSerialversionuid() {
        return serialVersionUID;
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
