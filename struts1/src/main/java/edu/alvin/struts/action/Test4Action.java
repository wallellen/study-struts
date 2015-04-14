package edu.alvin.struts.action;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import edu.alvin.struts.form.NumberForm;

/**
 * 控制器类，测试LookupDispatchAction类
 *
 * @author Alvin
 */
public class Test4Action extends LookupDispatchAction {

    // 用于检测的正则表达式
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\-?\\d+$|^\\-?\\d+\\.\\d+|^\\-?\\.\\d+$");

    /**
     * 获取提交按钮资源ID和控制器方法的对应关系
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String, String> map = new HashMap<>();
        map.put("submit.addtion", "addtion");
        map.put("submit.subtraction", "subtraction");
        map.put("submit.multiplication", "multiplication");
        map.put("submit.division", "division");
        return map;
    }

    /**
     * 例外处理，即该控制器不是由指定提交按钮提交访问的情况
     */
    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return input(mapping, form, request, response);
    }

    /**
     * 初始化表单项
     */
    public ActionForward input(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        NumberForm useForm = (NumberForm) form;

        // 设置表单项值
        useForm.setNumber1("0");
        useForm.setNumber2("0");
        useForm.setResult("0");

        // 跳转到名称为success的视图
        return mapping.findForward("input");
    }

    /**
     * 检测表单内容正确性
     *
     * @param form 表单对象
     * @return 错误信息
     */
    private Map<String, Object> checkForm(NumberForm form) {
        Map<String, Object> errors = new HashMap<>();
        if (NUMBER_PATTERN.matcher(form.getNumber1()).matches() == false) {
            errors.put("number1", "数值1不正确");
        }
        if (NUMBER_PATTERN.matcher(form.getNumber2()).matches() == false) {
            errors.put("number2", "数值2不正确");
        }
        return errors;
    }

    /**
     * 加法提交按钮
     */
    public ActionForward addtion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        NumberForm useForm = (NumberForm) form;
        // 验证表单
        Map<String, Object> errors = checkForm(useForm);

        // 跳转
        ActionForward forward;
        if (errors.isEmpty()) {
            double n1 = Double.parseDouble(useForm.getNumber1());
            double n2 = Double.parseDouble(useForm.getNumber2());
            useForm.setResult(String.valueOf(n1 + n2));
            useForm.setOperator("+");
            forward = mapping.findForward("result");
        } else {
            request.setAttribute("errors", errors);
            forward = mapping.findForward("input");
        }
        // 跳转到名称为success的视图
        return forward;
    }

    /**
     * 减法提交按钮
     */
    public ActionForward subtraction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        NumberForm useForm = (NumberForm) form;
        // 验证表单
        Map<String, Object> errors = checkForm(useForm);

        // 跳转
        ActionForward forward;
        if (errors.isEmpty()) {
            double n1 = Double.parseDouble(useForm.getNumber1());
            double n2 = Double.parseDouble(useForm.getNumber2());
            useForm.setResult(String.valueOf(n1 - n2));
            useForm.setOperator("-");
            forward = mapping.findForward("result");
        } else {
            request.setAttribute("errors", errors);
            forward = mapping.findForward("input");
        }
        // 跳转到名称为success的视图
        return forward;
    }

    /**
     * 乘法提交按钮
     */
    public ActionForward multiplication(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        NumberForm useForm = (NumberForm) form;
        // 验证表单
        Map<String, Object> errors = checkForm(useForm);

        // 跳转
        ActionForward forward;
        if (errors.isEmpty()) {
            double n1 = Double.parseDouble(useForm.getNumber1());
            double n2 = Double.parseDouble(useForm.getNumber2());
            useForm.setResult(String.valueOf(n1 * n2));
            useForm.setOperator("×");
            forward = mapping.findForward("result");
        } else {
            request.setAttribute("errors", errors);
            forward = mapping.findForward("input");
        }
        // 跳转到名称为success的视图
        return forward;
    }

    /**
     * 除法提交按钮
     */
    public ActionForward division(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        NumberForm useForm = (NumberForm) form;
        // 验证表单
        Map<String, Object> errors = checkForm(useForm);

        // 跳转
        ActionForward forward;
        if (errors.isEmpty()) {
            double n1 = Double.parseDouble(useForm.getNumber1());
            double n2 = Double.parseDouble(useForm.getNumber2());
            useForm.setResult(String.valueOf(n1 / n2));
            useForm.setOperator("÷");
            forward = mapping.findForward("result");
        } else {
            request.setAttribute("errors", errors);
            forward = mapping.findForward("input");
        }
        // 跳转到名称为success的视图
        return forward;
    }
}
