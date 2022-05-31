package Controlleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RedimensionnerImage {
    public static void main(String[] args) throws IOException {

        new RedimensionnerImage("Images/ExploBleu.png");
    }

    public RedimensionnerImage(String imagePathToRead) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(imagePathToRead));
        Image image = bufferedImage.getScaledInstance(1200, 720, Image.SCALE_DEFAULT);

        ImageIcon icon = new ImageIcon(image);

        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);
    }
}