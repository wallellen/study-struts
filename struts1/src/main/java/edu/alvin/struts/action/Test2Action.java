package edu.alvin.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.alvin.util.helper.StringHelper;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import edu.alvin.struts.form.ValueForm;

/**
 * 控制器类，测试MappingDispatchAction类
 *
 * @author Alvin
 */
public class Test2Action extends MappingDispatchAction {

    /**
     * 初始化表单项
     */
    public ActionForward input(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        ValueForm useForm = (ValueForm) form;

        // 设置表单项值
        useForm.setName("");
        useForm.setValue("");
        // 跳转到名称为success的视图
        return mapping.findForward("success");
    }

    /**
     * 处理提交的表单内容
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 保存错误信息的Map对象
        Map<String, Object> errors = new HashMap<>();
        // 获取表单对象
        ValueForm useForm = (ValueForm) form;

        // 检测表单项的值
        if (StringHelper.isNullOrEmpty(useForm.getName()) || useForm.getName().length() < 3) {
            errors.put("name", "无效的名称");
        }
        if (StringHelper.isNullOrEmpty(useForm.getValue())) {
            errors.put("value", "无效的值");
        }

        // 跳转到指定视图
        ActionForward forward;
        if (errors.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("valueForm", useForm);
            forward = mapping.findForward("success");
        } else {
            // 保存错误信息
            request.setAttribute("errors", errors);
            forward = mapping.getInputForward();
        }
        return forward;
    }
}
