package Controlleur;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

import Model.Bateau;
import Model.Explorateur;
import Model.Joueur;
import Model.LancerDe;
import Model.PhaseDuTour;
import Model.Tuile;
import Model.TuileEffet;
import Model.TypeTuile;
import Vue.AideJoueur;
import Vue.Plateau;

/**
 * <strong>La classe qui gère les cliques de la souris dans la phase principale
 * de jeu</strong>
 * <p>
 * Permet de gérer les cliques, implémente {@link MouseInputListener}.
 * </p>
 * 
 * @author Adrien Taberner
 * @see MouseEvent
 */
public class PlateauListener implements MouseInputListener {

	public static boolean phaseDePlacementExplorateur = true;
	public static boolean phaseDePlacementBateau = false;

	private JButton sourceAide;
	private JButton sourceLancerDe;
	private Joueur joueur;

	/** Permets de savoir si on a retourné une tuile avant de la prendre */
	private static boolean tuileSelection = false;

	/**
	 * Instancie un nouveau Mouse Listener.
	 * 
	 * @param source une {@link JFrame}
	 */
	public PlateauListener(JButton sourceAide,JButton sourceLancerDe) {
		super();
		if (ExplorateurControlleur.phasePlacementExploTerminer()) {
			PlateauListener.phaseDePlacementExplorateur = false;
		} else {
			PlateauListener.phaseDePlacementExplorateur = true;
		}
		if (ExplorateurControlleur.phasePlacementBateauTerminer()) {
			PlateauListener.phaseDePlacementBateau = false;
		} else {
			PlateauListener.phaseDePlacementBateau = true;
		}
		this.joueur = Joueur.listeJoueur.get(Joueur.indexTour);
		this.sourceAide = sourceAide;
		this.sourceLancerDe = sourceLancerDe;
	}

	/**
	 * Fonction qui gere le clique pour retouner une tuile.
	 *
	 * @param e l'evenement crée {@link MouseEvent}
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == sourceAide) {
			if (AideJoueur.aideActive == false) {
				AideJoueur.aideActive = true;
				AideJoueur.init_aide();
				AideJoueur.index += 1;
			}else{
				if (AideJoueur.index < 8) {
					AideJoueur.index += 1;
				} else {
					AideJoueur.aideActive = false;
					AideJoueur.index = 0;
				}
			}
			try {
				if(phaseDePlacementExplorateur == true) {
					Plateau.affichePlacementExplorateur(joueur);
				}else {
					Plateau.affichePhaseRetournement();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (phaseDePlacementExplorateur == true && AideJoueur.aideActive == false) {
			int index = joueur.listeJoueur.indexOf(joueur);
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getTypeTuile() != TypeTuile.VIDE || tuileTmp.getTypeTuile() != TypeTuile.MER
						|| tuileTmp.getTypeTuile() != TypeTuile.ARRIVEE) {
					if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
						Explorateur explorateurTmp = joueur.getMainJoueur().explorateurAPlacer();
						if (joueur.placerExplorateur(tuileTmp, explorateurTmp) == true) {
							try {
								if (Joueur.indexTour == (Joueur.listeJoueur.size() - 1)) {
									Joueur.indexTour = 0;
									joueur = Joueur.listeJoueur.get(Joueur.indexTour);
								} else {
									Joueur.indexTour += 1;
									joueur = Joueur.listeJoueur.get(Joueur.indexTour);
								}
								Plateau.affichePlacementExplorateur(joueur);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		}
		if (phaseDePlacementExplorateur == false && AideJoueur.aideActive == false && phaseDePlacementBateau == true) {
			int index = joueur.listeJoueur.indexOf(joueur);
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getTypeTuile() == TypeTuile.MER) {
					if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
						if(joueur.placerBateau(tuileTmp, Bateau.listeBateau.get(Bateau.index))){
							tuileTmp.setBateauSurTuile(Bateau.listeBateau.get(Bateau.index));
							if (Joueur.indexTour == (Joueur.listeJoueur.size() - 1)) {
								Joueur.indexTour = 0;
								joueur = Joueur.listeJoueur.get(Joueur.indexTour);
							} else {
								Joueur.indexTour += 1;
								joueur = Joueur.listeJoueur.get(Joueur.indexTour);
							}
							Bateau.index+=1;
							Plateau.affichePlacementBateau(joueur);
						}
					}
				}
			}
		}
		
		if (phaseDePlacementBateau == false && AideJoueur.aideActive == false && joueur.phaseDeJeu == PhaseDuTour.DEPLACER) {
			Tuile tuile = null;
			Explorateur explorateur = null;
			boolean voisin = false;
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
					tuile = tuileTmp;
				}
			}
			if (tuile.getTypeTuile() != TypeTuile.VIDE ) {
				if(ExplorateurControlleur.getIndexTuileSelection1() == -1 && ExplorateurControlleur.getIndexTuileSelection2() == -1) {
					ExplorateurControlleur.setIndexTuileSelection1(tuile.getIndex());
					Plateau.affichePhaseDeplacement(joueur);
				}
				for (Explorateur explorateurTmp : joueur.getMainJoueur().pionExplorateur) {
					if(explorateurTmp.getEmplacement() == Tuile.listeTuile.get(ExplorateurControlleur.getIndexTuileSelection1()) && Joueur.listeJoueur.get(Joueur.indexTour)==joueur) {
							explorateur=explorateurTmp;
					}
				}
				if(explorateur == null) {
					ExplorateurControlleur.setIndexTuileSelection1(-1);
					Plateau.affichePhaseDeplacement(joueur);
				}
				if(ExplorateurControlleur.getIndexTuileSelection1() != -1 && ExplorateurControlleur.getIndexTuileSelection2() == -1) {
					ExplorateurControlleur.setIndexTuileSelection2(tuile.getIndex());
				}
				if(ExplorateurControlleur.getIndexTuileSelection1()!= -1 && ExplorateurControlleur.getIndexTuileSelection2() !=-1  && tuile != null ) {
					for(Tuile tuilTmp :Tuile.listeTuile.get(ExplorateurControlleur.getIndexTuileSelection1()).getVoisin()) {
						if(tuilTmp == Tuile.listeTuile.get(ExplorateurControlleur.getIndexTuileSelection2())) {
							voisin = true;
						}
					}
				}
				if(ExplorateurControlleur.getIndexTuileSelection1() != -1 && ExplorateurControlleur.getIndexTuileSelection2() !=-1 && voisin == false) {
					ExplorateurControlleur.setIndexTuileSelection2(-1);
					ExplorateurControlleur.setIndexTuileSelection2(-1);
					Plateau.affichePhaseDeplacement(joueur);
				}else if(ExplorateurControlleur.getIndexTuileSelection1() != -1 && ExplorateurControlleur.getIndexTuileSelection2() !=-1 && voisin == true) {
					if(ExplorateurControlleur.deplacerPion(explorateur, tuile)) {
						ExplorateurControlleur.setIndexTuileSelection1(-1);
						ExplorateurControlleur.setIndexTuileSelection2(-1);
						if(joueur.getDeplacementFait() < 2) {
							joueur.setDeplacementFait(joueur.getDeplacementFait()+1);
							Plateau.affichePhaseDeplacement(joueur);
						}else {
							joueur.setDeplacementFait(0);
							joueur.phaseDeJeu = PhaseDuTour.RETOURNER;
						}
					}
				}
			}
			System.out.println(ExplorateurControlleur.getIndexTuileSelection1() +"  " + ExplorateurControlleur.getIndexTuileSelection2());
		}
		if (phaseDePlacementBateau == false && AideJoueur.aideActive == false && joueur.phaseDeJeu == PhaseDuTour.RETOURNER) {
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getTypeTuile() != TypeTuile.VIDE || tuileTmp.getTypeTuile() != TypeTuile.MER
						|| tuileTmp.getTypeTuile() != TypeTuile.ARRIVEE) {
					if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
						if (TuileControlleur.estRetournable(tuileTmp)) {
							if(tuileTmp.getEffetTuile() == TuileEffet.VOLCAN){
								Plateau.afficheFinDePartie();
							}else if (tuileTmp.isFaceUp() == true && tuileSelection == false) {
								tuileTmp.setFaceUp(false);
								tuileSelection = true;
								try {
									Plateau.affichePhaseRetournement();
									Plateau.afficherTuileInfo();
								} catch (IOException e1) {
									System.err.println("Immpossible d'afficher le nouveau plateau");
									e1.printStackTrace();
								}
							} else if (tuileSelection == true && tuileTmp.isFaceUp() == false) {
								Tuile.RetirerTuile(tuileTmp.getIndex());
								joueur.getMainJoueur().addTuile(tuileTmp);
								tuileSelection = false;
								joueur.phaseDeJeu = PhaseDuTour.LANCER_DE;
								try {
									Plateau.affichePhaseLancerDe();
								} catch (IOException e1) {
									System.err.println("Immpossible d'afficher le nouveau plateau");
									e1.printStackTrace();
								}
								if (Joueur.indexTour == (Joueur.listeJoueur.size() - 1)) {
									Joueur.indexTour = 0;
									joueur = Joueur.listeJoueur.get(Joueur.indexTour);
								} else {
									Joueur.indexTour += 1;
									joueur = Joueur.listeJoueur.get(Joueur.indexTour);
								}
							}
						}
					}
				}
			}
		}
		if (e.getSource()==sourceLancerDe) {
			try {
				LancerDe lance;
				System.out.println("scd");
				try {
					lance = new LancerDe();
					lance.setAlwaysOnTop(true);
					lance.setVisible(true);
					joueur.phaseDeJeu = PhaseDuTour.RETOURNER;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Plateau.affichePhaseLancerDe();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Mouse pressed.
	 *
	 * @param e the e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Mouse released.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Mouse entered.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Mouse exited.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Mouse dragged.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Quand la souris bouge met une case en surbrillance rouge.
	 *
	 * @param e l'evenement crée {@link MouseEvent}
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (AideJoueur.aideActive == true && phaseDePlacementExplorateur == true) {
			try {
				Plateau.affichePlacementExplorateur(joueur);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (AideJoueur.aideActive == true && phaseDePlacementExplorateur == false) {
			try {
				Plateau.affichePhaseRetournement();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (phaseDePlacementExplorateur == true && AideJoueur.aideActive == false) {
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
					Plateau.setIndexTuileEvidence(tuileTmp.getIndex());
					try {
						Plateau.affichePlacementExplorateur(joueur);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (phaseDePlacementBateau == true && AideJoueur.aideActive == false && phaseDePlacementExplorateur == false) {
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
					Plateau.setIndexTuileEvidence(tuileTmp.getIndex());
					Plateau.affichePlacementBateau(joueur);
				}
			}
		}
		if (phaseDePlacementBateau == true && AideJoueur.aideActive == true && phaseDePlacementExplorateur == false) {
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
					Plateau.setIndexTuileEvidence(tuileTmp.getIndex());
					Plateau.affichePlacementBateau(joueur);
				}
			}
		}
		if (phaseDePlacementBateau == false && AideJoueur.aideActive == false && joueur.phaseDeJeu == PhaseDuTour.DEPLACER) {
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
					Plateau.setIndexTuileEvidence(tuileTmp.getIndex());
					Plateau.affichePhaseDeplacement(joueur);
				}
			}
		}
		if (phaseDePlacementBateau == false && AideJoueur.aideActive == false && joueur.phaseDeJeu == PhaseDuTour.RETOURNER) {
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
					Plateau.setIndexTuileEvidence(tuileTmp.getIndex());
					try {
						Plateau.affichePhaseRetournement();
						if(tuileSelection==true) {
							Plateau.afficherTuileInfo();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (phaseDePlacementBateau == false && AideJoueur.aideActive == false && joueur.phaseDeJeu == PhaseDuTour.LANCER_DE) {
			for (Tuile tuileTmp : Tuile.listeTuile) {
				if (tuileTmp.getHexagon() != null && tuileTmp.getHexagon().contains(e.getPoint())) {
					Plateau.setIndexTuileEvidence(tuileTmp.getIndex());
					try {
						Plateau.affichePhaseLancerDe();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
