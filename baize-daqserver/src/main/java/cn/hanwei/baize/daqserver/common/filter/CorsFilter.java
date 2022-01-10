package cn.hanwei.baize.daqserver.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhen
 * @description:
 * @date 2019-06-20 17:01
 */
@WebFilter
public class CorsFilter implements Filter {
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type, Authorization, X-Requested-With,Accept,client_id,uuid");
        response.setHeader("Access-Control-Allow-Credentials","true");

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP1.1.
        response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. 
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(200);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) {}

}

