package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import model.AutoPaddle;
import model.Ball;
import model.Paddle;
import model.Pitch;

public class PongView extends JFrame implements Runnable, KeyListener{
	
	private Graphics gr;
	private Pitch pitch = new Pitch(0, 0, this);
	private Ball ball = new Ball(100, 100, this);
	private Paddle paddle = new Paddle(25, 180, 25, 125, Color.yellow);
	private AutoPaddle autoPaddle = new AutoPaddle(725, 180, 25, 125, Color.white);
	
	private boolean up;
	private boolean down;
	
	public PongView(String title){
		super(title);
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	
	public void paint(Graphics gr){
		super.paint(gr);
		//Spielfeld zeichnen
		pitch.paint(gr);
		//Ball zeichnen
		ball.paint(gr);
		//Paddle zeichnen
		paddle.paint(gr);
		//AI zeichnen
		autoPaddle.paint(gr);
	}

	public void init(){
		PongView pong = new PongView("Pong - The Game");
		pong.setSize(900, 600);
		pong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pong.setLocation(50,50);
		pong.setResizable(false);
		pong.setVisible(true);
		new Thread(this).start();

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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException in Thread");
			}
		}
	}

}
