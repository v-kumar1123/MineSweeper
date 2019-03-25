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


public class MinesweeperFrame extends JFrame {

    public MinesweeperFrame() {
        super("k0904676, Period 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);


        MineSweeperPanel panel=new MineSweeperPanel();
        panel.setBounds(0,0,getWidth(),getHeight());
        add(panel);
        setVisible(true);

    }
}
