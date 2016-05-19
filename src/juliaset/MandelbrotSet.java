// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2016 (c) Hy Truong Son. All rights reserved. Only use for academic purposes.

package juliaset;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MandelbrotSet extends JFrame {

    private final int MaxIterations = 30;
    private final double Threshold = 4.0;
    
    private final double zoomWidth = 3.0;
    private final double zoomHeight = 3.0;
    
    private final String titleFrame = "Mandelbrot Set";
    private final int widthFrame = 800;
    private final int heightFrame = 600;
    
    public static void main(String[] args) {
        new MandelbrotSet(); 
    }

    public MandelbrotSet(){
        setTitle(titleFrame);
        setSize(widthFrame, heightFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().add(new MyPanel());
        
        setVisible(true);
    }
    
    public int RGB(int red, int green, int blue){
        return (0xff000000) | (red << 16) | (green << 8) | blue;
    }
    
    public class MyPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(widthFrame, heightFrame, BufferedImage.TYPE_INT_RGB);
            
            for (int px = 0; px < widthFrame; px++){
                for (int py = 0; py < heightFrame; py++){
                    double cx = (px - widthFrame * 0.7) * zoomWidth / widthFrame;
                    double cy = (py - heightFrame * 0.5) * zoomHeight / heightFrame;
                    
                    double x = 0;
                    double y = 0;
                    
                    int nIterations = 0;
                    while ((x * x + y * y < Threshold) && (nIterations < MaxIterations)){
                        double nextx = x * x - y * y + cx;
                        double nexty = 2 * x * y + cy;
                        x = nextx;
                        y = nexty;
                        nIterations++;
                    }
                    
                    int scale = (int)(255.0 * nIterations / MaxIterations);
                    g.setColor(new Color(scale / 2, scale, scale));
                    g.drawLine(px, py, px, py);
                    
                    image.setRGB(px, py, RGB(scale / 2, scale, scale));
                }
            }
            
            File file = new File("mandelbrot.png");
            try {
                ImageIO.write(image, "png", file);
            } catch (IOException exc) {
                System.err.println(exc.toString());
            }
        }
    }
    
}