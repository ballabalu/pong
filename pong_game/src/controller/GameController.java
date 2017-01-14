package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    
    private boolean up;
    private boolean down;
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        // Pfeil nach oben = Keycode 38
        if (e.getKeyCode() == 38) {
            up = true;
            down = false;
            
            //Pfeil nach unten = Keycode 40
        } else if (e.getKeyCode() == 40){
            up = false;
            down = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        up = false;
        down = false;
        
    }
    
    public boolean getUp() {
        return up;
    }
    
    public boolean getDown() {
        return down;
    }
    
}
