package br.univesp.PJI1102024G02.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rubens
 */
@WebFilter(filterName = "SessionStartFilter", urlPatterns = {"/*"})
public class SessionStartFilter implements Filter {

    private FilterConfig filterConfig = null;
    
    public SessionStartFilter() { }    

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletRequest) request).getSession();
        chain.doFilter(request, response);
    }

    public void destroy() { }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }
}
