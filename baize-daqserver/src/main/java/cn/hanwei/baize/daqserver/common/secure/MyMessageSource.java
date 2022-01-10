package cn.hanwei.baize.daqserver.common.secure;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author XiongJiaLiang
 * @date 2021/3/12 15:52
 */

@Component
public class MyMessageSource {

    private static MessageSource messageSource;

    public MyMessageSource(MessageSource messageSource) {
        MyMessageSource.messageSource = messageSource;
    }

    public static String get(String code) {
        try {
            Session session = SecurityUtils.getSubject().getSession();
            Locale locale = (Locale) session.getAttribute("locale");
            if (locale == null) {
                return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
            }
            return messageSource.getMessage(code, null, locale);
        } catch (Exception e) {
            return code;
        }
    }

    public static String get(String code, Locale locale) {
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (Exception e) {
            return code;
        }
    }
}