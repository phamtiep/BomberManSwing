package Bomb;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Enitity.Entity;
import uet.oop.bomberman.components.graphics.Sprite;
import uet.oop.bomberman.core.Timers;

public class Flame extends Entity {

   static sprite 
    


    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        
    }
    
   @Override
   public void update() {
       time += Timers.getInstance().getDeltaTime();
       if (time <= flameTime) {
           switch (flameDirection) {
               case UP:
                   if (last) {
                       System.out.println("up");

                       image = Sprite.animation(explosionVerticalTopLast, time, flameTime);
                   } else {
                                              image = Sprite.animation(explosionVertical, time, flameTime);
                   }
                   break;
               case DOWN:
                   if (last) {
                       image = Sprite.animation(explosionVerticalDownLast, time, flameTime);
                   } else {
                       System.out.println("down");
                       image = Sprite.animation(explosionVertical, time, flameTime);
                   }
                   break;
               case LEFT:
                   if (last) {
                       image = Sprite.animation(explosionHorizontalLeftLast, time, flameTime);
                   } else {
                       image = Sprite.animation(explosionHorizontal, time, flameTime);
                   }
                   break;
               case RIGHT:
                   if (last) {
                       image = Sprite.animation(explosionHorizontalRightLast, time, flameTime);
                   } else {
                       image = Sprite.animation(explosionHorizontal, time, flameTime);
                   }
                   break;
               case CENTER:
                   image = Sprite.animation(bombExploded, time, flameTime);
                   break;
               default:
                   break;
           }
       } else {
           image = null;
           done = true;
       }
   }
 
}