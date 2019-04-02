import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MineSweeperPanel extends JPanel implements MouseListener, KeyListener, MouseMotionListener, Runnable {
    boolean clicked=false;
    public static final int UPS=3500;
    boolean timeStart=false;
    int timer=0;
    private long updatesDone=0;
    int mouseRow=-1;
    int mouseCol=-1;
    boolean rightPressed=false;
    boolean leftPressed=false;
    BufferedImage dead;
    boolean faceClicked=false;
    boolean gameOver=false;
    boolean mineCount=false;
/*
    textArea = new JTextArea(5, 20);
    JScrollPane scrollPane = new JScrollPane(textArea);
textArea.setEditable(false);*/
    JTextArea textArea=new JTextArea(500,500);
    JScrollPane scrollPane=new JScrollPane(textArea);
    Graphics graphics;
    boolean firstClicked=false;
    ArrayList<HighScorer> easyScorers=new ArrayList<HighScorer>();
    ArrayList<HighScorer> hardScorers=new ArrayList<HighScorer>();
    ArrayList<HighScorer> mediumScorers=new ArrayList<HighScorer>();
    ArrayList<HighScorer> insaneScorers=new ArrayList<HighScorer>();
    ArrayList<HighScorer> EZPZScorers=new ArrayList<HighScorer>();
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
    PrintWriter writer;
    BufferedImage digitFour;
    BufferedImage digitThree;
    BufferedImage digitHyphen;
    BufferedImage digitTwo;
    BufferedImage digitOne;
    BufferedImage digitZero;
    String s;
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
    boolean playerWon=false;
    BufferedImage question;
    BufferedImage mine;
    BufferedImage incorrectFlag;
    BufferedImage exploded;
    int mines=15;
    Thread t=new Thread(this);
    JScrollPane areaScrollPane;
    MinesweeperGame game;
    int flagsLeft=15;
    String rules="";
    boolean hovering=false;
    int emptyX;
    File file = new File("C:\\Users\\Varun\\Desktop\\MineSweeper\\src\\Scores");
    int emptyY;
    int blockNo=10;
    int tempNo=0;

    public void setBlockNo(int blockNo) {
        if(blockNo%5==0) {
            this.blockNo = blockNo;
        }
        else if(blockNo==12) {
            tempNo=blockNo;
            this.blockNo=10;
        }
        else if(blockNo==13) {
            tempNo=blockNo;
            this.blockNo=10;
        }
    }

    public MineSweeperPanel(int blockNo) {
        rules=("Game Modes:\n" +
                "  -Easy 10 by 10 with 15 mines\n" +
                "  -Medium 15 by 15 with 40 mines\n" +
                "  -Hard 20 by 20 with 100 mines\n" +
                "Rules:\n" +
                "  -Winning:\n" +
                "    *You win when all the non-mine squares have been revealed.\n" +
                "  -Losing:\n" +
                "    *You lose when you reveal a mine.\n" +
                "  -Controls:\n" +
                "    *Right clicking an un-revealed square cycles the following marks:\n" +
                "      -Flag - Denotes the square as a mine\n" +
                "      -Question - Denotes the square as unknown\n" +
                "      -Un-marked - Removes all markings\n" +
                "    *Left clicking\n" +
                "      -Reveals the clicked square\n" +
                "    *Pressing the left button down, then holding the right button down and releasing the left button.\n" +
                "      -Works on revealed numbers\n" +
                "      -Reveals the nighboring locations\n" +
                "      -Only functions when there are enough locations marked with flags to correspond to the numbers\n" +
                "Symbols:\n" +
                "  -1 to 8: number of mines near by\n" +
                "  -Mine: unfound mine\n" +
                "  -Mine with x: Incorrect Flag\n" +
                "  -Red Mine: Exploded mine\n");


        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        if(blockNo%5==0) {
            this.blockNo = blockNo;
        }
        else if(blockNo==12) {
            tempNo=blockNo;
            this.blockNo=10;
        }
        else if(blockNo==13) {
            tempNo=blockNo;
            this.blockNo=10;
        }

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
    }


    boolean rulesAsked=false;

    public void setRulesAsked(boolean rulesAsked) {
        this.rulesAsked = rulesAsked;
    }

    public ArrayList<HighScorer> getEasyScorers() {
        return easyScorers;
    }

    public ArrayList<HighScorer> getHardScorers() {
        return hardScorers;
    }

    public ArrayList<HighScorer> getMediumScorers() {
        return mediumScorers;
    }

    public ArrayList<HighScorer> getInsaneScorers() {
        return insaneScorers;
    }

    public ArrayList<HighScorer> getEZPZScorers() {
        return EZPZScorers;
    }

    public void paint(Graphics g) {
        graphics=g;
        /*for(int r=0;r<blockNo;r++) {
            for(int c=0;c<blockNo;c++) {
                g.drawImage(unclicked,(r*16)+50,(c*16)+50,null);
            }
        }*/

        if(rulesAsked) {
            JOptionPane.showMessageDialog(null,rules);
        }

        if(!firstClicked) {
            g.setColor(Color.GRAY);
            g.fillRect(0,0,getWidth(),getHeight());
            paintFirstBlocks(g,blockNo);
            if(faceClicked) {
                System.out.println("OUCH MY FACE Before Game");
                g.drawImage(happyDown,150,0,null);
            }

        }
        else if(!gameOver) {
            System.out.println("GAME NOT OVER");
            g.setColor(Color.GRAY);
            g.fillRect(0,0,getWidth(),getHeight());
            if(faceClicked) {
                System.out.println("OUCH MY FACE After Game");
                g.drawImage(happyDown,150,0,null);
            }
            int tempCount=0;
            for(int x=0;x<game.getBoard().length;x++) {
                for(int y=0;y<game.getBoard()[0].length;y++) {
                    if(!game.getBoard()[x][y].isMine&&game.getBoard()[x][y].isRevealed()) {
                        tempCount++;
                    }
                }
            }

            System.out.println("\t\t\t\t\t\t\t\t\t\tTEMP COUNT "+tempCount+" Mine Count "+game.getMineCount());

            if((Math.pow((game.getBoard().length)+0.0,2.0)-tempCount)==game.getMineCount()) {
                gameOver=true;
                int tempTimer=timer;
                System.out.println("\t\t\t\t\t\tI AM ASKING YOU A QUESTION SIRRRR");
                String s = JOptionPane.showInputDialog("What is your name?");
                String difficulty="";
                if(game.difficulty==game.EASY) {
                    difficulty="Easy";
                }
                else if(game.difficulty==game.EZPZ) {
                    difficulty="EZPZ";
                }
                else if(game.difficulty==game.MEDIUM) {
                    difficulty="MEDIUM";
                }
                else if(game.difficulty==game.HARD) {
                    difficulty="HARD";
                }
                else if(game.difficulty==game.INSANE) {
                    difficulty="INSANE";
                }

                mineCount=false;
                System.out.println("YAY I WON!!!");

                HighScorer tempScorer=new HighScorer(s,tempTimer,difficulty);

                try {
                    FileWriter FileWriter =(new FileWriter(file,true));
                    BufferedWriter writer=new BufferedWriter(FileWriter);
                    /*for(HighScorer h:easyScorers) {
                        writer.println(h);
                    }
                    for(HighScorer h:EZPZScorers) {
                        writer.println(h);
                    }
                    for(HighScorer h:hardScorers) {
                        writer.println(h);
                    }
                    for(HighScorer h:insaneScorers) {
                        writer.println(h);
                    }
                    for(HighScorer h:mediumScorers) {
                        writer.println(h);
                    }*/
                    writer.write(tempScorer.toString()+"\n");
                    writer.close();
                } catch (FileNotFoundException u) {
                    u.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tHELLO ");
                for(int x=0;x<game.getBoard().length;x++) {
                    for (int y = 0; y < game.getBoard().length; y++) {
                        if (game.getBoard()[x][y].isRevealed()) {
                            if (game.getBoard()[x][y].getMinesAround() == 0) {
                                g.drawImage(empty, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 1) {
                                g.drawImage(one, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 2) {
                                g.drawImage(two, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 3) {
                                g.drawImage(three, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 4) {
                                g.drawImage(four, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 5) {
                                g.drawImage(five, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 6) {
                                g.drawImage(six, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 7) {
                                g.drawImage(seven, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            if (game.getBoard()[x][y].getMinesAround() == 8) {
                                g.drawImage(eight, (x * 16) + 50, (y * 16) + 50, null);
                            }
                        }
                        else {
                            if(game.getBoard()[x][y].getStatus()==game.getBoard()[x][y].BLANK||game.getBoard()[x][y].getStatus()==game.getBoard()[x][y].QUESTIONED) {
                                g.drawImage(unclicked, (x * 16) + 50, (y * 16) + 50, null);
                            }
                            else if(game.getBoard()[x][y].getStatus()==game.getBoard()[x][y].FLAGGED) {
                                g.drawImage(flag, (x * 16) + 50, (y * 16) + 50, null);
                            }
                        }
                    }
                }
                g.drawImage(shades,150,0,null);

                for(int x=0;x<numberConverter(timer).size();x++) {
                    g.drawImage(numberConverter(timer).get(x),70+13*x,0,null);
                }
                mineCount=true;
                for(int x=0;x<numberConverter(flagsLeft).size();x++) {
                    //System.out.println("ALLO ");
                    g.drawImage(numberConverter(flagsLeft).get(x),10+13*x,0,null);
                }
                if(faceClicked) {
                    g.drawImage(happyDown,150,0,null);
                    faceClicked=false;
                    gameOver=false;
                    paintFirstBlocks(g,blockNo);
                    return;
                }
                return;
            }
            for(int x=0;x<numberConverter(timer).size();x++) {
                g.drawImage(numberConverter(timer).get(x),70+13*x,0,null);
            }
            mineCount=true;
            for(int x=0;x<numberConverter(flagsLeft).size();x++) {
                //System.out.println("ALLO ");
                g.drawImage(numberConverter(flagsLeft).get(x),10+13*x,0,null);
            }
            mineCount=false;
            for(int x=0;x<game.getBoard().length;x++) {
                for (int y = 0; y < game.getBoard()[0].length; y++) {
                    if (!game.getBoard()[x][y].isRevealed()) {
                        //System.out.println("SPOT NOT REVEALED");
                        if (game.getBoard()[x][y].getStatus() != game.getBoard()[x][y].FLAGGED && game.getBoard()[x][y].getStatus() != game.getBoard()[x][y].QUESTIONED) {

                            g.drawImage(unclicked, x * 16 + 50, y * 16 + 50, null);
                        } else {
                            if(game.getBoard()[x][y].getStatus()==game.getBoard()[x][y].FLAGGED) {
                                //System.out.println("\t\t\t\t\t\t HELLO WORLD IA M BACCCKKk");
                                g.drawImage(flag,x*16+50,y*16+50,null);
                            }
                            else if(game.getBoard()[x][y].getStatus()==game.getBoard()[x][y].QUESTIONED) {

                                ///System.out.println("\t\t\t\t\t\t HELLO WORLD IA M BACCCKKk??? AMI ??");
                                g.drawImage(question,x*16+50,y*16+50,null);
                            }
                            //continue;
                        }
                        if (leftPressed) {
                            if (game != null) {
                                //System.out.println("I AM TRYIGN TO DRAW HERE - MOUSEROW:" + mouseRow + " mouseCol: " + mouseCol);

                                if (mouseRow * 16 + 50 >= 50 && mouseRow * 16 + 50 < game.getBoard().length * 16 + 50 && mouseCol * 16 + 50 >= 50 && mouseCol * 16 + 50 < game.getBoard().length * 16 + 50 && !game.getBoard()[mouseRow][mouseCol].isRevealed()) {
                                    g.drawImage(empty, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                                    g.drawImage(oh, 150, 0, null);
                                }
                                for (int r = 0; r < game.getBoard().length; r++) {
                                    for (int c = 0; c < game.getBoard()[0].length; c++) {
                                        if (r != mouseRow || c != mouseCol) {
                                            if (!game.getBoard()[r][c].isRevealed()) {
                                                if (r * 16 + 50 >= 50 && r * 16 + 50 < game.getBoard().length * 16 + 50 && c * 16 + 50 >= 50 && c * 16 + 50 < game.getBoard().length * 16 + 50 && game.getBoard()[x][y].getStatus() != game.getBoard()[x][y].FLAGGED && game.getBoard()[x][y].getStatus() != game.getBoard()[x][y].QUESTIONED) {
                                                    g.drawImage(unclicked, r * 16 + 50, c * 16 + 50, null);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                //System.out.println("HELLO");
                                for (int r = 0; r < blockNo; r++) {
                                    for (int c = 0; c < blockNo; c++) {
                                        if (r != mouseRow || c != mouseCol) {
                                            g.drawImage(unclicked, r * 16 + 50, c * 16 + 50, null);
                                        }
                                    }
                                }
                            }
                        } else if (rightPressed) {
                            //System.out.println("RIGHT CLICKED");
                            if (!game.getBoard()[mouseRow][mouseCol].isRevealed()) {
                                if (game.getBoard()[mouseRow][mouseCol].getStatus() == Tile.BLANK) {
                                    g.drawImage(unclicked, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                                } else if (game.getBoard()[mouseRow][mouseCol].getStatus() == Tile.FLAGGED) {
                                    g.drawImage(flag, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                                } else if (game.getBoard()[mouseRow][mouseCol].getStatus() == Tile.QUESTIONED) {
                                    g.drawImage(question, (mouseRow * 16) + 50, (mouseCol * 16) + 50, null);
                                }
                            }
                            rightPressed = false;
                        }
                        continue;
                    } else if (game.getBoard()[x][y].isRevealed()) {

                        g.drawImage(happy, 150, 0, null);
                        //System.out.println("I AM REVEALED");


                        if (game.getBoard()[x][y].getStatus() == game.getBoard()[x][y].FLAGGED || game.getBoard()[x][y].getStatus() == game.getBoard()[x][y].QUESTIONED) {
                            //System.out.println("\t\t\t\t\t\t\tAh. I CAN SEe THER erjk welshfj ");
                        }

                        else if(game.getBoard()[x][y].isMine) {
                            g.drawImage(dead, 150, 0, null);
                            mineHasBeenClicked(g);
                            for (int r = 0; r < game.getBoard().length; r++) {
                                for (int c = 0; c < game.getBoard()[0].length; c++) {
                                    if (game.getBoard()[r][c].exploded) {
                                        ///System.out.println("I HAVE EXPLODED THEEEEEE");
                                        g.drawImage(exploded, (r * 16) + 50, (c * 16) + 50, null);
                                    }
                                    /*else if(game.getBoard()[r][c].isMine) {
                                        System.out.println("I HAVE MINED THEEE");
                                        g.drawImage(mine,(r*16)+50,(c*16)+50,null);
                                    }
                                    else if(!game.getBoard()[r][c].isRevealed()) {
                                        g.drawImage(unclicked,(r*16)+50,(c*16)+50,null);
                                    }
                                    else if(game.getBoard()[r][c].isRevealed()) {
                                        if(game.getBoard()[r][c].getMinesAround()==0) {
                                            g.drawImage(empty,(r*16)+50,(c*16)+50,null);
                                        }
                                        if(game.getBoard()[r][c].getMinesAround()==1) {
                                            g.drawImage(one,(r*16)+50,(c*16)+50,null);
                                        }
                                        if(game.getBoard()[r][c].getMinesAround()==2) {
                                            g.drawImage(two,(r*16)+50,(c*16)+50,null);
                                        }
                                        if(game.getBoard()[r][c].getMinesAround()==3) {
                                            g.drawImage(three,(r*16)+50,(c*16)+50,null);
                                        }
                                        if(game.getBoard()[r][c].getMinesAround()==4) {
                                            g.drawImage(four,(r*16)+50,(c*16)+50,null);
                                        }
                                        if(game.getBoard()[r][c].getMinesAround()==5) {
                                            g.drawImage(five,(r*16)+50,(c*16)+50,null);
                                        }
                                        if(game.getBoard()[r][c].getMinesAround()==6) {
                                            g.drawImage(six,(r*16)+50,(c*16)+50,null);
                                        }
                                        if(game.getBoard()[r][c].getMinesAround()==7) {
                                            g.drawImage(seven,(r*16)+50,(c*16)+50,null);
                                        }

                                        if(game.getBoard()[r][c].getMinesAround()==8) {
                                            g.drawImage(eight,(r*16)+50,(c*16)+50,null);
                                        }
                                    }*/
                                }
                            }
                            gameOver=true;
                            break;
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 0) {
                            emptyClicked(x, y);
                            g.drawImage(empty, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 1) {
                            g.drawImage(one, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 2) {
                            g.drawImage(two, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 3) {
                            g.drawImage(three, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 4) {
                            g.drawImage(four, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 5) {
                            g.drawImage(five, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 6) {
                            g.drawImage(six, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if (game.getBoard()[x][y].getMinesAround() == 7) {
                            g.drawImage(seven, (x * 16) + 50, (y * 16) + 50, null);
                        }

                        else if (game.getBoard()[x][y].getMinesAround() == 8) {
                            g.drawImage(eight, (x * 16) + 50, (y * 16) + 50, null);
                        }
                    }
                }
            }
        }
        else if(gameOver) {

            if(faceClicked) {
                g.drawImage(happyDown,150,0,null);
                faceClicked=false;
                gameOver=false;
                paintFirstBlocks(g,blockNo);
                return;
            }
            else {
            }
            gameOver=false;
            return;
        }
        return;
    }

    public void mineHasBeenClicked(Graphics g) {


        for(int x=0;x<blockNo;x++) {
            for (int y = 0; y < blockNo; y++) {
                g.drawImage(unclicked,x*16+50,y*16+50,null);
            }
        }
        //System.out.println("mineClicked");
        for(int x=0;x<blockNo;x++) {
            for (int y = 0; y < blockNo; y++) {
                if (game.getBoard()[x][y].isMine) {
                    game.getBoard()[x][y].setRevealed(true);
                    g.drawImage(mine,x*16+50,y*16+50,null);
                }
                else if(game.getBoard()[x][y].isRevealed()) {
                    if(game.getBoard()[x][y].getMinesAround()==0) {
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
                }
            }
        }
    }
    public void emptyClicked(int x, int y) {
        //System.out.println("emptyClicked");
        if(x>0&&game.getBoard()[x-1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y].isRevealed()&&game.getBoard()[x-1][y].getMinesAround()==0) {
                game.getBoard()[x-1][y].setRevealed(true);
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
        g.drawImage(happy,150,0,null);
        timer=0;
        for(int r=0;r<blockNo;r++) {
            for(int c=0;c<blockNo;c++) {
                g.drawImage(unclicked,(r*16)+50,(c*16)+50,null);
            }
        }

        for(int x=0;x<numberConverter(timer).size();x++) {
            g.drawImage(numberConverter(timer).get(x),70+13*x,0,null);
        }

        mineCount=true;
        for(int y=0;y<numberConverter(flagsLeft).size();y++) {
            g.drawImage(numberConverter(flagsLeft).get(y),10+13*y,0,null);
        }
        mineCount=false;
        g.drawImage(happy,150,0,null);
        for (int r = 0; r < blockNo; r++) {
            for (int c = 0; c < blockNo; c++) {
                if (mouseCol!=-1&&mouseRow!=-1&&r == mouseRow && c == mouseCol) {
                    if(leftPressed) {
                        g.drawImage(empty, r * 16 + 50, c * 16 + 50, null);
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyChar()=='g') {
            int mineCount = 0;
            for (int x = 0; x < game.getBoard().length; x++) {
                for (int y = 0; y < game.getBoard()[0].length; y++) {
                    if (game.getBoard()[x][y].isMine()) {
                        mineCount++;
                    }
                }
            }

            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tHELLO. I HAVE " + mineCount + " MINES!!!");
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
            leftPressed = true;
            if(e.getX()>=150&&e.getX()<=175&&e.getY()>=0&&e.getY()<=25) {
                game.init();
                System.out.println("OUCH MY FACE IN MOUSEPRESSED");
                faceClicked=true;
                repaint();
            }
        }
        if(e.getButton()==MouseEvent.BUTTON2) {
            int tempRow=(e.getX()-50)/16;
            int tempCol=(e.getY()-50)/16;

            if(game.getBoard()[tempRow][tempCol].isRevealed()) {
                if(checkFlagCountSameAsMinesAround(game.getBoard()[tempRow][tempCol].minesAround,tempRow,tempCol)) {
                    massDraw(tempRow,tempCol);
                }
            }
        }
    }
    public void massDraw(int x, int y) {
        if(x>0&&game.getBoard()[x-1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y].isRevealed()&&game.getBoard()[x-1][y].getStatus()==2) {
                getGraphics().drawImage(empty,(x-1)*16+50,y*16+50,null);
            }
        }
        if(x<game.getBoard().length-1&&game.getBoard()[x+1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y].isRevealed()&&game.getBoard()[x+1][y].getStatus()==2) {
                getGraphics().drawImage(empty,(x+1)*16+50,y*16+50,null);
            }
        }
        if(y>0&&game.getBoard()[x][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y-1].isRevealed()&&game.getBoard()[x][y-1].getStatus()==2) {
                getGraphics().drawImage(empty,(x)*16+50,(y-1)*16+50,null);
            }
        }
        if(y<game.getBoard()[0].length-1&&game.getBoard()[x][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y+1].isRevealed()&&game.getBoard()[x][y+1].getStatus()==2) {
                getGraphics().drawImage(empty,(x)*16+50,(y+1)*16+50,null);
            }
        }
        if(x<game.getBoard().length-1&&y<game.getBoard()[0].length-1&&game.getBoard()[x+1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y+1].isRevealed()&&game.getBoard()[x+1][y+1].getStatus()==2) {
                getGraphics().drawImage(empty,(x+1)*16+50,(y+1)*16+50,null);
            }
        }
        if(x>0&&y<game.getBoard()[0].length-1&&game.getBoard()[x-1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y+1].isRevealed()&&game.getBoard()[x-1][y+1].getStatus()==2) {
                getGraphics().drawImage(empty,(x-1)*16+50,(y+1)*16+50,null);
            }
        }
        if(x>0&&y>0&&game.getBoard()[x-1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y-1].isRevealed()&&game.getBoard()[x-1][y-1].getStatus()==2) {
                getGraphics().drawImage(empty,(x-1)*16+50,(y-1)*16+50,null);
            }
        }
        if(x<game.getBoard().length-1&&y>0&&game.getBoard()[x+1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y-1].isRevealed()&&game.getBoard()[x+1][y-1].getStatus()==2) {
                getGraphics().drawImage(empty,(x+1)*16+50,(y-1)*16+50,null);
            }
        }
    }
    public boolean checkFlagCountSameAsMinesAround(int minesAround,int x,int y) {
        int tempCount=0;

        if(x>0&&game.getBoard()[x-1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y].isRevealed()&&game.getBoard()[x-1][y].getStatus()==2) {
                tempCount++;
            }
        }
        if(x<game.getBoard().length-1&&game.getBoard()[x+1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y].isRevealed()&&game.getBoard()[x+1][y].getStatus()==2) {
                tempCount++;
            }
        }
        if(y>0&&game.getBoard()[x][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y-1].isRevealed()&&game.getBoard()[x][y-1].getStatus()==2) {
                tempCount++;
            }
        }
        if(y<game.getBoard()[0].length-1&&game.getBoard()[x][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y+1].isRevealed()&&game.getBoard()[x][y+1].getStatus()==2) {
                tempCount++;
            }
        }
        if(x<game.getBoard().length-1&&y<game.getBoard()[0].length-1&&game.getBoard()[x+1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y+1].isRevealed()&&game.getBoard()[x+1][y+1].getStatus()==2) {
                tempCount++;
            }
        }
        if(x>0&&y<game.getBoard()[0].length-1&&game.getBoard()[x-1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y+1].isRevealed()&&game.getBoard()[x-1][y+1].getStatus()==2) {
                tempCount++;
            }
        }
        if(x>0&&y>0&&game.getBoard()[x-1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y-1].isRevealed()&&game.getBoard()[x-1][y-1].getStatus()==2) {
                tempCount++;
            }
        }
        if(x<game.getBoard().length-1&&y>0&&game.getBoard()[x+1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y-1].isRevealed()&&game.getBoard()[x+1][y-1].getStatus()==2) {
                tempCount++;
            }
        }
        if(tempCount==minesAround) {
            return true;
        }
        else {
            return false;
        }
    }
    public void massReveal(int x, int y) {
        if(x>0&&game.getBoard()[x-1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y].isRevealed()&&game.getBoard()[x-1][y].getStatus()==2) {
                game.getBoard()[x-1][y].setRevealed(true);game.getBoard()[x-1][y].setStatus(1);
            }
        }
        if(x<game.getBoard().length-1&&game.getBoard()[x+1][y].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y].isRevealed()&&game.getBoard()[x+1][y].getStatus()==2) {
                game.getBoard()[x+1][y].setRevealed(true);game.getBoard()[x+1][y].setStatus(1);
            }
        }
        if(y>0&&game.getBoard()[x][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y-1].isRevealed()&&game.getBoard()[x][y-1].getStatus()==2) {
                game.getBoard()[x][y-1].setRevealed(true);game.getBoard()[x][y-1].setStatus(1);
            }
        }
        if(y<game.getBoard()[0].length-1&&game.getBoard()[x][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x][y+1].isRevealed()&&game.getBoard()[x][y+1].getStatus()==2) {
                game.getBoard()[x][y+1].setRevealed(true);game.getBoard()[x][y+1].setStatus(1);
            }
        }
        if(x<game.getBoard().length-1&&y<game.getBoard()[0].length-1&&game.getBoard()[x+1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y+1].isRevealed()&&game.getBoard()[x+1][y+1].getStatus()==2) {
                game.getBoard()[x+1][y+1].setRevealed(true);game.getBoard()[x+1][y+1].setStatus(1);
            }
        }
        if(x>0&&y<game.getBoard()[0].length-1&&game.getBoard()[x-1][y+1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y+1].isRevealed()&&game.getBoard()[x-1][y+1].getStatus()==2) {
                game.getBoard()[x-1][y+1].setRevealed(true);game.getBoard()[x-1][y+1].setStatus(1);
            }
        }
        if(x>0&&y>0&&game.getBoard()[x-1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x-1][y-1].isRevealed()&&game.getBoard()[x-1][y-1].getStatus()==2) {
                game.getBoard()[x-1][y-1].setRevealed(true); game.getBoard()[x-1][y-1].setStatus(1);
            }
        }
        if(x<game.getBoard().length-1&&y>0&&game.getBoard()[x+1][y-1].getMinesAround()>=0) {
            if(!game.getBoard()[x+1][y-1].isRevealed()&&game.getBoard()[x+1][y-1].getStatus()==2) {
                game.getBoard()[x+1][y-1].setRevealed(true); game.getBoard()[x+1][y-1].setStatus(1);
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON2) {
            int tempRow=(e.getX()-50)/16;
            int tempCol=(e.getY()-50)/16;

            if(game.getBoard()[tempRow][tempCol].isRevealed()) {
                if(checkFlagCountSameAsMinesAround(game.getBoard()[tempRow][tempCol].minesAround,tempRow,tempCol)) {
                    massReveal(tempRow,tempCol);
                    repaint();
                }
            }
        }

        if(e.getButton()==MouseEvent.BUTTON1) {
            leftPressed=false;
            if (!firstClicked&&!gameOver) {
                System.out.println("I SHOULDN'T BE HERE");
                if(!faceClicked&&e.getX()>50&&e.getY()>50&&e.getX()<blockNo*16+50&&e.getY()<blockNo*16+50) {
                    game = new MinesweeperGame((e.getX() - 50) / 16, (e.getY() - 50) / 16);
                    if(tempNo==12) {
                        game.setDifficulty(game.INSANE);
                    }
                    else if(tempNo==13) {
                        game.setDifficulty(game.EZPZ);
                    }
                    else if(blockNo==10) {
                        game.setDifficulty(game.EASY);
                    }
                    else if(blockNo==15) {
                        game.setDifficulty(game.MEDIUM);
                    }
                    else if(blockNo==20) {
                        System.out.println("\t\t\t\t\t\t\tGAME IS NOW HAAARD");
                        game.setDifficulty(game.HARD);
                    }
                    flagsLeft=game.getFlagCount();
                    game.setNotx((e.getX() - 50) / 16);
                    game.setNoty((e.getY() - 50) / 16);
                    game.init();
                        for(int x=0;x<game.getBoard().length;x++) {
                            for(int y=0;y<game.getBoard()[0].length;y++) {
                                if(game.getBoard()[x][y].isMine) {
                                    mines++;
                                }
                            }
                        }
                    t.start();
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setRevealed(true);
                    firstClicked=true;
                }
                else {

                }
            } else if (!gameOver) {
                if(e.getX()>50&&e.getY()>50&&e.getX()<game.getBoard().length*16+50&&e.getY()<game.getBoard().length*16+50) {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setRevealed(true);
                    if (game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].isMine) {
                        game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].exploded = true;
                    }
                }
            }

            faceClicked=false;
            leftPressed=false;
        }
        else if (e.getButton()==MouseEvent.BUTTON3) {
            rightPressed=true;

            mouseCol=(e.getY() - 50) / 16;
            mouseRow=(e.getX() - 50) / 16;
            if(firstClicked&&!game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].isRevealed()) {
                if(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].getStatus()==game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].BLANK) {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setStatus(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].FLAGGED);
                    flagsLeft--;
                }
                else if(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].getStatus()==game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].FLAGGED) {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setStatus(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].QUESTIONED);
                }
                else {
                    game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setStatus(game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].BLANK);
                    flagsLeft++;
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

        mouseCol=(e.getY() - 50) / 16;
        mouseRow=(e.getX() - 50) / 16;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void startTimer() {
        timeStart=true;
        t.run();
    }

    public ArrayList<BufferedImage> numberConverter(int numberConverter) {
        String number="";
        if(mineCount) {
            if(numberConverter==0) {
                number="0000";
            }
            else if(numberConverter<10&&numberConverter>0) {
                number="000"+numberConverter+"";
            }
            else if(numberConverter<100&&numberConverter>0) {
                number="00"+numberConverter+"";
            }
            else if(numberConverter>=100) {
                number="0"+numberConverter+"";
            }
            else if(numberConverter>-10) {
                String tempNo=numberConverter+"";
                String tempNOw="";
                for(int x=0;x<tempNo.length();x++) {
                    if(tempNo.charAt(x)!='-') {
                        tempNOw+=tempNo.charAt(x)+"";
                    }
                }
                number="-00"+tempNOw;
            }
            else if(numberConverter>-100){

                String tempNo=numberConverter+"";
                String tempNOw="";
                for(int x=0;x<tempNo.length();x++) {
                    if(tempNo.charAt(x)!='-') {
                        tempNOw+=tempNo.charAt(x)+"";
                    }
                }
                number = "-0"+tempNOw + "";
            }
        }
        else if(numberConverter<10) {
            number="000"+numberConverter+"";
        }
        else if(numberConverter<100) {
            number="00"+numberConverter+"";
        }

        else if(numberConverter<1000) {
            number="0"+numberConverter+"";
        }
        else if(numberConverter>=1000) {
            number=numberConverter+"";
        }
        String finalNumber="";
        for(int x=0;x<number.length();x++) {
            finalNumber+=number.charAt(x)+" ";
        }
        ArrayList<BufferedImage> numberConvert=new ArrayList<BufferedImage>();
        String[]numberConv=finalNumber.split(" ");

        for(String s:numberConv) {
            System.out.println(s);
            numberConvert.add(workForConverter(s));
        }
        return numberConvert;
    }

    public BufferedImage workForConverter(String s) {
        if(s.equals("-")) {
            return digitHyphen;
        }
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
        if(true) {
            long startTime = System.nanoTime();
            int sleepTime = 1000;
            while (true) {
                timer++;
                if(!gameOver) {
                    repaint();
                }
                else {
                    try {
                        /*EZPZScorers.clear();
                        hardScorers.clear();
                        mediumScorers.clear();
                        easyScorers.clear();
                        insaneScorers.clear();*/
                        Scanner reader = new Scanner(file);
                        while (reader.hasNextLine()) {
                            boolean b=false;
                            String line=reader.nextLine();
                            String[] readerLine = line.split(" ");
                            for(HighScorer h:EZPZScorers) {
                                if(h.getScore()==Integer.parseInt(readerLine[0])&&h.getName().equals(readerLine[2])&&h.getDifficulty().equals("EZPZ")) {
                                    b=true;
                                }
                            }
                            if(b) {
                                continue;
                            }
                            if (line!= null) {
                                if (line.contains("EZPZ")) {
                                    EZPZScorers.add(new HighScorer(readerLine[2], Integer.parseInt(readerLine[0]), "EZPZ"));
                                } else if (line.contains("Hard")) {
                                    hardScorers.add(new HighScorer(readerLine[2], Integer.parseInt(readerLine[0]), "Hard"));
                                } else if (line.contains("Medium")) {
                                    mediumScorers.add(new HighScorer(readerLine[2], Integer.parseInt(readerLine[0]), "Medium"));
                                } else if (line.contains("Easy")) {
                                    easyScorers.add(new HighScorer(readerLine[2], Integer.parseInt(readerLine[0]), "Easy"));
                                } else if (line.contains("Insane")) {
                                    insaneScorers.add(new HighScorer(readerLine[2], Integer.parseInt(readerLine[0]), "Insane"));
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println(EZPZScorers);
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
