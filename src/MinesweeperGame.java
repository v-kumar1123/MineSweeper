public class MinesweeperGame {
    Tile[][] board;
    int difficulty=0;

    public static final int EASY=0;
    public static final int MEDIUM=1;
    public static final int HARD=2;

    public MinesweeperGame() {
        init();
    }

    public void init() {
        if(difficulty==0) {
            board=new Tile[10][10];
        }
        else if(difficulty==1) {
            board=new Tile[15][15];
        }

        else if(difficulty==2) {
            board=new Tile[20][20];
        }
    }
}

