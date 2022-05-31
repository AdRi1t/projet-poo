package Vue.Menu;

import javax.swing.*;

public class Fenetre extends JFrame { // fenetre principal qu'on peut utilioser dans toutes les interfaces

    public Fenetre(String titre) {
        setTitle(titre); // On donne un titre à notr fenêtre
        setBounds(0, 0, 1200, 720); // On donne une taille à notre fenêtre
        setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
        setResizable(false); // On interdit la redimensionnement de la fenêtre sinn true
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // On dit à l'application de se fermer lors du clic sur la
                                                           // croix
    }

}
