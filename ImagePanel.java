package com.fill.flood;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

    private static final long serialVersionUID = 7160211253556927600L; 
    private BufferedImage image; 

    public ImagePanel(BufferedImage img) {
        this.image = img;
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}