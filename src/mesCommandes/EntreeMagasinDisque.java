package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class EntreeMagasinDisque extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomRecu;
		Stock stock;

		nomRecu = request.getParameter("nom");
		stock = new Stock();

		// ********************************************************************************************
		// Cr閑z deux variables de session : � nom � qui a pour valeur le nom de
		// l抲tilisateur
		// et � LeStock � qui a pour valeur une instance de la classe Stock,
		// et appeler la servlet AfficherLesDisques.java
		// ********************************************************************************************
		HttpSession session = request.getSession();
		session.setAttribute("nom", nomRecu);
		session.setAttribute("LeStock", stock);
		response.sendRedirect("achat");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
