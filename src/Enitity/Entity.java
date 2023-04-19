package Enitity;

import Graphics.Sprite;
import java.awt.Graphics;
public abstract class Entity  {
    protected int  x;
    protected int  y;
    protected boolean alive = true; 
    protected Sprite sprite; 
    
    public Entity() {
        x = 0; 
        y = 0;  
    }
    
    public Entity(int x , int y) {
    		this.x = x; 
    		this.y = y; 
    }
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return alive;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.alive = active;
    }
    
    public abstract void update() ;
    public abstract void render(Graphics g);
    
   

}
