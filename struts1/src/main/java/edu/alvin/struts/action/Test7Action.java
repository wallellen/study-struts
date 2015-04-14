package edu.alvin.struts.action;

import java.net.URLEncoder;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.alvin.util.helper.StringHelper;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * 控制器类，测试动态表单对象
 *
 * @author Alvin
 */
public class Test7Action extends MappingDispatchAction {

    /**
     * 初始化表单项
     */
    public ActionForward input(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        DynaValidatorActionForm actionForm = (DynaValidatorActionForm) form;

        // 设置表单项值
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -20);
        actionForm.set("birthday", new Date(now.getTimeInMillis()));

        // 跳转到名称为success的视图
        return mapping.findForward("success");
    }

    /**
     * 提交表单
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        DynaValidatorActionForm actionForm = (DynaValidatorActionForm) form;

        // 错误集合对象
        ActionErrors errors = new ActionErrors();
        // 模拟数据库查询，用户是否存在
        ServletContext context = getServlet().getServletContext();
        Map<String, ActionForm> accountMap = null;
        synchronized (context) {
            accountMap = (Map<String, ActionForm>) context.getAttribute("accountMap");
            if (accountMap == null) {
                accountMap = new ConcurrentHashMap<>();
                context.setAttribute("accountMap", accountMap);
            }
        }
        // 获取表单账号字段值
        if (accountMap.containsKey(actionForm.getString("account"))) {
            errors.add("account", new ActionMessage("error.form.accountExist"));
        } else {
            accountMap.put(actionForm.getString("account"), actionForm);
        }

        ActionForward forward;
        if (errors.isEmpty()) {
            forward = mapping.findForward("success");
            String url = MessageFormat.format(forward.getPath(), URLEncoder.encode(actionForm.getString("account"), "UTF-8"));
            forward = new ActionForward(url, forward.getRedirect());
        } else {
            addErrors(request, errors);
            forward = mapping.getInputForward();
        }
        return forward;
    }

    /**
     * 显示结果
     */
    public ActionForward result(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        // 模拟从数据库中读取用户信息
        ServletContext context = getServlet().getServletContext();
        Map<String, ActionForm> accountMap = (Map<String, ActionForm>) context.getAttribute("accountMap");
        if (accountMap != null) {
            String account = request.getParameter("uid");
            if (StringHelper.isNullOrEmpty(account) == false) {
                DynaValidatorActionForm actionForm = (DynaValidatorActionForm) accountMap.get(account);
                if (actionForm != null) {
                    request.setAttribute("userForm3", actionForm);
                    forward = mapping.findForward("success");
                }
            }
        }
        if (forward == null) {
            response.sendError(500);
        }
        return forward;
    }
}
