package controller;
 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Highscore;
import model.Player;
import view.HighscoreView;
import view.MenueView;
import view.PongView;


public class HighscoreController implements ActionListener{

	private Highscore highscore = new Highscore();
	private HighscoreView highscoreView;
	
	private static final String HIGHSCORE_FILE = "pongHighscore.txt";
	
	//ObjectOutputStream outputStream = null;
	//ObjectInputStream inputStream = null;
	
	public HighscoreController(){
		this.highscore = new Highscore();
		
	}
	
	public HighscoreController( HighscoreView highscoreview){
		this.highscore = new Highscore();
		this.highscoreView = highscoreview;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = (String) e.getActionCommand();
		
        if(actionCommand == "in Highscore eintragen"){
        	System.out.println("-----> in Highscore eintragen!-----------");  
        	this.highscoreView.addPlayer(this.highscore);
        }else if(actionCommand == "Highscore laden"){
        	System.out.println("-----> Highscore laden! -----------");   
        	this.highscoreView.showHighscoreInTextarea(this.highscore);
        	
        }else if( actionCommand == "test"){
        	System.out.println("-----> Test-Button-----------");  
        	
         
        }
        
	}
	
	
public static void showHighscore (){
		
		HighscoreView hsView = new HighscoreView("Highscore!");
		hsView.init(new Controller());
		
		Highscore highscore = new Highscore();
		
		highscore.addPlayer("a", 1);
		highscore.addPlayer("b", 5);
		highscore.addPlayer("c", 3);
		highscore.addPlayer("d", 2);
		
		System.out.println(highscore.toString());
		
		highscore.sort();
		
		System.out.println(highscore.toString());
		
		Player p1 = new Player("p1" , 9);
		highscore.addPlayerAndSortHighscore(p1);
		System.out.println(highscore.toString());
		
		Player p2 = new Player("p2" , 10);
		p2.setScore(20);
		p2.setScore(4);
		highscore.addPlayerAndSortHighscore(p2);
		System.out.println(highscore.toString());
		
		
		Player p3 = new Player();
		System.out.println(p3.toString());
		
		p3.increaseScore();
		System.out.println(p3.toString());
		
		p3.setPlayerName("p3");
		System.out.println(p3.toString());
		
		System.out.println("-----------");
		highscore.addPlayerAndSortHighscore(p3);
		System.out.println(highscore.toString());
		
		
		highscore.updateLocalScoreFile();
		
		
		highscore.loadLocalHighscoreFile();
		
		
		System.out.println(highscore.toString());
		
		
		Highscore highscore2 = new Highscore();
		highscore2.loadLocalHighscoreFile();
		highscore2.addPlayerAndSortHighscore("hs2-p1", 50);
		System.out.println(highscore2.toString());
		highscore2.updateLocalScoreFile();
		

		System.out.println("hs3------");
		Highscore highscore3 = new Highscore();
		highscore3.loadLocalHighscoreFile();
		System.out.println(highscore3.toString());
		
		//Highscore highscoreLocal = new Highscore();
		//highscoreLocal.loadLocalHighscoreFile();
		//System.out.println(highscoreLocal.toString());
	}
	
	
	
	/*public ArrayList<Spieler> getScores(){
		System.out.println("getScores");
		ArrayList<Spieler> highscoreArrayList = highscore.getPlayerList();
		sort();
		return highscoreArrayList;
		
	}
	*/

	/*public void addPlayer(Spieler player){
		System.out.println("HighscoreController: addPlayer");
		//loadScoreFile();
		highscore.addPlayer(player);
		//updateScoreFile();
	}
	*/
	
	/*public void addPlayer(String name, int score){
		System.out.println("HighscoreController: addPlayer");
		//loadScoreFile();
		highscore.addPlayer(name, score);
		//updateScoreFile();
	}
	*/
	
	/* private void loadScoreFile() {
		System.out.println("loadScoreFile");
		try{
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			ArrayList<Spieler> sortplayerList = (ArrayList<Spieler>) inputStream.readObject();
		}catch (FileNotFoundException e){
			System.out.println("FILE NOT FOUND: )" + e.getMessage()) ;
		}catch (IOException e){
			System.out.println("IOException: )" + e.getMessage()) ;
		}catch (ClassNotFoundException e){
			System.out.println("ClassNotFoundException: )" + e.getMessage()) ;
		}finally{
			try {
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException e){
				System.out.println("IO - ERROR: )" + e.getMessage()) ;
			}
		}
		
	}
	*/
	/*private void updateScoreFile() {
		System.out.println("updateScoreFile");
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(playerList);
		}catch (FileNotFoundException e){
			System.out.println("FILE NOT FOUND: )" + e.getMessage()) ;
		}catch (IOException e){
			System.out.println("IOException: )" + e.getMessage()) ;
		}finally{
			try {
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException e){
				System.out.println("IO - ERROR: )" + e.getMessage()) ;
			}
		}
		
	}
	*/

	/*public String getHighscoreString(){
		System.out.println("getHighscoreString");
		
		String highscoreString = "";
		int maxScores = 10;
		
		ArrayList<Spieler> player = null;
		player = getScores();
		
		System.out.println("player = getScores(); " + player);
		
		int i = 0;
		int size = player.size();
		
		if(size > maxScores){
			size = maxScores;
		}
		
		while (i < size){
			System.out.println(player.get(i).getPlayerName() + " " + player.get(i).getScore());
			highscoreString +=  (i+1) +  "." + player.get(i).getPlayerName() +  " " + player.get(i).getScore() + " Punkte ------ ";
			i++;
		}
		return highscoreString;
		
	}
	*/
	
	

	
}
