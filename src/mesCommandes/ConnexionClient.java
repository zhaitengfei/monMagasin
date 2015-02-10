package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class ConnexionClient extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nomRecu, motPasseRecu;
		String connexion, inscrit, erreur;
		String nomCookie;
		String nomAffiche, motDePasseAffiche;
		Cookie[] cookies;

		// ********************************************************************************************
		// initialisation des diff閞ents param鑤res possibles
		// ********************************************************************************************

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		nomRecu = request.getParameter("nom");
		motPasseRecu = request.getParameter("motDePasse");
		connexion = request.getParameter("connexion");
		inscrit = request.getParameter("inscrit");
		erreur = request.getParameter("erreur");
		cookies = request.getCookies();
		nomAffiche = "";
		motDePasseAffiche = "";
		
		// ********************************************************************************************
		// cas 1 pas de param鑤re connexion
		// demande nom et mot de passe par un formulaire et un bouton "submit"
		// de nom connexion
		// si parametre "inscrit" present : message diasant que l'inscription a
		// bien 閠� prise en compte
		// et mettre la valeur du nom dans le formulaire
		// si parametre "erreur" present : message indiquant le type d'errreur.
		// ********************************************************************************************
		if (connexion == null) {
			out.println("<html> <body>");
			if (inscrit != null && inscrit.equals("true")) {
				out.println("L'inscription a été bien faite!");
				nomAffiche = "value=" + nomRecu;
			}
			if (erreur != null) {
				if(erreur.equals("nomInconnu")){
					out.println("Le nom est inconnu!");
				} else if(erreur.equals("mdpMauvais")){
					out.println("Le mot de passe est mauvais!");
				}				
				nomAffiche = "value=" + nomRecu;
				motDePasseAffiche = "value=" + motPasseRecu;
			}
			out.println("<form action=\"seconnecter\" method=POST>");
			out.println("Nom " + "<input type=text size=20 name=nom " + nomAffiche + "><br>");
			out.println("Mot De Passe "
					+ "<input type=text size=20 name=motDePasse " + motDePasseAffiche + "><br>");
			out.println("<input type=hidden name=connexion value=\"true\">");
			out.println("<input type=submit value=\"Connexion\">");
			out.println("</form>");
			out.println("</body> </html> ");
			out.close();
		}

		// ********************************************************************************************
		// cas 2 param鑤re connexion pr閟ent (suite du cas 1)
		// la connexion est demand閑 on v閞ifie que le nom et le mot de passe sont
		// pr閟ents dans un cookie
		// utilisez la m閠hode � rechercheCookies � � compl閠er dans la classe
		// Util.java
		// si oui appel de la servlet � EntreeMagasinDisque � avec le param鑤re
		// nom
		// si non rappel de la servlet courante � ConnectionClient � avec le
		// param鑤re "erreur" indiquant le type d'erreur
		// ********************************************************************************************
		if (connexion != null) {
			nomCookie = Util.rechercheCookies(cookies, nomRecu);
			if (nomCookie.equals("non")) {				
				response.sendRedirect("seconnecter?erreur=nomInconnu&nom=" + nomRecu +"&motDePasse="+ motPasseRecu);
			} else if(!Util.identique(motPasseRecu, nomCookie)){
				response.sendRedirect("seconnecter?erreur=mdpMauvais&nom=" + nomRecu +"&motDePasse="+ motPasseRecu);
			}else {
				response.sendRedirect("entree?nom="+nomRecu);
				out.close();
			}
		}
	} // doGet(HttpServletRequest

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
