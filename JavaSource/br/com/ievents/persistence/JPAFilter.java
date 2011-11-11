package br.com.ievents.persistence;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebFilter(servletNames="Faces Servlet")
public class JPAFilter implements javax.servlet.Filter {

	private static Log log = LogFactory.getLog(JPAFilter.class);
	
	private EntityManager em;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			log.info("begin transaction");
			if (!em.getTransaction().isActive())
				em.getTransaction().begin();

			chain.doFilter(request, response);
			
			log.info("commit transaction");
			if (em.getTransaction().isActive())
				em.getTransaction().commit();
			
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			try {
				if (em.getTransaction().isActive()) {
					log.info("Trying to rollback database transaction after exception");
					em.getTransaction().rollback();
				}
			} catch (Throwable e2) {
				log.error("Could not rollback transaction after exception!", e2);
			}
			throw new ServletException(e);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.debug("get entityManager from JPAUtil");
		em = JPAUtil.getEntityManager();
	}

}
