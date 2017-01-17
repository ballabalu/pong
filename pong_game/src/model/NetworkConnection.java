package model;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public class NetworkConnection {
    private String url;
    private final String USER_AGENT = "Mozilla/5.0";
	
	public NetworkConnection(String url){
		this.url = url;
	}
	
	public NetworkConnection(){
	
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Player> getHighscoreFromURL() throws Exception{
		ArrayList<Player> loadedHighscore = null;
		
		URL url = new URL(this.url);
	     
	    ObjectInputStream input = new ObjectInputStream(url.openConnection().getInputStream());
	    loadedHighscore = (ArrayList<Player>) input.readObject();
	    return loadedHighscore;
	}
	
	
	public String getJson() throws Exception, UnknownHostException {
		
		String json = "";
		URL url = new URL(this.url);
		
		URLConnection connection = url.openConnection();
		
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);

        in.close();
        json = response.toString();
        return json;
	}

	
	
	
	
   public void postJson(Highscore highscore) throws IOException {
	   
	   String       postUrl     = this.url;
	   HttpClient 	client 		= HttpClients.createDefault();
	   HttpPost     post        = new HttpPost(postUrl);
	   String jsonString = highscore.toJson();
	   StringEntity postingString = new StringEntity(jsonString,"UTF-8");
	   postingString.setContentType("application/json");
	   
	   List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	   urlParameters.add(new BasicNameValuePair("json", jsonString));

	   post.setEntity(new UrlEncodedFormEntity(urlParameters));
	   
	   HttpResponse response = client.execute(post);

	   BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

	   StringBuffer result = new StringBuffer();
	   String line = "";
	   while ((line = rd.readLine()) != null) {
		   result.append(line);
	   }
	   System.out.println(result.toString());
		
    }

}