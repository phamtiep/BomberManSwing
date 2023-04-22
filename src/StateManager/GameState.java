package StateManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Config.GameConfig;
import Enemies.Enemy;
import Enemies.Ghost;
import Enemies.Otopus;
import Enitity.Item;
import Enitity.SpeedItem;
import Main.*;

public class GameState extends JPanel {

	Enemy otopus = new Otopus(0,0); 
	Enemy ghost = new Ghost(1, 1); 
	Item spi = new SpeedItem(0,0);
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        otopus.render(g);
        ghost.render(g);
        spi.render(g);
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
        this.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
      
    }
}
