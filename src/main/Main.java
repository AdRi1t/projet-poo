package main;

import java.awt.EventQueue;
import java.awt.Polygon;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import Model.Plateau;
import Model.Tuile;
import Vue.Menu.Fenetre;
import Vue.Menu.FenetrePrincipale;

public class Main {
// Ces modifs sont faites par wissal
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Main window = new Main();
					//window.frame.setVisible(true);
					FenetrePrincipale menu= new FenetrePrincipale();
					menu.fenetre_menu();
				//	menu.fenetre_regles();
					//menu.fenetre_regles_generales();
					//menu.fenetre_credits();
					//menu.fenetrePseudos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() throws IOException {
		initialize();
		Tuile.initTuiles();
		Plateau.initPlateau();
		Plateau.screenRender(frame);
		Tuile.setHexagone();
		Tuile.setImageTerrain();
		Tuile.initVoisin();
		for (Tuile tuile : Tuile.listeTuile) {
			System.out.println("x:"+tuile.PositionX + "  y:" +tuile.PositionY +"  index:"+tuile.getIndex() +"  hexagon:"+tuile.getHexagon() +  "  type:"+ tuile.getTypeTuile() +  "	polygone :" + tuile.getHexagon() + "	Voisin : " + tuile.getVoisin());
		}
		System.out.printf("\n  %d \n",Plateau.listHexagone.size());
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0, 1200,720);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
