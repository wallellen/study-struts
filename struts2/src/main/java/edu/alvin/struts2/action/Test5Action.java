package edu.alvin.struts2.action;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import net.alvin.util.helper.StringHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 控制器类，测试编程式表单验证
 *
 * @author Alvin
 */
public class Test5Action extends ActionSupport {
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

    /**
     * 输入表单处理方法
     */
    public String input() {
        // 设置表单项值
        account = "";
        password = "";
        passwordAck = "";
        name = "";
        gender = "M";
        email = "@";
        telephone = "";

        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -20);
        birthday = new Date(now.getTimeInMillis());
        return INPUT;
    }

    /**
     * 提交表单的方法
     */
    public String submit() {
        Map<String, Test5Action> accountMap = null;

        // 获取应用程序上下文对象
        Map<String, Object> application = ActionContext.getContext().getApplication();
        synchronized (application) {
            // 获取用户信息集合
            accountMap = (Map<String, Test5Action>) application.get("accountMap");
            if (accountMap == null) {
                // 创建新的用户信息集合并存储在应用程序上下文中
                accountMap = new ConcurrentHashMap<>();
                application.put("accountMap", accountMap);
            }
        }

        // 判断用户信息是否存在
        if (accountMap.containsKey(account)) {
            addFieldError("account", "该用户已存在，请更改其它的用户名");
            return INPUT;
        }
        accountMap.put(account, this);
        return "submit";
    }

    public void validateSubmit() {
        // 验证账号
        if (StringHelper.isNullOrEmpty(account) || Pattern.matches("^[a-zA-Z0-9\\.\\_\\-]{3,20}$", account) == false) {
            addFieldError("account", "请输入正确的账号（3~20个字符，可包含数字、字母、点、下划线或短横线）");
        }
        // 验证密码
        if (StringHelper.isNullOrEmpty(password) || Pattern.matches("^[a-zA-Z0-9\\.\\_\\-]{6,20}$", password) == false) {
            addFieldError("password", "请输入正确的密码（6~20个字符，可包含数字、字母、点、下划线或短横线）");
        }
        // 验证密码确认
        if (StringHelper.compare(password, passwordAck, false) != 0) {
            addFieldError("passwordAck", "两次输入的密码必须一致");
        }
        // 验证用户名
        if (StringHelper.isNullOrEmpty(name)) {
            addFieldError("name", "请输入用户姓名");
        }
        // 验证性别
        if ("M".equals(gender) == false && "F".equals(gender) == false) {
            addFieldError("gender", "无效的性别");
        }
        // 验证生日
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.YEAR, -90);
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, -18);
        if (birthday == null || birthday.getTime() < minDate.getTimeInMillis() || birthday.getTime() > maxDate.getTimeInMillis()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
            String message = MessageFormat.format("无效的生日（必须在{0}~{1}之间）",
                    dateFormat.format(minDate.getTime()),
                    dateFormat.format(maxDate.getTime()));
            addFieldError("birthday", message);
        }
        // 验证电话（非必填）
        if (StringHelper.isNullOrEmpty(telephone) == false) {
            if (Pattern.matches("^\\(?0\\d{2}[\\)\\-\\s]?\\d{8}$|^\\(?0\\d{3}[\\)\\-\\s]?\\d{7}$|^1\\d{10}$", telephone) == false) {
                addFieldError("telephone", "无效的电话号码格式");
            }
        }
        // 验证电子邮件（非必填）
        if (StringHelper.isNullOrEmpty(email) == false && "@".equals(email) == false) {
            if (Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$", email) == false) {
                addFieldError("email", "无效的电子邮件格式");
            }
        }
    }

    /**
     * 显示结果的方法
     */
    public String result() {
        if (StringHelper.isNullOrEmpty(account)) {
            return ERROR;
        }

        // 获取应用程序上下文对象
        Map<String, Object> application = ActionContext.getContext().getApplication();

        // 获取用户信息集合
        Map<String, Test5Action> actionMap = (Map<String, Test5Action>) application.get("accountMap");
        if (actionMap == null) {
            return ERROR;
        }

        // 查找用户信息对象
        Test5Action bean = actionMap.get(account);
        if (bean == null) {
            return ERROR;
        }
        account = bean.account;
        password = bean.password;
        name = bean.name;
        gender = bean.gender;
        birthday = bean.birthday;
        telephone = bean.telephone;
        email = bean.email;
        return "result";
    }

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
