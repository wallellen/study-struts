package edu.alvin.struts2.action;

import java.util.HashMap;
import java.util.Map;

import net.alvin.util.helper.StringHelper;

/**
 * 控制器类，测试通配符
 *
 * @author Alvin
 */
public class Test3Action {
    // 名称
    private String name;
    // 值
    private String value;
    // 表单错误信息
    private Map<String, Object> actionErrors;

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
        return "submit";
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
}
