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

        String url = req.getRequestURI();
        String contextPath = req.getContextPath(); 

        HttpSession session = req.getSession(false);
        String tokenId = (session != null) ? (String) session.getAttribute("tokenId") : null;

        boolean autenticado = (tokenId != null);

        boolean isPublico = url.equals(contextPath + "/index.jsp")
                         || url.equals(contextPath + "/index")
                         || url.equals(contextPath + "/login")
                         || url.equals(contextPath + "/logout")
                         || url.startsWith(contextPath + "/css")
                         || url.startsWith(contextPath + "/js")
                         || url.startsWith(contextPath + "/img");

        if (autenticado || isPublico) {
            chain.doFilter(request, response);
            return;
        }

        res.sendRedirect(contextPath + "/index.jsp"); 
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
