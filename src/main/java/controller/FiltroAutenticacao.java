package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/*") 
public class FiltroAutenticacao implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        String url = req.getRequestURI();
        String tokenId = (String) session.getAttribute("tokenId");
        
        if (tokenId != null)
            chain.doFilter(request, response);

        String contextPath = req.getContextPath();
        
        if (url.equals(contextPath + "/index.jsp") || url.equals(contextPath + "/index") || url.equals(contextPath + "/login") || url.equals(contextPath + "/logout")) 
            chain.doFilter(request, response);
        else
            res.sendRedirect("/projeto_adilson/index.jsp");

    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
