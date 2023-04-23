package Enemies;

import Graphics.Animation;

public class Ghost extends Enemy {
	
	public Ghost(int x, int y) {
		super(x, y);
		animationLeft = new Animation("/anhgame.png", 4, 0, 750_000_000 , 4); 
		animationRight = new Animation("/anhgame.png", 4, 1, 750_000_000 , 4); 
		initDirectionList(); 
		animationLeft.setLoop(true);
		animationRight.setLoop(true); 
		
		score = 200; 
	}

	@Override
	protected void move() {
		
	}
	
}
