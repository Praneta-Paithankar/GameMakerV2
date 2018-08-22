package com.test;
import javax.swing.*;  
import java.awt.*;
public class TestSwing extends JFrame{
	
	public TestSwing() {
		 super("Draw A Rectangle In JFrame");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(800,800);
		 setVisible(true);
	}
	public void paint (Graphics g) {
		 super.paint(g);
		 g.drawRect(100,200,1400,700);
		 g.drawRect(800,400,50,400);
	}
	
	public static void main(String[] args) {  
		TestSwing board = new TestSwing();

//		JFrame f=new JFrame();//creating instance of JFrame  
//		          
//		JButton b=new JButton("click");//creating instance of JButton  
//		b.setBounds(400,400,100, 40);//x axis, y axis, width, height  
//		          
//		f.add(b);//adding button in JFrame  
//		          
//		f.setSize(800,800);//400 width and 500 height  
//		f.setLayout(null);//using no layout managers  
//		f.setVisible(true);//making the frame visible  
	}
}