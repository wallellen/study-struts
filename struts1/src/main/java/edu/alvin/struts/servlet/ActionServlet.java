package edu.alvin.struts.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.alvin.util.helper.StringHelper;


public class ActionServlet extends org.apache.struts.action.ActionServlet {
    private static final long serialVersionUID = 1L;

    // 字符集编码
    private String encoding;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 从配置文件中获取请求编码名称
        encoding = config.getInitParameter("encoding");
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (StringHelper.isNullOrEmpty(encoding) == false) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        super.service(request, response);
    }
}
