package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class MonFiltre implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String nom, motPasse = null;
		String nomCookie = null;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		Cookie[] cookies = hrequest.getCookies();
		HttpSession session = hrequest.getSession();
		nom = (String) session.getAttribute("nom");
		Stock stockDisponible = (Stock) session.getAttribute("LeStock");
		// ********************************************************************************************
		// s抜l n抏xiste pas un cookie dont le nom est celui dans la variable �
		// nom � de session
		// vous pouvez utilisez la m閠hode � rechercheCookies � de la classe
		// Util.java
		// et qu抜l n抏xiste pas la variable de session � leStock � : appel de la
		// servlet � sinscrire �
		// Autrement on continue (chain.doFilter).
		// ********************************************************************************************
		nomCookie = Util.rechercheCookies(cookies, nom);
		if (nomCookie == null || stockDisponible == null) {
			hresponse.sendRedirect("sinscrire");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
