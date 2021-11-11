package maze.view;

import maze.components.MazeBuilder;
import maze.components.Updatable;
import maze.collider.AABBCollider;
import maze.controller.BaseController;
import maze.controller.GameController;
import maze.main.Main;
import maze.object.GameObject;
import maze.object.item.Carrot;
import maze.object.playable.Player;
import maze.object.display.Score;
import maze.utils.FileStream;
import maze.utils.ImageManager;
import sun.awt.image.ToolkitImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class GameView extends JPanel implements Updatable {

    private final JFrame frame;
    MazeBuilder mazeBuilder;
    int distance = 25;
    private final int mazeWidth = 51, mazeHeight = 31;
    private final int halfWidth = mazeWidth / 2, halfHeight = mazeHeight / 2;
    private final int width = distance * mazeWidth, height = distance * mazeHeight;
    maze.components.Point start = new maze.components.Point(
            halfWidth % 2 == 0 ? halfWidth + 1 : halfWidth,
            halfHeight % 2 == 0 ? halfHeight + 1 : halfHeight);
    private Point initialClick;

    int playerSize = (int) (distance * 0.6);

    BufferedImage bufferImg;
    Graphics bufferGraphics;

    Score score;
    Player player;
    GameController playerController = new GameController();

    public GameView(JFrame frame) throws IOException {
        this.frame = frame;

        ImageManager.saveImage("player", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/Player.png")), playerSize, playerSize));
        ImageManager.saveImage("speedPlayer", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/SpeedPlayer.png")), playerSize, playerSize));
        ImageManager.saveImage("light", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/Light.png")), width * 2 + 100, height * 2 + 100));
        ImageManager.saveImage("carrotLight", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/CarrotLight.png")), width * 2 + 100, height * 2 + 100));
        ImageManager.saveImage("spaceLight", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/SpaceLight.png")), width * 2 + 100, height * 2 + 100));
        ImageManager.saveImage("wall", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/Wall.png")), distance, distance));
        ImageManager.saveImage("goal", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/Goal.png")), distance, distance));
        ImageManager.saveImage("start", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/Start.png")), distance, distance));
        ImageManager.saveImage("itemCarrot", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/ItemCarrot.png")), distance, distance));
        ImageManager.saveImage("itemSpaceEye", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/ItemSpaceEye.png")), distance, distance));
        ImageManager.saveImage("itemApple", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/ItemApple.png")), distance, distance));
        ImageManager.saveImage("itemSpeedUp", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/ItemSpeedUp.png")), distance, distance));

        setSize(width, height);
        setBackground(Color.WHITE);
    }

    public void resetGame() {
        GameObject.getGameObjects().clear();
        playerController.reset();
        player = new Player(this, playerController, start.getX() * distance + distance / 2,
                start.getY() * distance + distance / 2);

        score = new Score(this, player, width - 200, 40, Color.WHITE, new Font("맑은고딕", Font.BOLD, 30));
        playerController.registerKeyBindings(this, new String[]{"UP", "DOWN", "LEFT", "RIGHT"});
        mazeBuilder = new MazeBuilder(mazeWidth, mazeHeight);
        mazeBuilder.buildMaze(this, distance, start);
    }

    @Override
    public void paint(Graphics g) {
        bufferImg = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_ARGB);
        bufferGraphics = bufferImg.getGraphics();
        update(g);
    }

    @Override
    public void update(Graphics g) {
        bufferGraphics.setColor(Color.getColor("00000000"));
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());
        for(GameObject gameObject : GameObject.getGameObjects()) {
            if(gameObject.getPanel() == this) {
                int x = (int) (gameObject.getX() - (gameObject.getImage() == null ? 0 : gameObject.getImage().getWidth(this) / 2.0));
                int y = (int) (gameObject.getY() - (gameObject.getImage() == null ? 0 : gameObject.getImage().getHeight(this) / 2.0));
                if(gameObject.isVisible()) {
                    if (gameObject.getImage() != null)
                        bufferGraphics.drawImage(gameObject.getImage(), x, y, this);
                    if (gameObject.getText() != null) {
                        bufferGraphics.setFont(gameObject.getText().getFont());
                        bufferGraphics.setColor(gameObject.getText().getColor());
                        bufferGraphics.drawString(gameObject.getText().getText(), x, y);
                    }
                }
            }
        }
        g.drawImage(bufferImg, 0, 0, this);
        repaint();
    }

    @Override
    public void update() {
        for (GameObject gameObject : GameObject.getGameObjects()) {
            gameObject.update();
        }
    }

    public void gameClear(int score) {
        this.setVisible(false);
        Main.getGameClearView().setVisible(true);
        Main.getGameClearView().setScore();
        FileStream fileStream = new FileStream(new File(System.getenv("APPDATA"), "blindmaze/scores.txt").getAbsolutePath());
        fileStream.write(fileStream.read() + player.getScore() + ' ' + new Date().getTime());
    }

    public void gameOver() {
        this.setVisible(false);
        Main.getGameOverView().setVisible(true);
    }

    public Player getPlayer() {
        return player;
    }
}
