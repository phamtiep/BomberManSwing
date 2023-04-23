package Enemies;

import Graphics.Animation;

public class Ghost extends Enemy {
	
	public Ghost(int x, int y) {
		super(x, y);
		animation = new Animation("/anhgame.png", 4, 0, 750_000_000 , 4); 
		initDirectionList(); 
		animation.setLoop(true); 
		
		score = 200; 
	}

	@Override
	protected void move() {
		
	}
	
}
