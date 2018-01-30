package aufgabe1.imageload;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.JFrame;

/**
 * This class build the canvas with the default image or load images.
 * 
 * @author Peter Albrecht, Stefan Streichan, Mark Deuerling
 */
public class ImageCanvas extends Canvas {
    
    /**
     * The manipulated image
     */
    protected BufferedImage bimg;
    
    /**
     * The Frame that resize the image
     */
    protected final JFrame frame;
    
    /**
     * Constructs a Canvas with a defined Image
     * 
     * @param frame Frame to manipulate the size
     **/
    public ImageCanvas(final JFrame frame) {
        this.frame = frame;

    }

    /**
     * Paints the image in size of the given frame.
     * This image is by default black with a red diagonal line
     * 
     * @param g points to the graphic objects to draw the image
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawBlackWithRedLine();
        
        g.drawImage(bimg, 0, 0, null);
    }

    /**
     * Model to draw the image with red line
     */
    protected void drawBlackWithRedLine() {
        bimg = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        WritableRaster raster = bimg.getRaster();
        ColorModel model = bimg.getColorModel();

        for (int x = 0; x < bimg.getWidth(); x++) {
            for (int y = 0; y < bimg.getHeight(); y++) {
                raster.setDataElements(x, y, model.getDataElements(drawRedLine(x, y).getRGB(), null));
                
                //alternative
                /*
                if(x == y){
                    bimg.setRGB(x, y, 0xff0000);
                }else{
                    bimg.setRGB(x, y, 0x000000);
                }
                */
            }
        }
    }

    /**
     * Draw the red line 
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @return Color Returns a color red or black to draw the image
     */
    protected Color drawRedLine(int x, int y) {
        if (x == y) {
            return Color.RED;
        }
        return Color.BLACK;
    }

    /**
     * 
     * @return the given image
     */
    public BufferedImage getImage() {
        return bimg;
    }

    
    public static void main(String[] args) {
        ImageCanvas imgCanvas;

        JFrame frame = new JFrame("Black with Red Line");

        imgCanvas = new ImageCanvas(frame);

        frame.setJMenuBar(new MyMenu(imgCanvas));

        Container con = frame.getContentPane();
        con.add(imgCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}