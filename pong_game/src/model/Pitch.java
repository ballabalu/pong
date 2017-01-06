package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Pitch{

	private Color color = Color.black;
	private int width;
	private int height;
	private Component comp;
	
	public Pitch(int width, int height, Component comp){
		this.width = width;
		this.height = height;
		this.comp = comp;
	}
	
	public void paintt(Graphics gr){
		gr.drawImage(Toolkit.getDefaultToolkit().getImage("img/pitch.png"), width, height, comp);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
