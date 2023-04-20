package Enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Config.Direction;
import Graphics.Sprite;

public class Otopus extends Enemy {
	private int aniTick , aniSpeed = 15 , aniIndex ; 

	public Otopus(int x, int y) {
		super(x, y);
		animation = new BufferedImage[4]; 
		initDirectionList();
		 currenDirection = Direction.values()[random.nextInt(Direction.values().length)];
		 score = 100;
		 loadAnimaion(); 
	}
	

	private void loadAnimaion() {
		sprite = new Sprite("/anhgame.png"); 
		for(int i = 0; i < 4; i++) {
			animation[i] = sprite.getSprite(i, 0); 
		}
	}


	@Override
	protected void move() {
		
	}
	
	public void updateAnimation() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++; 
			if(aniIndex >= animation.length) {
				aniIndex = 0;
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		updateAnimation();
		g.drawImage(animation[aniIndex],0,0,null); 
	}

}
