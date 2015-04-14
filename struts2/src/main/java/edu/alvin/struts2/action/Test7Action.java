package edu.alvin.struts2.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 控制器类，异常处理
 *
 * @author Alvin
 */
public class Test7Action extends ActionSupport {
    private static final long serialVersionUID = 1L;

    // 是否抛出异常
    private boolean raise;

    /**
     * 输入表单
     */
    public String input() {
        raise = true;
        return INPUT;
    }

    /**
     * 提交表单
     */
    public String submit() throws SQLException {
        if (raise) {
            throw new SQLException("测试异常");
        }
        addActionMessage("未引发异常");
        return INPUT;
    }

    public void setRaise(boolean raise) {
        this.raise = raise;
    }
}
