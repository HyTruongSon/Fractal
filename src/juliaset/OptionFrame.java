// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2016 (c) Hy Truong Son. All rights reserved. Only use for academic purposes.

package juliaset;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class OptionFrame extends JFrame {
    
    private final String titleFrame = "Options";
    private final int widthFrame = 480;
    private final int heightFrame = 500;
    private final int marginFrame = 20;
    private final int heightComponent = 50;
    private final int heightButton = 30;
    private final int widthComponent = widthFrame - 2 * marginFrame;
    private final int widthText = (widthFrame - 2 * marginFrame) / 2;
    
    private final int MIN = 200;
    private final int MAX = 2048;
    private final int INIT = 640;
    
    private final JLabel widthLabel;
    private JSlider widthSlider;
    private final JLabel heightLabel;
    private JSlider heightSlider;
    private final JLabel realLabel;
    private JTextField realText;
    private final JLabel imaginaryLabel;
    private JTextField imaginaryText; 
    private final JButton generateButton;
    
    public OptionFrame() {
        setTitle(titleFrame);
        setSize(widthFrame, heightFrame);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        int x = marginFrame;
        int y = marginFrame;

        widthLabel = new JLabel();
        widthLabel.setText("Width of plot:");
        widthLabel.setBounds(x, y, widthComponent, heightComponent);
        widthLabel.setForeground(Color.BLUE);
        add(widthLabel);
        
        y += heightComponent;
        
        widthSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        widthSlider.setBounds(x, y, widthComponent, heightComponent);
        widthSlider.setMajorTickSpacing(200);
        widthSlider.setPaintTicks(true);
        widthSlider.setPaintLabels(true);
        add(widthSlider);
        
        y += heightComponent;
        
        heightLabel = new JLabel();
        heightLabel.setText("Height of plot:");
        heightLabel.setBounds(x, y, widthComponent, heightComponent);
        heightLabel.setForeground(Color.BLUE);
        add(heightLabel);
        
        y += heightComponent;
        
        heightSlider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        heightSlider.setBounds(x, y, widthComponent, heightComponent);
        heightSlider.setMajorTickSpacing(200);
        heightSlider.setPaintTicks(true);
        heightSlider.setPaintLabels(true);
        add(heightSlider);
        
        y += heightComponent + marginFrame;
        
        realLabel = new JLabel("Real part of c:");
        realLabel.setBounds(x, y, widthText, heightButton);
        add(realLabel);
        
        realText = new JTextField("-0.4");
        realText.setBounds(widthFrame / 2, y, widthText, heightButton);
        add(realText);
        
        y += heightComponent;
        
        imaginaryLabel = new JLabel("Imaginary part of c:");
        imaginaryLabel.setBounds(x, y, widthText, heightButton);
        add(imaginaryLabel);
        
        imaginaryText = new JTextField("0.6");
        imaginaryText.setBounds(widthFrame / 2, y, widthText, heightButton);
        add(imaginaryText);
        
        y += heightComponent + marginFrame;
        
        generateButton = new JButton();
        generateButton.setText("Generate quadratic Julia f(z) = z ^ 2 + c");
        generateButton.setForeground(Color.BLUE);
        generateButton.setBounds(x, y, widthComponent, heightButton);
        add(generateButton);
        
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int width = widthSlider.getValue();
                final int height = heightSlider.getValue();
                final double c_real = Double.parseDouble(realText.getText());
                final double c_imaginary = Double.parseDouble(imaginaryText.getText());
                
                new Thread() {
                    @Override
                    public void run() {
                        JuliaSetFrame juliaSetFrame = new JuliaSetFrame(width, height, c_real, c_imaginary);
                    }
                }.start();
            }
        });
        
        setVisible(true);
    }
    
}
