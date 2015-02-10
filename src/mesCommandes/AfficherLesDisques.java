package mesCommandes;
import java.util.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class AfficherLesDisques extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		String nom = null;
		Stock stockDisponible = null;
		
	//  ********************************************************************************************        
	//   initialisez  nom et  stockDisponible  � partir des variables de session.
	//      pour l抜nstant il n抏st pas fait de test pour savoir si ces variables existent, par la suite ce test sera fait par un filtre.
	//  ********************************************************************************************                             	
	   HttpSession session = request.getSession();
	   nom = session.getAttribute("nom").toString();
	   stockDisponible = (Stock)(session.getAttribute("LeStock"));
	   
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   out.println("<html>");
	   out.println("<head>");
	   out.println("<title> Commande de disques </title>");
	   out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
	   out.println("</head>");
	   out.println("<body bgcolor=\"white\">");
	   out.println("<h1> Super Marché du disque </h1>");
	   out.println("<h3>" + "Bonjour " + nom.toUpperCase() + " vous pouvez commander un disque" + "</h3>");

		//  ********************************************************************************************        
	   	// Appel de la m閠hode. afficherVenteDisques de Paniers, avec trois param鑤res : 
		//    - un Iterateur sur les disques disponibles dans le magasin 
	    //                        (obtenu en demandant l'it閞ateur de la liste de stockDisponible)
		//    - le � PrintWriter � pour pouvoir rajouter ces disques dans la page de la r閜onse HTML,
		//    - et le repertoire courant de votre application  "request.getContextPath()"
		// 
	 	//  ********************************************************************************************                   
	   ArrayList<String> listeDisques = stockDisponible.getListeDisques();
	   Iterator <String> iter = listeDisques.iterator();
	   String repertoire = request.getContextPath();
	   Paniers.afficherVenteDisques(iter, out, repertoire);
	   
	   out.println("<h1> Super Marché du disque </h1>");                  
	   out.println("</body>");
	   out.println("</html>");
	} 


public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
{
   doGet(request, response);
}

}
