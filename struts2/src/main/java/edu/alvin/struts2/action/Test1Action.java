package edu.alvin.struts2.action;

import java.util.Date;

/**
 * 控制器类，测试基本控制器
 *
 * @author Alvin
 */
public class Test1Action {
    // 日期对象
    private Date now;

    /**
     * 控制器方法
     *
     * @return 跳转名称
     */
    public String input() {
        now = new Date();
        return "success";
    }

    public Date getNow() {
        return now;
    }
}
