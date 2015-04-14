package edu.alvin.struts2.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import net.alvin.util.helper.StringHelper;

/**
 * 控制器类，测试通配符
 *
 * @author Alvin
 */
public class Test4Action implements SessionAware {
    // 名称
    private String name;
    // 值
    private String value;
    // 表单错误信息
    private Map<String, Object> actionErrors;
    // 用户上下文对象
    private Map<String, Object> session;

    /**
     * 输入表单处理方法
     */
    public String input() {
        name = "";
        value = "";
        return "input";
    }

    /**
     * 提交表单的方法
     */
    public String submit() {
        actionErrors = new HashMap<>();
        // 检测表单项的值
        if (StringHelper.isNullOrEmpty(name) || name.length() < 3) {
            actionErrors.put("name", "无效的名称");
        }
        if (StringHelper.isNullOrEmpty(value)) {
            actionErrors.put("value", "无效的值");
        }
        if (actionErrors.isEmpty()) {
            session.put("name", name);
            session.put("value", value);
            return "submit";
        }
        return "input";
    }

    /**
     * 显示结果
     */
    public String result() {
        name = (String) session.remove("name");
        value = (String) session.remove("value");
        return "result";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Map<String, Object> getActionErrors() {
        return actionErrors;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
