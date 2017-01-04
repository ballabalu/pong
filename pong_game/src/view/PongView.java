package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AutoPaddle;
import model.Ball;
import model.Paddle;
import model.Pitch;

public class PongView extends JFrame implements Runnable, KeyListener{
	
	private PongView pong;
	private Graphics gr;
	private Pitch pitch = new Pitch(0, 0, this);
	private Ball ball = new Ball(100, 100, this);
	private AutoPaddle autoPaddle = new AutoPaddle(850, 180, 25, 125, Color.LIGHT_GRAY);
	private Paddle paddle = new Paddle(25, 180, 25, 125, Color.WHITE);
	
	private boolean up;
	private boolean down;
	
	public PongView(String title){
		super(title);
		JPanel panel = new JPanel(null);
		panel.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(this);
		setContentPane(panel);
	}
	
	@Override
	public void paint(Graphics gr){
		System.out.println("REPAINT");
		super.paint(gr);
		
		BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		
		//Spielfeld zeichnen
		pitch.paint(gr);
		//Ball zeichnen
		System.out.println(ball.getX());
		System.out.println(ball.getY());
		ball.paint(gr);
		//Paddle zeichnen
		paddle.paint(gr);
		//AI zeichnen
		autoPaddle.paint(gr);
		
		Graphics2D g2dComponent = (Graphics2D) gr;
		g2dComponent.drawImage(bufferedImage, 0, 0, null); 
	}

	public void init(){
		pong = new PongView("Pong - The Game");
		new Thread(pong).start();
		pong.setSize(900, 600);
		pong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pong.setLocation(50,50);
		pong.setResizable(false);
		pong.setVisible(true);


	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Pfeil nach oben = Keycode 38
		if (e.getKeyCode() == 38) {
			up = true;
			down = false;
			//Pfeil nach unten = Keycode 40
		} else if (e.getKeyCode() == 40){
			up = false;
			down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		up = false;
		down = false;
		
	}

	@Override
	public void run() {
		//Endlosschleife
		while (true) {
			System.out.println("run-Methode startet.");
			ball.moveBall();
			System.out.println("ball.moveBall();");

			if (up) {
				paddle.moveUp();
			} else if (down) {
				paddle.moveDown();
			}
			
			paddle.checkCollisionWithBall(ball);
			autoPaddle.moveAutoPaddle(ball);
			autoPaddle.checkCollisionWithBall(ball);
			
			repaint();

			System.out.println("repaint");
			try {
				//Pause der Endlosschleife
				Thread.sleep(60);

			} catch (InterruptedException e) {
				System.out.println("InterruptedException in Thread");
			}
		}
	}

}
