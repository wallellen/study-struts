package edu.alvin.struts.validator;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.Resources;
import org.apache.taglibs.standard.extra.spath.ParseException;

/**
 * 表单验证器类
 *
 * @author Alvin
 */
public class FormValidator implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 验证日期
     *
     * @throws ParseException
     */
    public static Object validateDateRange(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) throws ParseException {
        // 获取日期值
        String value;
        if (bean instanceof String) {        // 判断传入的值是否为String类型
            value = (String) bean;    // 获取传入的值
        } else {
            value = ValidatorUtils.getValueAsString(bean, field.getProperty());    // 将传入的值转为String类型
        }

        boolean result = false;
        // 验证传入的值是否存在
        if (GenericValidator.isBlankOrNull(value)) {
            result = true;        // 如果值不存在，无需验证，设置验证成功
        } else {
            // 获取配置中datePattern参数的值，表示日期格式
            String datePattern = Resources.getVarValue("datePattern", field, validator, request, false);
            boolean isStrict = false;
            // 判断参数是否存在
            if (GenericValidator.isBlankOrNull(datePattern)) {
                // 获取默认的datePatternStrict参数值，表示默认的日期格式
                datePattern = Resources.getVarValue("datePatternStrict", field, validator, request, false);
                // 判断datePatternStrict参数是否存在
                if (GenericValidator.isBlankOrNull(datePattern) == false) {
                    isStrict = true;
                }
            }
            // 获取本地化对象
            Locale locale = RequestUtils.getUserLocale(request, null);

            Date date = null;
            try {
                // 判断日期格式参数是否取到
                if (GenericValidator.isBlankOrNull(datePattern)) {
                    date = GenericTypeValidator.formatDate(value, locale);    // 使用系统默认格式处理日期字符串
                } else {
                    date = GenericTypeValidator.formatDate(value, datePattern, isStrict);    // 使用配置文件定义格式处理日期字符串
                }
            } catch (Exception e) {
                // 在这里记录错误日志
            }

            if (date == null) {
                result = false;
            } else {
                Date minDate = null;
                // 获取配置中min参数的值
                String dateValue = Resources.getVarValue("min", field, validator, request, false);
                // 判断min参数值是否取到
                if (GenericValidator.isBlankOrNull(dateValue) == false) {
                    try {
                        // 将min参数值格式化为日期类型
                        if (GenericValidator.isBlankOrNull(datePattern)) {
                            minDate = GenericTypeValidator.formatDate(value, locale);
                        } else {
                            minDate = GenericTypeValidator.formatDate(value, datePattern, isStrict);
                        }
                    } catch (Exception e) {
                        throw new ParseException(e.getMessage());
                    }
                }

                Date maxDate = null;
                // 获取配置中max参数的值
                dateValue = Resources.getVarValue("max", field, validator, request, false);
                // 判断max参数值是否取到
                if (GenericValidator.isBlankOrNull(dateValue) == false) {
                    try {
                        // 将max参数值格式化为日期类型
                        if (GenericValidator.isBlankOrNull(datePattern)) {
                            maxDate = GenericTypeValidator.formatDate(value, locale);
                        } else {
                            maxDate = GenericTypeValidator.formatDate(value, datePattern, isStrict);
                        }
                    } catch (Exception e) {
                        throw new ParseException(e.getMessage());
                    }
                }

                // 判断指定日期是否在指定范围内
                result = date.getTime() < minDate.getTime() || date.getTime() > maxDate.getTime();
            }
        }
        if (result == false) {
            // 设置错误信息
            errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
        }
        return result;
    }
}
