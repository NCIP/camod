package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

		private String onFailure = "login.jsp";
		private FilterConfig filterConfig;
		
		public void init( FilterConfig filterConfig) throws ServletException {
			this.filterConfig = filterConfig;
			onFailure = filterConfig.getInitParameter( "onFailure" );
		}
		
		public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
		throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			
			// if the requested page is the onFailure page continue
			//down the chain to avoid an infinite redirect loop
			if( req.getServletPath().equals(onFailure) ) {
				chain.doFilter( request, response );
				return;
			}
			
			HttpSession session = req.getSession(); //get the session or create it
			String user = (String) session.getAttribute( Constants.CURRENTUSER );
			
			if ( user == null) {
				
				//redirect to the login page
				res.sendRedirect( req.getContextPath() + onFailure );
			} else {
				chain.doFilter( request, response );
			}			
		}
		
		public void destroy() {	}		
}
