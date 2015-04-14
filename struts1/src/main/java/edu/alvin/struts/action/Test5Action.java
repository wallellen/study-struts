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

import edu.alvin.struts.form.UserForm1;

/**
 * 控制器类，测试编程式表单验证
 *
 * @author Alvin
 */
public class Test5Action extends MappingDispatchAction {

    /**
     * 初始化表单项
     */
    public ActionForward input(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        UserForm1 userForm1 = (UserForm1) form;

        // 设置表单项值
        userForm1.setAccount("");
        userForm1.setPassword("");
        userForm1.setPasswordAck("");
        userForm1.setName("");
        userForm1.setGender("M");
        userForm1.setEmail("@");
        userForm1.setTelephone("");

        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -20);
        userForm1.setBirthday(new Date(now.getTimeInMillis()));

        // 跳转到名称为success的视图
        return mapping.findForward("success");
    }

    /**
     * 提交表单
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取表单对象
        UserForm1 userForm1 = (UserForm1) form;

        // 错误集合对象
        ActionErrors errors = new ActionErrors();
        // 模拟数据库查询，用户是否存在
        ServletContext context = getServlet().getServletContext();
        Map<String, UserForm1> accountMap = null;
        synchronized (context) {
            accountMap = (Map<String, UserForm1>) context.getAttribute("accountMap");
            if (accountMap == null) {
                accountMap = new ConcurrentHashMap<>();
                context.setAttribute("accountMap", accountMap);
            }
        }
        if (accountMap.containsKey(userForm1.getAccount())) {
            errors.add("account", new ActionMessage("error.form.accountExist"));
        } else {
            accountMap.put(userForm1.getAccount(), userForm1);
        }
        ActionForward forward;
        if (errors.isEmpty()) {
            forward = mapping.findForward("success");
            String url = MessageFormat.format(forward.getPath(), URLEncoder.encode(userForm1.getAccount(), "UTF-8"));
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
        Map<String, UserForm1> accountMap = (Map<String, UserForm1>) context.getAttribute("accountMap");
        if (accountMap != null) {
            String account = request.getParameter("uid");
            if (StringHelper.isNullOrEmpty(account) == false) {
                UserForm1 userForm1 = accountMap.get(account);
                if (userForm1 != null) {
                    request.setAttribute("userForm1", userForm1);
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
