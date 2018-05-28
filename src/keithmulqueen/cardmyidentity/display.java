package keithmulqueen.cardmyidentity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class display {

	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	private int myGreen;
	private int otherColors;
	private Color bgColor;
	
	public display(String title, int width, int height)
	{
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay()
	{
		myGreen = 80;
		otherColors = 80;
		
		bgColor = new Color(otherColors, myGreen, otherColors);
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(bgColor);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas()
	{
		return canvas;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}

	public void setBackgroundColor(int passed_increment)
	{
		if((myGreen < 255)||(otherColors > 0))
		{
			if(myGreen < 255)
			{
				this.myGreen = myGreen + passed_increment;
				
				if(myGreen >= 255)
				{
					myGreen = 255;
				}
			}
			
			else if(otherColors > 0)
			{
				this.otherColors = otherColors - passed_increment;
				
				if(otherColors <= 0)
				{
					otherColors = 0;
				}				
			}
			
			bgColor = new Color(otherColors, myGreen, otherColors);
			
			frame.getContentPane().setBackground(bgColor);			
		}	
	}
	
}