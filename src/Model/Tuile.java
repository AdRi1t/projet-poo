package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.management.modelmbean.ModelMBean;
import javax.swing.ImageIcon;

import Model.Piece;
import Vue.Plateau;
import Vue.TuileFond;

/**
 * <strong>La classe qui nous permet de gÃ©rer les tuiles</strong>
 * <p>
 * ReprÃ©sente une case sur le plateau, avec coordonnÃ©, images et effet.
 * </p>
 *
 * @author Adrien Taberner
 */
public class Tuile extends Piece {

	/** Liste globale des tuiles du jeu. */
	public static ArrayList<Tuile> listeTuile = new ArrayList<Tuile>();

	/** Tableau pour construire toutes les tuiles. */
	private static int[][] tuliesTab;

	/** La liste de tous les voisins d'une tuile */
	private List<Tuile> voisin = new ArrayList<Tuile>();

	/** Le type d'une tuile, type {@link Model.TypeTuile}. */
	private TypeTuile typeTuile;

	/** l'effet de la tuile, type {@link Model.TuileEffet}. */
	private TuileEffet effetTuile;

	/** Si on voit le terrain */
	private boolean faceUp;
	
	/** l'index. */
	private int index;

	/** l'hexagon qui contient exactement oÃ¹ la tuile est placÃ©e de type {@link Polygon} */
	private Polygon hexagon = null;

	/** L'image du terrain. {@link Image} */
	private Image Terrain;

	/**  L'image de l'effet. {@link Image} */
	private Image Effet;
	
	/** Bateau sur tuile */
	private Bateau BateauSurTuile;

	/**
	 * Instancie une nouvelle Tuile avec sa position dans le plateau et sont index.
	 *
	 * @param posX  Position X sur le tableau.
	 * @param posY  Position Y sur le tableau.
	 * @param index L'index de la tuile De 0 Ã  168.
	 */
	public Tuile(int posX, int posY, int index) {
		this.PositionX = posX;
		this.PositionY = posY;
		this.index = index;
	}

	public Bateau getBateauSurTuile() {
		return BateauSurTuile;
	}
	
	public boolean isBateauSurTuile() {
		if(this.BateauSurTuile != null) {
			return true;
		}else {
			return false;
		}
	}

	public void setBateauSurTuile(Bateau bateauSurTuile) {
		BateauSurTuile = bateauSurTuile;
	}

	/**
	 * Permets d'initialiser la liste des tuiles du jeu {@link Model.Tuile#listeTuile}.
	 */
	public static void initListTuiles() {
		Tuile.initTabTuiles();
		Tuile.initTypeTuiles();
		Tuile.initEffetTuiles();
		Tuile.setHexagone();
		TuileFond.setImageTerrain();
		TuileFond.setImageEffet();
		Tuile.initVoisin();
	}

	/**
	 * Donne un {@link TypeTuile} Ã  toutes les tuiles de la liste.
	 */
	private static void initTypeTuiles() {
		ArrayList<TypeTuile> randomTypeTuiles = TypeTuile.getRandomTuiles();
		int posX = 0, posY = 0;
		int k = 0;
		for (Tuile tuileTmp : Tuile.listeTuile) {
			tuileTmp.setFaceUp(true);
			posX = tuileTmp.PositionX;
			posY = tuileTmp.PositionY;
			if (posY == 0) {
				if (posX <= 4) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 4 && posX <= 18) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 18) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 1) {
				if (posX <= 3) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 3 && posX <= 19) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 19) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 2) {
				if (posX <= 2) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 2 && posX <= 22) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 22) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 3) {
				if (posX <= 1) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 1 && posX <= 7) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 7 && posX <= 15) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX > 15 && posX <= 21) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 21) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if ((posX == 3 && posY == 1) || (posX == 2 && posY == 2) || (posX == 21 && posY == 1)
					|| (posX == 22 && posY == 2)) {
				tuileTmp.typeTuile = TypeTuile.ARRIVEE;
			}
			if (posY == 4) {
				if (posX == 0) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 0 && posX <= 6) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 6 && posX <= 16) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX > 16 && posX <= 22) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 22) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 5) {
				if (posX <= 3) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 3 && posX <= 19) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX > 19 && posX <= 23) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 23) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 6) {
				if (posX < 2) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX >= 2 && posX <= 4) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 4 && posX <= 10) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX == 12) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 12 && posX <= 18) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX > 18 && posX <= 22) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 22) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 7) {
				if (posX <= 3) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 3 && posX <= 19) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX > 19 && posX <= 23) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 23) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 8) {
				if (posX == 0) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 0 && posX <= 6) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 6 && posX <= 16) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX > 16 && posX <= 22) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 22) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 9) {
				if (posX <= 1) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 1 && posX <= 7) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 7 && posX <= 15) {
					tuileTmp.typeTuile = randomTypeTuiles.get(k);
					k += 1;
				}
				if (posX > 15 && posX <= 21) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 21) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 10) {
				if (posX <= 2) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 2 && posX <= 22) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 22) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 11) {
				if (posX <= 3) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 3 && posX <= 19) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 19) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if (posY == 12) {
				if (posX <= 4) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
				if (posX > 4 && posX <= 18) {
					tuileTmp.typeTuile = TypeTuile.MER;
				}
				if (posX > 18) {
					tuileTmp.typeTuile = TypeTuile.VIDE;
				}
			}
			if ((posX == 3 && posY == 11) || (posX == 2 && posY == 10) || (posX == 21 && posY == 11)
					|| (posX == 22 && posY == 10)) {
				tuileTmp.typeTuile = TypeTuile.ARRIVEE;
			}
		}
	}

	/**
	 * Permets de donner un effet {@link TuileEffet} Ã  chaque tuile de {@link Model.Tuile#listeTuile}.
	 */
	private static void initEffetTuiles() {
		ArrayList<TuileEffet> randomEffetPlage = TuileEffet.getPlageEffet();
		ArrayList<TuileEffet> randomEffetForet = TuileEffet.getForetEffet();
		ArrayList<TuileEffet> randomEffetMontagne = TuileEffet.getMontagneEffet();
		int i = 0, j = 0, k = 0;
		for (Tuile tuileTmp : Tuile.listeTuile) {
			if (tuileTmp.typeTuile == TypeTuile.PLAGE) {
				tuileTmp.effetTuile = randomEffetPlage.get(i);
				i++;
			}
			if (tuileTmp.typeTuile == TypeTuile.FORET) {
				tuileTmp.effetTuile = randomEffetForet.get(j);
				j++;
			}
			if (tuileTmp.typeTuile == TypeTuile.MONTAGNE) {
				tuileTmp.effetTuile = randomEffetMontagne.get(k);
				k++;
			}
		}
	}

	/**
	 * Attribue aux tuiles non vides de la liste tuiles d'avoir un {@link Polygon}.
	 * Les tuiles ont donc leur position sur le plateau. 
	 */
	private static void setHexagone() {
		int k = 0;
		Plateau.initHexagon();
		for (Tuile tuileTmp : listeTuile) {
			if (tuileTmp.typeTuile != TypeTuile.VIDE) {
				tuileTmp.hexagon = Plateau.listHexagone.get(k);
				k++;
			}
		}
	}

	/**
	 * Fonction pour crÃ©er {@link Model.Tuile#tuliesTab} qui sert Ã  repÃ©rer les tuiles 
	 * et leur position sur le plateau.
	 */
	private static void initTabTuiles() { 
		int i = 0, j = 0;
		int index = 0;
		tuliesTab = new int[13][26];
		for (j = 0; j < 13; j++) {
			for (i = 0; i < 26; i += 2) {
				if (j % 2 == 0) {
					tuliesTab[j][i] = i;
					tuliesTab[j][i + 1] = i + 1;
					listeTuile.add(new Tuile(i, j, index));

				}
				if (j % 2 == 1) {
					tuliesTab[j][i] = i;
					tuliesTab[j][i + 1] = i + 1;
					listeTuile.add(new Tuile(i + 1, j, index));
				}
				index += 1;
			}
		}
	}

	/**
	 * Valorise la liste des {@link Model.Tuile#voisin} pour chaque tuile.
	 */
	private static void initVoisin() {
		int posX, posY;
		for (Tuile tuileTmp : listeTuile) {
			posX = tuileTmp.PositionX;
			posY = tuileTmp.PositionY;
			if (posY == 0) {
				if (posX == 0) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
				if (posX > 0 && posX < 24) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY + 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
				if (posX == 24) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY + 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
			}
			if ((posY % 2) == 0 && (posY < 12) && (posY > 0)) {
				if (posX == 0) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
				if (posX > 0 && posX < 24) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY + 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
				if (posX == 24) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY + 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
			}
			if ((posY % 2) == 1) {
				if (posX == 1) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY + 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
				if (posX > 1 && posX < 25) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY + 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY + 1)));
				}
				if (posX == 25) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY + 1)));
				}
			}
			if (posY == 12) {
				if (posX == 0) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
				}
				if (posX > 0 && posX < 24) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, 0)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 2, 0)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
				}
				if (posX == 24) {
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 2, posY)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX - 1, posY - 1)));
					tuileTmp.voisin.add(Tuile.listeTuile.get(Tuile.getIndex(posX + 1, posY - 1)));
				}
			}
		}
	}

	/**
	 * Gets the index.
	 *
	 * @param posX Position dans le tableau en X
	 * @param posY Position dans le tableau en Y
	 * @return the index
	 */
	public static int getIndex(int posX, int posY) {
		for (Tuile tuileTmp : listeTuile) {
			if (tuileTmp.PositionX == posX && tuileTmp.PositionY == posY) {
				return tuileTmp.index;
			}
		}
		return 0;
	}

	/**
	 * Gets the terrain.
	 *
	 * @return the terrain
	 */
	public Image getTerrain() {
		return Terrain;
	}
	
	/**
	 * Permet d'obtenir l'emplacement de la tuiles.
	 *
	 * @return the hexagon
	 */
	public Polygon getHexagon() {
		return hexagon;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the type tuile.
	 *
	 * @return the type tuile
	 */
	public TypeTuile getTypeTuile() {
		return typeTuile;
	}

	/**
	 * Gets the tulies tab.
	 *
	 * @return the tulies tab
	 */
	public static int[][] getTuliesTab() {
		return tuliesTab;
	}

	/**
	 * Retourne tous les voisin d'une tuiles.
	 * @return la liste des voisins.
	 */
	public List<Tuile> getVoisin() {
		return voisin;
	}

	/**
	 * Retirer tuile.
	 * @param index l'index dans la liste globale
	 */
	public static void RetirerTuile(int index) {
		Tuile tuileTmp;
		tuileTmp = listeTuile.get(index);
		if (tuileTmp.typeTuile != TypeTuile.VIDE) {
			tuileTmp.typeTuile = TypeTuile.MER;
			listeTuile.set(index, tuileTmp);
		}
	}

	/**
	 * Sets the terrain.
	 * @param terrain the new terrain
	 */
	public void setTerrain(Image terrain) {
		Terrain = terrain;
	}

	/**
	 * Checks if is face up.
	 * @return true, if is face up
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * Sets the type tuile.
	 * @param typeTuile the new type tuile
	 */
	public void setTypeTuile(TypeTuile typeTuile) {
		this.typeTuile = typeTuile;
	}

	/**
	 * Sets the face up.
	 * @param faceUp the new face up
	 */
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	/**
	 * Gets the effet tuile.
	 * @return the effet tuile
	 */
	public TuileEffet getEffetTuile() {
		return effetTuile;
	}

	/**
	 * Sets the effet tuile.
	 * @param effetTuile the new effet tuile
	 */
	public void setEffetTuile(TuileEffet effetTuile) {
		this.effetTuile = effetTuile;
	}

	/**
	 * Gets the effet.
	 * @return the effet
	 */
	public Image getEffet() {
		return Effet;
	}

	/**
	 * Sets the effet.
	 * @param effet the new effet
	 */
	public void setEffet(Image effet) {
		Effet = effet;
	}

}
