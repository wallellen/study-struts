package edu.alvin.struts.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.springframework.stereotype.Controller;

/**
 * 控制器类，测试整合Spring框架
 *
 * @author Alvin
 */
@Controller("/test9/action")
public class Test9Action extends LookupDispatchAction {

    @Override
    protected Map getKeyMethodMap() {
        Map<String, String> methodMap = new HashMap<>();
        methodMap.put("submit.noException", "noExcepiton");
        methodMap.put("submit.doException", "doExcepiton");
        return methodMap;
    }

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

    public ActionForward noExcepiton(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("result", "未引发任何异常");
        return mapping.findForward("success");
    }

    public ActionForward doExcepiton(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new SQLException("测试异常信息");
    }
}