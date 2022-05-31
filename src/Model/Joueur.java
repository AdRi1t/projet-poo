/**
* Classe Model.Joueur.
* un nom et une main
*
* @version 1.0
*
* @seeUneAutreClasse
* @author Oumaima HAIMAR.
*/
package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * <strong>La classe qui nous permet de gérer les joueur</strong>
 * <p>
 * Représente un joueur avec ces tuiles et explorateur.
 * </p>
 * 
 * @author Oumaima HAIMAR et Adrien Taberner
 */
public class Joueur {

	/** Liste globale des joueurs. */
	public static List<Joueur> listeJoueur = new ArrayList<Joueur>();

	/** Quel joueur doit jouer */
	public static int indexTour = -1;

	/** Le nom. */
	private String nom;

	/** la main du joueur. Classe {@link MainJoueur} */
	private MainJoueur mainJoueur;

	/** L'image de profil du joueur. */
	private ImageIcon icon;

	private int deplacementFait;

	/** La couleur de ces pions. */
	private CouleurExplorateur couleur;

	public PhaseDuTour phaseDeJeu;

	/**
	 * Instancie un nouveau joueur
	 *
	 * @param nom le nom
	 */
	public Joueur(String nom) {
		super();
		this.setNom(nom);
		this.phaseDeJeu = PhaseDuTour.DEPLACER;
		this.setDeplacementFait(0);
		mainJoueur = new MainJoueur();
	}

	/**
	 * Initialise les pions {@link Explorateur} du joueur.
	 *
	 * @param couleur La couleur des explorateurs.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initExplorateursJoueur(CouleurExplorateur couleur) throws IOException {
		mainJoueur.initPionExplorateur(couleur);
		this.couleur = couleur;
		BufferedImage imageBuff = null;
		int x = 0, y = 0;
		switch (couleur) {
			case BLEU:
				imageBuff = ImageIO.read(new File("Images/Jbleu.png"));
				break;
			case VERT:
				imageBuff = ImageIO.read(new File("Images/JVert.png"));
				break;
			case ROUGE:
				imageBuff = ImageIO.read(new File("Images/JRouge.png"));
				break;
			case JAUNE:
				imageBuff = ImageIO.read(new File("Images/JJaune.png"));
				break;
			default:
				break;
		}
		x = imageBuff.getWidth();
		y = imageBuff.getHeight();
		icon = new ImageIcon(imageBuff.getScaledInstance((int) (x / 4), (int) (y / 4), 0));
	}

	/**
	 * Crée le bon nombre de joueurs et leur donne un nom par défaut.
	 *
	 * @param nombreJoueur le nombre de joueur.
	 */
	public static void initJoueurs(int nombreJoueur) {
		int i = 0;
		Joueur.indexTour = 0;
		for (i = 0; i < nombreJoueur; i++) {
			String name = new String("Player");
			name = name + " " + Integer.toString(i + 1);
			Joueur.listeJoueur.add(new Joueur(name));
		}
	}

	/**
	 * Place un {@link Explorateur}. donne à un pion explorateur une case
	 * {@link Tuile}
	 *
	 * @param tuile       La tuile où est poser l'explorateur
	 * @param explorateur L'explorateurs du joueur
	 * @return true, si on a réussi à le poser
	 */
	public boolean placerExplorateur(Tuile tuile, Explorateur explorateur) {
		if (tuile.getTypeTuile() != TypeTuile.PLAGE && tuile.getTypeTuile() != TypeTuile.FORET
				&& tuile.getTypeTuile() != TypeTuile.MONTAGNE) {
			return false;
		}
		for (Joueur joueurTmp : Joueur.listeJoueur) {
			for (Explorateur explorateurTmp : joueurTmp.mainJoueur.pionExplorateur) {
				if (explorateurTmp.getEmplacement() == tuile) {
					return false;
				}
			}
		}
		explorateur.setEmplacement(tuile);
		return true;
	}

	/**
	 * Place un {@link Bateau}. donne à un pion explorateur une case {@link Tuile}
	 *
	 * @param tuile  La tuile où est poser le bateau
	 * @param bateau le bateau du joueur
	 * @return true, si on a réussi à le poser
	 */
	public boolean placerBateau(Tuile tuile, Bateau bateau) {
		if (tuile.getTypeTuile() != TypeTuile.MER) {
			return false;
		}
		for (Bateau bateauTmp : Bateau.listeBateau) {
			if (bateauTmp.getEmplacement() == tuile) {
				return false;
			}
		}
		bateau.setEmplacement(tuile);
		return true;
	}

	/**
	 * Gets the main joueur.
	 * 
	 * @return the main joueur
	 */
	public MainJoueur getMainJoueur() {
		return mainJoueur;
	}

	/**
	 * Gets the tuile.
	 * 
	 * @param index the index
	 * @return the tuile
	 */
	public Piece getTuile(int index) {
		return mainJoueur.getTuile(index);
	}

	/**
	 * Retirer tuile.
	 * 
	 * @return the piece
	 */
	public Piece retirerTuile() {
		return mainJoueur.retirerTuile();
	}

	/**
	 * Gets the nom.
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Sets the nom.
	 * 
	 * @param nom the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets the icone.
	 * 
	 * @return the icone
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * Gets the couleur.
	 * 
	 * @return the couleur
	 */
	public CouleurExplorateur getCouleur() {
		return couleur;
	}

	/**
	 * Sets the couleur.
	 * 
	 * @param couleur the new couleur
	 */
	public void setCouleur(CouleurExplorateur couleur) {
		this.couleur = couleur;
	}

	public int getDeplacementFait() {
		return deplacementFait;
	}

	public void setDeplacementFait(int deplacementFait) {
		this.deplacementFait = deplacementFait;
	}

}
