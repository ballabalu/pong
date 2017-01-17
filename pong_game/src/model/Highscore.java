package model;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

 
public class Highscore {

	ArrayList<Player> highscore;
	
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	
	//private static final String HIGHSCORE_FILE = "pongHighscore.txt";
	
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
			highscoreString +=  i + ".    " + s.toString() + "\n";
			i++;
		}
		return highscoreString;
	}
	
	/*
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
	*/
	
	
	/*
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
	*/

	public void loadHighscoreFromServer() throws Exception {
		System.out.println("loadHighscoreFromServer");
		getJsonAndSetAsHighscore();
	}
	
	public void getJsonAndSetAsHighscore() throws Exception{
		System.out.println("getJsonAndSetAsHighscore");
		NetworkConnection networkConnection = new NetworkConnection("http://erdbeerwelt.com/mio/paf/hs.txt");
		String jsonString = "";
		
		
		jsonString = networkConnection.getJson();
		System.out.println("jsonString:" + jsonString);
		if (jsonString != "" ){
				//String test = "[{\"playerName\":\"f\",\"playerScore\":5},{\"playerName\":\"e\",\"playerScore\":2},{\"playerName\":\"a\",\"playerScore\":1},{\"playerName\":\"b\",\"playerScore\":1},{\"playerName\":\"c\",\"playerScore\":1},{\"playerName\":\"d\",\"playerScore\":1}]";
				//String test2 = "[{"playerName":"f","playerScore":5},{"playerName":"e","playerScore":2},{"playerName":"a","playerScore":1},{"playerName":"b","playerScore":1},{"playerName":"c","playerScore":1},{"playerName":"d","playerScore":1}]";
				//System.out.println(test);
			
				Gson gson = new Gson();
				Type collectionType = new TypeToken<ArrayList<Player>>(){}.getType();
				ArrayList<Player> highscore = gson.fromJson(jsonString, collectionType);
		    
				this.highscore = highscore;
			
			
		}else{
			this.highscore= new Highscore().getHighscore();
		}
	}
	
	
	public void saveHighscoreOnServer(){
		NetworkConnection networkConnection = new NetworkConnection("http://erdbeerwelt.com/mio/paf/upload.php");
		try {
			networkConnection.postJson3(this);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	  
    public String toJson(){
		Gson gson = new GsonBuilder().create();
		JsonArray jsonArray = gson.toJsonTree(this.highscore).getAsJsonArray();
		String jsonString = jsonArray.toString();
		System.out.println("----->" + jsonString);
		
    	return jsonString;
    }

	public String getTop(int top) {
		// TODO Auto-generated method stub
		boolean empty = this.getHighscore().isEmpty();
		
		int anz = this.getHighscore().size();
		String highscoreString = "";
		
		if (empty == false){
			if (anz>top){
				for(int i=0; i<top; i++){
					highscoreString +=  i+1 + ".    " + this.getHighscore().get(i).toString() + "\n";
				}
			}else{
				int i = 1;
				for (Player s : this.getHighscore()){
					highscoreString +=  i + ".    " + s.toString() + "\n";
					i++;
				}
			}
		}
		
		
		return highscoreString;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
