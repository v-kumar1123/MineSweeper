import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MineSweeperPanel extends JPanel implements MouseListener, KeyListener {

    BufferedImage dead;
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

        addKeyListener(this);

    }






    public void paint(Graphics g) {
        graphics=g;
        if(!firstClicked) {
            g.setColor(Color.GRAY);
            g.fillRect(0,0,getWidth(),getHeight());
            paintFirstBlocks(g,10);
        }
        else {
            for(int x=0;x<game.getBoard().length;x++) {
                for(int y=0;y<game.getBoard()[0].length;y++) {
                    if(game.getBoard()[x][y].isRevealed()) {
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
                                    if(game.getBoard()[r][c].isMine) {
                                        g.drawImage(mine,(r*16)+50,(c*16)+50,null);
                                    }
                                }
                            }
                            g.drawImage(exploded,(x*16)+50,(y*16)+50,null);
                            gameOver=true;

                            break;
                        }
                        if(gameOver) {
                            break;
                        }
                    }
                    else {
                        if(game.getBoard()[x][y].getStatus()==Tile.BLANK) {
                            g.drawImage(unclicked, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if(game.getBoard()[x][y].getStatus()==Tile.FLAGGED) {
                            g.drawImage(flag, (x * 16) + 50, (y * 16) + 50, null);
                        }
                        else if(game.getBoard()[x][y].getStatus()==Tile.QUESTIONED) {
                            g.drawImage(question, (x * 16) + 50, (y * 16) + 50, null);
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
            if(hovering) {
                g.drawImage(empty, emptyX,emptyY,null);
            }
        }
    }
    public void mineHasBeenClicked() {
        for(int x=0;x<game.getBoard().length;x++) {
            for(int y=0;y<game.getBoard()[0].length;y++) {
                if(game.getBoard()[x][y].isMine) {
                    System.out.println("I FOUND A MINE!");
                    game.getBoard()[x][y].setRevealed(true);
                }
            }
        }
    }
    public void emptyClicked(int x, int y) {
        if(x>0&&game.getBoard()[x-1][y].getMinesAround()==0) {
            if(!game.getBoard()[x-1][y].isRevealed()) {
                game.getBoard()[x-1][y].setRevealed(true);
                emptyClicked(x-1,y);
            }
        }
        if(x<game.getBoard().length-1&&game.getBoard()[x+1][y].getMinesAround()==0) {
            if(!game.getBoard()[x+1][y].isRevealed()) {
                game.getBoard()[x + 1][y].setRevealed(true);
                emptyClicked(x+1,y);
            }
        }
        if(y>0&&game.getBoard()[x][y-1].getMinesAround()==0) {
            if(!game.getBoard()[x][y-1].isRevealed()) {
                game.getBoard()[x][y-1].setRevealed(true);
                emptyClicked(x,y-1);
            }
        }
        if(y<game.getBoard()[0].length-1&&game.getBoard()[x][y+1].getMinesAround()==0) {
            if(!game.getBoard()[x][y+1].isRevealed()) {
                game.getBoard()[x][y+1].setRevealed(true);
                emptyClicked(x,y+1);
            }
        }
        if(x<game.getBoard().length-1&&y<game.getBoard()[0].length-1&&game.getBoard()[x+1][y+1].getMinesAround()==0) {
            if(!game.getBoard()[x+1][y+1].isRevealed()) {
                game.getBoard()[x + 1][y+1].setRevealed(true);
                emptyClicked(x+1,y+1);
            }
        }
        if(x>0&&y<game.getBoard()[0].length-1&&game.getBoard()[x-1][y+1].getMinesAround()==0) {
            if(!game.getBoard()[x-1][y+1].isRevealed()) {
                game.getBoard()[x - 1][y+1].setRevealed(true);
                emptyClicked(x-1,y+1);
            }
        }
        if(x>0&&y>0&&game.getBoard()[x-1][y-1].getMinesAround()==0) {
            if(!game.getBoard()[x-1][y-1].isRevealed()) {
                game.getBoard()[x - 1][y-1].setRevealed(true);
                emptyClicked(x-1,y-1);
            }
        }
        if(x<game.getBoard().length-1&&y>0&&game.getBoard()[x+1][y-1].getMinesAround()==0) {
            if(!game.getBoard()[x+1][y-1].isRevealed()) {
                game.getBoard()[x + 1][y-1].setRevealed(true);
                emptyClicked(x+1,y-1);
            }
        }
        repaint();
        return;
    }


    public void paintFirstBlocks(Graphics g, int difficulty) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,getWidth(),getHeight());
        for(int r=0;r<blockNo;r++) {
            for(int c=0;c<blockNo;c++) {
                g.drawImage(unclicked,(r*16)+50,(c*16)+50,null);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int mines=0;
        System.out.println("I AM LEGIT DONE I SWEAR TO GOD");
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

            //System.out.println("\t\t\t\t\t\t\t\t\t\t\tHELLO AGAIN");
            if(game!=null) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\tHELLO AGAIN");
                emptyY=(e.getY() - 50 / 16)*16+50;
                emptyX=(e.getX() - 50 / 16)*16+50;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1) {
            if (!firstClicked) {
                game = new MinesweeperGame((e.getX() - 50) / 16, (e.getY() - 50) / 16);

                game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setRevealed(true);
                firstClicked = true;
            } else if (!gameOver) {
                game.getBoard()[(e.getX() - 50) / 16][(e.getY() - 50) / 16].setRevealed(true);
                repaint();
            } else {

            }
            repaint();
        }
        else if (e.getButton()==MouseEvent.BUTTON3) {
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
            repaint();
        }
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //TODO 3/21: LOOOK AT TULLY'S DOCS, IMPLEMENT THEM INTO YOUR PROGRAM. DON'T FOLLOW HIS COMPLETELY, BUT HE HAS THINGS THAT YOU DONT KNOW YET..

}
