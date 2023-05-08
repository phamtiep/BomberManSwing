package StateManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Config.GameConfig;
import Main.*;

public class StateManager {
    public enum State{
        MENU,GAME
    }
    private static StateManager instance = null;
    public final MenuState menuState;
    public final GameState gameState;
    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
           
        }
        return instance;
    }
    private StateManager() {
      
        this.menuState = new MenuState();
        this.gameState = new GameState();
        menuState.setSize(GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
        Main.getInstance().add(gameState);
        gameState.add();
        Main.getInstance().add(menuState);
    }
    public void setCurrentState(State inputState) {
        if(inputState == State.MENU) {
            this.menuState.setVisible(true);
            this.gameState.setVisible(false);
            menuState.setFocusable(true);
            gameState.setFocusable(false);
            this.gameState.requestFocusInWindow();
        }
        else {
            this.menuState.setVisible(false);
            this.gameState.setVisible(true);
            gameState.setFocusable(true);
            this.gameState.requestFocusInWindow();
            menuState.setFocusable(false);
        }
        
    }

}
