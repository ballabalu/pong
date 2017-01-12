package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AutoPaddle;
import model.Ball;
import model.Paddle;
import model.Pitch;

public class PongView extends JFrame implements Runnable, KeyListener{
	
	private PongView pong;
	private Graphics gr;
	private Pitch pitch = new Pitch(0, 0, this);
	private AutoPaddle autoPaddle = new AutoPaddle(850, 180, 25, 125, Color.LIGHT_GRAY);
	private Paddle paddle = new Paddle(25, 180, 25, 125, Color.WHITE);
	private Ball ball = new Ball(100, 100, this, paddle, autoPaddle);
	
	private boolean up;
	private boolean down;
	
	public PongView(String title){
		super(title);
		this.setFocusable(true);
		this.addKeyListener(this);
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

	//JPAnel hinzugefügt
	public void init(){
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
//			System.out.println("run-Methode startet.");
			ball.moveBall();
//			System.out.println("ball.moveBall();");

			if (up) {
				getPaddle().moveUp();
			} else if (down) {
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

}