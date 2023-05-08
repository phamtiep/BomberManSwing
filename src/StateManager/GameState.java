package StateManager;



import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
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
import Graphics.SpriteSheet;
import Item.SpeedItem;
import Main.*;
import Map.LevelMap;
public class GameState extends JPanel {

    LevelMap map = LevelMap.getInstance();
    public static List<Integer> es = new ArrayList<Integer>();






    

    SpriteSheet gameLose = new SpriteSheet("/GameOver.png");
    SpriteSheet gameWin = new SpriteSheet("/game_completed.png");



   
    public static Stage gameStage;

    JLabel scoreLabel = new JLabel("Score : " + 0);
    JLabel lives = new JLabel("Lives : " + EntityManager.getInstance().lives);
    
    


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameStage == Stage.Playing) {
          //  lives.setLocation(200, 300);
            this.setBackground(Color.black);
            
            
            
          
            g.setFont(new Font("Arial", Font.BOLD,20));
            g.setColor(Color.CYAN);
            g.drawString("Lives : " + EntityManager.getInstance().lives  ,GameConfig.WIDTH  + 5, 30);
            g.drawString("Score : " + EntityManager.getInstance().score, GameConfig.WIDTH + 5, 60);
            LevelMap.getInstance().render(g);
            EntityManager.getInstance().render(g);
            EntityManager.getInstance().update();
            handleInput();
        }
        else if(gameStage == Stage.Lose) {
            handleInputMenu();
            g.drawImage(gameLose.getImg(),0, 0,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT, null);
        }
        
        else if (gameStage == Stage.Win) {
            handleInputMenu();
            g.drawImage(gameWin.getImg(),0, 0,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT, null);
        }
        
        
    }

    public enum Stage {
        Playing, Win, Lose
    }

    public GameState() {
        gameStage = Stage.Playing;

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


        this.addKeyListener(inp);
        this.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

    }



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
    public void add() {
        this.setLayout(null);
        this.add(lives);
        lives.setLocation(100, 100);
    }

}
