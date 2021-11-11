package maze.object.item;

import maze.collider.AABBCollider;
import maze.object.playable.Player;
import maze.utils.ImageManager;

import javax.swing.*;

public class SpeedUp extends Item {

    public SpeedUp(JPanel panel, int x, int y) {
        super(panel, x, y, ImageManager.getImage("itemSpeedUp"));
        this.collider = new AABBCollider(this, img.getWidth(),
                img.getHeight(), 0, 0);
    }

    @Override
    public void onTrigger(Player player) {
        super.onTrigger(player);
        player.setRemainSpeedUpTime(1.5, 7);
    }
}
