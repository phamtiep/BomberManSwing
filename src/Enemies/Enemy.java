package Enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Config.Direction;
import Config.GameConfig;
import Enitity.Entity;
import Enitity.LivingEntity;
import Graphics.Animation;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Map.LevelMap;

public abstract class Enemy extends LivingEntity {

    protected Animation animationLeft;
    protected Animation animationRight;
    protected Animation animationDeath;
    protected boolean isDone = false;
    /**
     * @return the isDone
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @param isDone the isDone to set
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected double moveX = 0;
    protected double moveY = 0;
    protected boolean canMoveR = false;
    protected boolean canMoveL = false;
    protected boolean canMoveU = false;
    protected boolean canMoveD = false;
    protected Random random = new Random();
    protected boolean randomAnimation = true;

    protected List<Direction> directionList = new ArrayList<>();
    protected Sprite sheet;
    protected int score;

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    public Enemy(int x, int y) {
        super(x, y);
        speed = 1;
    }

    public void render(Graphics g) {
        if (!destroyed) {
            if (randomAnimation) {
                animationRight.render(g, x, y);
                animationRight.update();
            } else {
                animationLeft.render(g, x, y);
                animationLeft.update();
            }
        } else {
            animationDeath.render(g, x, y);
            animationDeath.update();
        }

    }

    public void update() {
        if (!destroyed) {
            move();
        } else {
            if (animationDeath.isDone()) {
                isDone = true;
            }
        }
    }

    public boolean checkHashMap(int i, int j) {
        LevelMap map = LevelMap.getInstance();
        if (i < 0 || i > (map.getHeight() / GameConfig.SIZE_BLOCK) - 1 || j < 0
                || j > (map.getWidth() / GameConfig.SIZE_BLOCK) - 1) {
            return false;
        }

        return map.getHashAt(i, j) == map.getHash("grass");
    }

    protected void initDirectionList() {
        this.directionList.clear();
        directionList.add(Direction.UP);
        directionList.add(Direction.DOWN);
        directionList.add(Direction.LEFT);
        directionList.add(Direction.RIGHT);


    }

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
