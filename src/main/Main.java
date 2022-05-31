package main;

import java.awt.EventQueue;
import java.io.IOException;
import Vue.Menu.FenetrePrincipale;

// Fonction Main lance le programme
public class Main {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Création de la fenêtre du programme
					// Lancer automatique sur le menu principal
					FenetrePrincipale menu = new FenetrePrincipale();
					menu.fenetre_menu();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() throws IOException {

	}

}
