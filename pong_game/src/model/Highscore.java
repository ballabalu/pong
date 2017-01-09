package model;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;

 
public class Highscore {

	private ArrayList<Player> highscore;
	
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	
	private static final String HIGHSCORE_FILE = "pongHighscore.txt";
	
	public Highscore(){
		highscore = new ArrayList<Player>();
	}
	
	public Highscore(ArrayList<Player> al){
		this.highscore = al;
	}
	
	public ArrayList<Player> getHighscore(){
		return highscore;
	}
	
	public void addPlayer(String name, int score){
		highscore.add(new Player(name, score));
	}
	
	public void addPlayer(Player player){
		highscore.add(player);
	}
	
	public void sort(){
		Collections.sort(this.getHighscore());
		System.out.println("sort(): " + this.highscore.toString());
	}
	
	public void addPlayerAndSortHighscore(String name, int score){
		highscore.add(new Player(name, score));
		sort();
	}
	
	public void addPlayerAndSortHighscore(Player player){
		highscore.add(player);
		sort();
	}
	 
	@Override
	public String toString(){
		String highscoreString = "";
		int i = 1;
		for (Player s : this.getHighscore()){
			highscoreString +=  i + ". \t" + s.toString() + "\n";
			i++;
		}
		return highscoreString;
	}
	
	
	public void loadLocalHighscoreFile(){
		System.out.println("loadScoreFile");
		ArrayList<Player> loadedHighscore = null;
		
		try{
			inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			loadedHighscore = (ArrayList<Player>) inputStream.readObject();
			//return loadedHighscore;
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
		this.highscore = loadedHighscore;
	}
	
	
	public void updateLocalScoreFile() {
		System.out.println("updateScoreFile");
		try{
			outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			outputStream.writeObject(this.highscore);
		}catch (FileNotFoundException e){
			System.out.println("FILE NOT FOUND: " + e.getMessage()) ;
		}catch (IOException e){
			System.out.println("IOException: " + e.getMessage()) ;
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

	public void loadHighscoreFromServer() {
		NetworkConnection networkConnection = new NetworkConnection("http://erdbeerwelt.com/mio/pongHighscore.txt");
		try {
			this.highscore = networkConnection.getHighscoreFromURL();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void saveHighscoreOnServer(){
		NetworkConnection networkConnection = new NetworkConnection("http://oue.st/a/feo.php");
		try {
			//Highscore highscore2 = new Highscore( urlreader2.getHighscoreFromURL());
			
			networkConnection.sendPost(this.highscore);
		} catch (Exception e) {
			e.printStackTrace();
			
		}	

	}
	
	
	  
    public JSONArray toJson(){
		JSONArray jsonArray = new JSONArray();
		//JsonArray value = Json.createArrayBuilder();
    	int highscoresize = this.highscore.size();
    	
    	for (int i= 0; i < highscoresize; i++){
    		String player =this.highscore.get(i).getPlayerName();
    		int punkte = this.highscore.get(i).getScore();
    		jsonArray.put(player);
    		
    	}
    	
    	System.out.println(jsonArray);
    	return null;
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
