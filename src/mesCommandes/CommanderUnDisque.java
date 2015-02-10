package mesCommandes;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class CommanderUnDisque extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String nom = null;
		String ordre, code;
		HttpSession session = request.getSession();
		nom = (String) session.getAttribute("nom");
		ordre = request.getParameter("ordre");
		code = request.getParameter("code");

		// ***********************************************************
		// Si la personne dont le nom est dans la session, ne poss鑔e pas de
		// panier ,
		// son panier est cr殚 dans l抏nsemble des paniers, "Panier.lescommandes"
		// C抏st une nouvelle ArrayList qui est rajout� dans la TreeMap
		// "lescommandes" de la classe � Paniers �,
		// avec comme cl� le nom.
		//
		// ************************************************************
		if (!Paniers.lescommandes.containsKey(nom)) {
			ArrayList<String> commandes = new ArrayList<String>();
			Paniers.lescommandes.put(nom, commandes);
		}

		ArrayList<String> listeDisque = Paniers.lescommandes.get(nom);

		// ************************************************************
		// Si le param鑤re � ordre � est pr閟ent est a comme valeur � ajouter �,
		// la r閒閞ence du disque pass閑 en param鑤re est rajout閑 dans le panier
		// (ArrayList<String>).
		//
		// ***********************************************************
		if (ordre != null && ordre.equals("ajouter")) {

			listeDisque.add(code);
			Paniers.lescommandes.put(nom, listeDisque);
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>  votre commande </title>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<h3>" + "Bonjour  " + nom + "  voici  votre commande"
				+ "</h3>");

		// *********************************************************
		// affichage du contenu du panier par la m閠hode afficherIterationDisques
		// de � Paniers � avec trois param鑤res :
		// - un Iterateur sur les disques disponibles dans le magasin
		// - le � PrintWriter � pour pouvoir rajouter ces disques dans la r閜onse
		// HTML,
		// - le r閜ertoire courant de votre application
		// "request.getContextPath()"
		// *********************************************************
		Iterator<String> iter = listeDisque.iterator();
		String repertoire = request.getContextPath();
		Paniers.afficherIterationDisques(iter, out, repertoire);

		out.println(" </table>");
		out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
		out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
		out.println("<A HREF=facturer> Fin de la commande et demande de la facture </A><br> ");
		out.println("</body>");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
