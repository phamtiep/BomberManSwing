package StateManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Bomb.Bomb;
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
               
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
               EntityManager entityManager =  EntityManager.getInstance();
                switch (e.getKeyCode()) {
                case(KeyEvent.VK_W):{
                    
                    entityManager.bomber.setCurrenDirection(Direction.UP);
                   
                }
                    break;
                case(KeyEvent.VK_S):
                    entityManager.bomber.setCurrenDirection(Direction.DOWN);
                    break;
                case(KeyEvent.VK_A):
                    entityManager.bomber.setCurrenDirection(Direction.LEFT);
                    break;
                case(KeyEvent.VK_D):
                    entityManager.bomber.setCurrenDirection(Direction.RIGHT);
                    break;
                case(KeyEvent.VK_SPACE) : {
                   
                   entityManager.bombs.add(new Bomb(entityManager.bomber.getX(), entityManager.bomber.getY()));
                   
                }
                default:
                    break;
                }
                
            }
        };
        this.addKeyListener(inp);
        this.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
      
    }
}
