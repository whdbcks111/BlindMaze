package maze.components;

import java.util.Date;

public class RankData implements Comparable<RankData> {

    int score;
    Date date;

    public RankData(int score, Date date) {
        this.score = score;
        this.date = date;
    }

    @Override
    public String toString() {
        return score + "점 (" + date.toLocaleString() + ")";
    }

    @Override
    public int compareTo(RankData o) {
        return o.score - this.score;
    }
}
