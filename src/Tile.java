public class Tile {
    public static final int BLANK=1;

    public static final int FLAGGED=2;

    public static final int QUESTIONED=3;

    boolean revealed=false;

    boolean isMine=false;

    boolean exploded=false;

    int minesAround;

    int status=1;


    public Tile(int minesAround) {
        this.minesAround=minesAround;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround=minesAround;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static int getBLANK() {
        return BLANK;
    }

    public static int getFLAGGED() {
        return FLAGGED;
    }

    public static int getQUESTIONED() {
        return QUESTIONED;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public int getStatus() {
        return status;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

}
