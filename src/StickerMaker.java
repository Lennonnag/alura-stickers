import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerMaker {

    public void create(InputStream inputStream, String fileName) throws IOException {

        // Leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream inputStream = new URL(
        // "https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg")
        // .openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Criar memoria com transparência e tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeigth = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeigth, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();

        graphics.drawImage(originalImage, 0, 0, null);

        // configurar a fonte
        Font font = new Font("Impact", Font.BOLD, 150);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        // escrever uma mensagem na nova imagem
        graphics.drawString("TOPZERA", 170, newHeigth -25);
        // escrever a nova imagem em um arquivo

        ImageIO.write(newImage, "png", new File(fileName));
    }

}
