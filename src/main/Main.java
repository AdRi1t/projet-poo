package main;

import java.awt.EventQueue;
import java.awt.Polygon;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import Vue.Plateau;
import Model.Tuile;
import Vue.Menu.Fenetre;
import Vue.Menu.FenetrePrincipale;

public class Main {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
