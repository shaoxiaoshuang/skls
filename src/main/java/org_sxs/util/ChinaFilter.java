package org_sxs.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * 过滤器处理中文乱码
 */
@WebFilter(filterName = "ChinaFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "encoding", value = "utf-8")
})
public class ChinaFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //处理
        req.setCharacterEncoding(encoding);

        //过滤下一个或web资源
        chain.doFilter(req, resp);
    }

    private String encoding = "";

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

}
