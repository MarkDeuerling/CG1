/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe1.imageload;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class build the menubar for a frame
 *
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class MyMenu extends JMenuBar {

    /**
     * File that stores the path of the given image
     */
    protected File file;

    /**
     * Constructs the Menubar to save the image and managed the event
     * 
     * @param myImage Image to be saved or loaded
     */
    public MyMenu(final ImageCanvas myImage) {
        JMenu menu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JFileChooser choose = new JFileChooser();

        saveItem.addActionListener(e -> {
            if (0 == choose.showDialog(new JFrame(), "Save Image")) {
                file = choose.getSelectedFile();
                try {
                    String path = file.getAbsolutePath();
                    if (!path.endsWith("png")) {
                        path += ".png";
                        file = new File(path);
                    }
                    ImageIO.write(myImage.getImage(), "png", file);

                    System.out.println("Image successfully saved in " + file.getPath());
                } catch (IOException ex) {
                    ex.printStackTrace();

                    System.out.println("File save failed");
                }
            }

        });

        menu.add(saveItem);
        add(menu);
    }
}
