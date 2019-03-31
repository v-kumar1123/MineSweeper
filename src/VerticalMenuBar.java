import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class VerticalMenuBar extends JMenuBar {
    private static final LayoutManager grid = new GridLayout(0,1);
    public VerticalMenuBar() {
        setLayout(grid);
    }
}