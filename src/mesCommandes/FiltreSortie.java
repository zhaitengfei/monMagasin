package mesCommandes;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class FiltreSortie implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String nom = null;
		ArrayList<String> lesdisques = null;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpSession session = hrequest.getSession();
		chain.doFilter(request, response);

		// ************************************************************
		// ce filtre doit s'executer après la servlet
		// il efface le panier du client en cours
		// ************************************************************
		nom = (String) session.getAttribute("nom");
		lesdisques = Paniers.lescommandes.get(nom);
		if (!lesdisques.isEmpty()) {
			lesdisques = new ArrayList<String>();
			Paniers.lescommandes.put(nom, lesdisques);
		}
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
