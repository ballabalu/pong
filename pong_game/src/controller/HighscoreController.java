/**
 * Klasse HighscoreController: enthällt Konstruktoren und ActionListener für HighscoreView
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Highscore;
import view.HighscoreView;
import view.MenueView;
import view.PongView;


public class HighscoreController implements ActionListener{
	
	/** 
	 * Instanzen der Klassen Highscore und HighscoreView werden erzeugt
	 */
	private Highscore highscore = new Highscore();
	private HighscoreView highscoreView;
	
	
	
	/**
	 * Parameterloser Konstuktor für den HighscoreController
	 * erzeugt neuen Highscore
	 */
	public HighscoreController(){
		this.highscore = new Highscore();
	}
	
	
	
	/**
	 * Konstuktor für den HighscoreController mit Parameterübergabe
	 * erzeugt neuen Highscore und nimmt übergebene HighscoreView entgegen
	 * @param highscoreview 	HighscoreView mit der der HighscoreController arbeitet
	 */
	public HighscoreController(HighscoreView highscoreview){
		this.highscore = new Highscore();
		this.highscoreView = highscoreview;
	}

	
	
	/**
	 * nimmt ActionEvent entgegen und reagiert so auf Button-Klicks
	 * Zeigt wahlweise neues Spiel oder Menü an, bzw übermittelt vom Nutzer eingegebene Daten an Server
	 * @param e		übergebenes ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = (String) e.getActionCommand();
		
		if( actionCommand == "eintragen"){
        	this.highscoreView.postUpdatedHighscoreToServer(this.highscore);
        }else if(actionCommand == "jetzt spielen!"){
        	PongView pong = new PongView("Pong - The Game");
			pong.init(new GameController());
			HighscoreView.hsView.dispose();
        }else if(actionCommand == "zurück zum Menü"){
        	MenueView pong = new MenueView("Pong - The Game");
    		pong.init(new Controller());
			HighscoreView.hsView.dispose();
        }
	}
	
}
