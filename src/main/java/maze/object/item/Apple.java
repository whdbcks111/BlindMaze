package maze.object.item;

import maze.collider.AABBCollider;
import maze.object.playable.Player;
import maze.utils.ImageManager;

import javax.swing.*;

public class Apple extends Item {

    public Apple(JPanel panel, int x, int y) {
        super(panel, x, y, ImageManager.getImage("itemApple"));
        this.collider = new AABBCollider(this, img.getWidth(),
                img.getHeight(), 0, 0);
    }

    @Override
    public void onTrigger(Player player) {
        super.onTrigger(player);
        player.setScore(player.getScore() + 12000);
    }
}
