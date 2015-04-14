package edu.alvin.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * 表单类
 *
 * @author Alvin
 */
public class ValueForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    // 名称
    private String name;
    // 值
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
