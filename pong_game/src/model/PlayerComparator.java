package model;

import java.util.Comparator;
 

public class PlayerComparator implements Comparator<Player>{

	@Override
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

	
}
