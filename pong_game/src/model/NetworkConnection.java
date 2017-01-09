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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

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

	
	
	
	public void sendPost() throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		System.out.println("NetworkConnection: sendPost(): " + this.url );
		
		String url = this.url;
		String data = "test data";
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		
		
		//HttpClient client = new DefaultHttpClient();
		//HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("data", data));
		//urlParameters.add(new BasicNameValuePair("cn", ""));
		//urlParameters.add(new BasicNameValuePair("locale", ""));
		//urlParameters.add(new BasicNameValuePair("caller", ""));
		//urlParameters.add(new BasicNameValuePair("num", "12345"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
	}
	
	public void sendPost(ArrayList<Player> highscore) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		System.out.println("NetworkConnection: sendPost(): " + this.url );
		
		String url = this.url;
		String data = "test data2";
	
		ArrayList<Player> hs = highscore;
		
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		
		//HttpClient client = new DefaultHttpClient();
		//HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		//urlParameters.add(new BasicNameValuePair("data", data));
		urlParameters.add(new BasicNameValuePair("data", hs.toString()));
		//urlParameters.add(new BasicNameValuePair("cn", ""));
		//urlParameters.add(new BasicNameValuePair("locale", ""));
		//urlParameters.add(new BasicNameValuePair("caller", ""));
		//urlParameters.add(new BasicNameValuePair("num", "12345"));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
	}
	
	


    public static void postJson(ArrayList<Player> highscore) throws IOException {

        //Gson gson = new Gson();
        //String json = gson.toJson(user);
    	//JSONArray jsonarray = new JSONArray(Arrays.asList(highscore));
    	
    	
    	JSONArray jsArray = new JSONArray(highscore);
    	
        System.out.println(jsArray);

        /*
        String url = "http://localhost/testserveur/index.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("json", json);


        OutputStream os = con.getOutputStream();
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        //wr.write(new String("json=" + json).getBytes());
        String param = "json=" + URLEncoder.encode(json, "UTF-8");
        wr.write(param.getBytes());

        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
        */

    }
  

    public static void main(String[] args) throws Exception {
    	
    	
    }
	
	/*
	
	
	
	
	
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