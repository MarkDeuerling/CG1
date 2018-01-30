package aufgabe4.raytracer;

import aufgabe2.camera.Camera;
import aufgabe4.world.World;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author Mark Deuerling
 */
public class RenderThread extends RenderImageCanvas{
    
    protected final BufferedImage bimg;
    protected final int threadCount;
    public final static int RENDER_WITH_ONE_THREAD    = 1;
    public final static int RENDER_WITH_TWO_THREADS   = 2;
    public final static int RENDER_WITH_FOUR_THREADS  = 4;
    public final static int RENDER_WITH_SIX_THREADS   = 6;
    public final static int RENDER_WITH_EIGHT_THREADS = 8;
    
    public RenderThread(final World world, final Camera cam, final JFrame frame, final int threadCount){
        super(world, cam, frame);
        if(threadCount <= 0){
            throw new IllegalArgumentException("threadCount must be greater than 0");
        }
        bimg = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.threadCount = threadCount;
//        ThreadFactory();
        dynamicThreadBuild();
        
    }
    
    final protected void renderPixel(int dx, int dy, int width, int height){
        for(int x = dx; x < width; x++){
            for(int y = dy; y < height; y++){
                bimg.setRGB(x, height - 1 - y, super.renderColor(x, y).getRGB());
            }
            repaint();
        }
    }
    
    final protected void ThreadFactory(){
        switch(threadCount){
            case RENDER_WITH_ONE_THREAD:
                buildThread(0, 0, frame.getWidth(), frame.getHeight());
                break;
            case RENDER_WITH_TWO_THREADS:
                buildThread(0, 0, frame.getWidth()/2, frame.getHeight());
                buildThread(frame.getWidth()/2, 0, frame.getWidth(), frame.getHeight());
                break;
            case RENDER_WITH_FOUR_THREADS:
                int fourth = frame.getWidth() / 4;
                buildThread(0, 0, fourth, frame.getHeight());
                buildThread(fourth, 0, fourth*2, frame.getHeight());
                buildThread(fourth*2, 0, fourth*3, frame.getHeight());
                buildThread(fourth*3, 0, frame.getWidth(), frame.getHeight());
                break;
            case RENDER_WITH_SIX_THREADS:
                final int sixth = frame.getWidth() / 6;
                buildThread(0, 0, sixth, frame.getHeight());
                buildThread(sixth,   0, sixth*2, frame.getHeight());
                buildThread(sixth*2, 0, sixth*3, frame.getHeight());
                buildThread(sixth*3, 0, sixth*4, frame.getHeight());
                buildThread(sixth*4, 0, sixth*5, frame.getHeight());
                buildThread(sixth*5, 0, frame.getWidth(), frame.getHeight());
                break;
            case RENDER_WITH_EIGHT_THREADS:
                final int eighth = frame.getWidth() / 8;
                buildThread(0, 0, eighth, frame.getHeight());
                buildThread(eighth,   0, eighth*2, frame.getHeight());
                buildThread(eighth*2, 0, eighth*3, frame.getHeight());
                buildThread(eighth*3, 0, eighth*4, frame.getHeight());
                buildThread(eighth*4, 0, eighth*5, frame.getHeight());
                buildThread(eighth*5, 0, eighth*6, frame.getHeight());
                buildThread(eighth*6, 0, eighth*7, frame.getHeight());
                buildThread(eighth*7, 0, frame.getWidth(), frame.getHeight());
                break;
        }
    }
    
    final protected void dynamicThreadBuild(){
        final int part = frame.getWidth() / threadCount;
        
        for(int i = 0; i < threadCount; i++){
            buildThread(part*i, 0, part*(i+1), frame.getHeight());
        }
    }
    
    final protected void buildThread(final int x, final int y, final int width, final int height){
        Thread t = new Thread(()-> {
            renderPixel(x, y, width, height);
            repaint();
        });
        t.start();
    }

    @Override
    final public void paint(Graphics g) {
        g.drawImage(bimg, 0, 0, null);
    }

}
