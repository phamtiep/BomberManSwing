package Item;

import Boomber.Bomber;
import Enitity.EntityManager;
import Enitity.Item;

public class BombItem  extends Item{

	public BombItem(int x, int y) {
		super(x, y);
		Item = sprite.getSprite(2, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void changePower() {
		Bomber bomber = EntityManager.getInstance().bomber;
		if(bomber.getBombMax()<10) {
			bomber.setBombMax(bomber.getBombMax()+1);
		}
		done = true;
	}

}
