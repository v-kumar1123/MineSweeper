import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MineSweeperPanel extends JPanel implements MouseListener, KeyListener, MouseMotionListener, Runnable {
    boolean clicked=false;
    public static final int UPS=3500;
    int timer=0;
    private long updatesDone=0;
    int mouseRow=-1;
    int mouseCol=-1;
    boolean rightPressed=false;
    boolean leftPressed=false;
    BufferedImage dead;
    boolean faceClicked=false;
    boolean gameOver=false;
    Graphics graphics;
    boolean firstClicked=false;
    BufferedImage oh;
    BufferedImage down;
    BufferedImage happy;
    BufferedImage happyDown;
    BufferedImage shades;
    BufferedImage digitNine;
    BufferedImage digitEight;
    BufferedImage digitSeven;
    BufferedImage digitSix;
    BufferedImage digitFive;
    BufferedImage digitFour;
    BufferedImage digitThree;
    BufferedImage digitHyphen;
    BufferedImage digitTwo;
    BufferedImage digitOne;
    BufferedImage digitZero;
    BufferedImage eight;
    BufferedImage seven;
    BufferedImage six;
    BufferedImage five;
    BufferedImage four;
    BufferedImage three;
    BufferedImage two;
    BufferedImage one;
    BufferedImage empty;
    BufferedImage unclicked;
    BufferedImage flag;
    BufferedImage question;
    BufferedImage mine;
    BufferedImage incorrectFlag;
    BufferedImage exploded;

    Thread t=new Thread(this);
    MinesweeperGame game;
    boolean hovering=false;
    int emptyX;
    int emptyY;
    int blockNo=10;

    public void setBlockNo(int blockNo) {
        this.blockNo = blockNo;
    }

    public MineSweeperPanel(int blockNo) {
        this.blockNo=blockNo;
        try {
            dead = ImageIO.read((new File("Images\\Dead.png")));
            unclicked= ImageIO.read((new File("Images\\Unclicked.png")));
            digitEight=ImageIO.read((new File("Images\\Digit_Eight.png")));
            empty=ImageIO.read((new File("Images\\Digit_Eight.png")));
            digitFive=ImageIO.read((new File("Images\\Digit_Five.png")));
            digitFour=ImageIO.read((new File("Images\\Digit_Four.png")));
            digitHyphen=ImageIO.read(new File("Images\\Digit_Hyphen.png"));
            digitNine=ImageIO.read(new File("Images\\Digit_Nine.png"));
            digitOne=ImageIO.read(new File("Images\\Digit_One.png"));
            digitTwo=ImageIO.read(new File("Images\\Digit_Two.png"));
            digitSeven=ImageIO.read(new File("Images\\Digit_Seven.png"));
            digitSix=ImageIO.read(new File("Images\\Digit_Six.png"));
            digitThree=ImageIO.read(new File("Images\\Digit_Three.png"));
            digitZero=ImageIO.read(new File("Images\\Digit_Zero.png"));
            down=ImageIO.read(new File("Images\\Down.png"));
            eight=ImageIO.read(new File("Images\\Eight.png"));
            empty=ImageIO.read(new File("Images\\Empty.png"));
            exploded=ImageIO.read(new File("Images\\Exploded.png"));
            five=ImageIO.read(new File("Images\\Five.png"));
            flag=ImageIO.read(new File("Images\\Flag.png"));
            four=ImageIO.read(new File("Images\\Four.png"));
            happy=ImageIO.read(new File("Images\\Happy.png"));
            happyDown=ImageIO.read(new File("Images\\Happy_Down.png"));
            incorrectFlag=ImageIO.read(new File("Images\\IncorrectFlag.png"));
            mine=ImageIO.read(new File("Images\\Mine.png"));
            oh=ImageIO.read(new File("Images\\Oh.png"));
            one=ImageIO.read(new File("Images\\One.png"));
            question=ImageIO.read(new File("Images\\Question.png"));
            seven=ImageIO.read(new File("Images\\Seven.png"));
            shades=ImageIO.read(new File("Images\\Shades.png"));
            six=ImageIO.read(new File("Images\\Six.png"));
            three=ImageIO.read(new File("Images\\Three.png"));
            two=ImageIO.read(new File("Images\\Two.png"));

        } catch (IOException t) {
            t.printStackTrace();
        }catch (Exception e) {e.printStackTrace();}

        addMouseListener(this);

        addMouseMotionListener(this);

        addKeyListener(this);

        //t.start();


    }






    public void paint(Graphics g) {
        graphics=g;

        for(int r=0;r<blockNo;r++) {
            for(int c=0;c<blockNo;c++) {
                g.drawImage(unclicked,(r*16)+50,(c*16)+50,null);
            }
        }

        if(!firstClicked) {
            g.setColor(Color.GRAY);
            g.fillRect(0,0,getWidth(),getHeight());
            paintFirstBlocks(g,10);
        }
        else if(!gameOver) {
            //System.out.println("GAME NOT OVER");
            for(int x=0;x<game.getBoard().length;x++) {
                for(int y=0;y<game.getBoard()[0].length;y++) {
                    if(!game.getBoard()[x][y].isRevealed()) {
                        //System.out.println("SPOT NOT REVEALED");
                        if(leftPressed) {
                            if(game!=null) {
                                System.out.println("I AM TRYIGN TO DRAW HERE - MOUSEROW:" + mouseRow + " mouseCol: " + mouseCol);

                                if(mouseRow*16+50>=50&&mouseRow*16+50<game.getBoard().length*16+50&&mouseCol*16+50>=50&&mouseCol*16+50<game.getBoard().length*16+50&&!game.getBoard()[mouseRow][mouseCol].isRevealed()) {
                                    g.drawImage(empty, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                                }
                                for (int r = 0; r < game.getBoard().length; r++) {
                                    for (int c = 0; c < game.getBoard()[0].length; c++) {
                                        if (r != mouseRow || c != mouseCol) {
                                            if(!game.getBoard()[r][c].isRevealed()) {
                                                if (r * 16 + 50 >= 50 && r * 16 + 50 < game.getBoard().length * 16 + 50 && c * 16 + 50 >= 50 && c * 16 + 50 < game.getBoard().length * 16 + 50) {
                                                    g.drawImage(unclicked, r * 16 + 50, c * 16 + 50, null);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                //System.out.println("HELLO");
                                for (int r = 0; r < game.getBoard().length; r++) {
                                    for (int c = 0; c < game.getBoard()[0].length; c++) {
                                        if (r != mouseRow || c != mouseCol) {
                                            g.drawImage(unclicked, r * 16 + 50, c * 16 + 50, null);
                                        }
                                    }
                                }
                            }
                        }
                        else if(rightPressed) {
                            System.out.println("RIGHT CLICKED");
                            if (game.getBoard()[mouseRow][mouseCol].getStatus() == Tile.BLANK) {
                                g.drawImage(unclicked, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                            } else if (game.getBoard()[mouseRow][mouseCol].getStatus() == Tile.FLAGGED) {
                                g.drawImage(flag, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                            } else if (game.getBoard()[mouseRow][mouseCol].getStatus() == Tile.QUESTIONED) {
                                g.drawImage(question, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                            }
                        }
                        continue;
                    }
                    else if(game.getBoard()[x][y].isRevealed()) {

                        //System.out.println("I AM REVEALED");
                        if(game.getBoard()[x][y].getMinesAround()==0) {
                            emptyClicked(x, y);
                            g.drawImage(empty,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].getMinesAround()==1) {
                            g.drawImage(one,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].getMinesAround()==2) {
                            g.drawImage(two,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].getMinesAround()==3) {
                            g.drawImage(three,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].getMinesAround()==4) {
                            g.drawImage(four,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].getMinesAround()==5) {
                            g.drawImage(five,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].getMinesAround()==6) {
                            g.drawImage(six,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].getMinesAround()==7) {
                            g.drawImage(seven,(x*16)+50,(y*16)+50,null);
                        }

                        if(game.getBoard()[x][y].getMinesAround()==8) {
                            g.drawImage(eight,(x*16)+50,(y*16)+50,null);
                        }
                        if(game.getBoard()[x][y].isMine) {
                            mineHasBeenClicked();
                            for(int r=0;r<game.getBoard().length;r++) {
                                for(int c=0;c<game.getBoard()[0].length;c++) {
                                    if(game.getBoard()[r][c].exploded) {
                                        //System.out.println("I HAVE EXPLODED THEEEEEE");
                                        g.drawImage(exploded,(r*16)+50,(c*16)+50,null);
                                    }
                                    else if(game.getBoard()[r][c].isMine) {
                                       //System.out.println("I HAVE MINED THEEE");
                                        g.drawImage(mine,(r*16)+50,(c*16)+50,null);
                                    }
                                }
                            }
                            gameOver=true;

                            break;
                        }
                        if(gameOver) {
                            break;
                        }
                    }

                    if(gameOver) {
                        break;
                    }
                }

                if(gameOver) {
                    break;
                }
            }
        }
        else if(gameOver) {
            if(faceClicked) {
                gameOver=false;
                repaint();
                faceClicked=false;
            }
        }
    }

    public void mineHasBeenClicked() {
        for(int x=0;x<game.getBoard().length;x++) {
            for(int y=0;y<game.getBoard()[0].length;y++) {
                if(game.getBoard()[x][y].isMine) {
                    game.getBoard()[x][y].setRevealed(true);
                }
            }
        }
    }
    public void emptyClicked(int x, int y) {
        if(x>0&&game.getBoard()[x-1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y].isRevealed()&&game.getBoard()[x-1][y].getMinesAround()==0) {
                game.getBoard()[x-1][y].setRevealed(true);
                emptyClicked(x-1,y);
            }
            else {
                game.getBoard()[x-1][y].setRevealed(true);
            }
        }
        if(x<game.getBoard().length-1&&game.getBoard()[x+1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y].isRevealed()&&game.getBoard()[x+1][y].getMinesAround()==0) {
                game.getBoard()[x + 1][y].setRevealed(true);
                emptyClicked(x+1,y);
            }
            else {
                game.getBoard()[x+1][y].setRevealed(true);
            }
        }
        if(y>0&&game.getBoard()[x][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y-1].isRevealed()&&game.getBoard()[x][y-1].getMinesAround()==0) {
                game.getBoard()[x][y-1].setRevealed(true);
                emptyClicked(x,y-1);
            }
            else {

                game.getBoard()[x][y-1].setRevealed(true);
            }
        }
        if(y<game.getBoard()[0].length-1&&game.getBoard()[x][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y+1].isRevealed()&&game.getBoard()[x][y+1].getMinesAround()==0) {
                game.getBoard()[x][y+1].setRevealed(true);
                emptyClicked(x,y+1);
            }
            else {
                game.getBoard()[x][y+1].setRevealed(true);
            }
        }
        if(x<game.getBoard().length-1&&y<game.getBoard()[0].length-1&&game.getBoard()[x+1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y+1].isRevealed()&&game.getBoard()[x+1][y+1].getMinesAround()==0) {
                game.getBoard()[x + 1][y+1].setRevealed(true);
                emptyClicked(x+1,y+1);
            }
            else {
                game.getBoard()[x+1][y+1].setRevealed(true);
            }
        }
        if(x>0&&y<game.getBoard()[0].length-1&&game.getBoard()[x-1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y+1].isRevealed()&&game.getBoard()[x-1][y+1].getMinesAround()==0) {
                game.getBoard()[x - 1][y+1].setRevealed(true);
                emptyClicked(x-1,y+1);
            }
            else {
                game.getBoard()[x-1][y+1].setRevealed(true);
            }
        }
        if(x>0&&y>0&&game.getBoard()[x-1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y-1].isRevealed()&&game.getBoard()[x-1][y-1].getMinesAround()==0) {
                game.getBoard()[x - 1][y-1].setRevealed(true);
                emptyClicked(x-1,y-1);
            }
            else {
                game.getBoard()[x-1][y-1].setRevealed(true);
            }
        }
        if(x<game.getBoard().length-1&&y>0&&game.getBoard()[x+1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y-1].isRevealed()&&game.getBoard()[x+1][y-1].getMinesAround()==0) {
                game.getBoard()[x + 1][y-1].setRevealed(true);
                emptyClicked(x+1,y-1);
            }
            else {
                game.getBoard()[x+1][y-1].setRevealed(true);
            }
        }
        repaint();
        return;
    }


    public void paintFirstBlocks(Graphics g, int difficulty) {
        startTimer();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,getWidth(),getHeight());
        for(int r=0;r<blockNo;r++) {
            for(int c=0;c<blockNo;c++) {
                g.drawImage(unclicked,(r*16)+50,(c*16)+50,null);
            }
        }

        for(int x=0;x<numberConverter(timer).size();x++) {
            g.drawImage(numberConverter(timer).get(x),100+13*x,0,null);
        }
        g.drawImage(happy, 25, 25, null);
        if(faceClicked) {
            g.drawImage(happyDown, 25, 25, null);
        }

        for (int r = 0; r < blockNo; r++) {
            for (int c = 0; c < blockNo; c++) {
                if (mouseCol!=-1&&mouseRow!=-1&&r == mouseRow && c == mouseCol) {
                    g.drawImage(empty, r * 16 + 50, c * 16 + 50, null);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int mines=0;
        if(e.getKeyChar()=='k'||e.getKeyChar()=='q') {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\tHELLO I AM SAAAD");
            for(int x=0;x<game.getBoard().length;x++) {
                for(int y=0;y<game.getBoard()[0].length;y++) {
                    if(game.getBoard()[x][y].isMine) {
                        mines++;
                    }
                }
            }
            System.out.println("\t\t\t\t\t\t\t\t\t\t\tNumber of Mines: "+mines);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            System.out.println("aaaa");
            leftPressed = true;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            System.out.println("bb");
            leftPressed = false;
        }
        if(e.getButton()==MouseEvent.BUTTON1) {
            if (!firstClicked) {
                if(!faceClicked) {
                    game = new MinesweeperGame((e.getX() - 50) / 16, (e.getY() - 50) / 16);
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setRevealed(true);
                }
                firstClicked = true;
            } else if (!gameOver) {
                game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setRevealed(true);
                if(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].isMine) {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].exploded=true;
                }
            }
            leftPressed=false;
        }
        else if (e.getButton()==MouseEvent.BUTTON3) {
            rightPressed=true;

            mouseCol=(e.getY() - 50) / 16;
            mouseRow=(e.getX() - 50) / 16;
            if(firstClicked&&!game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].isRevealed()) {
                if(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].getStatus()==game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].BLANK) {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setStatus(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].FLAGGED);
                }
                else if(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].getStatus()==game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].FLAGGED) {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setStatus(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].QUESTIONED);
                }
                else {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setStatus(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].BLANK);
                }
            }
        }
        repaint();
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getX()>=25&&e.getX()<=50&&e.getY()>=25&&e.getY()<=50) {
            faceClicked=true;
        }

        mouseCol=(e.getY() - 50) / 16;
        mouseRow=(e.getX() - 50) / 16;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void startTimer() {
        graphics.setColor(Color.CYAN);


        graphics.drawImage(digitZero,100,0,null);

        graphics.drawImage(digitTwo,123,0,null);
        if(timer<10) {
            if(timer==2) {
            }
        }
    }

    public ArrayList<BufferedImage> numberConverter(int numberConverter) {
        String number="";
        if(numberConverter<10) {
            number="0"+numberConverter+"";
        }
        else {
            number=numberConverter+"";
        }
        String finalNumber="";
        for(int x=0;x<number.length();x++) {
            finalNumber+=number.charAt(x)+" ";
        }
        ArrayList<BufferedImage> numberConvert=new ArrayList<BufferedImage>();
        String[]numberConv=finalNumber.split(" ");

        for(String s:numberConv) {
            numberConvert.add(workForConverter(s));
        }
        return numberConvert;
    }

    public BufferedImage workForConverter(String s) {
        if(Integer.parseInt(s)==0) {
            return digitZero;
        }
        if(Integer.parseInt(s)==1) {
            return digitOne;
        }if(Integer.parseInt(s)==2) {
            return digitTwo;
        }if(Integer.parseInt(s)==3) {
            return digitThree;
        }if(Integer.parseInt(s)==4) {
            return digitFour;
        }if(Integer.parseInt(s)==5) {
            return digitFive;
        }if(Integer.parseInt(s)==6) {
            return digitSix;
        }if(Integer.parseInt(s)==7) {
            return digitSeven;
        }if(Integer.parseInt(s)==8) {
            return digitEight;
        }if(Integer.parseInt(s)==9) {
            return digitNine;
        }
        return null;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        int sleepTime = 1000;
        while (true) {
            timer++;
            repaint();
            try {
                Thread.sleep(sleepTime);
            }catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}