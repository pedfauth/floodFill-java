package com.fill.flood;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

class ImagePanel extends JPanel {

    private static final long serialVersionUID = 7160211253556927600L;
    private BufferedImage image; // Imagem a ser exibida no painel

    public ImagePanel(BufferedImage img) {
        // Construtor que inicializa o painel com uma imagem
        this.image = img;
    }

    public void setImage(BufferedImage img) {
        // Define uma nova imagem para o painel e solicita a repintura
        this.image = img;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Sobrescreve o método paintComponent para desenhar a imagem no painel
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}