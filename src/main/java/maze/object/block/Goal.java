package maze.object.block;

import maze.collider.AABBCollider;
import maze.object.GameObject;
import maze.utils.ImageManager;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Goal extends GameObject {

    public Goal(JPanel panel, int x, int y) {
        super(panel, x, y, ImageManager.getImage("goal"), 1);
        this.collider = new AABBCollider(this, img.getWidth(),
                img.getHeight(), 0, 0);
    }
}
