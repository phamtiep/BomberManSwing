package Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Enitity.Entity;
import Graphics.Sprite;

public class Brick extends Entity {
	
	private static boolean initialized = false; 
	private static BufferedImage brick;
	private static BufferedImage[] brickExplodes; 
	private boolean destroyed = false; 
	private int aniTick , aniIndex , aniSpeed = 15;
	
	public Brick(int x, int y) {
		super(x , y); 
		brickExplodes = new BufferedImage[4];  
		initBrick(); 
		loadAnimationExplodes(); 
	}
	
	

	private void loadAnimationExplodes() {
		for(int i = 0; i < 4; i++) {
			brickExplodes[i] = sprite.getSprite(3, i); 
		}
	}



	private void initBrick() {
		sprite = new Sprite("/anhgame.png"); 
		brick = sprite.getSprite(3, 0); 
	}
	
	public void updateAnimation() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++; 
			if(aniIndex >= 4) {
				aniIndex = 0;
			}
		}
	}

	public void ExploderBrick(Graphics g) {
		updateAnimation();
		g.drawImage(brickExplodes[aniIndex],2*32, 0*32, null); 
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(brick, 1*32 , 0 * 32 , null);
		ExploderBrick(g);
	}
	
}
