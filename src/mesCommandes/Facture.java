package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Facture extends HttpServlet {
	
	Connection connexion = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String nom = null;

		HttpSession session = request.getSession();
		nom = session.getAttribute("nom").toString();

		OuvreBase();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>  facturation </title>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
		out.println("</head>");

		out.println("<body bgcolor=\"white\">");
		out.println("<h3>" + " La commande de  " + nom.toUpperCase()
				+ " a ¨¦t¨¦ bien enregistr¨¦es !</h3>");
		
		SupprimerCommandeBase(nom);
		FermeBase();

	}

	protected void OuvreBase() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/magasin", "root", "");
			connexion.setAutoCommit(true);
			stmt = connexion.createStatement();
		} catch (Exception E) {
			log(" -------- probl¨¨me  " + E.getClass().getName());
			E.printStackTrace();
		}
	}

	protected void FermeBase() {
		try {
			stmt.close();
			connexion.close();
		} catch (Exception E) {
			log(" -------- problème  " + E.getClass().getName());
			E.printStackTrace();
		}
	}

	protected void SupprimerCommandeBase(String nom) {
		int cle = 0;
		try {
			ResultSet rset = null;
			pstmt = connexion
					.prepareStatement("SELECT id FROM client WHERE nom=?");
			pstmt.setString(1, nom);
			rset = pstmt.executeQuery();
			if (rset.next())
				cle = rset.getInt("id");
			
			pstmt = connexion
					.prepareStatement("DELETE FROM commande WHERE client=?");
			pstmt.setInt(1, cle);
			pstmt.executeUpdate();
		}

		catch (Exception E) {
			log(" - probl¨¨me  " + E.getClass().getName());
			E.printStackTrace();
		}
	}
}
