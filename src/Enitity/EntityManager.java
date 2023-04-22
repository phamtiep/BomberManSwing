package Enitity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Enemies.Enemy;
import Enemies.Otopus;
import Map.LeverMap;

public class EntityManager {
	
	
    
	
	public List<Enemy> enemy = new ArrayList<>();
    
    public List<Brick> brick = new ArrayList<>();    
    
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
    
    }
    
    public void update() {
        // các đối tượng update();
    }
    
    public void checkCollision() {
        
    }
    
    
    
    

}
