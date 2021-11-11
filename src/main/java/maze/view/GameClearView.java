package maze.view;

import maze.main.Main;
import maze.utils.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameClearView extends JPanel {

    private final JFrame frame;
    private final JLabel score;

    public GameClearView(JFrame frame, GameView gameView) throws IOException {
        this.frame = frame;
        setSize(gameView.getWidth(), gameView.getHeight());
        setLayout(null);

        ImageManager.saveImage("gameClear", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/GameClear.png")), getWidth(), getHeight()));

        JLabel title = new JLabel("Game Clear!", SwingConstants.CENTER);
        title.setFont(new Font("맑은고딕", Font.BOLD,50));
        title.setForeground(Color.WHITE);
        title.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() * 0.1), 400, 80);
        add(title);

        score = new JLabel("", SwingConstants.CENTER);
        score.setFont(new Font("맑은고딕", Font.BOLD,30));
        score.setForeground(Color.WHITE);
        score.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() * 0.2), 400, 80);
        add(score);

        JButton gotoTitle = new JButton("타이틀로 돌아가기");
        gotoTitle.setFont(new Font("맑은고딕", Font.BOLD,45));
        gotoTitle.setFocusPainted(false);
        gotoTitle.addActionListener(e -> {
            this.setVisible(false);
            Main.getTitleView().setVisible(true);
        });
        gotoTitle.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gotoTitle.setBackground(Color.BLACK);
        gotoTitle.setForeground(Color.WHITE);
        gotoTitle.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() * 0.8), 400, 80);
        add(gotoTitle);
    }

    public void setScore() {
        score.setText("점수 : " + Main.getGameView().getPlayer().getScore());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageManager.getImage("gameClear"), 0, 0, frame);
        setOpaque(false);
        super.paintComponent(g);
    }
}
