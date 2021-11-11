package maze.main;

import maze.utils.ImageManager;
import maze.view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    private static GameView gameView;
    private static TitleView titleView;
    private static GameClearView gameClearView;
    private static GameOverView gameOverView;
    private static RankingView rankingView;
    private static long start;
    public static double deltaTime = 1 / 60.0;

    public Main() {
        BaseMouseAdapter mouseAdapter = new BaseMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    public static void main(String[] args) {
        Main frame = new Main();

        try {
            ImageManager.saveImage("icon", ImageIO.read(Main.class.getResourceAsStream("/icon.png")));

            gameView = new GameView(frame);
            titleView = new TitleView(frame, gameView);
            gameClearView = new GameClearView(frame, gameView);
            gameOverView = new GameOverView(frame, gameView);
            rankingView = new RankingView(frame, gameView);

            frame.add(gameView);
            frame.add(titleView);
            frame.add(gameOverView);
            frame.add(gameClearView);
            frame.add(rankingView);
            frame.setSize(gameView.getWidth(), gameView.getHeight());
            frame.setUndecorated(true);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(ImageManager.getImage("icon"));
            frame.setTitle("Blind Maze");
            gameView.setVisible(false);
            gameOverView.setVisible(false);
            rankingView.setVisible(false);
            gameClearView.setVisible(false);
            titleView.setVisible(true);
            Thread gameThread = new Thread(() -> {
                try {
                    while (true) {
                        start = System.nanoTime();
                        if(gameView.isVisible()) {
                            gameView.update();
                        }
                        Thread.sleep(1000 / 60);
                        deltaTime = (double) (System.nanoTime() - start) / 1e9;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            gameThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class BaseMouseAdapter extends MouseAdapter {
        Point initialClick;

        public void mousePressed(MouseEvent e)
        {
            initialClick = e.getPoint();
            getComponentAt(initialClick);
        }

        public void mouseDragged(MouseEvent e)
        {
            JFrame jf = ((JFrame) e.getSource());

            int thisX = jf.getLocation().x;
            int thisY = jf.getLocation().y;

            int xMoved = e.getX() - initialClick.x;
            int yMoved = e.getY() - initialClick.y;

            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            jf.setLocation(X, Y);
        }
    }

    public static GameView getGameView() {
        return gameView;
    }

    public static TitleView getTitleView() {
        return titleView;
    }

    public static GameClearView getGameClearView() {
        return gameClearView;
    }

    public static GameOverView getGameOverView() {
        return gameOverView;
    }

    public static RankingView getRankingView() {
        return rankingView;
    }
}
