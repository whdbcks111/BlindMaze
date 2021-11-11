package maze.object.item;

import maze.collider.AABBCollider;
import maze.object.GameObject;
import maze.object.playable.Player;
import maze.utils.ImageManager;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Carrot extends Item {

    public Carrot(JPanel panel, int x, int y) {
        super(panel, x, y, ImageManager.getImage("itemCarrot"));
        this.collider = new AABBCollider(this, img.getWidth(),
                img.getHeight(), 0, 0);
    }

    @Override
    public void onTrigger(Player player) {
        super.onTrigger(player);
        player.setRemainCarrotTime(3.5);
    }
}
