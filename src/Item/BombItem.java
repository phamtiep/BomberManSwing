package Item;

import java.awt.image.BufferedImage;

import Boomber.Bomber;
import Config.GameConfig;
import Enitity.EntityManager;
import Enitity.Item;

public class BombItem  extends Item{

	public BombItem(int x, int y) {
		super(x, y);
		Item = sprite.getSprite(2,0);
	}
	
	@Override
	public void changePower() {
	    done = true;
	//	System.out.println("da an item");
		Bomber bomber = EntityManager.getInstance().bomber;
	
		if(bomber.getBombMax()<10) {
			bomber.setBombMax(bomber.getBombMax()+1);
		}
		
		appear = false; 
	}
	@Override
	public BufferedImage getImage() {
		return sprite.getSprite(2, 0);
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
