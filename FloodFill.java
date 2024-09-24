package com.fill.flood;

import java.awt.image.BufferedImage; // manipulação de imagens
import java.io.File; // manipulação de arquivos
import java.io.IOException; // exceções de I/O
import java.util.Scanner; // entrada de dados
import javax.imageio.ImageIO; // leitura de imagens
import javax.swing.JFrame; // janela gráfica

public class FloodFill {

    private BufferedImage image; // Imagem a ser preenchida
    private ImagePanel imagePanel; // Painel para exibir a imagem
    public int op; // Opção de renderização (1 para pilha, 2 para fila)

    public void readFile() {
        // Lê a imagem do arquivo e inicia o processo de preenchimento
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Escreva o caminho da sua img: ");
            String path = scan.nextLine();
            File imageFile = new File(path);
            image = ImageIO.read(imageFile);
            System.out.println("Escolha o indice x: ");
            int x = scan.nextInt();
            System.out.println("Escolha o indice y: ");
            int y = scan.nextInt();
            System.out.println("Escolha forma de renderização: 1-pilha 2-fila");
            op = scan.nextInt();

            if (image != null) {
                // Solicitar a cor ao usuário
                String color = getColorFromUser();

                // Exibir a imagem antes do preenchimento
                showImage(image);

                // Iniciar o flood fill em uma nova thread para não bloquear a UI
                new Thread(() -> {
                    BufferedImage filledImage = floodFill(image, x, y, color);

                    if (filledImage != null) {
                        // Atualizar a imagem final (opcional se já estiver atualizando durante o preenchimento)
                        imagePanel.setImage(filledImage);
                    } else {
                        System.out.println("Falha ao colorir a imagem.");
                    }
                }).start();
            } else {
                System.out.println("Falha ao carregar a imagem.");
            }

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler a imagem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getColorFromUser() {
        // Solicita a cor ao usuário
        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha a cor, opções (red, green, blue):");
        return scan.next();
    }

    public BufferedImage floodFill(BufferedImage image, int x, int y, String color) {
        // Realiza o preenchimento da imagem usando a cor especificada
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            System.out.println("Coordenadas fora dos limites da imagem.");
            return null;
        }

        int targetColor = image.getRGB(x, y);
        int newColor = getColorHex(color);

        if (targetColor == newColor) {
            return image;
        }

        if (this.op == 1) {
            // Usar pilha para o preenchimento
            Pilha<int[]> pilha = new Pilha<>(image.getWidth() * image.getHeight());
            pilha.push(new int[] { x, y });

            while (!pilha.isEmpty()) {
                int[] coordenada = pilha.pop();
                int cx = coordenada[0];
                int cy = coordenada[1];

                if (cx < 0 || cx >= image.getWidth() || cy < 0 || cy >= image.getHeight()) {
                    continue;
                }

                if (image.getRGB(cx, cy) != targetColor) {
                    continue;
                }

                image.setRGB(cx, cy, newColor);

                // Atualizar a interface gráfica (renderizar conforme o colorimento)
                imagePanel.repaint();

                try {
                    // Adicionar um pequeno atraso para visualização gradual
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                pilha.push(new int[] { cx + 1, cy });
                pilha.push(new int[] { cx - 1, cy });
                pilha.push(new int[] { cx, cy + 1 });
                pilha.push(new int[] { cx, cy - 1 });
            }
        } else {
            // Usar fila para o preenchimento
            Fila<int[]> fila = new Fila<>(image.getWidth() * image.getHeight());
            fila.add(new int[] { x, y });

            while (!fila.isEmpty()) {
                int[] coordenada = fila.remove();
                int cx = coordenada[0];
                int cy = coordenada[1];

                if (cx < 0 || cx >= image.getWidth() || cy < 0 || cy >= image.getHeight()) {
                    continue;
                }

                if (image.getRGB(cx, cy) != targetColor) {
                    continue;
                }

                image.setRGB(cx, cy, newColor);

                // Atualizar a interface gráfica (renderizar conforme o colorimento)
                imagePanel.repaint();

                try {
                    // Adicionar um pequeno atraso para visualização gradual
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fila.add(new int[] { cx + 1, cy });
                fila.add(new int[] { cx - 1, cy });
                fila.add(new int[] { cx, cy + 1 });
                fila.add(new int[] { cx, cy - 1 });
            }
        }

        return image;
    }

    // Função auxiliar para converter a cor para hexadecimal
    public static int getColorHex(String color) {
        switch (color.toLowerCase()) {
        case "red":
            return 0xFFFF0000;
        case "green":
            return 0xFF00FF00;
        case "blue":
            return 0xFF0000FF;
        default:
            System.out.println("Cor não reconhecida. Usando preto como padrão.");
            return 0xFF000000; // Preto
        }
    }

    public void showImage(BufferedImage img) {
        // Exibe a imagem em um JFrame
        JFrame frame = new JFrame("Exibição de Imagem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight());

        imagePanel = new ImagePanel(img); // Criar o painel com a imagem
        frame.add(imagePanel); // Adicionar o painel ao JFrame
        frame.setVisible(true); // Tornar a janela visível
    }

    public static void main(String[] args) {
        // Método principal para iniciar o programa
        new FloodFill().readFile();
    }
}