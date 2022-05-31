package Model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LancerDe extends JFrame {
    private JPanel contentPane;

    private ImageIcon[] image = new ImageIcon[8];
    JLabel ImageLabel;

    /**
     * Create the frame.
     * 
     * @throws IOException
     */
    public LancerDe() throws IOException {
        image[0] = new ImageIcon("Images/1fnum6.png");
        image[1] = new ImageIcon("Images/2fnum6.png");
        image[2] = new ImageIcon("Images/3fnum6.png");
        image[3] = new ImageIcon("Images/fnum1.png");
        image[4] = new ImageIcon("Images/fnum2.png");
        image[5] = new ImageIcon("Images/fnum3.png");
        image[6] = new ImageIcon("Images/fnum4.png");
        image[7] = new ImageIcon("Images/fnum5.png");
        ImageLabel = new JLabel(image[0]);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(10, 10, 400, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        Panel panel = new Panel();
        contentPane.add(panel, BorderLayout.CENTER);

        panel.add(ImageLabel);

        Panel panel_1 = new Panel();
        contentPane.add(panel_1, BorderLayout.EAST);

        Button button = new Button("Click");
        contentPane.add(button, BorderLayout.SOUTH);
        button.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int y = 0; y < 3; y++) {
                            for (int j = 3; j < 8; j++) {

                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                ImageLabel.setIcon(image[j]);
                            }
                        }

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int i = random();
                        System.out.println("La valeur du de est " + String.valueOf(i));
                        ImageLabel.setIcon(image[i]);
                    }
                });
                th.start();

            }

        });
    }

    public static int random() {
        int i = (int) Math.floor(Math.random() * (3));
        return i;
    }
}
