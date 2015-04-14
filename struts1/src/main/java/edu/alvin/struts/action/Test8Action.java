package edu.alvin.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.springframework.stereotype.Controller;

/**
 * 控制器类，测试整合Spring框架
 *
 * @author Alvin
 */
@Controller("/test8/action")
public class Test8Action extends MappingDispatchAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 跳转到名称为success的视图
        return mapping.findForward("success");
    }
}