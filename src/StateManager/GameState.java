package StateManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Enemies.Otopus;
import Main.*;

public class GameState extends JPanel {

	Otopus otopus = new Otopus(0, 0); 
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        otopus.render(g);
    }

    public GameState() {
        KeyListener inp = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        };
        this.addKeyListener(inp);
        this.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
      
    }
}
