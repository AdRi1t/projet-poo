package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;

import Controlleur.ExplorateurControlleur;
import Controlleur.PlateauListener;
import Model.Joueur;
import Model.LancerDe;
import Model.Tuile;

// TODO: Auto-generated Javadoc
/**
 * <strong>La classe qui nous permet d'afficher le plateau</strong>
 * <p>
 * Affiche les explorateurs, les tuiles et le fond.
 * </p>
 *
 * @author Adrien Taberner
 */
public class Plateau extends JFrame {

	/** La liste des hexagones à dessiner de type {@link Polygon}. */
	public static ArrayList<Polygon> listHexagone = new ArrayList<Polygon>();

	/** La frame principal. */
	public static JFrame main_frame = null;

	/** The Fond. */
	public static JPanel Fond;

	/** l'image de fond. */
	private static Image imageFond = null;

	private static MouseInputListener mouseInputListener;

	/** Les tuiles que l'on met en surbrillance. */
	private static int indexTuileEvidence = -1;

	/** Taille de l'image. */
	static int posX = 0,
			posY = 0,
			imageX = 1200,
			imageY = 720;

	/**
	 * Initialise le Plateau. On remplit la liste des hexagones et l'image de fond.
	 * 
	 * @param main_frame la fenêtre principale
	 */
	public static void initPlateau(JFrame main_frame) {
		Plateau.initHexagon();
		Plateau.main_frame = main_frame;
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("Images/PlateauJeu.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Image image = bufferedImage;
		Plateau.imageFond = image;
	}

	/**
	 * Permets d'afficher le plateau et le joueur dont c'est le tour.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void affichePhaseRetournement() throws IOException {
		JPanel fond = new DrawPlateau();
		Fond = fond;
		Joueur joueur = Joueur.listeJoueur.get(Joueur.indexTour);

		JLabel joueurIcon = new JLabel(joueur.getIcon(), JLabel.CENTER);
		JLabel joueurName = new JLabel(joueur.getNom(), JLabel.CENTER);
		JLabel indication = new JLabel("<html>Retourner &nbsp une<br>tuile</html>", JLabel.CENTER);
		joueurIcon.setBounds(new Rectangle(22, 130, joueur.getIcon().getIconWidth(), joueur.getIcon().getIconHeight()));
		joueurName.setForeground(new Color(250, 250, 250));
		joueurName.setFont(new Font("Impact", Font.TRUETYPE_FONT, 26));
		joueurName.setBounds(15, 75, 165, 50);
		joueurName.setVerticalAlignment(JLabel.NORTH);
		indication.setBounds(15, 350, 167, 120);
		indication.setForeground(new Color(250, 250, 250));
		indication.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));

		JButton Aide = new JButton("AIDE");
		Aide.setBounds(1040, 645, 120, 60);
		Aide.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		Aide.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		Aide.addMouseListener(new PlateauListener(Aide, null));

		JLabel aideIcon = new JLabel();
		if (AideJoueur.aideActive == true) {
			aideIcon = new JLabel(AideJoueur.getAideJoueurListe().get(AideJoueur.index));
		}
		aideIcon.setBounds(0, 0, 1200, 720);
		JButton lancerDe = new JButton("Lancer le dé");
		lancerDe.setBounds(510, 670, 200, 45);
		lancerDe.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		lancerDe.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		lancerDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LancerDe lance;
				try {
					lance = new LancerDe();
					lance.setAlwaysOnTop(true);
					lance.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		Fond.setLayout(null);
		Fond.add(joueurIcon);
		Fond.add(joueurName);
		Fond.add(indication);
		Fond.add(lancerDe);
		Fond.add(aideIcon);
		Fond.add(Aide);
		Fond.removeMouseListener(Plateau.mouseInputListener);
		Fond.removeMouseMotionListener(Plateau.mouseInputListener);
		Plateau.mouseInputListener = new PlateauListener(Aide, null);
		Fond.addMouseListener(mouseInputListener);
		Fond.addMouseMotionListener(mouseInputListener);
		main_frame.add(Fond);
		main_frame.setVisible(true);
	}

	/**
	 * Affiche l'interface lors de la phase de placement des explorateurs.
	 *
	 * @param joueur Le joueur dont c'est le tour de placer son explorateur
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void affichePlacementExplorateur(Joueur joueur) throws IOException {
		JPanel fond = new DrawPlateau();
		Fond = fond;
		ImageIcon explorateurImage;
		explorateurImage = new ImageIcon(TuileFond.getExplorateurImage(joueur.getCouleur()));

		JLabel joueurIcon = new JLabel(joueur.getIcon(), JLabel.CENTER);
		JLabel joueurName = new JLabel(joueur.getNom(), JLabel.CENTER);
		JLabel indication = new JLabel("<html>Placer &nbsp un<br>explorateur</html>", JLabel.CENTER);
		JLabel explorateurIcone = new JLabel(explorateurImage, JLabel.CENTER);
		JLabel explorateurValeur = new JLabel("Valeur: " + joueur.getMainJoueur().explorateurAPlacer().getValeur(),
				JLabel.CENTER);

		joueurIcon.setBounds(new Rectangle(22, 130, joueur.getIcon().getIconWidth(), joueur.getIcon().getIconHeight()));
		joueurName.setForeground(new Color(255, 255, 255));
		joueurName.setFont(new Font("Impact", Font.TRUETYPE_FONT, 26));
		joueurName.setBounds(15, 75, 165, 50);
		joueurName.setVerticalAlignment(JLabel.NORTH);
		indication.setBounds(15, 350, 167, 120);
		indication.setForeground(new Color(250, 250, 250));
		indication.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		explorateurIcone.setBounds(new Rectangle(1025, 140, 150, 140));

		explorateurValeur.setForeground(new Color(250, 250, 250));
		explorateurValeur.setFont(new Font("Impact", Font.TRUETYPE_FONT, 30));
		explorateurValeur.setBounds(1015, 240, 167, 120);

		JButton Aide = new JButton("AIDE");
		Aide.setBounds(1040, 645, 120, 60);
		Aide.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		Aide.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		Aide.addMouseListener(new PlateauListener(Aide, null));
		JLabel aideIcon = new JLabel();
		if (AideJoueur.aideActive == true) {
			aideIcon = new JLabel(AideJoueur.getAideJoueurListe().get(AideJoueur.index));
		}
		aideIcon.setBounds(0, 0, 1200, 720);
		JButton lancerDe = new JButton("Lancer le dé");
		lancerDe.setBounds(510, 670, 200, 45);
		lancerDe.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		lancerDe.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		lancerDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LancerDe lance;
				try {
					lance = new LancerDe();
					lance.setAlwaysOnTop(true);
					lance.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		Fond.setLayout(null);
		Fond.add(joueurIcon);
		Fond.add(joueurName);
		Fond.add(indication);
		Fond.add(explorateurIcone);
		Fond.add(explorateurValeur);
		Fond.add(lancerDe);
		Fond.add(Aide);
		Fond.add(aideIcon);
		Fond.removeMouseListener(Plateau.mouseInputListener);
		Fond.removeMouseMotionListener(Plateau.mouseInputListener);
		Plateau.mouseInputListener = new PlateauListener(Aide, null);
		Fond.addMouseMotionListener(Plateau.mouseInputListener);
		Fond.addMouseListener(Plateau.mouseInputListener);
		main_frame.add(Fond);
		main_frame.setVisible(true);
	}

	/**
	 * Permets d'afficher le plateau et le joueur dont c'est le tour.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void affichePhaseLancerDe() throws IOException {
		JPanel fond = new DrawPlateau();
		Fond = fond;
		Joueur joueur = Joueur.listeJoueur.get(Joueur.indexTour);

		JLabel joueurIcon = new JLabel(joueur.getIcon(), JLabel.CENTER);
		JLabel joueurName = new JLabel(joueur.getNom(), JLabel.CENTER);
		JLabel indication = new JLabel("<html>Lancer &nbsp le<br>dé</html>", JLabel.CENTER);
		joueurIcon.setBounds(new Rectangle(22, 130, joueur.getIcon().getIconWidth(), joueur.getIcon().getIconHeight()));
		joueurName.setForeground(new Color(250, 250, 250));
		joueurName.setFont(new Font("Impact", Font.TRUETYPE_FONT, 26));
		joueurName.setBounds(15, 75, 165, 50);
		joueurName.setVerticalAlignment(JLabel.NORTH);

		indication.setBounds(15, 350, 167, 120);
		indication.setForeground(new Color(250, 250, 250));
		indication.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));

		JButton Aide = new JButton("AIDE");
		Aide.setBounds(1040, 645, 120, 60);
		Aide.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		Aide.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		JLabel aideIcon = new JLabel();
		if (AideJoueur.aideActive == true) {
			aideIcon = new JLabel(AideJoueur.getAideJoueurListe().get(AideJoueur.index));
		}
		aideIcon.setBounds(0, 0, 1200, 720);

		JButton lancerDe = new JButton("Lancer le dé");
		lancerDe.setBounds(510, 670, 200, 45);
		lancerDe.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		lancerDe.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		lancerDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LancerDe lance;
				try {
					lance = new LancerDe();
					lance.setAlwaysOnTop(true);
					lance.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		Fond.setLayout(null);
		Fond.add(joueurIcon);
		Fond.add(joueurName);
		Fond.add(indication);
		Fond.add(Aide);
		Fond.add(lancerDe);
		Fond.add(aideIcon);
		Fond.removeMouseListener(Plateau.mouseInputListener);
		Fond.removeMouseMotionListener(Plateau.mouseInputListener);
		Plateau.mouseInputListener = new PlateauListener(Aide, lancerDe);
		Fond.addMouseMotionListener(Plateau.mouseInputListener);
		Fond.addMouseListener(Plateau.mouseInputListener);
		main_frame.add(Fond);
		main_frame.setVisible(true);
	}

	public static void affichePhaseDeplacement(Joueur joueur) {
		JPanel fond = new DrawPlateau();
		Fond = fond;

		JLabel joueurIcon = new JLabel(joueur.getIcon(), JLabel.CENTER);
		JLabel joueurName = new JLabel(joueur.getNom(), JLabel.CENTER);
		JLabel indication = new JLabel("<html>Déplacer &nbsp un<br>explorateur</html>", JLabel.CENTER);

		joueurIcon.setBounds(new Rectangle(22, 130, joueur.getIcon().getIconWidth(), joueur.getIcon().getIconHeight()));
		joueurName.setForeground(new Color(255, 255, 255));
		joueurName.setFont(new Font("Impact", Font.TRUETYPE_FONT, 26));
		joueurName.setBounds(15, 75, 165, 50);
		joueurName.setVerticalAlignment(JLabel.NORTH);
		indication.setBounds(15, 350, 167, 120);
		indication.setForeground(new Color(250, 250, 250));
		indication.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));

		JButton Aide = new JButton("AIDE");
		Aide.setBounds(1040, 645, 120, 60);
		Aide.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		Aide.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		Aide.addMouseListener(new PlateauListener(Aide, null));
		JLabel aideIcon = new JLabel();
		if (AideJoueur.aideActive == true) {
			aideIcon = new JLabel(AideJoueur.getAideJoueurListe().get(AideJoueur.index));
		}
		aideIcon.setBounds(0, 0, 1200, 720);

		JButton lancerDe = new JButton("Lancer le dé");
		lancerDe.setBounds(510, 670, 200, 45);
		lancerDe.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		lancerDe.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		lancerDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LancerDe lance;
				try {
					lance = new LancerDe();
					lance.setAlwaysOnTop(true);
					lance.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		Fond.setLayout(null);
		Fond.add(joueurIcon);
		Fond.add(joueurName);
		Fond.add(indication);
		Fond.add(Aide);
		Fond.add(lancerDe);
		Fond.add(aideIcon);
		Fond.removeMouseListener(Plateau.mouseInputListener);
		Fond.removeMouseMotionListener(Plateau.mouseInputListener);
		Plateau.mouseInputListener = new PlateauListener(Aide, null);
		Fond.addMouseMotionListener(Plateau.mouseInputListener);
		Fond.addMouseListener(Plateau.mouseInputListener);
		main_frame.add(Fond);
		main_frame.setVisible(true);

	}

	public static void affichePlacementBateau(Joueur joueur) {
		JPanel fond = new DrawPlateau();
		Fond = fond;

		BufferedImage bateauBuff;
		ImageIcon bateauImage = new ImageIcon();
		try {
			bateauBuff = ImageIO.read(new File("Images/PieceBateau.png"));
			bateauImage = new ImageIcon(bateauBuff.getScaledInstance(200, 200, Image.SCALE_FAST));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel joueurIcon = new JLabel(joueur.getIcon(), JLabel.CENTER);
		JLabel joueurName = new JLabel(joueur.getNom(), JLabel.CENTER);
		JLabel indication = new JLabel("<html>Placer &nbsp un<br>Bateau</html>", JLabel.CENTER);
		JLabel bateauIcon = new JLabel(bateauImage, JLabel.CENTER);

		joueurIcon.setBounds(new Rectangle(22, 130, joueur.getIcon().getIconWidth(), joueur.getIcon().getIconHeight()));
		joueurName.setForeground(new Color(255, 255, 255));
		joueurName.setFont(new Font("Impact", Font.TRUETYPE_FONT, 26));
		joueurName.setBounds(15, 75, 165, 50);
		joueurName.setVerticalAlignment(JLabel.NORTH);
		indication.setBounds(15, 350, 167, 120);
		indication.setForeground(new Color(250, 250, 250));
		indication.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		bateauIcon.setBounds(new Rectangle(1025, 140, 150, 140));

		JButton Aide = new JButton("AIDE");
		Aide.setBounds(1040, 645, 120, 60);
		Aide.setFont(new Font("Impact", Font.TRUETYPE_FONT, 24));
		Aide.setBorder(new LineBorder(new Color(220, 25, 120), 3));
		Aide.addMouseListener(new PlateauListener(Aide, null));
		JLabel aideIcon = new JLabel();
		if (AideJoueur.aideActive == true) {
			aideIcon = new JLabel(AideJoueur.getAideJoueurListe().get(AideJoueur.index));
		}
		aideIcon.setBounds(0, 0, 1200, 720);

		Fond.setLayout(null);
		Fond.add(joueurIcon);
		Fond.add(joueurName);
		Fond.add(indication);
		Fond.add(bateauIcon);
		Fond.add(Aide);
		Fond.add(aideIcon);
		Fond.removeMouseListener(Plateau.mouseInputListener);
		Fond.removeMouseMotionListener(Plateau.mouseInputListener);
		Plateau.mouseInputListener = new PlateauListener(Aide, null);
		Fond.addMouseMotionListener(Plateau.mouseInputListener);
		Fond.addMouseListener(Plateau.mouseInputListener);
		main_frame.add(Fond);
		main_frame.setVisible(true);
	}

	/**
	 * Initialise la liste des hexagones de type {@link Polygon}
	 * en leur donnant une coordonée sur le plateau.
	 */
	public static void initHexagon() {
		int i, j;
		int ligne[] = { 7, 10, 11, 10, 11, 12, 11, 12, 11, 10, 11, 10, 7 };
		int positionCorrect[] = { 9, 11, 11, 11, 12, 12, 12, 12, 11, 11, 11, 9, 11 };
		int colonne = 13;
		int Xhexagon[] = { 27, 54, 54, 27, 0, 0 };
		int Yhexagon[] = { 0, 15, 47, 62, 47, 15 };
		Polygon hexagon = new Polygon(Xhexagon, Yhexagon, 6);
		hexagon.translate(Plateau.posX + 415, Plateau.posY + 40);
		for (j = 0; j < colonne; j++) {
			for (i = 0; i < ligne[j]; i++) {
				Plateau.listHexagone.add(new Polygon(hexagon.xpoints, hexagon.ypoints, 6));
				hexagon.translate(54, 0);
			}
			hexagon.translate(-54 * positionCorrect[j], 0);
			hexagon.translate(27, 47);
		}
	}

	/**
	 * Affiche l'info sur la tuiles retournée (pas encore fini).
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void afficherTuileInfo() throws IOException {
		JLabel info = new JLabel("");
		info.setForeground(new Color(0, 0, 0));
		info.setFont(new Font("Corbel", Font.BOLD, 20));
		info.setBounds(1018, 405, 164, 162);
		// info.setBorder(new LineBorder(new Color(0, 0, 0),3));
		info.setText("Effet de la tuile");
		info.setVerticalAlignment(JLabel.NORTH);
		Fond.setLayout(null);
		Fond.add(info);
		main_frame.add(Fond);
		main_frame.setVisible(true);
	}

	/**
	 * Gets the image fond.
	 *
	 * @return the image fond
	 */
	public static Image getImageFond() {
		return imageFond;
	}

	/**
	 * Gets the index tuile evidence.
	 * 
	 * @return the index tuile evidence
	 */
	public static int getIndexTuileEvidence() {
		return indexTuileEvidence;
	}

	/**
	 * Sets the index tuile evidence.
	 * 
	 * @param indexTuileEvidence the new index tuile evidence
	 */
	public static void setIndexTuileEvidence(int indexTuileEvidence) {
		Plateau.indexTuileEvidence = indexTuileEvidence;
	}

	public static MouseInputListener getMouseInputListener() {
		return mouseInputListener;
	}

	public static void setMouseInputListener(MouseInputListener mouseInputListener) {
		Plateau.mouseInputListener = mouseInputListener;
	}

	public static void afficheFinDePartie() {
		JPanel fond = new JPanel();
		Fond = fond;
		JLabel victoire = new JLabel(new ImageIcon("Images/FenetreVictoire.png"));
		Fond.add(victoire);
		Fond.removeMouseListener(Plateau.mouseInputListener);
		Fond.removeMouseMotionListener(Plateau.mouseInputListener);
		main_frame.add(Fond);
		main_frame.setVisible(true);
		System.exit(0);
	}

}

/**
 * <strong>La classe qui va dessiner les hexagones,le exploray=teur et les
 * bateau</strong>
 * <p>
 * hérite de {@link JFrame}
 * </p>
 * 
 * @author Adrien Taberner
 */
class DrawPlateau extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i, j, k;
		int ligne[] = { 7, 10, 11, 10, 11, 12, 11, 12, 11, 10, 11, 10, 7 };
		int colonne = 13;
		int[] ligneX;
		int[] ligneY;
		BasicStroke stroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
		BasicStroke separation = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
		Polygon hexagon = new Polygon();
		Graphics2D g2d = (Graphics2D) g.create();
		this.setPreferredSize(new Dimension(1200, 720));

		g2d.drawImage(Plateau.getImageFond(), Plateau.posX, Plateau.posY, null);
		g2d.setColor(Color.BLACK);

		k = 0;
		for (j = 0; j < colonne; j++) {
			for (i = 0; i < ligne[j]; i++) {
				hexagon = Plateau.listHexagone.get(k);
				g2d.setStroke(stroke);
				g2d.drawPolygon(hexagon);
				k += 1;
				g2d.setStroke(separation);
				if (i >= 3 && i <= 6 && j == 3) {
					ligneX = new int[] { hexagon.xpoints[5], hexagon.xpoints[0], hexagon.xpoints[1] };
					ligneY = new int[] { hexagon.ypoints[5], hexagon.ypoints[0], hexagon.ypoints[1] };
					if (i == 3) {
						ligneX = new int[] { hexagon.xpoints[4], hexagon.xpoints[5], hexagon.xpoints[0],
								hexagon.xpoints[1] };
						ligneY = new int[] { hexagon.ypoints[4], hexagon.ypoints[5], hexagon.ypoints[0],
								hexagon.ypoints[1] };
					}
					if (i == 6) {
						ligneX = new int[] { hexagon.xpoints[5], hexagon.xpoints[0], hexagon.xpoints[1],
								hexagon.xpoints[2] };
						ligneY = new int[] { hexagon.ypoints[5], hexagon.ypoints[0], hexagon.ypoints[1],
								hexagon.ypoints[2] };
					}
					g2d.drawPolyline(ligneX, ligneY, ligneX.length);
				}
				if (j == 4) {
					if (i == 3) {
						ligneX = new int[] { hexagon.xpoints[4], hexagon.xpoints[5], hexagon.xpoints[0] };
						ligneY = new int[] { hexagon.ypoints[4], hexagon.ypoints[5], hexagon.ypoints[0] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 7) {
						ligneX = new int[] { hexagon.xpoints[0], hexagon.xpoints[1], hexagon.xpoints[2] };
						ligneY = new int[] { hexagon.ypoints[0], hexagon.ypoints[1], hexagon.ypoints[2] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
				}
				if (j == 5) {
					if (i == 2) {
						ligneX = new int[] { hexagon.xpoints[3], hexagon.xpoints[4], hexagon.xpoints[5],
								hexagon.xpoints[0], hexagon.xpoints[1] };
						ligneY = new int[] { hexagon.ypoints[3], hexagon.ypoints[4], hexagon.ypoints[5],
								hexagon.ypoints[0], hexagon.ypoints[1] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 3) {
						ligneX = new int[] { hexagon.xpoints[5], hexagon.xpoints[0] };
						ligneY = new int[] { hexagon.ypoints[5], hexagon.ypoints[0] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 8) {
						ligneX = new int[] { hexagon.xpoints[0], hexagon.xpoints[1] };
						ligneY = new int[] { hexagon.ypoints[0], hexagon.ypoints[1] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 9) {
						ligneX = new int[] { hexagon.xpoints[5], hexagon.xpoints[0], hexagon.xpoints[1],
								hexagon.xpoints[2], hexagon.xpoints[3] };
						ligneY = new int[] { hexagon.ypoints[5], hexagon.ypoints[0], hexagon.ypoints[1],
								hexagon.ypoints[2], hexagon.ypoints[3] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
				}
				if (j == 6) {
					if (i == 2) {
						ligneX = new int[] { hexagon.xpoints[4], hexagon.xpoints[5] };
						ligneY = new int[] { hexagon.ypoints[4], hexagon.ypoints[5] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 8) {
						ligneX = new int[] { hexagon.xpoints[1], hexagon.xpoints[2] };
						ligneY = new int[] { hexagon.ypoints[1], hexagon.ypoints[2] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
				}
				if (j == 7) {
					if (i == 2) {
						ligneX = new int[] { hexagon.xpoints[2], hexagon.xpoints[3], hexagon.xpoints[4],
								hexagon.xpoints[5], hexagon.xpoints[0] };
						ligneY = new int[] { hexagon.ypoints[2], hexagon.ypoints[3], hexagon.ypoints[4],
								hexagon.ypoints[5], hexagon.ypoints[0] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 3) {
						ligneX = new int[] { hexagon.xpoints[3], hexagon.xpoints[4] };
						ligneY = new int[] { hexagon.ypoints[3], hexagon.ypoints[4] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 8) {
						ligneX = new int[] { hexagon.xpoints[2], hexagon.xpoints[3] };
						ligneY = new int[] { hexagon.ypoints[2], hexagon.ypoints[3] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 9) {
						ligneX = new int[] { hexagon.xpoints[0], hexagon.xpoints[1], hexagon.xpoints[2],
								hexagon.xpoints[3], hexagon.xpoints[4] };
						ligneY = new int[] { hexagon.ypoints[0], hexagon.ypoints[1], hexagon.ypoints[2],
								hexagon.ypoints[3], hexagon.ypoints[4] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
				}
				if (j == 8) {
					if (i == 3) {
						ligneX = new int[] { hexagon.xpoints[5], hexagon.xpoints[4], hexagon.xpoints[3] };
						ligneY = new int[] { hexagon.ypoints[5], hexagon.ypoints[4], hexagon.ypoints[3] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
					if (i == 7) {
						ligneX = new int[] { hexagon.xpoints[1], hexagon.xpoints[2], hexagon.xpoints[3] };
						ligneY = new int[] { hexagon.ypoints[1], hexagon.ypoints[2], hexagon.ypoints[3] };
						g2d.drawPolyline(ligneX, ligneY, ligneX.length);
					}
				}
				if (i >= 3 && i <= 6 && j == 9) {
					ligneX = new int[] { hexagon.xpoints[2], hexagon.xpoints[3], hexagon.xpoints[4] };
					ligneY = new int[] { hexagon.ypoints[2], hexagon.ypoints[3], hexagon.ypoints[4] };
					if (i == 3) {
						ligneX = new int[] { hexagon.xpoints[2], hexagon.xpoints[3], hexagon.xpoints[4],
								hexagon.xpoints[5] };
						ligneY = new int[] { hexagon.ypoints[2], hexagon.ypoints[3], hexagon.ypoints[4],
								hexagon.ypoints[5] };
					}
					if (i == 6) {
						ligneX = new int[] { hexagon.xpoints[1], hexagon.xpoints[2], hexagon.xpoints[3],
								hexagon.xpoints[4] };
						ligneY = new int[] { hexagon.ypoints[1], hexagon.ypoints[2], hexagon.ypoints[3],
								hexagon.ypoints[4] };
					}
					g2d.drawPolyline(ligneX, ligneY, ligneX.length);
				}
			}
		}
		TuileFond.afficherTuile(g2d);
		TuileFond.afficherExplorateur(g2d);
		TuileFond.afficherBateau(g2d);
		TuileFond.afficherCreature(g2d);
		TuileFond.afficherTuileJoueur(g2d, Joueur.listeJoueur.get(Joueur.indexTour));
		if (Plateau.getIndexTuileEvidence() >= 0) {
			Tuile specialTuile = Tuile.listeTuile.get(Plateau.getIndexTuileEvidence());
			g2d.setColor(new Color(240, 20, 20, 120));
			g2d.fillPolygon(specialTuile.getHexagon());
		}
		if (ExplorateurControlleur.getIndexTuileSelection1() >= 0) {
			Tuile TuileSelection = Tuile.listeTuile.get(ExplorateurControlleur.getIndexTuileSelection1());
			g2d.setColor(new Color(20, 20, 240, 120));
			g2d.fillPolygon(TuileSelection.getHexagon());
		}
		g2d.dispose();
	}
}
