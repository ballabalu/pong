package model;

public class HighscoreTester {

	
	public static void main (String[] args){
		Highscore highscore = new Highscore();
		highscore.scoreHinzufügen("aaa", 200);
		highscore.scoreHinzufügen("bbb", 300);
		highscore.scoreHinzufügen("ccc", 300);
		highscore.scoreHinzufügen("ddd", 1000);
		highscore.scoreHinzufügen("eee", 100);
		highscore.scoreHinzufügen("fff", 1200);
		highscore.scoreHinzufügen("ggg", 1250);
		highscore.scoreHinzufügen("hhh", 120);
		highscore.scoreHinzufügen("iii", 600);
		highscore.scoreHinzufügen("jjj", 20);
		highscore.scoreHinzufügen("kkk", 100);
		
		System.out.print(highscore.getHighscoreString());
		
	}
	 
	
	/*
	public static void main (String[] args){
		System.out.println("ScoreTester");
		
		Score_alt globalScore = new Score_alt();
		
		Player player1 = new Player(1, "aaaa");
		Player player2 = new Player(2, "bbbb");
		
		System.out.println(player1.getPlayerName() + ": " + player1.getPlayerScore());
		
		System.out.println("player1.increasePlayerScore()");
		player1.increasePlayerScore();
		System.out.println(player1.getPlayerName() + ": " + player1.getPlayerScore());
		
		System.out.println();
		System.out.println("globalScore.printScore();");
		globalScore.printScore();
		
		System.out.println();
		System.out.println("globalScore.addPlayer();");
		globalScore.addPlayer(player1);
		globalScore.addPlayer(player2);
		globalScore.printScore();
		
		System.out.println();
		System.out.println("player1.increasePlayerScore() + player2.increasePlayerScore()");
		player1.increasePlayerScore();
		player2.increasePlayerScore();
		globalScore.printScore();
		
		System.out.println();
		System.out.println("getWinner()");
		Player player3 = new Player(1, "cccc");
		Player player4 = new Player(2, "dddd");
		globalScore.addPlayer(player3);
		globalScore.addPlayer(player4);
		globalScore.printScore();
		System.out.println("Winner: " + globalScore.getWinner().getPlayerName());
	}
	
	*/
}
