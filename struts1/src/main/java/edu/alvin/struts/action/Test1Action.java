package edu.alvin.struts.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 控制器类，测试基本Action类
 *
 * @author Alvin
 */
public class Test1Action extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 在请求上下文中存储信息
        request.setAttribute("now", new Date());
        // 跳转到名称为success的视图
        return mapping.findForward("success");
    }
}