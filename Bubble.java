//*********************************************************************************
// Bubble.java										Author: Evan Jackson
//
// Represents a single breathing bubble.
//*********************************************************************************

import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Bubble extends Thread
{
	private Color color;
	private int min, max;
	private boolean running = true;
	private Random generator;
	private BubblePanel panel = null;

	//----------------------------------------------------------------------
	// Constructor initializes this bubble's color (random) and maximum and
	// minimum diameter (random), as well as its associated BubblePanel.
	//----------------------------------------------------------------------
	public Bubble(BubblePanel bp)
	{
		generator = new Random();
		float red = generator.nextFloat();
		float green = generator.nextFloat();
		float blue = generator.nextFloat();
		color = new Color(red, green, blue);
		min = generator.nextInt(20) + 10; //min diameter
		max = generator.nextInt(100) + 50; //max diameter
		panel = bp;
	}

	//-------------------------------------------
	// Returns the minimum diameter.
	//-------------------------------------------
	public int getMin()
	{
		return min;
	}

	//--------------------------------------------
	// Returns the maximum diameter.
	//--------------------------------------------
	public int getMax()
	{
		return max;
	}

	//---------------------------------------------
	// Returns this bubble's color.
	//---------------------------------------------
	public Color getColor()
	{
		return color;
	}

	//----------------------------------------------
	// Returns true if the animation is running
	// and false otherwise.
	//----------------------------------------------
	public boolean isRunning()
	{
		return running;
	}

	//-------------------------------------------------
	// Runs this thread.
	//-------------------------------------------------
	public void run()
	{
		while (true)
		{
			try
			{
				sleep(100);
			}
			catch (InterruptedException ie)
			{
				System.exit(1);
			}

			if (running)
				panel.changeBubble();
		}
	}

	//------------------------------------------------------------
	// Toggle's the animation on and off; used by control buttons.
	//------------------------------------------------------------
	public void toggle()
	{
		running = !running;
	}
}