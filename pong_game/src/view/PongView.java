package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	
//	private boolean up;
//	private boolean down;
	
	public PongView(String title){
		super(title);
		this.setFocusable(true);
		this.addKeyListener(gc);
	}
	
	public PongView(Paddle paddle){
		this.paddle = paddle;
		
		//Durchfuehrung der Registrierung beim uebergebenden Subjekt
		this.paddle.addObserver(this);
	}
	
	@Override
	public void paint(Graphics gr){
		//System.out.println("REPAINT");
		super.paint(gr);
		
		BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		
		//Spielfeld zeichnen
		pitch.paintt(gr);
		//Ball zeichnen
//		System.out.println(ball.getX());
//		System.out.println(ball.getY());
		ball.paintt(gr);
		//Paddle zeichnen
		getPaddle().paintt(gr);
		//AI zeichnen
		autoPaddle.paintt(gr);
		
		Graphics2D g2dComponent = (Graphics2D) gr;
		g2dComponent.drawImage(bufferedImage, 0, 0, null); 
	}

	//JPAnel hinzugefuegt
	public void init(KeyListener keylistener){
		pong = new PongView("Pong - The Game");
		JPanel panel = new JPanel(null);
		
		JLabel lbl1 = new JLabel("Score: ");
		pong.add(lbl1);
		lbl1.setLocation(27, 20);
		lbl1.setSize(86, 14);
		pong.setComponentZOrder(lbl1,0);
		
		panel.setDoubleBuffered(true);
		pong.add(panel);
		setContentPane(panel);
		new Thread(pong).start();
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
//			System.out.println("run-Methode startet.");
			ball.moveBall();
//			System.out.println("ball.moveBall();");

			if (gc.getUp()) {
				getPaddle().moveUp();
			} else if (gc.getDown()) {
				getPaddle().moveDown();
			}
			
			getPaddle().checkCollisionWithBall(ball);
			autoPaddle.moveAutoPaddle(ball);
			autoPaddle.checkCollisionWithBall(ball);
			
			repaint();

//			System.out.println("repaint");
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