package Item;

import java.awt.image.BufferedImage;

import Bomb.Bomb;
import Bomb.Flame;
import Config.GameConfig;
import Enitity.EntityManager;
import Enitity.Item;

public class FlameItem extends Item {

	public FlameItem(int x, int y) {
		super(x, y);
		Item = sprite.getSprite(0,0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void changePower() {
		if(Bomb.getFlameLength()<Flame.MAX_LENGTH) {
			Bomb.setFlameLength(Bomb.getFlameLength()+1);
			
		}
		done = true;
		appear = false; 
	}
	@Override
	public BufferedImage getImage() {
		return sprite.getSprite(0, 0);
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
