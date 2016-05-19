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

public class JuliaSetFrame extends JFrame {
    
    private final double c_real;
    private final double c_imaginary;
    
    private final String titleFrame = "Julia Set";
    private final int width;
    private final int height;
    
    private final int MaxIterations = 100;
    private final double threshold = 4.0;
    
    public JuliaSetFrame(int width, int height, double c_real, double c_imaginary) {
        this.width = width;
        this.height = height;
        this.c_real = c_real;
        this.c_imaginary = c_imaginary;
        
        setTitle(titleFrame);
        setSize(this.width, this.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        setVisible(true);
    
        new Thread() {
            @Override
            public void run() {
                repaint();
            }
        }.start();
    }
    
    public int RGB(int red, int green, int blue){
        return (0xff000000) | (red << 16) | (green << 8) | blue;
    }
    
    @Override
    public void paint(Graphics g) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                double a = (double)(x - width / 2) * 4.0 / width;
                double b = (double)(y - height / 2) * 4.0 / height;
                
                int nIterations = 0;
                while ((a * a + b * b < 4.0) && (nIterations < MaxIterations)) {
                    // Trang's equation: f(z) = 2 * pi * i * e ^ z, where z = a + i * b
                    // double a_next = - 2.0 * Math.PI * Math.exp(a) * Math.sin(b);
                    // double b_next = 2.0 * Math.PI * Math.exp(a) * Math.cos(b);
                    
                    // Quadratic equation: f(z) = z ^ 2 + c, where z = a + i * b
                    double a_next = a * a - b * b + c_real;
                    double b_next = 2 * a * b + c_imaginary;
                    
                    a = a_next;
                    b = b_next;
                    ++nIterations;
                }
                
                int scale = (int)(255.0 * nIterations / MaxIterations);
                g.setColor(new Color(scale / 2, scale, scale));
                g.drawLine(x, y, x, y);
                
                image.setRGB(x, y, RGB(scale / 2, scale, scale));
            }
        }
        
        File file = new File("julia.png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException exc) {
            System.err.println(exc.toString());
        }
    }
    
}
