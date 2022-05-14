package Vue.Menu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Imageslider extends JFrame  implements ActionListener
{
    ImageIcon s[];
    JLabel l;
    JButton b1,b2;
    int i,l1;
    JPanel p;
    public Imageslider()
    {
        setLayout(new BorderLayout( ));
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p=new JPanel(new FlowLayout());
        b1=new JButton("Revenir");
        b2=new JButton("Suivant");
        p.add(b1);
        p.add(b2);
        add(p,BorderLayout.SOUTH);

        b1.addActionListener(this);
        b2.addActionListener(this);

        s = new ImageIcon[3];
        s[0] = new ImageIcon("Images/RèglesPièce.png");
        s[1] = new ImageIcon("Images/ReglesPiece2.png");
        s[2] = new ImageIcon("Images/ReglesGeneral.png");
        l = new JLabel("",JLabel.CENTER);
        add(l,BorderLayout.CENTER);
        l.setIcon(s[0]);

    }
    public  void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
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
        if(e.getSource()==b2)
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
    }
    public static void main(String args[])
    {
        Imageslider obj = new Imageslider();
    }
}