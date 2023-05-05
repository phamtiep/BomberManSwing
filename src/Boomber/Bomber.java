package Boomber;

import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

import Config.Action;
import Config.Direction;
import Config.GameConfig;
import Enitity.BoxCollider;
import Enitity.EntityManager;
import Enitity.Item;
import Enitity.LivingEntity;
import Graphics.Animation;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Map.LevelMap;

public class Bomber extends LivingEntity {
    
    private static boolean initialized = false;
    public static final double DEFAULT_SPEED = 2;
    public static final int DEFAULT_LIVES = 5;
    public static Image currentSprite ;
    private final List<Item> eatenItems = new ArrayList<>();
    
    
    public static  Animation animationLeft;
    public static Animation animationRight;
    public static Animation animationDown;
    public static Animation animationUp;
    
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
    int leftCol ;
    int rightCol ;
    int topRow ;
    int bottomRow;
    Action playerAction;
    static String  path = "/final.png";

 //   protected Action playerAction = Action.IDLE;
   
    protected final BoxCollider bomberBox;

    public Bomber(int x, int y) {
        super(x, y);
        initialX = x;
        initialY = y;
        this.x = x ;
        this.y = y;
        speed = DEFAULT_SPEED;
     
        init();
       
        currentDirection = Direction.DOWN;
       // lives = DEFAULT_LIVES;
        playerAction = Action.MOVING;
        
        bomberBox = new BoxCollider(x, y,26,35);
        updateBoxCollider();
        
    }

    public void updateBoxCollider() {
        bomberBox.setLocation(
                this.x ,
                this.y 
        );
    }

    public static void init() {
        try {
            animationLeft = new Animation(path,5, 2, 750_000_000, 0,26,35);
            animationLeft.setLoop(true);
            animationRight = new Animation(path,5, 3, 750_000_000, 0,26,35);
            animationRight.setLoop(true);
            animationUp = new Animation(path,3, 0, 750_000_000, 0,26,35);
            animationUp.setLoop(true);
            animationDown = new Animation(path,3, 1, 750_000_000, 0,26,35);
            animationDown.setLoop(true);
        }
        catch(Exception e) {
            System.out.println(animationLeft == null );
        }
      
    }

    @Override
    public void render(Graphics g) {
        switch (currentDirection) {
        case UP:
            animationUp.render(g,x, y);
            animationUp.update();
            break;
        case DOWN:
            animationDown.render(g,x, y);
            animationDown.update();
            break;
        case LEFT:
            animationLeft.render(g,x, y);
            animationLeft.update();
            break;
        case RIGHT:
            animationRight.render(g,x, y);
            animationRight.update();
            break;
        default:
            break;
        }
    }

    @Override
    public void update() {
       
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
//
//    public void placeBomb() {
//        List<Bomb> bombList = EntityManager.getInstance().bombs;
//
//        double centerX = bomberBox.getX() + bomberBox.getWidth() / 2;
//        double centerY = bomberBox.getY() + bomberBox.getHeight() / 2;
//
//        if (LevelMap.getInstance().getHashAt((int) centerY / GameConfig.SIZE_BLOCK, (int) centerX / GameConfig.SIZE_BLOCK)
//            != LeveMap.getInstance().getHash("grass")) {
//            return;
//        }
//        if (bombList.size() < bombMax) {
//            int bombX = ((int) centerX / GameConfig.SIZE_BLOCK) * GameConfig.SIZE_BLOCK;
//            int bombY = ((int) centerY / GameConfig.SIZE_BLOCK) * GameConfig.SIZE_BLOCK;
//
//            boolean hasBomb = false;
//            for (Bomb bomb : bombList) {
//                if (bomb.getX() == bombX && bomb.getY() == bombY) {
//                    hasBomb = true;
//                    break;
//                }
//            }
//            if (!hasBomb) {
//                
//
//                LeverMap.getInstance().setHashAt(
//                        bombY / GameConfig.SIZE_BLOCK,
//                        bombX / GameConfig.SIZE_BLOCK,
//                        "bomb");
//            }
//        }
//    }

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


    @Override
    public void move() {
       
        double steps = speed;
        if (playerAction == Action.IDLE || playerAction == Action.DEAD) {
            return;
        }

        switch (currentDirection) {
            case DOWN:
                y += steps;
                break;
            case UP:
               steps = -steps;
                y += steps;
                break;
            case RIGHT:
                x += steps;
                break;
            case LEFT:
                steps = -steps;
                x += steps;
                break;
        }

        LevelMap levelMap = LevelMap.getInstance();
        if ((x < 0) || (x + GameConfig.SIZE_BLOCK > levelMap.getWidth())) {
            x -= steps;     //Move back
        }

        if ((y < 0) || (y + GameConfig.SIZE_BLOCK  > levelMap.getHeight())) {
            y -= steps;     //Move back
        }

        updateBoxCollider();

        leftCol = (int) (bomberBox.getX()) / GameConfig.SIZE_BLOCK;
        rightCol = (int) (bomberBox.getX() + bomberBox.getWidth()) /  GameConfig.SIZE_BLOCK;
        topRow = (int) (bomberBox.getY()+16) /  GameConfig.SIZE_BLOCK;
        bottomRow = (int) (bomberBox.getY() + bomberBox.getHeight()) /  GameConfig.SIZE_BLOCK;
        //System.out.println(currentDirection);
        //Barrier checker.
        boolean topLeftCheck = checkBarrier(topRow, leftCol);
        boolean topRightCheck = checkBarrier(topRow, rightCol);
        boolean bottomLeftCheck = checkBarrier(bottomRow, leftCol);
        boolean bottomRightCheck = checkBarrier(bottomRow, rightCol);

        switch (currentDirection) {
            case UP:
                if (topLeftCheck || topRightCheck) {
                    y -= steps;
                }
                break;
            case DOWN:
                if (bottomLeftCheck || bottomRightCheck) {
                    y -= steps;
                    //System.out.println(bottomRow + " " + rightCol);
                }
                break;
            case RIGHT:
                if (topRightCheck || bottomRightCheck) {
                    x -= steps;
                }
                break;
            case LEFT:
                if (topLeftCheck || bottomLeftCheck) {
                    x -= steps;
                }
                break;
        }
    }
    private boolean checkBarrier(int i, int j) {
        LevelMap levelMap = LevelMap.getInstance();
//        if (levelMap.getHashAt(i, j) == levelMap.getHash("grass")) {
//            System.out.println("grass");
//        }
        
        if (levelMap.getHashAt(i, j) == levelMap.getHash("bomb")) {
            
            return !canPassBomb;
            
        }
        if (levelMap.getHashAt(i, j) == levelMap.getHash("brick")) {
            
            return !canPassBrick;
            
        }

//      if(levelMap.getHashAt(i, j)== levelMap.getHash("wall")) {
//          System.out.println(x + " " + y);
//          System.out.println( i + " " + j);
//      }
        return levelMap.getHashAt(i, j) == levelMap.getHash("wall");
    }
    public void reset() {
        eatenItems.clear();
        canPassBrick = false;
        canPassBomb = false;
        canPassFlame = false;
    }

}
