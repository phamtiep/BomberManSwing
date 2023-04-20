package Enitity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Enemies.Otopus;

public class EntityManager {
	
	public Otopus otopus = new Otopus(0, 0); 
    
    public List<Entity> entities = new ArrayList<>();
    // list các đối tượng
    
    public EntityManager() {
        // TODO Auto-generated constructor stub
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
