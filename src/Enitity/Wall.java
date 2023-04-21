package Enitity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Graphics.Sprite;

public class Wall extends Entity {
	
	public static final boolean initialized = false; 
	public static  BufferedImage wall ; 
	private boolean appear = false;
	//private boolean canPass = false; 
	public static Sprite sprite;
	public Wall(int x , int y) {
		super(x , y); 
		initWall(); 
	}
	
	public static void initWall() {
		sprite = new Sprite("/anhgame.png"); 
		wall = sprite.getSprite(3, 5); 
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(wall , x*32 , y*32 , null); 
	}

	

	public boolean isAppear() {
		return appear;
	}

	public void setAppear(boolean appear) {
		this.appear = appear;
	}

    public void setLocation(int i, int j) {
        // TODO Auto-generated method stub
        x = i ;
        y = j;
    }


	

}
