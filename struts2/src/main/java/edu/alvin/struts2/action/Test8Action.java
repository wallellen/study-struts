package edu.alvin.struts2.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import net.alvin.util.helper.StringHelper;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import edu.alvin.struts2.vo.ValidateUser;

/**
 * 控制器类，测试注解配置
 *
 * @author Alvin
 */
@ParentPackage("default")
@Namespace("/test8")
@Result(name = "input", location = "/WEB-INF/jsp/test8/input.jsp")
public class Test8Action extends ActionSupport {
    private static final long serialVersionUID = 1L;

    // 用户对象
    private ValidateUser user;

    /**
     * 输入表单
     */
    @Actions({
            @Action("input"), @Action("default")
    })
    public String input() {
        // 设置表单项值
        user = new ValidateUser();
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
     * 提交表单
     */
    @Action(value = "submit", results = {
            @Result(name = "submit", type = "redirectAction", params = {"actionName", "result", "account", "${user.account}"})
    })
    @VisitorFieldValidator(fieldName = "user", shortCircuit = false)
    public String submit() throws SQLException {
        // 获取应用程序上下文
        Map<String, Object> application = ActionContext.getContext().getApplication();

        Map<String, ValidateUser> accountMap = null;
        synchronized (application) {
            // 从ServletContext中获取用户信息集合
            accountMap = (Map<String, ValidateUser>) application.get("accountMap");
            if (accountMap == null) {
                // 创建用户信息集合并存入ServletContext对象
                accountMap = new ConcurrentHashMap<>();
                application.put("accountMap", accountMap);
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
     * 显示结果
     */
    @Action(value = "result", results = {
            @Result(name = "result", location = "/WEB-INF/jsp/test8/result.jsp")
    })
    public String result() throws IOException {
        // 获取请求上下文集合
        Map<String, Object> params = ActionContext.getContext().getParameters();
        // 获取响应对象
        HttpServletResponse response = ServletActionContext.getResponse();
        // 获取应用程序上下文对象
        Map<String, Object> application = ActionContext.getContext().getApplication();

        // 判断是否传递了account参数
        String account = ((String[]) params.get("account"))[0];
        if (StringHelper.isNullOrEmpty(account)) {
            response.sendError(500);
            return NONE;
        }

        // 获取用户信息集合
        Map<String, ValidateUser> actionMap = (Map<String, ValidateUser>) application.get("accountMap");
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

    public ValidateUser getUser() {
        return user;
    }

    public void setUser(ValidateUser user) {
        this.user = user;
    }
}
