package maze.object.item;

import maze.collider.AABBCollider;
import maze.object.playable.Player;
import maze.utils.ImageManager;

import javax.swing.*;

public class SpaceEye extends Item {

    public SpaceEye(JPanel panel, int x, int y) {
        super(panel, x, y, ImageManager.getImage("itemSpaceEye"));
        this.collider = new AABBCollider(this, img.getWidth(),
                img.getHeight(), 0, 0);
    }

    @Override
    public void onTrigger(Player player) {
        super.onTrigger(player);
        player.setRemainSpaceTime(6.5);
        player.setRemainSpeedUpTime(0.4, 6.5);
    }
}
