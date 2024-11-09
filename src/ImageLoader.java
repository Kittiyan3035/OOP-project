import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(ImageLoader.class.getResource(path));
    }

    public static BufferedImage[] loadImages(String[] paths) {
        BufferedImage[] images = new BufferedImage[paths.length];
        for (int i = 0; i < paths.length; i++) {
            try {
                images[i] = loadImage(paths[i]);
            } catch (IOException e) {
                e.printStackTrace();
                images[i] = null;
            }
        }
        return images;
    }
}
