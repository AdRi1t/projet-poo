package Model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import Model.CouleurExplorateur;

/**
 * Classe Model.Explorateur.
 * Pour
 *
 * @version 1.0
 *
 * @author Oumaima HAIMAR.
 */
public class Explorateur {
    private int valeur;
    private CouleurExplorateur couleur;
    private Tuile emplacement = null;
    private boolean enBateau = false;
	private Bateau bateau = null;


	private Image imageExpolorateur;

    public Explorateur(int valeur, CouleurExplorateur couleur){
        super();
        this.valeur = valeur;
        this.couleur = couleur;
    }
    
    public static List<Explorateur> initJoueur_PionsExplorateur(CouleurExplorateur couleur){
    	List<Explorateur> pionExplorateur = new ArrayList<Explorateur>();
    	for (int i=1;i<7;i++){
            /**
             ** 3 exemplaires de 1
             */
            if(i==1){
                for(int j=1;j<3;j++){
                    System.out.println("création d'explorateur ["+couleur+"]["+i+"]");
                    pionExplorateur.add(new Explorateur(i,couleur));

                }
            }
            /**
             ** 2 exemplaires de 2
             */
            if(i==2){
                for(int j=1;j<2;j++){
                    System.out.println("création d'explorateur ["+couleur+"]["+i+"]");
                    pionExplorateur.add(new Explorateur(i,couleur));
                }
            }
            /**
             ** 2 exemplaires de 1
             */
            if(i==3){
                for(int j=1;j<2;j++){
                    System.out.println("création d'explorateur ["+couleur+"]["+i+"]");
                    pionExplorateur.add(new Explorateur(i,couleur));
                }
            }
            System.out.println("création d'explorateur ["+couleur+"]["+i+"]");
            pionExplorateur.add(new Explorateur(i,couleur));
    	}
    	return pionExplorateur;
    }
    
    public boolean isEnBateau() {
    	if(this.bateau != null) {
    		this.enBateau = true;
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public CouleurExplorateur getCouleur(){
        return couleur;
    }

    public int getValeur(){
        return valeur;
    }

	public Tuile getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Tuile emplacement) {
		this.emplacement = emplacement;
	}

	public Image getImageExpolorateur() {
		return imageExpolorateur;
	}

	public void setImageExpolorateur(Image imageExpolorateur) {
		this.imageExpolorateur = imageExpolorateur;
	}
	
	public Bateau getBateau() {
		return bateau;
		}

	public void setBateau(Bateau bateau) {
		this.bateau = bateau;
	}
	public void setEnBateau(boolean enBateau) {
		this.enBateau = enBateau;
	}

}
