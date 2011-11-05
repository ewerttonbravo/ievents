package br.com.ievents.persistence;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(servletNames="Faces Servlet")
public class JPAFilter implements javax.servlet.Filter {

	private EntityManagerFactory emf;
	
	@Override
	public void destroy() {
		emf.close();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		EntityManager em = emf.createEntityManager();
		((HttpServletRequest) request).getSession().setAttribute("entityManager", em);
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		emf = Persistence.createEntityManagerFactory("ieventsPU");
	}

}
