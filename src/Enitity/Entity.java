package Enitity;

import java.awt.Graphics;

import Graphics.IRender;
import Graphics.Sprite;

public abstract class Entity implements IRender {
    protected int  x;
    protected int  y;
    protected Sprite sprite; 
    
    public Entity() {
        x = 0; 
        y = 0;  
    }
    
    public Entity(int x , int y , int width , int height) {
    		this.x = x; 
    		this.y = y; 
    }
    
    public Entity(int x , int  y) {
    	this.x = x ; 
    	this.y = y ; 
    }
    
    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
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
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public abstract void update() ;
    public abstract void render(Graphics g);
    
   

}
