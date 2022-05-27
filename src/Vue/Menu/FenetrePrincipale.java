package Vue.Menu;
import Controlleur.GetAction;
import Controlleur.RedimensionnerImage;
import Model.CouleurExplorateur;
import Model.Joueur;
import Model.Tuile;
import Vue.Plateau;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

public class FenetrePrincipale implements ActionListener {
	   	JFrame frame;
	    int width;
	    int height;
	private JLabel lblTitre;
	private JRadioButton rdbtn2;
	private JLabel lblNombreJoueur;
	private JRadioButton rdbtn4;
	private JLabel lblPseudo;
	private JTextField textFieldPseudo1;
	private JTextField textFieldPseudo2;
	private JTextField textFieldPseudo3;
	private JButton btnLancer;
	private JTextField textFieldPseudo4;
	private JLabel lblJoueur1;
	private JLabel lblJoueur2;
	private JLabel lblJoueur3;
	private JLabel lblJoueur4;
	private JLabel lblAffichage1;
	private JLabel lblAffichage2;
	private JLabel lblAffichage3;
	private JLabel lblAffichage4;

	public void init_fenetre() {
	        this.width = 1216;
	        this.height = 759;
	    }

	    public void fenetre_menu() throws IOException {

	        this.frame = new JFrame("MENU PRINCIPAL");

	        JButton b1 = new JButton("Jouer");
	        JButton b2 = new JButton("Regles du jeu");
	        JButton b3 = new JButton("Crédits");

			BufferedImage bufferedImage = ImageIO.read(new File("Images/Menu_Principal.png"));
			Image image = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

			ImageIcon icon = new ImageIcon(image);

			JLabel jLabel = new JLabel();
			jLabel.setIcon(icon);
	        this.frame.add(jLabel);

			b1.setBounds(500, 270, 200, 40);
			b1.setBackground(Color.RED);
			b1.addActionListener(this);
	        jLabel.add(b1);

	        b2.setBounds(500, 370 , 200, 40);
			b2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					//Imageslider i = new Imageslider();
					try {
						fenetre_regles_generales();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
			});

			jLabel.add(b2);
	        b3.setBounds(500, 470, 200, 40);
	        jLabel.add(b3);

	        this.frame.setResizable(false);
	        this.frame.setSize(1216, 759);
	        this.frame.setDefaultCloseOperation(3);
	        this.frame.setVisible(true);
	    }
	    public void fenetre_regles() {

	        this.frame = new JFrame("REGLES DU JEU");

	        JLabel image = new JLabel(new ImageIcon("Images/backgroundregles.png"));

	        JButton b1 = new JButton("Règles générale");
	        JButton b2 = new JButton("Règles pièces");

	        Icon icon = new ImageIcon(
	                new ImageIcon("Imagees/fleche.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	        JButton b3 = new JButton(icon);

	        // Récupère l'image source
	        Image imgS = Toolkit.getDefaultToolkit().createImage("Images/PiècePNG.png");

	        // récupération du coin haut gauche x=0,y=0,w=100,h=100 image[0][0]
	        Icon Image1 = new ImageIcon(Toolkit.getDefaultToolkit()
	                .createImage(new FilteredImageSource(imgS.getSource(), new CropImageFilter(0, 0, 100, 100))));
	        JButton b4 = new JButton(Image1);

	        // récupération de l'image[1][0]
	        Icon Image2 = new ImageIcon(Toolkit.getDefaultToolkit()
	                .createImage(new FilteredImageSource(imgS.getSource(), new CropImageFilter(200, 0, 100, 100))));
	        JButton b5 = new JButton(Image2);

	        image.setBounds(0, 0, 850, 600);
	        this.frame.add(image);

	        b1.setBounds(200, 100, 200, 40);
	        image.add(b1);

	        b2.setBounds(200, 200, 200, 40);
	        image.add(b2);

	        b3.setBounds(200, 300, 200, 40);
	        image.add(b3);

	        b4.setBounds(200, 400, 200, 40);
	        image.add(b4);

	        b5.setBounds(200, 500, 200, 40);
	        image.add(b5);

	        this.frame.setResizable(false);
	        this.frame.setSize(1216, 759);
	        this.frame.setDefaultCloseOperation(3);
	        this.frame.setVisible(true);
	    }

	    public void fenetre_regles_generales() throws IOException {

	        this.frame = new JFrame("REGLES DU JEU");

	        JLabel image = new JLabel(new ImageIcon("Images/backgroundregles.png"));

			BufferedImage bufferedImage = ImageIO.read(new File("Images/Menu_Regles.png"));
			Image image2 = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

			ImageIcon icon = new ImageIcon(image2);

			JLabel jLabel = new JLabel();
			jLabel.setIcon(icon);
			this.frame.add(jLabel);

	        JButton boutonReglesGenerales = new JButton("Règles générale");

			boutonReglesGenerales.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					try {
						Imageslider i = new Imageslider(1);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
			});

			JButton boutonReglesPieces = new JButton("Règles pièces");
			boutonReglesPieces.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					try {
						Imageslider i = new Imageslider(2);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
			});
	        JButton boutonRetour = new JButton("Retour");
	        this.frame.add(jLabel);

	        boutonReglesGenerales.setBounds(500, 270, 200, 40);
	        jLabel.add(boutonReglesGenerales);

	        boutonReglesPieces.setBounds(500, 370, 200, 40);
	        jLabel.add(boutonReglesPieces);

	        boutonRetour.setBounds(500, 470, 200, 40);
	        jLabel.add(boutonRetour);

			boutonRetour.addActionListener(e -> {
				frame.setVisible(false);
				FenetrePrincipale menu= new FenetrePrincipale();
				try {
					menu.fenetre_menu();
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			});
			this.frame.setResizable(false);
	        this.frame.setSize(1216, 759);
	        this.frame.setDefaultCloseOperation(3);
	        this.frame.setVisible(true);
	    }
	    public void fenetre_credits() {
	        this.frame.setTitle("CREDITS");
	        this.frame.setResizable(false);
	        this.frame.setSize(1216, 759);
	        this.frame.setDefaultCloseOperation(3);
	        this.frame.setVisible(true);
	    }
 		public void fenetrePseudos() throws IOException {
			this.frame = new JFrame("MenuJoueur");

			BufferedImage bufferedImage = ImageIO.read(new File("Images/Menu_Regles_Generales.png"));
			Image image = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

			ImageIcon icon = new ImageIcon(image);
			JLabel jLabel = new JLabel();
			jLabel.setIcon(icon);
			this.frame.add(jLabel);

			/*lblTitre = new JLabel("THE ISLAND");
			lblTitre.setForeground(Color.white);
			lblTitre.setFont(new Font("Noteworthy", Font.PLAIN, 26));
			lblTitre.setBounds(500, 20, 350, 80);
			jLabel.add(lblTitre);*/

			rdbtn2 = new JRadioButton("2 Joueurs");
			rdbtn2.setActionCommand("2");
			rdbtn2.setForeground(Color.BLACK);
			rdbtn2.setBounds(400, 196, 140, 23);
			jLabel.add(rdbtn2);

			rdbtn4 = new JRadioButton("4 Joueurs");
			rdbtn4.setActionCommand("4");
			rdbtn4.setForeground(Color.BLACK);
			rdbtn4.setBounds(650, 196, 141, 23);
			jLabel.add(rdbtn4);

			ActionListener sliceActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton aButton = (AbstractButton) actionEvent.getSource();
					System.out.println("Selected: " + aButton.getText());
					if(aButton.getText() == "2 Joueurs"){
						//textFieldPseudo3.setBackground(Color.gray);
						textFieldPseudo4.setBackground(Color.lightGray);
						textFieldPseudo3.setBackground(Color.lightGray);
						textFieldPseudo3.setEnabled(false);
						textFieldPseudo4.setEnabled(false);
						jLabel.remove(lblAffichage3);
						jLabel.remove(lblAffichage4);
						rdbtn4.setSelected(false);
					}else{
						textFieldPseudo4.setBackground(Color.white);
						textFieldPseudo3.setBackground(Color.white);
						textFieldPseudo3.setEnabled(true);
						textFieldPseudo4.setEnabled(true);
						jLabel.add(lblAffichage3);
						jLabel.add(lblAffichage4);
						rdbtn2.setSelected(false);
					}
				}
			};

			rdbtn2.addActionListener(sliceActionListener);
			rdbtn4.addActionListener(sliceActionListener);

			lblNombreJoueur = new JLabel("Choisissez le nombre de joueurs",JLabel.CENTER);
			lblNombreJoueur.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			lblNombreJoueur.setForeground(Color.white);
			lblNombreJoueur.setBounds(0, 148, 1215, 60);
			jLabel.add(lblNombreJoueur);

			lblPseudo = new JLabel("Saisissez vos pseudos",JLabel.CENTER);
			lblPseudo.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			lblPseudo.setForeground(Color.white);
			lblPseudo.setBounds(0, 240, 1215, 60);
			jLabel.add(lblPseudo);

			/**
			 ** Pseudo1 **
			 */
			textFieldPseudo1 = new JTextField();
			textFieldPseudo1.setColumns(10); //On lui donne un nombre de colonnes à afficher
			textFieldPseudo1.setBounds(500, 330, 252, 36);
			jLabel.add(textFieldPseudo1);

			/**
			 ** Pseudo2 **
			 */
			textFieldPseudo2 = new JTextField();
			textFieldPseudo2.setColumns(10);
			textFieldPseudo2.setBounds(500, 380, 252, 36);
			jLabel.add(textFieldPseudo2);

			/**
			 ** Pseudo3 **
			*/
			textFieldPseudo3 = new JTextField();
			textFieldPseudo3.setColumns(10);
			textFieldPseudo3.setBounds(500, 430, 252, 36);
			jLabel.add(textFieldPseudo3);

			/**
			 ** Pseudo4 **
			*/
			textFieldPseudo4 = new JTextField();
			textFieldPseudo4.setColumns(10);
			textFieldPseudo4.setBounds(500, 480, 252, 36);
			jLabel.add(textFieldPseudo4);

			/**
			 ** Label joueurs **
			 */
			lblJoueur1 = new JLabel("Joueur 1 :");
			lblJoueur1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblJoueur1.setBounds(350, 330, 90, 39);
			lblJoueur1.setForeground(Color.white);
			jLabel.add(lblJoueur1);

			lblJoueur2 = new JLabel("Joueur 2 :");
			lblJoueur2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblJoueur2.setBounds(350, 380, 90, 40);
			lblJoueur2.setForeground(Color.white);
			jLabel.add(lblJoueur2);

			lblJoueur3 = new JLabel("Joueur 3 :");
			lblJoueur3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblJoueur3.setBounds(350, 430, 90, 36);
			lblJoueur3.setForeground(Color.white);
			jLabel.add(lblJoueur3);

			lblJoueur4 = new JLabel("Joueur 4 :");
			lblJoueur4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblJoueur4.setBounds(350, 480, 90, 36);
			lblJoueur4.setForeground(Color.white);
			jLabel.add(lblJoueur4);

			lblAffichage1 = new JLabel("");
			lblAffichage1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblAffichage1.setForeground(Color.white);
			lblAffichage1.setBounds(778, 302, 202, 15);
			jLabel.add(lblAffichage1);

			lblAffichage2 = new JLabel("");
			lblAffichage2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblAffichage2.setForeground(Color.white);
			lblAffichage2.setBounds(778, 359, 202, 15);
			jLabel.add(lblAffichage2);

			lblAffichage3 = new JLabel("");
			lblAffichage3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblAffichage3.setForeground(Color.white);
			lblAffichage3.setBounds(778, 425, 202, 15);
			jLabel.add(lblAffichage3);

			lblAffichage4 = new JLabel("");
			lblAffichage4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblAffichage4.setForeground(Color.white);
			lblAffichage4.setBounds(778, 486, 202, 15);
			jLabel.add(lblAffichage4);

			/**
			 ** Button Lancer Jeu & ActionEvent **
			 */
			btnLancer = new JButton(new GetAction(this,"Lancer le jeu"));
			btnLancer.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			btnLancer.setBackground(Color.BLUE);
			btnLancer.setForeground(Color.BLACK);
			btnLancer.setBackground(Color.blue);
			btnLancer.setBounds(520, 550, 200, 50);
			jLabel.add(btnLancer);

			btnLancer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(rdbtn2.isSelected()) {
						if(textFieldPseudo1.getText().length()>1  && textFieldPseudo2.getText().length()>1) {
							Joueur.initJoueurs(2);
							Joueur.listeJoueur.get(0).setNom(textFieldPseudo1.getText());
							Joueur.listeJoueur.get(1).setNom(textFieldPseudo2.getText());
							try {
								Joueur.listeJoueur.get(0).initExplorateursJoueur(CouleurExplorateur.BLEU);
								Joueur.listeJoueur.get(1).initExplorateursJoueur(CouleurExplorateur.VERT);
								Tuile.initListTuiles();
								frame.setVisible(false);
								frame = new JFrame("The island");
								frame.setResizable(false);
						        frame.setSize(1216, 759);
						        frame.setDefaultCloseOperation(3);
						       	frame.setVisible(true);
								Plateau.initPlateau(frame);
								Plateau.affichePlacementExplorateur(Joueur.listeJoueur.get(0));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					
						}
					}else if(rdbtn4.isSelected()) {
						if(textFieldPseudo1.getText().length()>1  && textFieldPseudo2.getText().length()>1
						&& textFieldPseudo3.getText().length()>1  && textFieldPseudo4.getText().length()>1) {
							Joueur.initJoueurs(4);
							Joueur.listeJoueur.get(0).setNom(textFieldPseudo1.getText());
							Joueur.listeJoueur.get(1).setNom(textFieldPseudo2.getText());
							Joueur.listeJoueur.get(2).setNom(textFieldPseudo3.getText());
							Joueur.listeJoueur.get(3).setNom(textFieldPseudo4.getText());
							try {
								Joueur.listeJoueur.get(0).initExplorateursJoueur(CouleurExplorateur.BLEU);
								Joueur.listeJoueur.get(1).initExplorateursJoueur(CouleurExplorateur.VERT);
								Joueur.listeJoueur.get(2).initExplorateursJoueur(CouleurExplorateur.JAUNE);
								Joueur.listeJoueur.get(3).initExplorateursJoueur(CouleurExplorateur.ROUGE);
								Tuile.initListTuiles();
								frame.setVisible(false);
								frame = new JFrame("The island");
								frame.setResizable(false);
						        frame.setSize(1216, 759);
						        frame.setDefaultCloseOperation(3);
						       	frame.setVisible(true);
								Plateau.initPlateau(frame);
								Plateau.affichePlacementExplorateur(Joueur.listeJoueur.get(0));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					
				}
			});

			JButton retour = new JButton("Retour");
			retour.setBounds(50, 30, 100, 40);
			jLabel.add(retour);

			retour.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.setVisible(false);
					FenetrePrincipale menu= new FenetrePrincipale();
					try {
						menu.fenetre_menu();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
			});

			this.frame.setResizable(false);
			this.frame.setSize(1216, 759);
			this.frame.setDefaultCloseOperation(3);
			this.frame.setVisible(true);		}

	public JTextField getTextFieldPseudo1(){
		return textFieldPseudo1;
	}
	public JTextField getTextFieldPseudo2(){
		return textFieldPseudo2;
	}
	public JTextField getTextFieldPseudo3(){
		return textFieldPseudo3;
	}
	public JTextField getTextFieldPseudo4(){
		return textFieldPseudo4;
	}

	public JLabel getLabelPseudo(){
		return lblAffichage1;
	}
	public JLabel getLabelPseudo2(){
		return lblAffichage2;
	}
	public JLabel getLabelPseudo3(){
		return lblAffichage3;
	}
	public JLabel getLabelPseudo4(){
		return lblAffichage4;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		FenetrePrincipale menu= new FenetrePrincipale();
		try {
			menu.fenetrePseudos(); //affiche la fenetre de saisie des pseudos
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		this.frame.setVisible(false); // ferme la fenetre du menu
	}
}

