package model;
import java.awt.Component;
import java.awt.Graphics;

public class Ball {
	private int x, y;
	private Component comp;
	public Ball(int x, int y, Component comp){
		this.x = x;
		this.y = y;
		this.comp = comp;
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void paint(Graphics gr){
		gr.drawImage(Toolkit.getDefaultToolkit.,x, y, comp);
	}
	
	
}
