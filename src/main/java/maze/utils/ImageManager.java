package maze.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageManager {
    public static final HashMap<String, BufferedImage> imageMap = new HashMap<>();

    public static void saveImage(String name, BufferedImage image) {
        imageMap.put(name, image);
    }

    public static BufferedImage getImage(String name) {
        return imageMap.get(name);
    }

    public static BufferedImage resize(BufferedImage origin, int width, int height) {

        BufferedImage outputImage =
                new BufferedImage(width, height, origin.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(origin, 0, 0, width, height, null);
        graphics2D.dispose();

        return outputImage;
    }
}
