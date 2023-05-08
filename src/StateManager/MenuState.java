package StateManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Config.GameConfig;
import Enitity.EntityManager;
import Graphics.SpriteSheet;
import Main.Main;
import StateManager.GameState.Stage;
import StateManager.StateManager.State;

public class MenuState extends JPanel {

	protected BufferedImage menu , background; 
	protected SpriteSheet spriteSheet; 
	protected SpriteSheet spriteSheet2; 
	protected MouseListener myMouse;
	protected MouseMotionListener mouseCheck; 
	protected BufferedImage[] PLAY , OPPTION, QUIT; 
	protected int p = 0 , o = 0 , q = 0; 
	
	
	public MenuState() {
        this.setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        initMyMouse(); 
        mouseLocation(); 
        repaint();
    }
	
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        initBackGroud(g); 
        initButtons(g);
    }
    private void mouseLocation() {
       mouseCheck = new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(checkMouse(e.getX(), e.getY()) == 1) {
					p = 1;
					o = 0; 
					q = 0; 
					 
				}else if (checkMouse(e.getX(), e.getY()) == 2) {
					p = 0; 
					o = 1; 
					q = 0; 
				}else if (checkMouse(e.getX(), e.getY()) == 3) {
					p = 0 ; 
					o  = 0; 
					q = 1; 
				}
				else {
					p = 0;
					o = 0; 
					q = 0; 
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		this.addMouseMotionListener(mouseCheck);
	
	}


	private void initButtons(Graphics g) {
    	PLAY = new BufferedImage[2]; 
    	OPPTION = new BufferedImage[2]; 
    	QUIT = new BufferedImage[2]; 		
    	spriteSheet2 = new SpriteSheet("/button_atlas.png"); 
    	for(int i = 0; i < 2; i++) {
    		PLAY[i] = spriteSheet2.getImg().getSubimage(i*141, 0, 141, 56); 
    		OPPTION[i] = spriteSheet2.getImg().getSubimage(i*141,56,141, 56); 
        	QUIT[i] = spriteSheet2.getImg().getSubimage(i*141,112,141, 56); 
    	}
    	g.drawImage(PLAY[p],GameConfig.WIDTH / 2 - 80/ 2, GameConfig.WIDTH / 2 -100 , null); 
    	g.drawImage(OPPTION[o],GameConfig.WIDTH / 2 - 80/ 2, GameConfig.WIDTH / 2 -50 , null); 
    	g.drawImage(QUIT[q],GameConfig.WIDTH / 2 - 80/ 2, GameConfig.WIDTH / 2, null); 
		
	}


	private void initMyMouse() {
		myMouse = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkMouse(e.getX(), e.getY()) == 1 ) {
				   GameState.gameStage = Stage.Playing;
				   EntityManager.getInstance().reset();
					StateManager.getInstance().setCurrentState(State.GAME);
					
				}
				else if(checkMouse(e.getX(), e.getY()) == 3){
					System.exit(0);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		this.addMouseListener(myMouse);
		
	}
	private void initBackGroud(Graphics g) {
		spriteSheet2 = new SpriteSheet("/bombermangamebg.png"); 
		spriteSheet = new SpriteSheet("/menu.png"); 
		background = spriteSheet2.getImg(); 
		menu = spriteSheet.getImg(); 
		g.setColor(Color.BLACK); 
		g.fillRect(0, 0, GameConfig.WIDTH + 32*2, GameConfig.HEIGHT + 32);
		g.drawImage(background , 0 , 0 ,this.getWidth() , this.getHeight(), null); 
		g.drawImage(menu,GameConfig.WIDTH / 2 - 200 / 2, GameConfig.WIDTH / 2 - 402 / 2 , 256 , 402 , null); 
	}
	
	public int checkMouse(int dx , int dy) {
		if(dx >= GameConfig.WIDTH / 2 - 80/ 2 && dx <= GameConfig.WIDTH / 2 - 80/ 2 + 141 && dy >= GameConfig.WIDTH / 2 - 100 && dy <= GameConfig.WIDTH / 2 - 100 + 56) {
		    return 1; 
		}
		else if (dx >= GameConfig.WIDTH / 2 - 80/ 2 && dx <= GameConfig.WIDTH / 2 - 80/ 2 + 141 && dy >= GameConfig.WIDTH / 2 - 50 && dy <= GameConfig.WIDTH / 2 - 50 + 56 ) {
			return 2; 
		}
		else if (dx >= GameConfig.WIDTH / 2 - 80/ 2 && dx <= GameConfig.WIDTH / 2 - 80/ 2 + 141 && dy >= GameConfig.WIDTH / 2 && dy <= GameConfig.WIDTH / 2  + 56) {
			return 3; 
		}
		return 0; 
	}

}
