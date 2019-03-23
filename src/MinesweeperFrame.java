import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class MinesweeperFrame extends JFrame implements KeyListener, MouseListener {

    BufferedImage dead;
    boolean firstClick=false;
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
    MinesweeperGame game;


    public MinesweeperFrame() {
        super("k0904676, Period 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);
        addMouseListener(this);
        addKeyListener(this);
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
        setVisible(true);
    }

    public void paint(Graphics g) {

        if(!firstClick) {

            g.setColor(Color.GRAY);
            g.fillRect(0,0,getWidth(),getHeight());
            paintFirstBlocks(g);
        }
        else {
            for(int x=0;x<game.getBoard().length;x++) {
                for(int y=0;y<game.getBoard()[0].length;y++) {
                    if(game.getBoard()[x][y].isRevealed()) {
                        if(game.getBoard()[x][y].getMinesAround()==0) {
                            emptyClicked();
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
                            g.drawImage(mine,(x*16)+50,(y*16)+50,null);
                        }
                    }
                }
            }
        }
    }
    public void mineHasBeenClicked() {
        for(int x=0;x<game.getBoard().length;x++) {
            for(int y=0;y<game.getBoard()[0].length;y++) {
                if(game.getBoard()[x][y].isMine) {
                    game.getBoard()[x][y].setRevealed(true);
                    repaint();
                }
            }
        }
    }
    public void emptyClicked() {

    }

    public void paintFirstBlocks(Graphics g) {
        for(int r=0;r<10;r++) {
            for(int c=0;c<10;c++) {
                g.drawImage(unclicked,(r*16)+50,(c*16)+50,null);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
            //TODO: ADDMINES IS RECURSIVE AND IS INFINITE. STOP IT.
        if(!firstClick) {
            game = new MinesweeperGame((e.getX()-50) / 16, (e.getY()-50) / 16);
            System.out.println("Not X: " + game.notx + " Not Y: " + game.noty);
            System.out.println("Hello I am not the recursive one");

            game.getBoard()[(e.getX()-50) / 16][ (e.getY()-50) / 16].setRevealed(true);
            firstClick = true;
        }
        else {
            game.getBoard()[(e.getX()-50) / 16][ (e.getY()-50) / 16].setRevealed(true);
            repaint();
        }
            repaint();
            return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //TODO 3/21: LOOOK AT TULLY'S DOCS, IMPLEMENT THEM INTO YOUR PROGRAM. DON'T FOLLOW HIS COMPLETELY, BUT HE HAS THINGS THAT YOU DONT KNOW YET..
}
