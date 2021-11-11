package maze.view;

import maze.main.Main;
import maze.utils.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameOverView extends JPanel {

    private final JFrame frame;

    public GameOverView(JFrame frame, GameView gameView) throws IOException {
        this.frame = frame;
        setSize(gameView.getWidth(), gameView.getHeight());
        setLayout(null);

        ImageManager.saveImage("gameOver", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/GameOver.png")), getWidth(), getHeight()));

        JLabel title = new JLabel("Game Over", SwingConstants.CENTER);
        title.setFont(new Font("맑은고딕", Font.BOLD,50));
        title.setForeground(Color.RED);
        title.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() * 0.1), 400, 80);
        add(title);

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

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageManager.getImage("gameOver"), 0, 0, frame);
        setOpaque(false);
        super.paintComponent(g);
    }
}
