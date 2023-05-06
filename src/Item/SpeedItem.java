package Item;

import java.awt.image.BufferedImage;

import Boomber.Bomber;
import Enitity.EntityManager;
import Enitity.Item;
import Graphics.Sprite;

public class SpeedItem extends Item {
	private static int numberEaten = 0;
	private static final int MAX_EATEN = 5;

	public SpeedItem(int x, int y) {
		super(x, y);
		Item = sprite.getSprite(1, 0);
	}

	@Override
	public void changePower() {
		Bomber bomber = EntityManager.getInstance().bomber;
		numberEaten++;
		if(numberEaten<= MAX_EATEN) {
			bomber.setSpeed(16.0/(8-numberEaten));
		}
		done = true;
	}
	@Override
	public BufferedImage getImage() {
		return sprite.getSprite(1,0);
	}
	
	

	
}
