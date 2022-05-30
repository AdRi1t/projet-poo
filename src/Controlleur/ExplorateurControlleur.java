package Controlleur;

import java.util.List;

import Model.Bateau;
import Model.Explorateur;
import Model.Joueur;
import Model.Tuile;
import Model.TypeTuile;

public class ExplorateurControlleur {
	
	
	/**
	 * Pour savoir si on a fini le placement des explorateur.
	 *
	 * @return vrai si on a fini.
	 */
	public static boolean phasePlacementExploTerminer() {
		int nombreAPoser = (Joueur.listeJoueur.size()*10);
		for(Joueur joueur: Joueur.listeJoueur) {
			for(Explorateur explorateur : joueur.getMainJoueur().pionExplorateur) {
				if(explorateur.getEmplacement() != null) {
					nombreAPoser -=1;
				} 
			}
		}
		if(nombreAPoser == 0) {
			return true;
		}else {
		return false;
		}	
	}
	/**
	 * Pour savoir si on a fini le placement des bateau.
	 *
	 * @return vrai si on a fini.
	 */
	public static boolean phasePlacementBateauTerminer() {
		int nombreAPoser = (Joueur.listeJoueur.size()*2);
		for(Bateau bateau : Bateau.listeBateau) {
			if(bateau.getEmplacement() != null) {
				nombreAPoser -=1;
			}
		}
		if(nombreAPoser == 0) {
			return true;
		}else {
		return false;
		}	
	}
/*
    public Boolean deplacerPion(Explorateur pion ,Tuile tuileDest){   
    	List<Tuile> list = pion.getEmplacement().getVoisin();
    	int i  = 0 ;	
        Boolean sontVoisin = false ;	
            //verifier que la tuile actuelle et destination sont adjacentes 
            while (i<list.size() && !(sontVoisin)){
            	if(list.get(i) == tuileDest){   
            		sontVoisin =  true;
            	}
                i++;
            }
            if(!sontVoisin){
                 System.out.println("il ne sont pas adjacent ");
    	        return false ; 
            }
    	    else { 
                         
            if( pion.getEmplacement().getTypeTuile() != TypeTuile.MER && tuileDest.getTypeTuile() != TypeTuile.MER)
            {	
                //deplaceement du pion  sur le terrain
                pion.setEmplacement(tuileDest);
                return true;

            }        
            //deplacement pion de tuile terre sur une tuile mer    
            if(pion.getEmplacement().getTypeTuile() != TypeTuile.MER && tuileDest.getTypeTuile() == TypeTuile.MER)
            {
                //verifier le bateau est sur la tuile 
                // s'il ya bateau il faut embarquer le pion sur la tuile
                //Si non le pion est devient nageur  
                if(tuileDest.getBateauSurTuile() )
                {
                   int sizeBateau =  tuileDest.getBateauSurTuile().passager.size();
                   if (sizeBateau<3)
                   {
                       tuileDest.getBateauSurTuile().passager.add(pion);
                       pion.enBateau = true ; 
                       pion.setEmplacement(tuileDest);
                        return true;
                   }
                   
                }
                else{
                    pion.setEmplacement(tuileDest);
                    return true;
                }
            }
            //Si le pion est najeur 
            if(pion.getEmplacement().getTypeTuile() ==TypeTuile.MER && tuileDest.getTypeTuile() == TypeTuile.MER)
            {   
                if(pion.getEmplacement().equals(tuileDest)) {
                    if(pion.enBateau)
                    {
                        //sauter du bateau 
                    pion.setEnBateau(false);
                    return true;
                    }
                    if(tuileDest.getBateauSurTuile() && !(pion.enBateau))
                    {
                        //menter sur le bateau 
                    Int sizeBateau =  tuileDest.getBateauSurTuile().passager.size();
                    if (sizeBateau<3)
                    {
                        tuileDest.getBateauSurTuile().passager.add(pion);
                        pion.enBateau = true ; 
                            return true;
                    }
                    else {
                        return false:
                    }
                    }              
                }
                else{ 
                    if(pion.enBateau)
                    {// le ecouler le bateau
                        tuileDest.getBateauSurTuile().couler()
                    }
                    else{
                        //najer dans le mer 
                    pion.setEmplacement(tuileDest);
                    return true;
                    }
                }
                  
                    
            }
        }
    }*/
}
