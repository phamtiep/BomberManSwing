package Enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Config.Direction;
import Graphics.Animation;
import Graphics.Sprite;

public class Otopus extends Enemy {
	
	public Otopus(int x, int y) {
		super(x, y);
		animation = new Animation("/anhgame.png", 4, 0, 1000_000_000 , 0); 
		initDirectionList();
		animation.setLoop(true);
		 currenDirection = Direction.values()[random.nextInt(Direction.values().length)];
		 score = 100;
	}

	@Override
	protected void move() {
		
	}
	
	/*
	 * @Override public void render(Graphics g) { animation.render(g, x, y);
	 * animation.update(); }
	 */
}
