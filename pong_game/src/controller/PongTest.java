package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import model.Paddle;
import model.Pitch;
import view.MenueView;
import view.PongView;

public class PongTest extends JFrame implements Runnable, KeyListener{
	
	//Hier irgendwo muss das Paddle aufgerufen werden
	private Paddle paddle = new Paddle(25, 180, 25, 125, Color.yellow);
	
	// und der Ball dann bspw. auch
	
	private boolean up;
	private boolean down;
	
	//Konstruktor
	public PongTest(String title){
		super(title);
		this.setFocusable(true);
		this.addKeyListener(this);
	}
		
	public static void main(String[] args){
		MenueView pong = new MenueView("Pong - The Game - Test");
		pong.init(new Controller());

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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// Ball
		// Methode für Ballbewegung aufrufen
		
		// Paddle
		// Methoden zum Bewegen des Paddle aufrufen
		if (up) {
			paddle.moveUp();
		} else if (down) {
			paddle.moveDown();
		}
		
		repaint();
		try {
			Thread.sleep(20);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
   
}
