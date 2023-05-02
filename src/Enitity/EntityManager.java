package Enitity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Boomber.Bomber;
import Enemies.Enemy;
import Enemies.Otopus;
import Map.LevelMap;

public class EntityManager {
	
	
    
	
	public List<Enemy> enemy = new ArrayList<>();
    
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
      bomber.render(g);
    }
    
    public void update() {
        enemy.forEach(enemy -> enemy.update());
        bomber.update();
    }
    
    public void checkCollision() {
        
    }
    
    
    
    

}
