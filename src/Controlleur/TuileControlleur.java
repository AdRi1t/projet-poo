package Controlleur;

import Model.Explorateur;
import Model.Joueur;
import Model.Tuile;
import Model.TypeTuile;

/**
 * <strong>Permet de géré l'ordre des tuiles à enlever</strong>
 * 
 * @author Adrien Taberner
 */
public class TuileControlleur {

	/**
	 * Compte le nombre de {@link Model.Tuile#typeTuile} dans {@link Model.Tuile#listeTuile}.
	 *
	 * @return Le nombre de TypeTuile restant {@link Model.Tuile#typeTuile}.
	 */
	public static int countTypeRestant(TypeTuile typeTuile) {
		int count = 0 ;
		switch (typeTuile) {
		case MONTAGNE:
			for(Tuile tuileTmp : Tuile.listeTuile) {
				if(tuileTmp.getTypeTuile() == TypeTuile.MONTAGNE && tuileTmp.isFaceUp()) {
					count += 1;  
				}
			}
			break;
		case FORET:
			for(Tuile tuileTmp : Tuile.listeTuile) {
				if(tuileTmp.getTypeTuile() == TypeTuile.FORET && tuileTmp.isFaceUp()) {
					count += 1;  
				}
			}
			break;
		case PLAGE:
			for(Tuile tuileTmp : Tuile.listeTuile) {
				if(tuileTmp.getTypeTuile() == TypeTuile.PLAGE && tuileTmp.isFaceUp()) {
					count += 1;  
				}
			}
			break;
		default:
			break; 
		}
		return count;
	}
	 
	/**
	 * Pour savoir si on peut retourner une tuile.
	 *
	 * @return si on peut retourner la tuile.
	 */
	public static boolean estRetournable(Tuile tuile) {
		if (tuile.getTypeTuile() == TypeTuile.PLAGE) {
			return true;
		}
		if (tuile.getTypeTuile() == TypeTuile.FORET && countTypeRestant(TypeTuile.PLAGE) == 0) {
			return true;
		}
		if (tuile.getTypeTuile() == TypeTuile.MONTAGNE && countTypeRestant(TypeTuile.PLAGE) == 0 && countTypeRestant(TypeTuile.FORET) == 0) {
			return true;
		}
		return false;
	}

}
