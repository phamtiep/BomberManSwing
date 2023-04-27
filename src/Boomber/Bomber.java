package Boomber;

import java.awt.Graphics;
import java.util.*;

import javax.swing.Action;

import Config.Direction;
import Config.GameConfig;
import Enitity.BoxCollider;
import Enitity.EntityManager;
import Enitity.Item;
import Enitity.LivingEntity;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Map.LeverMap;

public abstract class Bomber extends LivingEntity {
    private static final Map<String, Sprite[]> spritesDict = new HashMap<>();
    private static boolean initialized = false;
    public static final double DEFAULT_SPEED = 2;
    public static final int DEFAULT_LIVES = 5;

    private final List<Item> eatenItems = new ArrayList<>();

    private static SpriteSheet bombermanSheet;
    private static SpriteSheet bombermanFlashSheet;

    private int initialX;        //reset in each level.
    private int initialY;        //reset in each level.
    protected boolean canPassBomb = false;
    protected boolean canPassFlame = false;
    protected boolean canPassBrick = false;
    private boolean invincible = false;
    private int frameCounter;
    private boolean canResetLocation = false;
    private int bombMax = 100;
    private int currentSpriteIndex = 0;
    Action playerAction;
    

 //   protected Action playerAction = Action.IDLE;

    protected final BoxCollider bomberBox;

    public Bomber(int x, int y) {
        super(x, y);
        initialX = x;
        initialY = y;
        speed = DEFAULT_SPEED;
        currenDirection = Direction.DOWN;
       // lives = DEFAULT_LIVES;
        
        bomberBox = new BoxCollider(0, 0);
        updateBoxCollider();
    }

    public void updateBoxCollider() {
        bomberBox.setLocation(
                this.x ,
                this.y 
        );
    }

    public static void init() {
       
    }

    @Override
    public void render(Graphics g) {
      
    }

    @Override
    public void update() {
        resetLocation();
        move();
       
        }

        // Remove Pass item when it's done variable == true
        


    public void setInitialLocation(int x, int y) {
        this.initialX = x;
        this.initialY = y;
        updateBoxCollider();
    }

    public void addItem(Item item) {
        eatenItems.add(item);
      
        }

    public void placeBomb() {
        List<Bomb> bombList = EntityManager.getInstance().bombs;

        double centerX = bomberBox.getX() + bomberBox.getWidth() / 2;
        double centerY = bomberBox.getY() + bomberBox.getHeight() / 2;

        if (LeverMap.getInstance().getHashAt((int) centerY / GameConfig.SIZE_BLOCK, (int) centerX / GameConfig.SIZE_BLOCK)
            != LeverMap.getInstance().getHash("grass")) {
            return;
        }
        if (bombList.size() < bombMax) {
            int bombX = ((int) centerX / GameConfig.SIZE_BLOCK) * GameConfig.SIZE_BLOCK;
            int bombY = ((int) centerY / GameConfig.SIZE_BLOCK) * GameConfig.SIZE_BLOCK;

            boolean hasBomb = false;
            for (Bomb bomb : bombList) {
                if (bomb.getX() == bombX && bomb.getY() == bombY) {
                    hasBomb = true;
                    break;
                }
            }
            if (!hasBomb) {
                

                LeverMap.getInstance().setHashAt(
                        bombY / GameConfig.SIZE_BLOCK,
                        bombX / GameConfig.SIZE_BLOCK,
                        "bomb");
            }
        }
    }

    public void setBombMax(int bombMax) {
        this.bombMax = bombMax;
    }

    public int getBombMax() {
        return bombMax;
    }

    public void setCanPassBrick(boolean canPassBrick) {
        this.canPassBrick = canPassBrick;
    }

    public void setCanResetLocation(boolean canResetLocation) {
        this.canResetLocation = canResetLocation;
    }

    public void setCanPassFlame(boolean canPassFlame) {
        this.canPassFlame = canPassFlame;
    }

    public boolean isCanPassFlame() {
        return canPassFlame;
    }

    public void setCanPassBomb(boolean canPassBomb) {
        this.canPassBomb = canPassBomb;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public Action getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(Action playerAction) {
        this.playerAction = playerAction;
    }

    public BoxCollider getBomberBox() {
        return bomberBox;
    }

    public void resetLocation() {
        if (canResetLocation) {
            LeverMap levelMap = LeverMap.getInstance();
            double centerX = bomberBox.getX();
            double centerY = bomberBox.getY();
            int jBomber = ((int) centerX / GameConfig.SIZE_BLOCK);
            int iBomber = ((int) centerY / GameConfig.SIZE_BLOCK);
            boolean checkL = true;
            boolean checkR = true;
            boolean checkU = true;
            boolean checkD = true;
            if (levelMap.getHashAt(iBomber, jBomber) == levelMap.getHash("brick")) {
                int i = 1;
                while (true) {
                    if (jBomber - i >= 0) {
                        if (levelMap.getHashAt(iBomber, jBomber - i) == levelMap.getHash("wall")) {
                            checkL = false;
                        }
                        if (levelMap.getHashAt(iBomber, jBomber - i) == levelMap.getHash("grass") && checkL) {
                            x -= speed;
                            break;
                        }
                    }
                    if (jBomber + i < levelMap.getMapHash()[0].length) {
                        if (levelMap.getHashAt(iBomber, jBomber + i) == levelMap.getHash("wall")) {
                            checkR = false;
                        }
                        if (levelMap.getHashAt(iBomber, jBomber + i) == levelMap.getHash("grass") && checkR) {
                            x += speed;
                            break;
                        }
                    }
                    if (iBomber - i >= 0) {
                        if (levelMap.getHashAt(iBomber - i, jBomber) == levelMap.getHash("wall")) {
                            checkU = false;
                        }
                        if (levelMap.getHashAt(iBomber - i, jBomber) == levelMap.getHash("grass") && checkU) {
                            y -= speed;
                            break;
                        }
                    }
                    if (iBomber + i < levelMap.getMapHash().length) {
                        if (levelMap.getHashAt(iBomber + i, jBomber) == levelMap.getHash("wall")) {
                            checkD = false;
                        }
                        if (levelMap.getHashAt(iBomber + i, jBomber) == levelMap.getHash("grass") && checkD) {
                            y += speed;
                            break;
                        }
                    }
                    i++;
                }
                updateBoxCollider();
            } else {
                canResetLocation = false;
            }
        }
    }
    public void reset() {
        eatenItems.clear();
        canPassBrick = false;
        canPassBomb = false;
        canPassFlame = false;
    }
}
