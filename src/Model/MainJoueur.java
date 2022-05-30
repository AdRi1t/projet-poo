package Model; /**
 * Classe Model.MainJoueur.
 * un ensemble de piece de jeu détenues par un joueur
 *
 * @version 1.0
 *
 * @see UneAutreClasse
 * @author Oumaima HAIMAR.
 */

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import Controlleur.RedimensionnerImage;
import Vue.TuileFond;

/**
 * <strong>La main du joueur avec ses tuiles et ses explorateurs.</strong>
 * 
 * @author Oumaima HAIMAR et Adrien Taberner
 */
public class MainJoueur {
    
    /** Les tuiles ramassées par le joueur. */
    public List<Tuile>tuiles;
    
    /** Les explorateurs à sauver */
    public List<Explorateur>pionExplorateur;


	/**
	 * Instancie une nouvelle main du joueur.
	 */
	public MainJoueur(){
        tuiles = new ArrayList<Tuile>();
        pionExplorateur = new ArrayList<Explorateur>();
    }

    /**
     * Initialise les pions explorateurs d'un joueur. On leur atrribue une {@link Image}, une 
     * {@link Explorateur#valeur} et une {@link CouleurExplorateur}.
     *
     * @param couleur La couleur
     */
    public void initPionExplorateur(CouleurExplorateur couleur) {
    	pionExplorateur = Explorateur.initJoueur_PionsExplorateur(couleur);
    	TuileFond.setImageExplorateur(pionExplorateur);
    }
    
    /**
     * Donne un explorateur à placer sur le plateau.
     *
     * @return l'explorateur que l'on peut placer
     */
    public Explorateur explorateurAPlacer() {
    	int i=0;
    	for (i=pionExplorateur.size()-1;i>=0;i--) {
			if(pionExplorateur.get(i).getEmplacement()==null) {
				return pionExplorateur.get(i);
			}
    	}
		return pionExplorateur.get(0);
    }
    
    /**
     * Gets the pion explorateur.
     *
     * @return the pion explorateur
     */
    public List<Explorateur> getPionExplorateur() {
		return pionExplorateur;
	}
	
    /**
     * Adds the tuile.
     *
     * @param tl the tl
     */
    public void addTuile(Tuile tl){
    	if(tl.getEffetTuile() == TuileEffet.BALEINE_ROUGE  || tl.getEffetTuile() == TuileEffet.REQUIN_ROUGE || 
    		tl.getEffetTuile() == TuileEffet.BATEAU_ROUGE || tl.getEffetTuile() == TuileEffet.DAUPHIN ||
    		tl.getEffetTuile() == TuileEffet.SERPENT || tl.getEffetTuile() == TuileEffet.SUPPRIME_BALEINE ||
    		tl.getEffetTuile() == TuileEffet.SUPPRIME_REQUIN) {
    			tuiles.add(tl);
    	}
    }

    /**
     * Gets the tuile.
     *
     * @param index the index
     * @return the tuile
     */
    public Piece getTuile(int index){
        return  tuiles.get(index);
    }
    
    /**
     * Retirer tuile.
     *
     * @return the piece
     */
    public Piece retirerTuile(){
        return tuiles.remove(0);
    }
}
