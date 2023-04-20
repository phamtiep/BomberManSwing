package Enitity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Enemies.Enemy;
import Enemies.Otopus;

public class EntityManager {
	
	public Otopus otopus = new Otopus(0, 0); 
    
	
	  public List<Enemy> enemy = new ArrayList<>();
    public List<Entity> entities = new ArrayList<>();
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
       otopus.render(g); 
    }
    
    public void update() {
        // các đối tượng update();
    }
    
    public void checkCollision() {
        
    }
    
    
    
    

}
