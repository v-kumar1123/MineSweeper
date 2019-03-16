public class Tile {
    public static final int BLANK=1;

    public static final int FLAGGED=2;

    public static final int QUESTIONED=3;

    boolean opened=false;

    int minesAround;

    public Tile(int minesAround) {
        this.minesAround=minesAround;
    }

    public void setMinesAround() {

    }
}
