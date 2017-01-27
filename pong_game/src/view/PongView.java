/**
 * PongView.java beinhaltet alle Objekte und Controller,
 * die für das eigentliche Spiel benötigt werden.
 * Für diese Klasse werden die Schnittstellen JFrame, Runnable und Observer implementiert.
 * 
 * @author Enrico Barig, Steven Kranhold, Naamah Richter, Stefanie Schwanke
 * @version 1.0, Stand: 17/01/26
 * 
 */

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.GameController;
import controller.HighscoreController;
import model.AutoPaddle;
import model.Ball;
import model.Paddle;
import model.Pitch;
import model.Player;

public class PongView extends JFrame implements Runnable, Observer{
	
	/**
	 * Instanzen der allerbenötigten Klassen werden erzeugt
	 * und Variablen definiert.
	 */
	private static PongView pong;
	private Graphics gr;
	private Pitch pitch = new Pitch(0, 0, this);
	private AutoPaddle autoPaddle = new AutoPaddle(850, 180, 25, 125, Color.LIGHT_GRAY);
	private Paddle paddle = new Paddle(25, 180, 25, 125, Color.WHITE);
	private Ball ball = new Ball(100, 100, this, paddle, autoPaddle);
	private GameController gc = new GameController();
	private Player player;
	private int scoreInt = 0;
	private String score = "0";
	private boolean breakLoop = false; 

	
	/**
	 * Konstruktor der PongView-Klasse
	 * Hier wird der GameController aktiviert und
	 * der Observer auf die zu beobachtenden Objekte des Ball und des Paddle gesetzt.
	 * @param String title
	 */
	public PongView(String title){
		super(title);
		this.setFocusable(true);
		this.addKeyListener(gc);
		this.player = new Player();
		this.paddle.addObserver(this.player);
		this.ball.addObserver(this.player);
		this.ball.addObserver(this);
	}
	
	/**
	 * Parameterfreier Konstruktor für den Observer
	 */
	public PongView() {
	}

	/**
	 * Überschriebene paint-Methode der Java AWT-Klasse Graphics
	 * Alle benötigten, bereits generierten Objekte werden gezeichtnet.
	 * @param Graphics gr
	 */
	@Override
	public void paint(Graphics gr){
		super.paint(gr);			
		//Spielfeld zeichnen
		pitch.paintt(gr);
		//Ball zeichnen
		ball.paintt(gr);
		//Paddle zeichnen
		getPaddle().paintt(gr);
		//AI zeichnen
		autoPaddle.paintt(gr);
		//Score Text zeichnen
		
		gr.setColor(Color.WHITE);
		gr.setFont(new Font("Arial", Font.PLAIN, 30)); 
		
		
		//************* Test für Observer *********************
		scoreInt = this.player.getScore();
		//************* Test für Observer *********************
		
		score = String.valueOf(scoreInt);
		gr.drawString("Score: " + score, 20, 70);
		
		//bufferedImage gegen das Flackern (das eigentlich nichts nutzt) ;)
		BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dComponent = (Graphics2D) gr;
		g2dComponent.drawImage(bufferedImage, 0, 0, null); 
	}

	
	/**
	 * init-Methode initiiert den JFrame/ das Swing-Fenster
	 * @param KeyListener keylistener
	 * KeyListener wird benötigt, damit der GameController auf die Tasteneingaben
	 * des Spielers reagieren kann.
	 */
	public void init(KeyListener keylistener){
		//Fenster erstellen
		pong = new PongView("Pong - The Game");

		//Anti-Flacker Label :D
		JLabel lbl1 = new JLabel("Score: ");
		pong.add(lbl1);
		pong.setComponentZOrder(lbl1,0);
		
		//Thread starten
		new Thread(pong).start();
		
		//Parameter fuer das Fenster setzen
		pong.setSize(900, 600);
		pong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pong.setLocation(50,50);
		pong.setResizable(false);
		pong.setVisible(true);
	}


	/**
	 * run-Methode, die beim Einsatz der Schnittstelle Runnable implementiert und definiert werden muss.
	 * Sobald der Thread gestartet wird, wird die Methode aufgerufen und parallel zum restlichen Programm
	 * immer wieder in einer Endlosschleife abgearbeitet.
	 */
	@Override
	public void run() {
		//Endlosschleife
		while (true) {
			//Ball bewegen
			ball.moveBall();

			// Spieler-Paddle bewegen
			// Spieler-Paddle wird nach oben bewegt, sobald Y-Wert größer als 20 ist.
			if (gc.getUp() && getPaddle().getY() > 20) {		
				getPaddle().moveUp();
				// Spieler-Paddle wird nach unten bewegt, sobald Y-Wert kleiner als 475 ist.
			} else if (gc.getDown() && getPaddle().getY() < 475) {
				getPaddle().moveDown();
			}
			
			//automatische Bewegung des KI-Paddle
			autoPaddle.moveAutoPaddle(ball);
			
			//Spieler- und KI-Paddle auf Kollision mit Ball pruefen
			getPaddle().checkCollisionWithBall(ball);
			autoPaddle.checkCollisionWithBall(ball);
			
			
			
			if (breakLoop == true){
				
				//Thread.currentThread().interrupt();
				try {
					Thread.sleep(200000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Aufruf der paint-Methode				
			repaint();

			try {
				//Pause der Endlosschleife
				Thread.sleep(20);

			} catch (InterruptedException e) {
				System.out.println("InterruptedException in Thread");
			}
			
		}
		
	}

	/**
	 * Getter und setter für Paddle
	 * @return
	 */
	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	
	
	/**
	 * update-Methode, die beim Gebrauch der Observer-Klasse/-Schnittstelle
	 * definiert bzw. überschrieben werden muss.
	 * Das hier übergebene Argument, sind die erreichten Punkte.
	 * Hier: 0Punkte = Gameover = Aufruf der HighscoreView
	 */
	@Override
	public void update(Observable o, Object arg) {
		int p = (Integer) arg;
		
		if (p == 0){
			
			breakLoop = true;
			
		}

		HighscoreView highscoreView = new HighscoreView("Highscores", this.player);
    	highscoreView.init(new HighscoreController(highscoreView));
    	PongView.pong.dispose();
		
	}
}