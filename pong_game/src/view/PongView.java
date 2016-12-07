package view;

import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.*;

import model.Pitch;

public class PongView extends JFrame{

	private Graphics gr;
	private Pitch pitch = new Pitch(0, 0, this);
	
	public PongView(String title){
		super(title);
	}
	
	public void paint(Graphics gr){
		super.paint(gr);
		//Spielfeld zeichnen
		pitch.paint(gr);
	}

	public void init(){
		PongView pong = new PongView("Pong - The Game");
		pong.setSize(900, 600);
		pong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pong.setLocation(50,50);
		pong.setResizable(false);
		pong.setVisible(true);

	}

}
