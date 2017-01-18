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
import model.AutoPaddle;
import model.Ball;
import model.Paddle;
import model.Pitch;
import model.Player;

public class PongView extends JFrame implements Runnable, Observer{
	
	private PongView pong;
	private Graphics gr;
	private Pitch pitch = new Pitch(0, 0, this);
	private AutoPaddle autoPaddle = new AutoPaddle(850, 180, 25, 125, Color.LIGHT_GRAY);
	private Paddle paddle = new Paddle(25, 180, 25, 125, Color.WHITE);
	private Ball ball = new Ball(100, 100, this, paddle, autoPaddle);
	private GameController gc = new GameController();
	private Player player;
	private int scoreInt = 0;
	private String score = "0";
	
//	private boolean up;
//	private boolean down;
	
	public PongView(String title){
		super(title);
		this.setFocusable(true);
		this.addKeyListener(gc);
		
	
		//************* Test für Observer *********************
		this.paddle = new Paddle(25, 180, 25, 125, Color.WHITE);
		this.player = new Player();
		this.paddle.addObserver(this.player);
		//************* Test für Observer *********************
	}
	
	public PongView(Paddle paddle){
		this.paddle = paddle;
		
		//Durchfuehrung der Registrierung beim uebergebenden Subjekt
		this.paddle.addObserver(this);
	}
	
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
		score = String.valueOf(scoreInt);
		gr.drawString("Score: " + score, 20, 70);
		
		//bufferedImage gegen das flackern
		BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dComponent = (Graphics2D) gr;
		g2dComponent.drawImage(bufferedImage, 0, 0, null); 
	}

	/**
	 * Initiert das JSwing Fenster

	 * @param keylistener listener f�r den GameController
	 */
	public void init(KeyListener keylistener){
		//Fenster erstellen
		pong = new PongView("Pong - The Game");

		//Anti-flacker Label :D
		JLabel lbl1 = new JLabel("Score: ");
		pong.add(lbl1);
		pong.setComponentZOrder(lbl1,0);
		
		//Thread starten
		new Thread(pong).start();
		
		//Parameter f�r das Fenster setzen
		pong.setSize(900, 600);
		pong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pong.setLocation(50,50);
		pong.setResizable(false);
		pong.setVisible(true);
	}



	@Override
	public void run() {
		//Endlosschleife
		while (true) {
			//Ball bewegen
			ball.moveBall();

			//Spieler-Paddle bewegen
			if (gc.getUp() && getPaddle().getY() > 20) {		
				getPaddle().moveUp();
			} else if (gc.getDown() && getPaddle().getY() < 475) {
				getPaddle().moveDown();
			}
			
			//KI-Paddle bewegen
			autoPaddle.moveAutoPaddle(ball);
			
			//Spiler- und KI-Paddle auf Kollision mit Ball pr�fen
			getPaddle().checkCollisionWithBall(ball);
			autoPaddle.checkCollisionWithBall(ball);
			
			//N�chstes Frame zeichnen				
			repaint();

			try {
				//Pause der Endlosschleife
				Thread.sleep(20);

			} catch (InterruptedException e) {
				System.out.println("InterruptedException in Thread");
			}
		}
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// auf neuen Status reagieren
		int newScore = player.getScore();
		System.out.println("Observer hat getroffen - Nachricht aus PongView");
		System.out.println(newScore);
		
	}
}