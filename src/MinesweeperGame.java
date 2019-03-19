import java.util.ArrayList;

public class MinesweeperGame {
    Tile[][] board;
    int difficulty=0;

    ArrayList<Integer> spotsx=new ArrayList<Integer>();

    ArrayList<Integer> spotsy=new ArrayList<Integer>();


    public static final int EASY=0;
    public static final int MEDIUM=1;
    public static final int HARD=2;
    public static final int PLAYING=0;
    public static final int WON=1;
    public static final int LOST=3;


    int gameStatus=PLAYING;

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

        for(int x=0;x<board.length;x++) {
            for (int y=0;y<board[0].length;y++) {
                board[x][y]=new Tile(0);
                board[x][y].setRevealed(false);
            }
        }

        addMines();

        for(int x=0;x<board.length;x++) {
            for(int y=0;y<board[0].length;y++) {
                if(!board[x][y].isMine()) {
                    setTileValue(x, y);
                }
            }
        }
    }
    public void firstClickIsMine(int x, int y) {
        for(int r=0;r<board.length;r++) {
            for(int c=0;c<board[0].length;c++) {

            }
        }
    }
    public boolean checkSpot(int ys, int xs) {
        for(int x=0;x< spotsx.size();x++) {
            if(xs==spotsx.get(x)) {
                return false;
            }
        }
        for(int x=0;x< spotsy.size();x++) {
            if(ys==spotsy.get(x)) {
                return false;
            }
        }
        spotsx.add(xs);
        spotsy.add(ys);

        return true;
    }

    public void addMines() {
        int xs=0;
        int ys=0;
        if(difficulty==0) {
            for(int x=0;x<15;x++) {
                while(!checkSpot(ys,xs)) {
                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }

                board[xs][ys].setMine(true);
            }
        }
        else if(difficulty==1) {
            for(int x=0;x<40;x++) {
                while(!checkSpot(ys,xs)) {
                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }

                board[xs][ys].setMine(true);
            }
        }
        else if(difficulty==2) {
            for(int x=0;x<100;x++) {
                while(!checkSpot(ys,xs)) {
                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }

                board[xs][ys].setMine(true);
            }
        }
    }
    public void setTileValue(int x, int y) {
        if(x>=0&&board[x-1][y].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x<board.length-1&&board[x+1][y].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(y>=0&&board[x][y-1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(y<board.length-1&&board[x][y+1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x>=0&&y>=0&&board[x-1][y-1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x<board.length-1&&y<board[0].length-1&&board[x+1][y+1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x>=0&&y<board[0].length&&board[x-1][y+1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x<board.length-1&&y>=0&&board[x+1][y-1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
    }

    public void playGame() {

    }
}

