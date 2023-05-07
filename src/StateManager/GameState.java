package StateManager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        LevelMap.getInstance().render(g);
        EntityManager.getInstance().render(g);
        EntityManager.getInstance().update();
        handleInput();
    }

    public GameState() {
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
//                entityManager.bomber.setPlayerAction(Action.MOVING);
//            
//                switch (e.getKeyCode()) {
//                case (KeyEvent.VK_W): {
//                    
//                    entityManager.bomber.setCurrenDirection(Direction.UP);
//
//                }
//                    break;
//                case (KeyEvent.VK_S):
//                    entityManager.bomber.setCurrenDirection(Direction.DOWN);
//                    break;
//                case (KeyEvent.VK_A):
//                    entityManager.bomber.setCurrenDirection(Direction.LEFT);
//                    break;
//                case (KeyEvent.VK_D):
//                    entityManager.bomber.setCurrenDirection(Direction.RIGHT);
//                    break;
//                case (KeyEvent.VK_SPACE): {
//                    if (entityManager.bombs.size() < entityManager.bomber.getBombMax()) {
//                        entityManager.bombs.add(
//                                new Bomb(entityManager.bomber.getX(), entityManager.bomber.getY()));
//                        LevelMap.getInstance().setHashAt(
//                                (entityManager.bombs.get(entityManager.bombs.size() - 1).getY()
//                                        / 32),
//                                (entityManager.bombs.get(entityManager.bombs.size() - 1).getX()
//                                        / 32),
//                                "bomb");
//                    }
//                }
//                
//                    break;
//                }

            }
        };
        this.addKeyListener(inp);
        this.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

    }

    public void handleInput() {
        EntityManager entityManager = EntityManager.getInstance();
        if (!es.isEmpty()) {
            entityManager.bomber.setPlayerAction(Action.MOVING);
            if (es.contains(KeyEvent.VK_SPACE)) {
                if (entityManager.bombs.size() < entityManager.bomber.getBombMax()) {
                    entityManager.bombs.add(
                            new Bomb(entityManager.bomber.getX(), entityManager.bomber.getY()));
                    LevelMap.getInstance().setHashAt(
                            (entityManager.bombs.get(entityManager.bombs.size() - 1).getY() / 32),
                            (entityManager.bombs.get(entityManager.bombs.size() - 1).getX() / 32),
                            "bomb");
                    System.out.println(entityManager.bombs.size());
                }
            } else if (es.contains(KeyEvent.VK_W) || es.contains(KeyEvent.VK_UP)) {
                entityManager.bomber.setCurrenDirection(Direction.UP);
            } else if (es.contains(KeyEvent.VK_A) || es.contains(KeyEvent.VK_LEFT)) {
                entityManager.bomber.setCurrenDirection(Direction.LEFT);
            } else if (es.contains(KeyEvent.VK_D) || es.contains(KeyEvent.VK_RIGHT)) {
                entityManager.bomber.setCurrenDirection(Direction.RIGHT);
            } else if (es.contains(KeyEvent.VK_S) || es.contains(KeyEvent.VK_DOWN)) {
                entityManager.bomber.setCurrenDirection(Direction.DOWN);
            }

        } else {
            entityManager.bomber.setPlayerAction(Action.IDLE);
        }

    }

}
