package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class InscriptionClient extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nomRecu, motPasseRecu;
		String inscrire, erreur;

		// ********************************************************************************************
		// initialisation des diff閞ents param鑤res possibles
		// ********************************************************************************************

		// ********************************************************************************************
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		nomRecu = request.getParameter("nom");
		motPasseRecu = request.getParameter("motDePasse");
		inscrire = request.getParameter("inscrire");
		erreur = request.getParameter("erreur");

		// ********************************************************************************************
		// cas 1 pas de param鑤re inscrire
		// Si le param鑤re "erreur" est pr閟ent, informez que les informations
		// transmises ne sont pas acceptables.
		// demandez des informations (nom, mot de passe, email, t閘閜hone) par un
		// formulaire
		// et rappel de cette m阭e servlet avec ces informations en param鑤re
		// le param鑤re inscrire doit aussi 阾re envoy� avec comme valeur inscrire
		// (bouton submit)
		// ********************************************************************************************
		if (inscrire == null) {
			out.println("<html> <body>");
			if (erreur != null && erreur.equals("true")) {
				out.println("Les informations envoyées n'est pas satisfaisantes: le nom et le mot de passe sont obligatoires et de plus de 4 caractères !");
			}
			out.println("<form action=\"sinscrire\" method=POST>");
			out.println("Nom " + "<input type=text size=20 name=nom>" + "<br>");
			out.println("Mot De Passe "
					+ "<input type=text size=20 name=motDePasse>" + "<br>");
			out.println("Email " + "<input type=text size=20 name=email>"
					+ "<br>");
			out.println("Telephone "
					+ "<input type=text size=20 name=telephone>" + "<br>");
			out.println("<input type=hidden name=inscrire value=\"true\">");
			out.println("<input type=submit value=\"Soumettre la requête\">");
			out.println("</form>");
			out.println("</body> </html> ");
			out.close();
		}

		// ********************************************************************************************
		// cas 2 param鑤re inscrire pr閟ent
		// si les param鑤res "nom" et "motdepasse" sont pr閟ent et font plus de 4
		// caract鑢es alors :
		// cr閍tion du cookie du nom "nomRecu" et de valeur "motPasseRecu".
		// et appel de la servlet ConnexionClient avec le param鑤re "inscrit"
		// Sinon rappel de cette m阭e servlet avec le param鑤re erreur
		// ********************************************************************************************
		if (inscrire != null) {
			if (nomRecu == null || nomRecu.length() <= 4 || nomRecu == null
					|| motPasseRecu.length() <= 4) {
				response.sendRedirect("sinscrire?erreur=true");
			} else {
				String cookieName = nomRecu;
				String cookieValue = motPasseRecu;
				Cookie nomCookie = new Cookie(cookieName, cookieValue);
				response.addCookie(nomCookie);
				response.sendRedirect("seconnecter?inscrit=true&nom="+nomRecu);
				out.close();
			}
		}
	} // doGet(HttpServletRequest

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
