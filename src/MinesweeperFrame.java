import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class MinesweeperFrame extends JFrame{

    BufferedImage dead;
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


    public MinesweeperFrame() {
        super("k0904676, Period 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLayout(null);
        setVisible(true);
        try {
            dead = ImageIO.read((new File("Images\\Dead")));
        }catch (IOException t) {t.printStackTrace();}
    }

    public void paint(Graphics g) {
        g.drawImage(dead,0,0, null);
    }
}
