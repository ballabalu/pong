package model;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {
 
	private String playerName;
	private int playerScore;
	
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
	
	public void increaseScore(){
		this.playerScore = this.playerScore +1;
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
		spielerString = this.getPlayerName() + ": \t" + this.getScore() + " Punkte! ";
		return spielerString;
	}
	
}
