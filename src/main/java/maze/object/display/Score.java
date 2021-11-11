package maze.object.display;

import maze.object.GameObject;
import maze.object.playable.Player;

import javax.swing.*;
import java.awt.*;

public class Score extends GameObject {

    private final Player player;

    public Score(JPanel panel, Player player, int x, int y, Color color, Font font) {
        super(panel, x, y, null, 10);
        this.text = new Text("", color, font);
        this.player = player;
    }

    @Override
    public void update() {
        super.update();
        this.text.setText("점수 " + String.format("%06d", player.getScore()));
    }
}
