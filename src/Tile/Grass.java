package Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Enitity.Entity;
import Graphics.Sprite;

public class Grass extends Entity {
	private static boolean initialized = false; 
	private BufferedImage grass; 
	
	public Grass(int x , int y) {
		super(x, y); 
		initGrass(); 
		initialized = true; 
	}
	

	private void initGrass() {
		sprite = new Sprite("/anhgame.png"); 
		grass = sprite.getSprite(3, 4); 
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(grass , 3 * 32 , 0, null); 
	}
	
}
