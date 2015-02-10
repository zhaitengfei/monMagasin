package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

class Util {

	static public String rechercheCookies(Cookie[] lescookies, String nom) {
		String reponse = "non";

		// ********************************************************************************************
		// recherche si dans le tableau de cookies il en existe un avec le nom
		// donn�.
		// si oui la valeur de ce cookie est donn閑 en r閟ultat (mot de passe)
		// Cette m閠hode sera appel閑 par d抋utres classes aussi elle est � public
		// �
		// et � static � pour pouvoir l抋ppeler directement par la classe
		// "Util.rechercheCookies(..)"
		// ********************************************************************************************
		if ((lescookies != null) && (lescookies.length > 0)) {
			for (Cookie cookie : lescookies) {
				if (cookie.getName().equals(nom)) {
					reponse = cookie.getValue();
				}
			}

		}

		return reponse;
	}

	static boolean identique(String recu, String cookie) {
		return ((cookie != null) && (recu.equals(cookie)));

	}
}