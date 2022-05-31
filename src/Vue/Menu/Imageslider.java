package Vue.Menu;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Imageslider extends JFrame implements ActionListener {
    ImageIcon s[];
    JLabel l;
    JButton boutonRevenir, boutonSuivant, boutonRetour;
    int i, l1;
    JPanel p;

    public Imageslider(int regle) throws IOException {
        setLayout(new BorderLayout());
        setSize(1200, 734);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // setBackground(Color.lightGray);

        boutonRevenir = new JButton("Revenir");
        boutonSuivant = new JButton("Suivant");
        boutonRetour = new JButton("Retour");

        l = new JLabel("", JLabel.CENTER);
        add(l);

        boutonRevenir.setBounds(330, 630, 200, 40);
        boutonSuivant.setBounds(690, 630, 200, 40);
        boutonRetour.setBounds(50, 30, 100, 40);

        l.add(boutonRevenir);
        l.add(boutonSuivant);
        l.add(boutonRetour);

        boutonRevenir.addActionListener(this);
        boutonSuivant.addActionListener(this);
        boutonRetour.addActionListener(this);

        if (regle == 1) {

            BufferedImage bufferedImage = ImageIO.read(new File("Images/Menu_Regles_Generales.jpg"));
            Image image0 = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage1 = ImageIO.read(new File("Images/Menu_Regles_Generales(2).png"));
            Image image1 = bufferedImage1.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage2 = ImageIO.read(new File("Images/Menu_Regles_Generales(3).png"));
            Image image2 = bufferedImage2.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage3 = ImageIO.read(new File("Images/Menu_Regles_Generales(4).png"));
            Image image3 = bufferedImage3.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage4 = ImageIO.read(new File("Images/Menu_Regles_Generales(5).png"));
            Image image4 = bufferedImage4.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage5 = ImageIO.read(new File("Images/Menu_Regles_Generales(6).png"));
            Image image5 = bufferedImage5.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage6 = ImageIO.read(new File("Images/Menu_Regles_Generales(7).png"));
            Image image6 = bufferedImage6.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            s = new ImageIcon[7];
            s[0] = new ImageIcon(image0);
            s[1] = new ImageIcon(image1);
            s[2] = new ImageIcon(image2);
            s[3] = new ImageIcon(image3);
            s[4] = new ImageIcon(image4);
            s[5] = new ImageIcon(image5);
            s[6] = new ImageIcon(image6);
            l.setIcon(s[0]);

        } else if (regle == 2) {
            BufferedImage bufferedImage = ImageIO.read(new File("Images/Menu_Regles_Generales(1).png"));
            Image image0 = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage10 = ImageIO.read(new File("Images/Menu_Regles_Generales2.png"));
            Image image1 = bufferedImage10.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage1 = ImageIO.read(new File("Images/Menu_Regles_TuilesV.png"));
            Image image2 = bufferedImage1.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage2 = ImageIO.read(new File("Images/Menu_Regles_TuilesR.png"));
            Image image3 = bufferedImage2.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage3 = ImageIO.read(new File("Images/Menu_Regles_TuilesVR.png"));
            Image image4 = bufferedImage3.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage4 = ImageIO.read(new File("Images/Volcan.png"));
            Image image5 = bufferedImage4.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage5 = ImageIO.read(new File("Images/deCreature.png"));
            Image image6 = bufferedImage5.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage6 = ImageIO.read(new File("Images/Dbaleine.png"));
            Image image7 = bufferedImage6.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage7 = ImageIO.read(new File("Images/Drequin.png"));
            Image image8 = bufferedImage7.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage8 = ImageIO.read(new File("Images/Deserpant.png"));
            Image image9 = bufferedImage8.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage9 = ImageIO.read(new File("Images/Menu_Regles_Creature.png"));
            Image image10 = bufferedImage9.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            s = new ImageIcon[11];
            s[0] = new ImageIcon(image0);
            s[1] = new ImageIcon(image1);
            s[2] = new ImageIcon(image2);
            s[3] = new ImageIcon(image3);
            s[4] = new ImageIcon(image4);
            s[5] = new ImageIcon(image5);
            s[6] = new ImageIcon(image6);
            s[7] = new ImageIcon(image7);
            s[8] = new ImageIcon(image8);
            s[9] = new ImageIcon(image9);
            s[10] = new ImageIcon(image10);
            l.setIcon(s[0]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonRevenir) {
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "Vous ne pouvez plus revenir plus");
            } else {
                i = i - 1;
                l.setIcon(s[i]);
            }
        }
        if (e.getSource() == boutonSuivant) {
            if (i == s.length - 1) {
                JOptionPane.showMessageDialog(null, "Fin de Régles");
            } else {
                i = i + 1;
                l.setIcon(s[i]);
            }
        }
        if (e.getSource() == boutonRetour) {
            setVisible(false);
            FenetrePrincipale menu = new FenetrePrincipale();
            try {
                menu.fenetre_regles_generales();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}