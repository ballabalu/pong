package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Player implements Serializable, Comparable<Player>, Observer {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String playerName;
	private int playerScore;
	private Paddle paddle;
	private Ball ball;
	
	public Player(){
		this.playerName = "";
		this.playerScore = 0;
	}
	
	
	public Player (String playerName, int playerScore){
		this.playerName= playerName;
		this.playerScore = playerScore;
		
	}
	
	public int getScore(){
		return playerScore;
	}
	
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void setScore(int score){
		this.playerScore = score;
	}
	
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	
	public void increaseScore(int punkte){
		this.playerScore = this.playerScore + punkte;
	}
	
	
	public int compare(Player p1, Player p2) {
		int  scorePlayer1 = p1.getScore();
		int scorePlayer2 = p2.getScore();
		
		if (scorePlayer1 > scorePlayer2){
			return -1;
		}else if  (scorePlayer1 < scorePlayer2){
			return 1;
		}else{
			return 0;
		}
		
		 
	}

	@Override
	public int compareTo(Player p2) {
		int scorePlayer1 = this.getScore();
		int scorePlayer2 = p2.getScore();
		
		if (scorePlayer1 > scorePlayer2){
			return -1;
		}else if  (scorePlayer1 < scorePlayer2){
			return 1;
		}else{
			return 0;
		}
		
		
	}
	
	@Override
	public String toString(){
		String spielerString = "";
		String name = this.getPlayerName();
		int zeichen = name.length();
		if (zeichen >= 13){
			name = name.substring(0, 13);
			spielerString = name + "... \t " + this.getScore() + " Punkte ";
		// 1-11 Zeichen
		}else if (zeichen >10){
			spielerString = name + " \t " + this.getScore() + " Punkte ";
		}else if (zeichen >3){
			spielerString = name + "\t \t " + this.getScore() + " Punkte ";
		}else {
			spielerString = name + "\t \t \t " + this.getScore() + " Punkte ";
		}
		
		return spielerString;
	}


	@Override
	public void update(Observable o, Object arg) {
		// bei Observerupdate Punktestand increasen
		System.out.println("Der Observer hat getroffen - Nachricht aus Player: " + arg);
		int p = (Integer) arg;
		if (p != 0){
			
			this.increaseScore(p);
			
			//************* Test für Observer *********************
			System.out.println("Punkte: " + this.playerScore);
			//************* Test für Observer *********************
			
		}

		
	}


	
}
