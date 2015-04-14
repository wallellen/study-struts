package edu.alvin.struts2.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.alvin.util.helper.StringHelper;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.alvin.struts2.vo.User;

/**
 * 控制器类，测试配置式表单验证
 *
 * @author Alvin
 */
public class Test6Action extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {
    private static final long serialVersionUID = 1L;

    // 用户对象
    private User user;

    // 响应对象
    private HttpServletResponse response;
    // 请求对象
    private HttpServletRequest request;
    // 应用程序上下文对象
    private ServletContext application;

    /**
     * 输入表单处理方法
     */
    @SkipValidation
    public String input() {
        // 设置表单项值
        user = new User();
        user.setAccount("");
        user.setPassword("");
        user.setPasswordAck("");
        user.setName("");
        user.setGender("M");
        user.setEmail("");
        user.setTelephone("");

        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -20);
        user.setBirthday(new Date(now.getTimeInMillis()));
        return INPUT;
    }

    /**
     * 提交表单的方法
     */
    public String submit() {
        Map<String, User> accountMap = null;
        synchronized (application) {
            // 从ServletContext中获取用户信息集合
            accountMap = (Map<String, User>) application.getAttribute("accountMap");
            if (accountMap == null) {
                // 创建用户信息集合并存入ServletContext对象
                accountMap = new ConcurrentHashMap<>();
                application.setAttribute("accountMap", accountMap);
            }
        }

        // 判断用户信息是否已经存在
        if (accountMap.containsKey(user.getAccount())) {
            addFieldError("user.account", "该用户已存在，请更改其它的用户名");
            return INPUT;
        }
        // 保存用户信息
        accountMap.put(user.getAccount(), user);
        return "submit";
    }

    /**
     * 显示结果的方法
     *
     * @throws IOException
     */
    @SkipValidation
    public String result() throws IOException {
        // 判断是否传递了account参数
        String account = request.getParameter("account");
        if (StringHelper.isNullOrEmpty(account)) {
            response.sendError(500);
            return NONE;
        }

        // 获取用户信息集合
        Map<String, User> actionMap = (Map<String, User>) application.getAttribute("accountMap");
        if (actionMap == null) {
            response.sendError(500);
            return NONE;
        }

        user = actionMap.get(account);
        if (user == null) {
            response.sendError(500);
            return NONE;
        }
        return "result";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletContext(ServletContext context) {
        this.application = context;
    }
}
