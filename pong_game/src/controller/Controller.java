/**
 * Controller.java definiert die Aktionen, 
 * die beim Klick auf die Buttons in der MenueView.java aufgerufen werden.
 * Controller-Klasse implementiert die Schnittsctelle ActionListener.
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Player;
import view.HighscoreView;
import view.MenueView;
import view.PongView;


public class Controller implements ActionListener {
	
	/**
	 * Instanz der Klasse Player wird erstellt
	 */
	Player player = new Player();

	
	/**
	 * Aktionen/Button-Klicks, die die verschiedenen Views aufrufen. 
	 * @param ActionEvent e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Spiel starten"){
			PongView pong = new PongView("Pong - The Game");
			pong.init(new GameController());
			MenueView.pong.dispose();
        }	
		
        else if(e.getActionCommand() == "Highscores"){
        	HighscoreView highscoreView = new HighscoreView("Highscores");
        	highscoreView.init(new HighscoreController(highscoreView));
			MenueView.pong.dispose();
        }
		
		
        else if(e.getActionCommand() == "Spiel beenden"){
        	System.exit(0);
        }
		
	}
 
	
}


    

	

