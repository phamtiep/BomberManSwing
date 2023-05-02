package StateManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Config.Direction;
import Config.GameConfig;
import Enemies.Enemy;
import Enemies.Ghost;
import Enemies.Otopus;
import Enitity.EntityManager;
import Enitity.Item;
import Item.SpeedItem;
import Main.*;
import Map.LevelMap;

public class GameState extends JPanel {

	
	LevelMap map = LevelMap.getInstance(); 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        LevelMap.getInstance().render(g);
        EntityManager.getInstance().render(g);
        EntityManager.getInstance().update();
        
    }

    public GameState() {
        KeyListener inp = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyCode()) {
                case(KeyEvent.VK_UP):
                    EntityManager.getInstance().bomber.setCurrenDirection(Direction.UP);
                    break;
                case(KeyEvent.VK_DOWN):
                    EntityManager.getInstance().bomber.setCurrenDirection(Direction.DOWN);
                    break;
                case(KeyEvent.VK_LEFT):
                    EntityManager.getInstance().bomber.setCurrenDirection(Direction.LEFT);
                    break;
                case(KeyEvent.VK_RIGHT):
                    EntityManager.getInstance().bomber.setCurrenDirection(Direction.RIGHT);
                    break;

                default:
                    break;
                }
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
