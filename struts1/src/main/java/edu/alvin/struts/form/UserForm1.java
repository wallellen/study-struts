package edu.alvin.struts.form;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.alvin.util.helper.StringHelper;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * 用户信息表单
 *
 * @author Alvin
 */
public class UserForm1 extends ActionForm {
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
        // 错误集合对象
        ActionErrors errors = new ActionErrors();
        // 验证账号
        if (StringHelper.isNullOrEmpty(account) || Pattern.matches("^[a-zA-Z0-9\\.\\_\\-]{3,20}$", account) == false) {
            errors.add("account", new ActionMessage("error.form.account", 3, 20));
        }
        // 验证密码
        if (StringHelper.isNullOrEmpty(password) || Pattern.matches("^[a-zA-Z0-9\\.\\_\\-]{6,20}$", password) == false) {
            errors.add("password", new ActionMessage("error.form.password", 6, 20));
        }
        // 验证密码确认
        if (StringHelper.compare(password, passwordAck, false) != 0) {
            errors.add("passwordAck", new ActionMessage("error.form.passwordAck"));
        }
        // 验证用户名
        if (StringHelper.isNullOrEmpty(name)) {
            errors.add("name", new ActionMessage("error.form.name"));
        }
        // 验证性别
        if ("M".equals(gender) == false && "F".equals(gender) == false) {
            errors.add("gender", new ActionMessage("error.form.gender"));
        }
        // 验证生日
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.YEAR, -90);
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, -18);
        if (birthday == null || birthday.getTime() < minDate.getTimeInMillis() || birthday.getTime() > maxDate.getTimeInMillis()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
            errors.add("birthday", new ActionMessage("error.form.birthday", dateFormat.format(minDate.getTime()), dateFormat.format(maxDate.getTime())));
        }
        // 验证电话（非必填）
        if (StringHelper.isNullOrEmpty(telephone) == false) {
            if (Pattern.matches("^\\(?0\\d{2}[\\)\\-\\s]?\\d{8}$|^\\(?0\\d{3}[\\)\\-\\s]?\\d{7}$|^1\\d{10}$", telephone) == false) {
                errors.add("telephone", new ActionMessage("error.form.telephone"));
            }
        }
        // 验证电子邮件（非必填）
        if (StringHelper.isNullOrEmpty(email) == false && "@".equals(email) == false) {
            if (Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$", email) == false) {
                errors.add("email", new ActionMessage("error.form.email"));
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
