/**
 * 
 * $Id: AuthenticationFilter.java,v 1.7 2008-08-14 17:12:21 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/11/09 17:33:19  pandyas
 * Commented out debug code
 *
 * Revision 1.5  2006/04/17 19:10:50  pandyas
 * Added $Id: AuthenticationFilter.java,v 1.7 2008-08-14 17:12:21 pandyas Exp $ and $log:$
 *
 * 
 */


package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class AuthenticationFilter implements Filter {

    private String onFailure = "login.jsp";
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        onFailure = this.filterConfig.getInitParameter("onFailure");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // if the requested page is the onFailure page continue
        // down the chain to avoid an infinite redirect loop
        if (req.getServletPath().equals(onFailure)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(); // get the session or create it
        String user = (String) session.getAttribute(Constants.CURRENTUSER);

        if (user == null) {

            session.setAttribute(Constants.LOGINFAILED, "true");

            // redirect to the login page
            res.sendRedirect(req.getContextPath() + onFailure);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
