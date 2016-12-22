package model;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class NetworkConnection {
    private String url;
	
	public NetworkConnection(String url){
		this.url = url;
	}
	
	public String getTextFromURL() throws Exception{
		 URL url = new URL(this.url);
	     BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	     String text = ""; 
	     String inputLine;
	     while ((inputLine = in.readLine()) != null){
	     		text = text + inputLine.toString() + "\n";
	     }  
	     in.close();
	     return text;
	}
	
	public ArrayList<Player> getHighscoreFromURL() throws Exception{
		ArrayList<Player> loadedHighscore = null;
		
		URL url = new URL(this.url);
	     
	     ObjectInputStream input2 = new ObjectInputStream(url.openConnection().getInputStream());
	     loadedHighscore = (ArrayList<Player>) input2.readObject();
	     
	     /* //BufferedReader inFile = new BufferedReader(new InputStreamReader(url.openStream()));
	     System.out.println(inFile.getClass());
	     ArrayList<Player> al = new ArrayList<Player>();
	     String inLine; 
	     
	     while ((inLine = inFile.readLine()) != null) 
	     {
	    	// al.add(inLine);
	    	 //highscoreArrayList.add(inLine);
	     }
	     inFile.close();  
	     System.out.println(al.toString());
	   
	     Highscore hs = new Highscore(al); 
	     */
	     return loadedHighscore;
	}
	
	/*
	
	public static void main(String[] args) throws Exception {
		 System.out.println("URLReader ");
		 
		URLReader urlreader = new URLReader("http://erdbeerwelt.com/mio/test.txt");
		String test1 = urlreader.getTextFromURL();
		String inputLine;
		System.out.println(test1);
		
		
		 
		NetworkConnection urlreader2 = new NetworkConnection("http://erdbeerwelt.com/mio/pongHighscore.txt");
		Highscore hs = new Highscore( urlreader2.getHighscoreFromURL());
		
		 System.out.println(hs.toString());
		 
		 
		
		 System.out.println("-----------");
		 
        URL oracle = new URL("http://erdbeerwelt.com/mio/test.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        //System.out.println(in.toString());
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
           
    } */
}