package edu.alvin.struts.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorCodeExceptionHandler extends ExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorCodeExceptionHandler.class);

    @Override
    public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOG.error(ex.getMessage(), ex);
        return super.execute(ex, ae, mapping, formInstance, request, response);
    }
}
