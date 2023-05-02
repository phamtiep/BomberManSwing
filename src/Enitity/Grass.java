package Enitity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Graphics.Sprite;

public class Grass extends Entity {
	private static boolean initialized = false; 
	public  BufferedImage grass; 
	public  Sprite sprite ;
	public Grass(int x , int y) {
		super(x, y); 
		initGrass(); 
		initialized = true; 
	}
	

	public  void initGrass() {
		sprite = new Sprite("/anhgame.png",1); 
		grass = sprite.getSprite(4, 3); 
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(grass , x , y, null); 
	}


    public void setLocation(int i, int j) {
       x = i;
       y = j;
        
    }
	
}
