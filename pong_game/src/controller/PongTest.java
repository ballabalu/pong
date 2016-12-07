package controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.*;

import model.Pitch;
import view.PongView;

public class PongTest extends JFrame{
		
	public static void main(String[] args){
		PongView pong = new PongView("Pong - The Game");
		pong.init();

	}
   
}
