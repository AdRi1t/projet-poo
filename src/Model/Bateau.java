package Model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import Vue.TuileFond;

public class Bateau extends Piece{
	
	public Bateau(Tuile emplacement) {
		super();
		this.emplacement = emplacement;
	}

	public static List<Bateau> listeBateau = new ArrayList<Bateau>();
	private List<Explorateur> passager = new ArrayList<Explorateur>();
	public static int index;
	private Image imageBateau;
	
	
	public static void initBateauListe(int nombreJoueur) {
		Bateau.index = 0;
		int i = 0;
		for(i=0;i<nombreJoueur*2;i++) {
			Bateau bateau = new Bateau(null);
			Bateau.listeBateau.add(bateau);
		}
		TuileFond.setImageBateau(Bateau.listeBateau);
	}
	
	public Image getImageBateau() {
		return imageBateau;
	}

	public void setImageBateau(Image imageBateau) {
		this.imageBateau = imageBateau;
	}

	private Tuile emplacement;
	
	
    public Tuile getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Tuile emplacement) {
		this.emplacement = emplacement;
	}

	public Bateau(int posX ,int PosY) {
    	this.PositionX = posX;
    	this.PositionY = PosY;
    }
    
    public void couler() {
        // TODO implement here
    }

    public void getNBExplorateur() {
        // TODO implement here
    }

    public void RecupererExplorateur(Explorateur explorateur) {
    	if(passager.size() < 3 ) {
    		passager.add(explorateur);
    	}
    }

}