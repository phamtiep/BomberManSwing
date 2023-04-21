package StateManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Config.GameConfig;
import Main.Main;

public class MenuState extends JPanel {

	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
       
    }
    public MenuState() {
        this.setSize(GameConfig.WIDTH + GameConfig.SIZE_BLOCK*2, GameConfig.HEIGHT + GameConfig.SIZE_BLOCK*2);
        repaint();
    }

}
