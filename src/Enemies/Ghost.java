package Enemies;

import Config.Direction;
import Config.GameConfig;
import Graphics.Animation;
import Map.LevelMap;

public class Ghost extends Enemy {
	
	public Ghost(int x, int y) {
		super(x, y);
		animationLeft = new Animation("/anhgame.png", 4, 0, 750_000_000 , 4); 
		animationRight = new Animation("/anhgame.png", 4, 1, 750_000_000 , 4); 
		animationDeath = new Animation("/NewDeadAnimation.png", 3, 0, 750_000_000, 0); 
		initDirectionList(); 
		animationLeft.setLoop(true);
		animationRight.setLoop(true); 
		animationDeath.setLoop(true);
		score = 200; 
	}

	@Override
	protected void move() {
		 int j = (int) (x / GameConfig.SIZE_BLOCK); // toa do
	        int i = (int) (y / GameConfig.SIZE_BLOCK); // toa do
	        if (Math.abs((double) j * GameConfig.SIZE_BLOCK - x) < speed
	                && Math.abs((double) i * GameConfig.SIZE_BLOCK - y) < speed) {
	            moveX = 0;
	            moveY = 0;
	            canMoveR = checkHashMap(i, j + 1);
	            canMoveL = checkHashMap(i, j - 1);
	            canMoveU = checkHashMap(i - 1, j);
	            canMoveD = checkHashMap(i + 1, j);

	            checkMove();
	            // initDirectionList();
	            if (moveY == 0 && moveX == 0 && directionList.size() != 0) {
	                int ran = random.nextInt(directionList.size());
	                currentDirection = directionList.get(ran);
	            }
	        }
	        x += moveX;
	        y += moveY;
	}
	
	// override lai ham checkmove
	@Override
	 protected void checkMove() {
        if (currentDirection == null) {
            initDirectionList();
            return;
        }
        switch (currentDirection) {
        case UP: {
            if (canMoveU) {
                moveY = -speed;
                initDirectionList();
            } else {
                directionList.remove(Direction.UP);
            }
            break;
        }
        case DOWN: {
            if (canMoveD) {
                moveY = speed;
                initDirectionList();
            } else {
                directionList.remove(Direction.DOWN);
            }
            break;
        }
        case LEFT: {
            if (canMoveL) {
                moveX = -speed;
                randomAnimation = false; 
                initDirectionList();
            } else {
                directionList.remove(Direction.LEFT);
            }
            break;
        }
        case RIGHT: {
            if (canMoveR) {
                moveX = speed;
                randomAnimation = true; 
                initDirectionList();
            } else {
                directionList.remove(Direction.RIGHT);
            }
            break;
        }
        default:
            break;
        }
    }
	
	
}
