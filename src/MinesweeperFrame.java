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
            //flag=ImageIO.read(new File())
        } catch (IOException t) {
            t.printStackTrace();
        }catch (Exception e) {e.printStackTrace();}
        setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,getWidth(),getHeight());

        if(!firstClick) {
            paintFirstBlocks(g);
        }
    }

    public void paintFirstBlocks(Graphics g) {
        for(int r=0;r<10;r++) {
            for(int c=0;c<10;c++) {
                g.drawImage(unclicked,r*16+50,c*16+50,null);
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
            game = new MinesweeperGame(e.getX() / 16-50, e.getY() / 16-50);
            System.out.println("Not X: " + game.notx + " Not Y: " + game.noty);
            System.out.println("Hello I am not the recursive one");
            firstClick=true;
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
