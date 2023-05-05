package Enitity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Bomb.Bomb;
import Boomber.Bomber;
import Config.GameConfig;
import Enemies.Enemy;
import Enemies.Otopus;
import Map.LevelMap;


public class EntityManager {

    public List<Enemy> enemy = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();
    public List<Brick> brick = new ArrayList<>();
    public Bomber bomber;
    // list các đối tượng
    public static EntityManager instance = new EntityManager();

    /**
     * @return the instance
     */
    public static EntityManager getInstance() {
        return instance;
    }

    private EntityManager() {

    }

    public void render(Graphics g) {
        enemy.forEach(enemy -> enemy.render(g));
        brick.forEach(brick -> brick.render(g));
        bombs.forEach(bomb -> bomb.render(g));
        bomber.render(g);
    }

    public void update() {
        enemy.forEach(enemy -> enemy.update());
       
            for (int i = 0; i < bombs.size(); i++) {
                if (!bombs.get(i).isDone()) {
                    bombs.get(i).update();
                } else {
                    LevelMap.getInstance().setHashAt(
                            (int) bombs.get(i).getY() /GameConfig.SIZE_BLOCK,
                            (int) bombs.get(i).getX() / GameConfig.SIZE_BLOCK,
                            "grass");
                    bombs.remove(i);
                    i--;
                }
            }
        bomber.update();
    }

    public void checkCollision() {

    }

}
