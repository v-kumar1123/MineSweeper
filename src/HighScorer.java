public class HighScorer implements Comparable {
    int score=0;
    String difficulty="";
    String name="";
    public HighScorer(String name, int score, String difficulty) {
        this.name=name;
        this.score=score;
        this.difficulty=difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
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

    @Override
    public int compareTo(Object o) {
        return score-((HighScorer)o).getScore();
    }

    @Override
    public String toString() {
        return score +
                " " + difficulty + " " + name;
    }
}
