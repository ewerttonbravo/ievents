package br.com.ievents.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns= {"/pages/users/* ", " /pages/events/*"} )
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		
		if (null == session) {
			response.sendRedirect( request.getContextPath() + "/login.jsf" );
		} else {
			Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
			if (null == loggedIn) {
				response.sendRedirect( request.getContextPath() + "/login.jsf" );
			}
		}
		chain.doFilter(request, response); // continua...
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
