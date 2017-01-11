package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Player;
import view.HighscoreView;
import view.MenueView;
import view.PongView;

 
public class Controller implements ActionListener {
	
	Player player = new Player("", 8);

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Spiel starten"){
			PongView pong = new PongView("Pong - The Game");
			pong.init();
			MenueView.pong.dispose();
        }		
        else if(e.getActionCommand() == "Highscores"){
        	System.out.println("highscore");
        	HighscoreView highscoreView = new HighscoreView("Highscores", player);
        	highscoreView.init(new HighscoreController(highscoreView));
        	
			MenueView.pong.dispose();
        }
		
		//******** zum Testen ************
        else if(e.getActionCommand() == "Highscores (aus PongView)"){
        	System.out.println("highscore");
        	HighscoreView highscoreView = new HighscoreView("Highscores", player);
        	highscoreView.init(new HighscoreController(highscoreView));
        	
			MenueView.pong.dispose();
        }
		//******** zum Testen ************
		
        else if(e.getActionCommand() == "Spiel beenden"){
        	System.exit(0);
        }
		
	}
 
	
}


    

	

