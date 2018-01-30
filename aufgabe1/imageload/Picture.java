/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe1.imageload;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class render an image from a directory path
 *
 * @author Peter Albrecht, Stefan Steichan, Mark Deuerling
 */
public class Picture extends Canvas {

    public Image image;

    /**
     * Constructor of the class to get the image of a directory
     *
     * @param image Image that should be rendered
     */
    public Picture(final Image image) {
        this.image = image;
        
    }

    /**
     * Draw the image on screen
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) throws IOException {
        JFileChooser chooser = new JFileChooser();
        
        chooser.setFileFilter(new FileFilterPNGAndJPEG());

        if (0 == chooser.showDialog(null, "Choose Image")) {
            
            File file = chooser.getSelectedFile();

            Image image = ImageIO.read(file);

            JFrame frame = new JFrame(file.getName());
            frame.setSize(image.getWidth(null), image.getHeight(null));
            frame.getContentPane().add(new Picture(image));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
        }

    }
}
