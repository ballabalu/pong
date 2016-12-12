package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MenueView;
import view.PongView;


public class Controller implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Spiel starten"){
			PongView pong = new PongView("Pong - The Game");
			pong.init();
			MenueView.pong.dispose();
        }		
        else if(e.getActionCommand() == "Highscores"){
        	System.out.println("highscore");
        }
        else if(e.getActionCommand() == "Spiel beenden"){
        	System.exit(0);
        }
		
	}
 
	
}

 /*
 
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Pitch;
import view.PongView;

public class Controller {

	
	
    /*private Pitch pitch;
    private PongView pongview;
    private ActionListener actionListener;
    
    public Controller(Pitch pitch, PongView pongview){
        this.pitch = pitch;
        this.pongview = pongview;
                          
    }
    
    public void control(){                     
    	pongview.getBtnRedraw().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {                  
          	  pongview.getRechti().setColor(pitch.getColor());
          	  pongview.getRechti().setSize(pitch.getWidth(), pitch.getHeight());
            }
    	});
                     
        pongview.changeColor().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {                  
          	  if (pitch.getColor() == Color.blue){
          		pitch.setColor(Color.red);
          	  } else {
          		pitch.setColor(Color.blue); 
          	  }
            }
        }); 
    }*/
    

	

