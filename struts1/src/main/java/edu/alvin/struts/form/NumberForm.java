package edu.alvin.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * 表示数字的表单类
 *
 * @author Alvin
 */
public class NumberForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    // 数字1
    private String number1;
    // 数字2
    private String number2;
    // 操作符
    private String operator;
    // 数字结果
    private String result;

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
