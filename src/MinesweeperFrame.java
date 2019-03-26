import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class MinesweeperFrame extends JFrame implements Runnable, KeyListener {
    public static final int EASY=0;
    public static final int MEDIUM=1;
    public static final int HARD=2;

    private long updatesDone=0;
    int playerWants=EASY;
    Thread thread=new Thread();
    int noBlocksToPaint=0;
    MineSweeperPanel panel=new MineSweeperPanel(10);


    public MinesweeperFrame() {
        super("k0904676, Period 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        if(playerWants==EASY) {
            noBlocksToPaint=10;
        }

        else if(playerWants==MEDIUM) {
            noBlocksToPaint=15;
        }
        else if(playerWants==HARD) {
            noBlocksToPaint=20;
        }

        super.setSize(280, 315);

        panel = new MineSweeperPanel(10);
        panel.setBounds(0,0,getWidth(),getHeight());
        add(panel);

        panel.addNotify();
        panel.requestFocus();

        Thread t=new Thread(this);
        t.start();

        addKeyListener(this);

        setUndecorated(true);
        /*TODO 3/25/19: When player starts game, playerWants is zero. If the process operation shows
        *TODO: that he wants a easy board, change playerWants to the correct value corresponding
        * TODO: Then, when he makes his first click, use playerWants to choose a difficulty in game
        * TODO: when the player chooses easy, then chooses hard, use updateDifficulty to change difficulty and
        * TODO: how to draw the board in panel using paintFirstBlocks.
        *
        * */
        setVisible(true);
    }

    public void updateDifficulty() {

    }

    public void process(ActionEvent e) {
        if(e.getActionCommand().equals("HARD")){

        }
    }

    //TODO NIGHT: JUST PLEASE FIX THE START TO PLAY BOARD, then do icons for right click and menu

    public void run() {

       /* long startTime = System.nanoTime();
        double sleeptime=200;
        while(true) {


            long updatesNeed = (long) (((System.nanoTime() - startTime) / 1000000) / sleeptime);
            for (; updatesDone < updatesNeed; updatesDone++) {
                if (panel.game != null) {
                    panel.game.setDifficulty(panel.game.HARD);
                    if (panel.game.difficulty == panel.game.EASY) {
                        super.setSize(280, 315);
                        panel = new MineSweeperPanel(10);
                    } else if (panel.game.difficulty == panel.game.MEDIUM) {
                        super.setSize(310, 345);
                        panel = new MineSweeperPanel(15);
                    } else if (panel.game.difficulty == panel.game.HARD) {
                        super.setSize(340, 375);
                        panel = new MineSweeperPanel(20);
                    }
                } else {
                    System.out.println("\t\t\t\t\t\t\t\t YOTEEE");
                    super.setSize(280, 315);
                }
            }
        }*/
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
}
