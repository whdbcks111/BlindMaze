package maze.object.item;

import maze.object.GameObject;
import maze.object.playable.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends GameObject {

    public Item(JPanel panel, double x, double y, BufferedImage img) {
        super(panel, x, y, img, 1);
    }

    public void onTrigger(Player player) {
        setVisible(false);
    };
}
