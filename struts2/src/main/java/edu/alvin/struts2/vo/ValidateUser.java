package edu.alvin.struts2.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.DateRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * 用户VO类
 *
 * @author Alvin
 */
public class ValidateUser implements Serializable {
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

    @RequiredStringValidator(trim = true, message = "请输入正确的账号")
    @StringLengthFieldValidator(minLength = "3", maxLength = "20", message = "请输入正确的账号（${minLength}~${maxLength}个字符，可包含数字、字母、点、下划线或短横线）")
    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(message = "请输入正确的密码")
    @StringLengthFieldValidator(minLength = "6", maxLength = "20", message = "请输入正确的密码（${minLength}~${maxLength}个字符，可包含数字、字母、点、下划线或短横线）")
    public void setPassword(String password) {
        this.password = password;
    }

    @RequiredStringValidator(message = "请输入确认密码")
    @FieldExpressionValidator(expression = "user.password==user.passwordAck", message = "两次输入的密码必须一致")
    public String getPasswordAck() {
        return passwordAck;
    }

    public void setPasswordAck(String passwordAck) {
        this.passwordAck = passwordAck;
    }

    public String getName() {
        return name;
    }

    @RequiredStringValidator(trim = true, message = "名称不能为空")
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    @FieldExpressionValidator(expression = "(user.gender==\"M\") || (user.gender==\"F\")", message = "无效的性别")
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    @ConversionErrorFieldValidator(message = "无效的日期格式")
    @RequiredFieldValidator(message = "请填写正确的生日")
    @DateRangeFieldValidator(dateFormat = "yyyy-M-d", message = "生日日期格式有误")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    @RegexFieldValidator(regexExpression = "^\\(?0\\d{2}[\\)\\-\\s]?\\d{8}$|^\\(?0\\d{3}[\\)\\-\\s]?\\d{7}$|^1\\d{10}$", message = "无效的电话号码")
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    @EmailValidator(message = "无效的电子邮件地址")
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
