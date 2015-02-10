package mesCommandes;

import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

public class Paniers {
	public static TreeMap<String, ArrayList<String>> lescommandes = new TreeMap<String, ArrayList<String>>();

	// String

	static public void afficherIterationDisques(Iterator<String> iter,
			PrintWriter out, String repertoire) {
		Disque leDisque;
		out.println("<table border=1>");
		while (iter.hasNext()) {
			leDisque = Stock.trouveDisque((String) iter.next());
			out.println("<tr> <td>" + leDisque.getTitre() + ",  "
					+ leDisque.getNom() + ",  " + leDisque.getPrix()
					+ " Euros,  Ann¨¦e: " + leDisque.getAnnee() + "</td>");
			out.println("<td> <IMG SRC= '" + repertoire + "/images/"
					+ leDisque.getImage() + "'  BORDER=0> </A><br> </td> ");
		}
		out.println("</tr> </table>");
	}

	static public void afficherVenteDisques(Iterator<String> iter,
			PrintWriter out, String repertoire) {
		Disque leDisque;
		out.println("<table border=1>");
		while (iter.hasNext()) {
			leDisque = Stock.trouveDisque((String) iter.next());
			out.println("<tr> <td>" + leDisque.getTitre() + ",  "
					+ leDisque.getNom() + ",  " + leDisque.getPrix()
					+ " Euros,  Ann¨¦e: " + leDisque.getAnnee() + "</td>");
			out.println("<td> <IMG SRC= '" + repertoire + "/images/"
					+ leDisque.getImage() + "'  BORDER=0> </A><br> </td> ");
			out.println("<td><A HREF='commande?code=" + leDisque.getReference()
					+ "&ordre=ajouter'>");
			out.println("<IMG SRC='" + repertoire
					+ "/images/panier.gif\' BORDER=0></A><br> </td> </tr>");
		}
		out.println("</tr> </table>");
	}

	static public void afficherTuplesDisques(ResultSet rs, PrintWriter out,
			String repertoire) {
		Disque leDisque;

		try {
			out.println("<table border=1>");
			while (rs.next()) {
				 //out.println( rs.getString("nomarticle") + "<br>" );

				leDisque = Stock.trouveDisque(rs.getString("nomarticle"));
				out.println("<tr> <td>" + leDisque.getTitre() + "  ,  "
						+ leDisque.getNom() + " ,  " + leDisque.getPrix()
						+ " Euros  ,  Ann¨¦3 :" + leDisque.getAnnee() + "</td>");
				out.println("<td> <IMG SRC= '" + repertoire + "/images/"
						+ leDisque.getImage() + "'  BORDER=0> </A><br> </td> ");
			}
			out.println("</tr></table>");
		} catch (Exception E) {
			out.println("erreur base");
			System.out.println(" - probeme ajoute " + E.getClass().getName());
			E.printStackTrace();
		}
	}
}
