package StateManager;



import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;



import Bomb.Bomb;
import Config.Action;
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
    public static List<Integer> es = new ArrayList<Integer>();

<<<<<<< HEAD
=======


    public Stage gameStage;
    JLabel scoreLabel = new JLabel("Score : " + 0);
    JLabel lives = new JLabel("Lives : " + EntityManager.getInstance().lives);
    
    

>>>>>>> 4105c78bca27d749d8b970e17d400cca8641db30
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameStage == Stage.Playing) {
            lives.setText("Lives :" + EntityManager.getInstance().lives);
            
            LevelMap.getInstance().render(g);
            EntityManager.getInstance().render(g);
            EntityManager.getInstance().update();
            handleInput();
        }
        else if(gameStage == Stage.Lose) {
            handleInputMenu();
        }
        
        else if (gameStage == Stage.Win) {
            handleInputMenu();
            
        }
        
        
    }

    public enum Stage {
        Playing, Win, Lose
    }

    public GameState() {
        gameStage = Stage.Playing;
        //lives.setBounds(0, 0, lives.getWidth(), lives.getHeight());
        this.add(lives);
        
        this.add(scoreLabel);
        KeyListener inp = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (Iterator<Integer> iterator = GameState.es.iterator(); iterator.hasNext();) {
                    if ((Integer) iterator.next() == e.getKeyCode()) {
                        iterator.remove();
                    }
                    ;

                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (!es.contains(e.getKeyCode())) {
                    es.add(e.getKeyCode());
                }
//               
            }
        };
        
<<<<<<< HEAD
=======
      //  addLabel(); 
>>>>>>> 4105c78bca27d749d8b970e17d400cca8641db30
        
        this.addKeyListener(inp);
        this.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

    }

<<<<<<< HEAD
=======
   
>>>>>>> 4105c78bca27d749d8b970e17d400cca8641db30
	public void handleInput() {
        EntityManager entityManager = EntityManager.getInstance();
        if (entityManager.bomber.getPlayerAction() == Action.DEAD)
            return;
        if (!es.isEmpty()) {

            if (es.contains(KeyEvent.VK_SPACE)) {
                entityManager.bomber.placeBomb();
            } else if (es.contains(KeyEvent.VK_W) || es.contains(KeyEvent.VK_UP)) {
                entityManager.bomber.setPlayerAction(Action.MOVING);
                entityManager.bomber.setCurrenDirection(Direction.UP);
            } else if (es.contains(KeyEvent.VK_A) || es.contains(KeyEvent.VK_LEFT)) {
                entityManager.bomber.setPlayerAction(Action.MOVING);
                entityManager.bomber.setCurrenDirection(Direction.LEFT);
            } else if (es.contains(KeyEvent.VK_D) || es.contains(KeyEvent.VK_RIGHT)) {
                entityManager.bomber.setPlayerAction(Action.MOVING);
                entityManager.bomber.setCurrenDirection(Direction.RIGHT);
            } else if (es.contains(KeyEvent.VK_S) || es.contains(KeyEvent.VK_DOWN)) {
                entityManager.bomber.setPlayerAction(Action.MOVING);
                entityManager.bomber.setCurrenDirection(Direction.DOWN);
            }

        } else {
            entityManager.bomber.setPlayerAction(Action.IDLE);
        }

    }
    public void handleInputMenu() {
        if(es.contains(KeyEvent.VK_R)){
            StateManager.getInstance().setCurrentState(StateManager.State.MENU);
            es.clear();
        }
    }

}
