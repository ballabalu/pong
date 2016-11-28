package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Pads;

public class PongView extends JPanel {

	private JButton redraw;
	private JButton changeColor;
	private MyCanvas rechti;
	public Pads player1;
	public Pads player2;
	
	
	public PongView(){
		init();
	}
	
	public void init(){
		JFrame frm = new JFrame();
		frm.setTitle("JFrame mit setSize()");

		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frm.setSize(800,600);
		frm.setLocation(50,50);
		frm.setResizable(false);
		frm.setVisible(true);
		
        this.redraw = new JButton("Redraw");        
        frm.getContentPane().add(redraw, BorderLayout.SOUTH); 
        
        this.changeColor = new JButton("change Color");        
        frm.getContentPane().add(changeColor, BorderLayout.NORTH);    
        
        this.rechti = new MyCanvas();
        frm.getContentPane().add(rechti);
	}
	
    public JButton getBtnRedraw(){
        return redraw;
    }
    
    public JButton changeColor(){
        return changeColor;
    }
	
    public MyCanvas getRechti(){
		return rechti;  	
    }
    
	public class MyCanvas extends JComponent {

		private Color color = Color.green;
		  public void paint(Graphics g) {
		    g.setColor(color);
		    g.fillRect(10, 195, 90, 60);
		  }
		  
		  public void setColor(Color color){
			  this.color = color;
			  repaint();
		  }
	}
	

	
}
