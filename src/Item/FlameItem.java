package Item;

import java.awt.image.BufferedImage;

import Bomb.Bomb;
import Bomb.Flame;
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
	}
	@Override
	public BufferedImage getImage() {
		return sprite.getSprite(0, 0);
	}
}
