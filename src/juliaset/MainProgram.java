// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2016 (c) Hy Truong Son. All rights reserved. Only use for academic purposes.

package juliaset;

public class MainProgram {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                OptionFrame optionFrame = new OptionFrame();
            }
        }.start();
        
        new Thread() {
            @Override
            public void run() {
                new MandelbrotSet();
            }
        }.start();
    }
    
}
