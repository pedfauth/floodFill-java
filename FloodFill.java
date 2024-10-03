package com.fill.flood;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FloodFill {

	private BufferedImage image;
	private ImagePanel imagePanel;
	public int op;

	public void readFile() {
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
				String color = getColorFromUser();

				showImage(image);

				new Thread(() -> {
					BufferedImage filledImage = floodFill(image, x, y, color);

					if (filledImage != null) {
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
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha a cor, opções (red, green, blue):");
		return scan.next();
	}

	public BufferedImage floodFill(BufferedImage image, int x, int y, String color) {
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

				imagePanel.repaint();

				try {
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

				imagePanel.repaint();

				try {
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
			return 0xFF000000; 
		}
	}

	public void showImage(BufferedImage img) {
		JFrame frame = new JFrame("Exibição de Imagem");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(img.getWidth(), img.getHeight());

		imagePanel = new ImagePanel(img); 
		frame.add(imagePanel); 
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new FloodFill().readFile();
	}
}