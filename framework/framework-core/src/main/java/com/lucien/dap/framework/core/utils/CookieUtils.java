package com.lucien.dap.framework.core.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    /**
     * 设置cookie</br>
     *
     * @param name     cookie名称
     * @param value    cookie值
     * @param request  http请求
     * @param response http响应
     */
    public static void setCookie(String name, String value, HttpServletRequest request, HttpServletResponse response) {
        int maxAge = -1;
        CookieUtils.setCookie(name, value, maxAge, request, response);
    }

    /**
     * 设置cookie</br>
     *
     * @param name     cookie名称
     * @param value    cookie值
     * @param maxAge   最大生存时间
     * @param request  http请求
     * @param response http响应
     */
    public static void setCookie(String name, String value, int maxAge, HttpServletRequest request, HttpServletResponse response) {
        String domain = request.getServerName();
        setCookie(name, value, maxAge, domain, response);
    }

    public static void setCookie(String name, String value, int maxAge, String domain, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取cookie的值</br>
     *
     * @param name    cookie名称
     * @param request http请求
     * @return cookie值
     */
    public static String getCookie(String name, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (name.equalsIgnoreCase(cookies[i].getName())) {
                return cookies[i].getValue();
            }
        }
        return null;
    }

    /**
     * 删除cookie</br>
     *
     * @param name     cookie名称
     * @param request  http请求
     * @param response http响应
     */
    public static void deleteCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.setCookie(name, "", -1, request, response);
    }

    /**
     * 删除cookie</br>
     *
     * @param name     cookie名称
     * @param response http响应
     */
    public static void deleteCookie(String name, String domain, HttpServletResponse response) {
        CookieUtils.setCookie(name, "", -1, domain, response);
    }

    public static String getSessionIdFromCookie(HttpServletRequest request, HttpServletResponse response) {
        String sid = CookieUtils.getCookie("JSESSIONID", request);
        if (StringUtils.isEmpty(sid) && response != null) {
            sid = java.util.UUID.randomUUID().toString();
            CookieUtils.setCookie("JSESSIONID", sid, request, response);
            request.setAttribute("JSESSIONID", sid);
        }
        return sid;
    }
}
