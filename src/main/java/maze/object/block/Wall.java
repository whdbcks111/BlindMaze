package maze.object.block;

import maze.collider.AABBCollider;
import maze.object.GameObject;
import maze.utils.ImageManager;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObject {

    public Wall(JPanel panel, int x, int y) {
        super(panel, x, y, ImageManager.getImage("wall"), 3);
        this.collider = new AABBCollider(this, img.getWidth(),
                img.getHeight(), 0, 0);
    }
}
