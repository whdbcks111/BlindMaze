package maze.view;

import maze.main.Main;
import maze.utils.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TitleView extends JPanel {

    private final JFrame frame;

    public TitleView(JFrame frame, GameView gameView) throws IOException {
        this.frame = frame;
        setSize(gameView.getWidth(), gameView.getHeight());
        setLayout(null);

        ImageManager.saveImage("background", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/Background.png")), getWidth(), getHeight()));

        JLabel title = new JLabel("Blind Maze",SwingConstants.CENTER);
        title.setFont(new Font("맑은고딕", Font.BOLD,70));
        title.setForeground(Color.WHITE);
        title.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() * 0.1), 400, 80);
        add(title);

        JButton start = new JButton("Start");
        start.setFont(new Font("맑은고딕", Font.BOLD,50));
        start.setFocusPainted(false);
        start.addActionListener(e -> {
            this.setVisible(false);
            gameView.resetGame();
            gameView.setVisible(true);
        });
        start.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        start.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        start.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() - 150), 400, 60);
        add(start);

        JButton rank = new JButton("Rank");
        rank.setFont(new Font("맑은고딕", Font.BOLD,50));
        rank.setFocusPainted(false);
        rank.addActionListener(e -> {
            this.setVisible(false);
            Main.getRankingView().updateRankText();
            Main.getRankingView().setVisible(true);
        });
        rank.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rank.setBackground(Color.BLACK);
        rank.setForeground(Color.WHITE);
        rank.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() - 150 + 70), 400, 60);
        add(rank);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageManager.getImage("background"), 0, 0, frame);
        setOpaque(false);
        super.paintComponent(g);
    }
}
