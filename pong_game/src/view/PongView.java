package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.Controller;
import controller.PongTest;
import model.Ball;
import model.Paddle;
import model.Pitch;

public class PongView extends JFrame implements Runnable, KeyListener{
	
	private Paddle paddle = new Paddle(25, 180, 25, 125, Color.yellow);
	private Ball ball = new Ball(100, 100, Color.white);
		
	private boolean up;
	private boolean down;
	

	private Graphics gr;
	private Pitch pitch = new Pitch(0, 0, this);
	
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
	}

	public void init(){
		PongView pong = new PongView("Pong - The Game");
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

		while (true) {
			
			ball.moveBall();

			if (up) {
				paddle.moveUp();
			} else if (down) {
				paddle.moveDown();
			}
			
			paddle.checkCollisionWithBall(ball);
			
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException in Thread");
			}
		}
	}
	
//	public static void main(String[] args){

	//	PongView pongView = new PongView("Pong - The Game - Test");
		//new Thread(pongView).start();
		//pongView.setSize(width, height);
		//pongTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pongTest.setVisible(true);
	//}

}
