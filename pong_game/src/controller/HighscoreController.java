package controller;
 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Highscore;
import view.HighscoreView;
import view.MenueView;
import view.PongView;


public class HighscoreController implements ActionListener{

	private Highscore highscore = new Highscore();
	private HighscoreView highscoreView;
	
	public HighscoreController(){
		this.highscore = new Highscore();
	}
	
	public HighscoreController(HighscoreView highscoreview){
		this.highscore = new Highscore();
		this.highscoreView = highscoreview;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = (String) e.getActionCommand();
		
		if( actionCommand == "eintragen"){
        	System.out.println("-----> in Highscore eintragen -> Server-----------");  
        	this.highscoreView.postUpdatedHighscoreToServer(this.highscore);
         
        }else if(actionCommand == "jetzt spielen!"){
        	PongView pong = new PongView("Pong - The Game");
			pong.init(new GameController());
			highscoreView.dispose();
        }else if(actionCommand == "zurück zum Menü"){
        	MenueView pong = new MenueView("Pong - The Game");
    		pong.init(new Controller());
			highscoreView.dispose();
        }
	}
	
}
