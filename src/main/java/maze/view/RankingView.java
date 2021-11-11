package maze.view;

import maze.components.RankData;
import maze.main.Main;
import maze.utils.FileStream;
import maze.utils.ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class RankingView extends JPanel {

    private final JFrame frame;
    private final JLabel rank;

    public RankingView(JFrame frame, GameView gameView) throws IOException {
        this.frame = frame;
        setSize(gameView.getWidth(), gameView.getHeight());
        setLayout(null);

        ImageManager.saveImage("rank", ImageManager.resize(ImageIO.read(getClass().getResourceAsStream("/Ranking.png")), getWidth(), getHeight()));

        JLabel title = new JLabel("RANK",SwingConstants.CENTER);
        title.setFont(new Font("맑은고딕", Font.BOLD,70));
        title.setForeground(Color.WHITE);
        title.setBounds(gameView.getWidth() / 2 - 200, (int) (gameView.getHeight() * 0.1), 400, 80);
        add(title);

        rank = new JLabel("", SwingConstants.CENTER);
        rank.setFont(new Font("맑은고딕", Font.BOLD,20));
        rank.setForeground(Color.WHITE);
        rank.setBounds((int) (gameView.getWidth() * 0.45) - 50, (int) (gameView.getHeight() * 0.1 + 90), (int) (gameView.getWidth() * 0.55), (int) (gameView.getHeight() * 0.9 - 200));
        add(rank);

        updateRankText();

        JButton resetRank = new JButton("랭킹 리셋");
        resetRank.setFont(new Font("맑은고딕", Font.BOLD,40));
        resetRank.setFocusPainted(false);
        resetRank.addActionListener(e -> {
            FileStream fileStream = new FileStream(new File(System.getenv("APPDATA"), "blindmaze/scores.txt").getAbsolutePath());
            fileStream.write("");
            updateRankText();
        });
        resetRank.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        resetRank.setBackground(Color.BLACK);
        resetRank.setForeground(Color.WHITE);
        resetRank.setBounds(gameView.getWidth() / 2 - 200, gameView.getHeight() - 160, 400, 60);
        add(resetRank);

        JButton gotoTitle = new JButton("타이틀로 돌아가기");
        gotoTitle.setFont(new Font("맑은고딕", Font.BOLD,40));
        gotoTitle.setFocusPainted(false);
        gotoTitle.addActionListener(e -> {
            this.setVisible(false);
            Main.getTitleView().setVisible(true);
        });
        gotoTitle.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        gotoTitle.setBackground(Color.BLACK);
        gotoTitle.setForeground(Color.WHITE);
        gotoTitle.setBounds(gameView.getWidth() / 2 - 200, gameView.getHeight() - 90, 400, 60);
        add(gotoTitle);

    }

    public void updateRankText() {
        FileStream fileStream = new FileStream(new File(System.getenv("APPDATA"), "blindmaze/scores.txt").getAbsolutePath());
        String[] formattedScores = fileStream.read().split("\n");
        StringBuilder rankStr = new StringBuilder("<html>");
        List<RankData> rankDataList = new ArrayList<>();
        for(String format : formattedScores) {
            if(!format.matches("^\\d+ \\d+$")) continue;
            String[] splits = format.split(" ");
            int score = Integer.parseInt(splits[0]);
            long time = Long.parseLong(splits[1]);
            Date date = new Date(time);
            rankDataList.add(new RankData(score, date));
        }
        Collections.sort(rankDataList);
        int rankNum = 0;
        for (RankData rankData : rankDataList) {
            rankNum++;
            rankStr.append("<span style=\"color: #ffffff;\">").append(rankNum).append("위 &gt; </span>")
                    .append("<span style=\"color: #999999;\">")
                    .append(rankData.toString()).append("</span><br>");
            if(rankNum >= 10) break;
        }
        rankStr.append("</html>");
        rank.setText(rankStr.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ImageManager.getImage("rank"), 0, 0, frame);
        setOpaque(false);
        super.paintComponent(g);
    }
}
