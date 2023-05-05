package Enemies;

import java.util.LinkedList;
import java.util.Queue;

import Config.Direction;
import Config.GameConfig;
import Enitity.EntityManager;
import Graphics.Animation;
import Map.LevelMap;

public class BossCrap extends Enemy {

	public BossCrap(int x, int y) {
		super(x, y);
		animationLeft = new Animation("/shark_atlas.png", 4, 0, 750_000_000 , 0); 
		animationRight = new Animation("/shark_atlas.png", 4, 1, 750_000_000 , 0); 
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
	            
	            currentDirection = findWay(i, j); 
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
	    
	    private Direction findWay(int i, int j) {
	        LevelMap levelMap = LevelMap.getInstance();
	        boolean[][] checkPass =
	                new boolean[levelMap.getMapHash().length][levelMap.getMapHash()[0].length];

	        for (int m = 0; m < checkPass.length; m++) {
	            for (int n = 0; n < checkPass[0].length; n++) {
	               if(levelMap.getHashAt(m, n) == levelMap.getHash("wall") || levelMap.getHashAt(m, n) == levelMap.getHash("brick")) {
	            	   checkPass[m][n] = true; 
	               }else {
	            	   checkPass[m][n] = false; 
	               }
	                
	            }
	        }
	        double bomberX = EntityManager.getInstance().bomber.getX();
	        double bomberY = EntityManager.getInstance().bomber.getY();

	        int jBomber = (int) (bomberX + GameConfig.SIZE_BLOCK / 2) / GameConfig.SIZE_BLOCK;
	        int iBomber = (int) (bomberY + GameConfig.SIZE_BLOCK/ 2) / GameConfig.SIZE_BLOCK;
	        
	        canMoveR = checkMapHash(i, j + 1);
	        canMoveL = checkMapHash(i, j - 1);
	        canMoveU = checkMapHash(i - 1, j);
	        canMoveD = checkMapHash(i + 1, j);

	        Queue<Direction> direc = new LinkedList<>();
	        Queue<Integer> iTile = new LinkedList<>();
	        Queue<Integer> jTile = new LinkedList<>();

	        checkPass[i][j] = true;
	        if (canMoveR && !checkPass[i][j + 1]) {
	            checkPass[i][j + 1] = true;
	            direc.add(Direction.RIGHT);
	            iTile.add(i);
	            jTile.add(j + 1);
	        }
	        if (canMoveL && !checkPass[i][j - 1]) {
	            checkPass[i][j - 1] = true;
	            direc.add(Direction.LEFT);
	            iTile.add(i);
	            jTile.add(j - 1);
	        }
	        if (canMoveU && !checkPass[i - 1][j]) {
	            checkPass[i - 1][j] = true;
	            direc.add(Direction.UP);
	            iTile.add(i - 1);
	            jTile.add(j);
	        }
	        if (canMoveD && !checkPass[i + 1][j]) {
	            checkPass[i + 1][j] = true;
	            direc.add(Direction.DOWN);
	            iTile.add(i + 1);
	            jTile.add(j);
	        }

	        while (!direc.isEmpty()) {
	            Direction direction = direc.poll();
	            i = iTile.poll();
	            j = jTile.poll();
	            if (i == iBomber && j == jBomber) {
	                for(int k = 0; k < directionList.size(); k++){
	                    if(directionList.get(k) == direction){
	                        return direction;
	                    }
	                }
	            }

	            canMoveR = checkMapHash(i, j + 1);
	            canMoveL = checkMapHash(i, j - 1);
	            canMoveU = checkMapHash(i - 1, j);
	            canMoveD = checkMapHash(i + 1, j);

	            if (canMoveR && !checkPass[i][j + 1]) {
	                checkPass[i][j + 1] = true;
	                direc.add(Direction.RIGHT);
	                iTile.add(i);
	                jTile.add(j + 1);
	            }
	            if (canMoveL && !checkPass[i][j - 1]) {
	                checkPass[i][j - 1] = true;
	                direc.add(Direction.LEFT);
	                iTile.add(i);
	                jTile.add(j - 1);
	            }
	            if (canMoveU && !checkPass[i - 1][j]) {
	                checkPass[i - 1][j] = true;
	                direc.add(Direction.UP);
	                iTile.add(i - 1);
	                jTile.add(j);
	            }
	            if (canMoveD && !checkPass[i + 1][j]) {
	                checkPass[i + 1][j] = true;
	                direc.add(Direction.DOWN);
	                iTile.add(i + 1);
	                jTile.add(j);
	            }
	        }

	        int ran = random.nextInt(directionList.size());
	        return directionList.get(ran);
	        
	    }
}