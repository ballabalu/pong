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


    

	

