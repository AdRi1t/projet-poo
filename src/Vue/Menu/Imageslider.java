package Vue.Menu;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Imageslider extends JFrame  implements ActionListener
{
    ImageIcon s[];
    JLabel l;
    JButton boutonRevenir, boutonSuivant, boutonRetour;
    int i,l1;
    JPanel p;
    public Imageslider(int regle) throws IOException {
        setLayout(new BorderLayout( ));
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //setBackground(Color.lightGray);

        JPanel p=new JPanel(new FlowLayout());
        boutonRevenir=new JButton("Revenir");
        boutonSuivant =new JButton("Suivant");
        boutonRetour =new JButton("Retour");
/*
        p.add(b3);
        p.add(b1);

        p.add(b2);

        add(p,BorderLayout.PAGE_START);
        p.setBackground(Color.lightGray);*/

        l = new JLabel("",JLabel.CENTER);
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

        if(regle==1){

        BufferedImage bufferedImage = ImageIO.read(new File("Images/Menu_Regles_Generales(1).png"));
        Image image0 = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

        BufferedImage bufferedImage1 = ImageIO.read(new File("Images/RegleExplo.png"));
        Image image1 = bufferedImage1.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

        BufferedImage bufferedImage2 = ImageIO.read(new File("Images/RegleTuileForet.png"));
        Image image2 = bufferedImage2.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

        BufferedImage bufferedImage3 = ImageIO.read(new File("Images/RegleTuilePlage.png"));
        Image image3 = bufferedImage3.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

        BufferedImage bufferedImage4 = ImageIO.read(new File("Images/RegleTuileMontagne.png"));
        Image image4 = bufferedImage4.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

       // BufferedImage bufferedImage5 = ImageIO.read(new File("Images/Retirer_tuile2.png"));
      //  Image image5 = bufferedImage5.getScaledInstance(200, 200, Image.SCALE_DEFAULT);

        s = new ImageIcon[6];
        s[0] = new ImageIcon(image0);
        s[1] = new ImageIcon(image1);
        s[2] = new ImageIcon(image2);
        s[3] = new ImageIcon(image3);
        s[4] = new ImageIcon(image4);
       // s[5] = new ImageIcon(image5);
        l.setIcon(s[0]);

        } else if (regle==2) {
            BufferedImage bufferedImage = ImageIO.read(new File("Images/Menu_Regles_Tuiles.png"));
            Image image0 = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage1 = ImageIO.read(new File("Images/Menu_Monstres.png"));
            Image image1 = bufferedImage1.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage2 = ImageIO.read(new File("Images/RegleTuileForet.png"));
            Image image2 = bufferedImage2.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage3 = ImageIO.read(new File("Images/tuiles2.png"));
            Image image3 = bufferedImage3.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            BufferedImage bufferedImage4 = ImageIO.read(new File("Images/RegleTuileMontagne.png"));
            Image image4 = bufferedImage4.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

            s = new ImageIcon[5];
            s[0] = new ImageIcon(image0);
            s[1] = new ImageIcon(image1);
            s[2] = new ImageIcon(image2);
            s[3] = new ImageIcon(image3);
            s[4] = new ImageIcon(image4);
            l.setIcon(s[0]);
        }
    }
    public  void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==boutonRevenir)
        {
            if(i==0)
            {
                JOptionPane.showMessageDialog(null,"Vous ne pouvez plus revenir plus");
            }
            else
            {
                i=i-1;
                l.setIcon(s[i]);
            }
        }
        if(e.getSource()== boutonSuivant)
        {
            if(i==s.length-1)
            {
                JOptionPane.showMessageDialog(null,"Fin de Régles");
            }
            else
            {
                i=i+1;
                l.setIcon(s[i]);
            }
        }
        if(e.getSource()== boutonRetour){
            setVisible(false);
            FenetrePrincipale menu= new FenetrePrincipale();
            try {
                menu.fenetre_regles_generales();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        }
    }
