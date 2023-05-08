package Item;

import java.awt.image.BufferedImage;

import Boomber.Bomber;
import Config.GameConfig;
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
			bomber.setSpeed(bomber.getSpeed()+1);
		}
		done = true;
		appear = false; 
	}
	@Override
	public BufferedImage getImage() {
		return sprite.getSprite(1,0);
	}
	
	@Override
	public void checkEaten() {
		double bomberX = EntityManager.getInstance().bomber.getX();
        double bomberY = EntityManager.getInstance().bomber.getY();

        int jBomber = (int) (bomberX + GameConfig.SIZE_BLOCK / 2) / GameConfig.SIZE_BLOCK;
        int iBomber = (int) (bomberY + GameConfig.SIZE_BLOCK/ 2) / GameConfig.SIZE_BLOCK;

        if(jBomber*32 == x && iBomber*32 == y) {
        	System.out.println("da an item");
        	eaten = true; 
        }
		
	}
	
	

	
}
