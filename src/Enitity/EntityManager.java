package Enitity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Bomb.Bomb;
import Boomber.Bomber;
import Config.Action;
import Config.GameConfig;
import Enemies.Enemy;
import Enemies.Otopus;
import Map.LevelMap;

public class EntityManager {

    public List<Enemy> enemy = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();
    public List<Brick> brick = new ArrayList<>();
    public List<Item> item = new ArrayList<>(); 
    
    public Bomber bomber;
    public final int LIVE_DEFAULT = 3;
    public int lives = LIVE_DEFAULT;
    /**
     * @return the lives
     */
    public int getLives() {
        return lives;
    }
   

    /**
     * @param lives the lives to set
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

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
        enemy.forEach(enemy -> {
            if (!enemy.isDone()) {
                enemy.render(g);
            }
        });
        item.forEach(item ->{
        	if(!item.done) {
        		item.render(g);
        	}
        });
        brick.forEach(brick -> brick.render(g));
        bombs.forEach(bomb -> bomb.render(g));
        bomber.render(g);
    }

    public void update() {
        System.out.println(lives);
        enemy.forEach(enemy -> enemy.update());
        item.forEach(item ->{
        	if(!item.done) {
        		item.update();
        	}
        });
        
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isDone()) {
                bombs.get(i).update();
            } else {
                LevelMap.getInstance().setHashAt((int) bombs.get(i).getY() / GameConfig.SIZE_BLOCK,
                        (int) bombs.get(i).getX() / GameConfig.SIZE_BLOCK, "grass");
                bombs.remove(i);
                i--;
            }
        }
        bombs.forEach(bomb -> {
            bomb.getFlameList().forEach(flame -> {
                if (!flame.isDone()) {
                    BoxCollider flameBox = new BoxCollider(flame.getX(), flame.getY(), 32, 32);
                    BoxCollider bomberBox = bomber.getBomberBox();
                    if (bomber.getPlayerAction() != Action.DEAD &&bomberBox.isCollidedWith(flameBox)&&!bomber.isUndead) {
                        bomber.setPlayerAction(Action.DEAD);
                        this.lives--;
                    }
                        enemy.forEach(enemy -> {
                        if (!enemy.isDone()) {
                            BoxCollider enemyBox = new BoxCollider(enemy.getX(), enemy.getY(), 32,
                                    32);
                            if (!enemy.isDestroyed() && enemyBox.isCollidedWith(flameBox)) {
                                enemy.setDestroyed(true);
                                System.out.println("0w");
                            }

                        }
                    });
                }
            });
        });
        checkCollision();
        bomber.update();
    }

    public void checkCollision() {
        for (int i = 0; i < enemy.size(); i++) {
            Enemy enemys = enemy.get(i);
            if (!enemys.isDone()) {
                BoxCollider enemyBox = null;
                enemyBox = new BoxCollider(enemys.getX(), enemys.getY(), 32, 32);

                BoxCollider bomberBox = bomber.getBomberBox();
                if (bomber.getPlayerAction() != Action.DEAD &&!enemys.isDestroyed() && bomberBox.isCollidedWith(enemyBox)&&!bomber.isUndead) {
                    bomber.setPlayerAction(Action.DEAD);
                    this.lives--;
                   
                }
            }
        }
    }
    
    public void checkEaten() {
    	
    }

}
