import javafx.stage.PopupWindow;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class MinesweeperFrame extends JFrame implements Runnable, KeyListener {
    public static final int EASY=0;
    public static final int MEDIUM=1;
    public static final int HARD=2;
    PrintWriter writer;

    private long updatesDone=0;
    int playerWants=EASY;
    Thread thread=new Thread();
    int noBlocksToPaint=0;
    JMenuBar menuBar=new JMenuBar();
    JMenu file= new JMenu("File");
    JMenu about=new JMenu("Help");
    MineSweeperPanel panel=new MineSweeperPanel(10);


    public MinesweeperFrame() {
        super("k0904676, Period 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        try {
            writer=new PrintWriter(new File("C:\\Users\\varun\\Desktop\\MineSweeper\\src\\High Scores"));
        }catch (Exception e ) {e.printStackTrace();}
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
        JMenu newGame=new JMenu("New Game");


        newGame.add("EZPZ");
        newGame.add("Easy");
        newGame.add("Medium");
        newGame.add("Hard");
        newGame.add("Insane");
        about.add("Rules");
        about.add("About");
        for(int x=0;x<newGame.getItemCount();x++) {
            newGame.getItem(x).addActionListener((e) ->process(e));
        }
        file.add(newGame);
        file.add("High Scores");
        file.add("Exit");
        for(int x=0;x<file.getItemCount();x++) {
            file.getItem(x).addActionListener((e) ->process(e));
        }
        for(int x=0;x<about.getItemCount();x++) {
            about.getItem(x).addActionListener((e) ->process(e));
        }
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
        menuBar.add(file);
        menuBar.add(about);

        setJMenuBar(menuBar);


        setVisible(true);
    }

    public void updateDifficulty() {

    }

    public void process(ActionEvent e) {
        if(e.getActionCommand().equals("Easy")){
            setSize(300,350);
            panel.setSize(getWidth(),getHeight());
            panel.setBlockNo(10);
            panel.paintFirstBlocks(getGraphics(),10);
            panel.repaint();
            add(panel);
        }
        else if(e.getActionCommand().equals("Insane")){
            setSize(300,350);
            panel.setSize(getWidth(),getHeight());
            panel.setBlockNo(12);
            panel.paintFirstBlocks(getGraphics(),10);
            panel.repaint();
            add(panel);
        }

        else if(e.getActionCommand().equals("EZPZ")){
            setSize(300,350);
            panel.setSize(getWidth(),getHeight());
            panel.setBlockNo(13);
            panel.paintFirstBlocks(getGraphics(),10);
            panel.repaint();
            add(panel);
        }

        else if(e.getActionCommand().equals("Medium")){
            setSize(400,450);
            panel.setSize(getWidth(),getHeight());
            panel.setBlockNo(15);
            panel.paintFirstBlocks(getGraphics(),15);
            panel.repaint();
            add(panel);
        }

        else if(e.getActionCommand().equals("Hard")){
            setSize(500,550);
            panel.setSize(getWidth(),getHeight());
            panel.setBlockNo(20);
            panel.paintFirstBlocks(getGraphics(),20);
            panel.repaint();
            add(panel);
        }
        else if(e.getActionCommand().equals("Rules")) {

            panel.setRulesAsked(true);
            System.out.println("\t\t\t\t\t\t\t\t\tHELO");
        }

        else if(e.getActionCommand().equals("About")) {
            JOptionPane.showMessageDialog(null, "MINESWEEPER CREATED BY ME, VARUN K");
        }
        else if(e.getActionCommand().equals("High Scores")) {
            String message="";
            Collections.sort(panel.getEZPZScorers());
            Collections.sort(panel.getMediumScorers());
            Collections.sort(panel.getHardScorers());
            Collections.sort(panel.getEasyScorers());
            if(panel.getEZPZScorers().size()>5) {
                for(int x=4;x<panel.getEZPZScorers().size();x++) {
                    panel.getEZPZScorers().remove(x);
                }
            }
            if(panel.getMediumScorers().size()>5) {
                for(int x=4;x<panel.getMediumScorers().size();x++) {
                    panel.getMediumScorers().remove(x);
                }
            }
            if(panel.getHardScorers().size()>5) {
                for(int x=4;x<panel.getHardScorers().size();x++) {
                    panel.getHardScorers().remove(x);
                }
            }
            if(panel.getEasyScorers().size()>5) {
                for(int x=4;x<panel.getEasyScorers().size();x++) {
                    panel.getEasyScorers().remove(x);
                }
            }
            message+="EZPZ Top Scores\n";
            for(int x=0;x<panel.getEZPZScorers().size();x++) {
                System.out.println("I AM GOING TROUGH");
                message+=x+1+". "+panel.getEZPZScorers().get(x).getName()+", "+panel.getEZPZScorers().get(x).getScore()+"\n";
            }
            message+="Easy Top Scores\n";
            for(int x=0;x<panel.getEasyScorers().size();x++) {
                System.out.println("I AM GOING TROUGH");
                message += x + 1 + ". " + panel.getEasyScorers().get(x).getName() + ", " + panel.getEasyScorers().get(x).getScore() + "\n";
            }
            message+="Medium Top Scores\n";
            for(int x=0;x<panel.getMediumScorers().size();x++) {
                System.out.println("I AM GOING TROUGH");
                message+=x+1+". "+panel.getMediumScorers().get(x).getName()+", "+panel.getMediumScorers().get(x).getScore()+"\n";
            }
            message+="Hard Top Scores\n";
            for(int x=0;x<panel.getHardScorers().size();x++) {
                System.out.println("I AM GOING TROUGH");
                message+=x+1+". "+panel.getHardScorers().get(x).getName()+", "+panel.getHardScorers().get(x).getScore()+"\n";
            }
            JOptionPane.showMessageDialog(null, message);
        }
        if(e.getActionCommand().equals("Exit")) {
            System.exit(0);
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
