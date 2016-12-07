package model;
  

/* Player.java playe*/

public class Spieler implements Comparable<Spieler> {
 
	private String playerName;
	private int playerScore;
	
	
	public Spieler (String playerName, int playerScore){
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
	
	
	public int compare(Spieler p1, Spieler p2) {
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
	public int compareTo(Spieler p2) {
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
		spielerString = this.getPlayerName() + ": " + this.getScore() + " Punkte! ";
		return spielerString;
	}
	
}
