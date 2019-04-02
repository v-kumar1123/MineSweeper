import java.util.ArrayList;

public class MinesweeperGame {
    Tile[][] board;
    int difficulty=0;

    ArrayList<Integer> spotsx=new ArrayList<Integer>();

    ArrayList<Integer> spotsy=new ArrayList<Integer>();

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        init();
    }
    int mineCount=0;
    public static final int EASY=0;
    public static final int MEDIUM=1;
    public static final int HARD=2;
    public static final int INSANE=3;
    public static final int EZPZ=4;
    public static final int PLAYING=0;
    public static final int WON=1;
    public static final int LOST=3;
    int notx=0;
    int noty=0;


    int gameStatus=PLAYING;

    public MinesweeperGame(int notx, int noty) {
        this.notx=notx;
        this.noty=noty;
        init();
    }

    public int getMineCount() {
        return mineCount;
    }

    public void init() {
        if(difficulty==0) {
            board=new Tile[10][10];
            mineCount=15;
        }
        else if(difficulty==1) {
            board=new Tile[15][15];
            mineCount=40;
        }
        else if(difficulty==2) {
            board=new Tile[20][20];
            mineCount=100;
        }
        else if(difficulty==INSANE) {
            board=new Tile[10][10];
            mineCount=97;
        }

        else if(difficulty==EZPZ) {
            board=new Tile[10][10];
            mineCount=1;
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

    }

    public Tile[][] getBoard() {
        return board;
    }

    public boolean checkSpotIsGood(int ys, int xs) {
        /*for(int x=0;x< spotsx.size();x++) {
            if((xs==spotsx.get(x)||xs==notx)||(ys==spotsy.get(x)||ys==noty)) {
                return false;
            }
        }
        spotsx.add(xs);
        spotsy.add(ys);
        return true;*/
        if(board[xs][ys].isMine()||(xs==notx&&ys==noty)) {
            return false;
        }
        return true;
    }

    public void addMines() {
        //TODO: 3/21/19: ADDMINES HAS A WHILE LOOP THAT NEVER STOPS
        int xs=0;
        int ys=0;

        xs=(int)(Math.random()*board.length);
        ys=(int)(Math.random()*board[0].length);
        if(difficulty==0) {
            for(int x=0;x<15;x++) {
                while(!checkSpotIsGood(ys,xs)) {
                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }


                board[xs][ys].setMine(true);
            }

            spotsx.clear();
            spotsy.clear();
        }
        else if(difficulty==INSANE) {
            System.out.println("HELLO AGAIN OBIWAN");
            for(int x=0;x<97;x++) {
                while(!checkSpotIsGood(ys,xs)) {
                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }


                board[xs][ys].setMine(true);
            }

            spotsx.clear();
            spotsy.clear();
        }
        else if(difficulty==EZPZ) {
            for(int x=0;x<1;x++) {
                while(!checkSpotIsGood(ys,xs)) {
                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }


                board[xs][ys].setMine(true);
            }

            spotsx.clear();
            spotsy.clear();
        }
        else if(difficulty==1) {
            for(int x=0;x<40;x++) {
                while(!checkSpotIsGood(ys,xs)) {
                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }

                board[xs][ys].setMine(true);
            }


            spotsx.clear();
            spotsy.clear();
        }
        else if(difficulty==2) {
            for(int x=0;x<100;x++) {
                while(!checkSpotIsGood(ys,xs)) {

                    xs=(int)(Math.random()*board.length);
                    ys=(int)(Math.random()*board[0].length);
                }

                board[xs][ys].setMine(true);
            }

            spotsx.clear();
            spotsy.clear();
        }
    }

    public void setNotx(int notx) {
        this.notx = notx;
    }

    public void setNoty(int noty) {
        this.noty = noty;
    }

    public int getFlagCount() {
        if(difficulty==EZPZ) {
            return 5;
        }
        if(difficulty==EASY) {
            return 15;
        }
        if(difficulty==MEDIUM) {
            return 40;
        }
        if(difficulty==HARD) {
            return 100;
        }
        if(difficulty==INSANE) {
            return 97;
        }
        return 0;
    }
    public void setTileValue(int x, int y) {
        if(x>0&&board[x-1][y].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x<board.length-1&&board[x+1][y].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(y>0&&board[x][y-1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(y<board.length-1&&board[x][y+1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x>0&&y>0&&board[x-1][y-1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x<board.length-1&&y<board[0].length-1&&board[x+1][y+1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x>0&&y<board[0].length-1&&board[x-1][y+1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
        if(x<board.length-1&&y>0&&board[x+1][y-1].isMine()) {
            board[x][y].setMinesAround(board[x][y].getMinesAround()+1);
        }
    }



    public void playGame() {

    }
}

