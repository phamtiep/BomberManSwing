package StateManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import Main.Main;

public class MenuState extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
    }
    public MenuState() {
        this.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        repaint();
    }

}
