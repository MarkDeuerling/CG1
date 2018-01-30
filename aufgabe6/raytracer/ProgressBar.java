/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe6.raytracer;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Peter Albrecht
 */
public class ProgressBar extends JPanel{
    
    protected final JProgressBar progress;
    protected final JLabel timeLabel;
    protected int currentX;
    protected long timeTotal;
    
    public ProgressBar(int maxValue){        
        progress = new JProgressBar(0, maxValue);
        GridLayout layout = new GridLayout(2, 1);
        setLayout(layout);
        
        progress.setValue(0);
        progress.setStringPainted(true);
        add(progress);
     
        this.timeLabel = new JLabel();
        this.add(timeLabel);
        
        currentX = 1;
        timeTotal = 0;
    }
    
    public long updateBar(long oldTime){
        long timeCurrent = System.nanoTime();
        double time = calculateTime(oldTime, timeCurrent);
        progress.setValue(currentX);
        timeLabel.setText((int)time/60 + "min " + (int)time%60 + "s");
        this.paint(this.getGraphics()); 
        currentX++;
        return timeCurrent;
    } 
    
    public double calculateTime(long oldTime, long newTime){
        long timeCurrent = System.nanoTime();
        long difference = timeCurrent - oldTime;
        timeTotal += difference;
        double average = timeTotal/currentX;
        double time = (average * (progress.getMaximum() - currentX)/1000000000);
        return time;
    }
    
    synchronized final public void renderProgress(){
        JFrame frame = new JFrame();
	frame.setSize(300, 100);
	frame.setTitle("JProgressBar Beispiel"); 
        frame.add(this);
	frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }
}
