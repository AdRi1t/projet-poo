package Vue;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.management.modelmbean.ModelMBean;

import Model.Bateau;
import Model.CouleurExplorateur;
import Model.Explorateur;
import Model.Joueur;
import Model.Tuile;
import Model.TuileEffet;
import Model.TypeTuile;

/**
 * <strong>TuileFond</strong>
 * <p>
 * Dessine les tuiles et attribue les images aux {@link Model.Tuile}
 * </p>
 *
 * @author Adrien Taberner
 */
public class TuileFond {
	
	 /**
 	 * Donne une {@link Image} à {@link Model.Tuile#Terrain}
 	 */
 	public static void setImageTerrain() {
	    	BufferedImage bufferedImage = null;
	    	Image foret = null, montagne = null, plage = null;
			try {
				bufferedImage = ImageIO.read(new File("Images/PiecePNG.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			foret = bufferedImage.getSubimage(200,200,100,100);
			montagne = bufferedImage.getSubimage(100,200,100,100);
			plage = bufferedImage.getSubimage(0,200,100,100);
			for(Tuile tuileTmp : Tuile.listeTuile) {
				if(tuileTmp.getTypeTuile() == TypeTuile.FORET) {
					tuileTmp.setTerrain(foret.getScaledInstance(91, 91, 0));
				}
				if(tuileTmp.getTypeTuile() == TypeTuile.MONTAGNE) {
					tuileTmp.setTerrain(montagne.getScaledInstance(91, 91, 0));
				}
				if(tuileTmp.getTypeTuile() == TypeTuile.PLAGE) {
					tuileTmp.setTerrain(plage.getScaledInstance(91, 91, 0));
				}
			}
		}
	    

	 /**
 	 * Affiche les tuiles.
 	 *
 	 * @param g2d le contexte pour dessiner.
 	 */
 	public static void afficherTuile(Graphics2D g2d) {
	    	for(Tuile tuileTmp : Tuile.listeTuile) {
	    		if(tuileTmp.getTypeTuile()!= TypeTuile.VIDE || tuileTmp.getTypeTuile()!= TypeTuile.ARRIVEE) {
		    		if(tuileTmp.getTypeTuile() == TypeTuile.MER) {
		    			tuileTmp.setTerrain(null);
		    		}
		    		else if(tuileTmp.isFaceUp() == true){
			    		if( tuileTmp.getTypeTuile() == TypeTuile.FORET) {
			    			g2d.drawImage(tuileTmp.getTerrain(), tuileTmp.getHexagon().xpoints[0]-43, tuileTmp.getHexagon().ypoints[0]-7,null);
			    		}
			    		if(tuileTmp.getTypeTuile() ==TypeTuile.MONTAGNE) {
			    			g2d.drawImage(tuileTmp.getTerrain(), tuileTmp.getHexagon().xpoints[0]-45, tuileTmp.getHexagon().ypoints[0]-7,null);
			    		}
						if(tuileTmp.getTypeTuile() ==TypeTuile.PLAGE ) {
			    			g2d.drawImage(tuileTmp.getTerrain(), tuileTmp.getHexagon().xpoints[0]-47, tuileTmp.getHexagon().ypoints[0]-7,null);
						}
		    		}else {
		    				g2d.drawImage(tuileTmp.getEffet(),tuileTmp.getHexagon().xpoints[0]-49, tuileTmp.getHexagon().ypoints[0]-19,null);					
						}
		    		}
	    		}
	    	}
	 	
 	/**
 	 * Donne une {@link Image} à {@link Model.Bateau}.
 	 */
 	public static void setImageBateau(List<Bateau> bateauList) {
 		Image bateauTmp;
 		try {
			bateauTmp = ImageIO.read(new File("Images/PieceBateau.png"));
			for(Bateau bateau : bateauList) {
	 			bateau.setImageBateau(bateauTmp.getScaledInstance(130,130, 0));
	 		}
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
 	
 	/**
 	 * Donne une {@link Image} à {@link Model.Tuile#Effet}.
 	 */
 	public static void setImageEffet() {
		 BufferedImage image = null;
		 	BufferedImage baleineRouge = null, baleineVert = null, supprimeBaleine = null,
	    		bateauVert = null, bateauRouge = null, dauphin = null,
	    		serpent = null, requinRouge = null, requinVert = null,
	    		supprimeRequin = null, tourbillon = null, volcan = null;
		 	Image baleineRougeI = null, baleineVertI = null, supprimeBaleineI = null,
		    		bateauVertI = null, bateauRougeI = null, dauphinI = null,
		    		serpentI = null, requinRougeI = null, requinVertI = null,
		    		supprimeRequinI = null, tourbillonI = null, volcanI = null;
	    	
			try {
				baleineRouge = ImageIO.read(new File("Images/Piece Baleine Rouge.png"));
				baleineVert = ImageIO.read(new File("Images/Piece Baleine Verte.png"));
				supprimeBaleine = ImageIO.read(new File("Images/Piece Baleine Rouge croix.png"));
				bateauVert = ImageIO.read(new File("Images/PièceBarque.png"));
				bateauRouge = ImageIO.read(new File("Images/PièceBarque rouge.png"));
				dauphin = ImageIO.read(new File("Images/PieceDauphin.png"));
				serpent = ImageIO.read(new File("Images/PieceDragon.png"));
				requinRouge = ImageIO.read(new File("Images/PieceRequin Rouge.png"));
				requinVert = ImageIO.read(new File("Images/PieceRequin vert.png"));
				supprimeRequin = ImageIO.read(new File("Images/PieceRequin Rouge croix.png"));
				tourbillon = ImageIO.read(new File("Images/PieceTyphon.png"));
				volcan = ImageIO.read(new File("Images/PieceVolcan.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			baleineRougeI = baleineRouge.getSubimage(100,100, 376, 376);
			baleineVertI = baleineVert.getSubimage(100,100, 376, 376) ;
			supprimeBaleineI = supprimeBaleine.getSubimage(100,100, 376, 376);
			bateauVertI = bateauVert.getSubimage(100,100, 376, 376) ;
			bateauRougeI = bateauRouge.getSubimage(100,100, 376, 376) ;
			dauphinI = dauphin.getSubimage(100,100, 376, 376) ;
			serpentI = serpent.getSubimage(100,100, 376, 376) ;
			requinRougeI = requinRouge.getSubimage(100,100, 376, 376) ;
			requinVertI = requinVert.getSubimage(100,100, 376, 376) ;
			supprimeRequinI = supprimeRequin.getSubimage(100,100, 376, 376) ;
			tourbillonI = tourbillon.getSubimage(100,100, 376, 376) ;
			volcanI = volcan.getSubimage(100,100, 376, 376);
			
			for(Tuile tuileTmp : Tuile.listeTuile) {
				if(tuileTmp.getTypeTuile()!= TypeTuile.VIDE || tuileTmp.getTypeTuile()!= TypeTuile.MER || tuileTmp.getTypeTuile()!= TypeTuile.ARRIVEE) {
					if(tuileTmp.getEffetTuile() == TuileEffet.BALEINE_ROUGE) {
						tuileTmp.setEffet(baleineRougeI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.BALEINE_VERT) {
						tuileTmp.setEffet(baleineVertI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.SUPPRIME_BALEINE) {
						tuileTmp.setEffet(supprimeBaleineI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.BATEAU_VERT) {
						tuileTmp.setEffet(bateauVertI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.BATEAU_ROUGE) {
						tuileTmp.setEffet(bateauRougeI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.DAUPHIN) {
						tuileTmp.setEffet(dauphinI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.SERPENT) {
						tuileTmp.setEffet(serpentI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.REQUIN_ROUGE) {
						tuileTmp.setEffet(requinRougeI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.REQUIN_VERT) {
						tuileTmp.setEffet(requinVertI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.SUPPRIME_REQUIN) {
						tuileTmp.setEffet(supprimeRequinI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.TOURBILLON) {
						tuileTmp.setEffet(tourbillonI.getScaledInstance(100,100, 0));
					}
					if(tuileTmp.getEffetTuile() == TuileEffet.VOLCAN) {
						tuileTmp.setEffet(volcanI.getScaledInstance(100,100, 0));
					}
				}
			}
	 	}
 	 
 	public static void afficherTuileJoueur(Graphics2D g2d,Joueur joueur) {
 		int i = 0;
 		int j = 0;
 		for(Tuile tuile : joueur.getMainJoueur().tuiles) {
 			if(i<70) {
 				g2d.drawImage(tuile.getEffet(), 1020+i, 110+j, null);
	 			i+=70;
 			}else {
 				g2d.drawImage(tuile.getEffet(), 1020+i, 110+j, null);
 				i=0;
 				j+=70;
 			}
 		}
 	}
 	/**
 	 * Affiche les Explorateur.
 	 *
 	 * @param g2d le contexte pour dessiner.
 	 */
	public static void afficherExplorateur(Graphics2D g2d) {
		int x1=0,y1=0, x2=0,y2=0;
		int nombreExploTuile = 0;
		Explorateur explorateur2 = null;
		for(Joueur joueur : Joueur.listeJoueur) {
			for(Explorateur explorateur : joueur.getMainJoueur().pionExplorateur) {
				if(explorateur.getEmplacement()!= null) {
					nombreExploTuile = 1;
					for(Joueur joueurTmp : Joueur.listeJoueur) {
						for(Explorateur explorateurTmp : joueurTmp.getMainJoueur().pionExplorateur) {
							if(explorateur.getEmplacement() == explorateurTmp.getEmplacement() && explorateur!= explorateurTmp) {
								nombreExploTuile += 1;
								explorateur2 = explorateurTmp;
						}
					}
				}
				switch (nombreExploTuile) {
				case 1:
					x1 = explorateur.getEmplacement().getHexagon().xpoints[0]-15;
					y1 = explorateur.getEmplacement().getHexagon().ypoints[0]+10;
					g2d.drawImage(explorateur.getImageExpolorateur(),x1, y1,null);
					break;
				case 2:
					x1 = explorateur.getEmplacement().getHexagon().xpoints[0]-5;
					y1 = explorateur.getEmplacement().getHexagon().ypoints[0]+10;
					x2 = explorateur.getEmplacement().getHexagon().xpoints[0]-25;
					y2 = explorateur.getEmplacement().getHexagon().ypoints[0]+10;
					g2d.drawImage(explorateur.getImageExpolorateur(),x1, y1,null);
					g2d.drawImage(explorateur2.getImageExpolorateur(),x2, y2,null);
					break;
				default:	
					break;
					}
				}
			}
		}
		
	}
	/**
 	 * Affiche les Bateau.
 	 *
 	 * @param g2d le contexte pour dessiner.
 	 */
	public static void afficherBateau(Graphics2D g2d) {
		int x=0,y=0;
		for(Bateau BateauTmp : Bateau.listeBateau) {
			if(BateauTmp.getEmplacement()!= null) {
				x = BateauTmp.getEmplacement().getHexagon().xpoints[0]-65;
				y = BateauTmp.getEmplacement().getHexagon().ypoints[0]-35;
				g2d.drawImage(BateauTmp.getImageBateau(),x, y,null);
			}
		}
	}
	
	public static Image getExplorateurImage(CouleurExplorateur couleur) {
 		BufferedImage explorateurBuff = null;
 		Image explorateurImage = null;
 		try {
 		switch (couleur) {
		case BLEU:
			explorateurBuff =  ImageIO.read(new File("Images/ExploBleu.png"));
			break;
		case JAUNE:
			explorateurBuff =  ImageIO.read(new File("Images/ExploJaune.png"));
			break;
		case ROUGE:
			explorateurBuff =  ImageIO.read(new File("Images/ExploRouge.png"));
			break;
		case VERT:
			explorateurBuff =  ImageIO.read(new File("Images/ExploVERT.png"));
			break;
		default:
			break;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
 		explorateurImage = explorateurBuff.getScaledInstance(140,140, Image.SCALE_FAST);
 		return explorateurImage;
	}

	
	/**
 	 * Donne une {@link Image} à {@link Model.MainJoueur#pionExplorateur}.
 	 */
 	public static void setImageExplorateur(List<Explorateur> explorateurs) {
 		BufferedImage explorateurBuff = null;
 		Image explorateurImage = null;
 		try {
 		switch (explorateurs.get(0).getCouleur()) {
		case BLEU:
			explorateurBuff =  ImageIO.read(new File("Images/ExploBleu.png"));
			break;
		case JAUNE:
			explorateurBuff =  ImageIO.read(new File("Images/ExploJaune.png"));
			break;
		case ROUGE:
			explorateurBuff =  ImageIO.read(new File("Images/ExploRouge.png"));
			break;
		case VERT:
			explorateurBuff =  ImageIO.read(new File("Images/ExploVERT.png"));
			break;
		default:
			break;
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
 		explorateurImage = explorateurBuff;
 		for(Explorateur explorateurTmp : explorateurs) {
 			explorateurTmp.setImageExpolorateur(explorateurImage.getScaledInstance(32,32,0));
 		}
 	}

}