package Bomb;


import java.awt.Graphics;
import java.awt.Image;



import Enitity.Entity;
import Graphics.SpriteSheet;


public class Flame extends Entity {
	public static final int MAX_LENGTH = 6;

    public Image img  = null;
    public static final int dispTime = 30_000;
  
    public enum FlameDirection {
        UP, DOWN, LEFT, RIGHT, CENTER
    }
    public boolean isLast;
    public static SpriteSheet inp = new SpriteSheet("/anhgame.png");
    
    public Flame(int x, int y, FlameDirection direction, boolean isLast) {
        this.x = x;
        this.y = y;
        switch (direction) {
        case UP:
            if(isLast)img = inp.getSprite(1, 5);
            else {
                img = inp.getSprite(1, 6);
            }
            break;
        case CENTER:
            if(isLast)img = inp.getSprite(1, 5);
            else {
                img = inp.getSprite(1, 6);
            }
            break;
        case LEFT:
            if(isLast)img = inp.getSprite(1, 5);
            else {
                img = inp.getSprite(1, 6);
            }
            break;
        case DOWN:
            if(isLast)img = inp.getSprite(1, 5);
            else {
                img = inp.getSprite(1, 6);
            }
            break;
        case RIGHT:
            if(isLast)img = inp.getSprite(1, 5);
            else {
                img = inp.getSprite(1, 6);
            }
            break;
        default:
            break;
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        
        g.drawImage(img, x,y,null);
        
    }



    @Override
    public void update() {
        
    }
    
    
   
 
}