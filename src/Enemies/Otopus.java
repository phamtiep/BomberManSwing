package Enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Config.Direction;
import Config.GameConfig;
import Graphics.Animation;
import Graphics.Sprite;

public class Otopus extends Enemy {

    public Otopus(int x, int y) {
        super(x, y);
        animationLeft = new Animation("/anhgame.png", 4, 0, 700_000_000, 0);
        animationRight = new Animation("/anhgame.png", 4, 1, 700_000_000, 0); 
        initDirectionList();
        animationLeft.setLoop(true);
        animationRight.setLoop(true); 
        currenDirection = Direction.values()[random.nextInt(Direction.values().length)];
        score = 100;

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
                currenDirection = directionList.get(ran);
            }
        }
        x += moveX;
        y += moveY;
    }
}
