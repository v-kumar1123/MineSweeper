public class HighScorer {
    int score=0;
    String name="";
    public HighScorer(String name, int score) {
        this.name=name;
        this.score=score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
