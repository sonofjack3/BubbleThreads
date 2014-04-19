//*********************************************************************************
// BubblePanel.java										Author: Evan Jackson
//
// Displays the panel on which a breathing bubble and two control buttons will
// be displayed.
//*********************************************************************************

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class BubblePanel extends JPanel
{
	private int bubbleValue; //initialize phase to 0
	private JButton start, stop;
	private Bubble bubble;
	private JPanel buttonPanel, canvas;

	//------------------------------------------------------------------------
	// Constructor creates a panel and adds buttons and an empty canvas.
	//------------------------------------------------------------------------
	public BubblePanel()
	{
		setPreferredSize(new Dimension(200, 250));
		setBackground(Color.white);
		bubble = new Bubble(this);
		buttonPanel = makeButtonPanel();
		canvas = makeCanvas();
		add(canvas);
		add(buttonPanel);

	}

	//--------------------------------------------
	// Starts the breathing bubble in this panel.
	//--------------------------------------------
	public void startBubble()
	{
		bubble.start();
	}

	//------------------------------------------------------
	// Creates an empty panel to hold the bubble.
	//------------------------------------------------------
	private JPanel makeCanvas()
	{
		JPanel bp = new JPanel();
		bp.setPreferredSize(new Dimension(200, 200));
		bp.setBackground(Color.white);
		return bp;
	}

	//------------------------------------------------------
	// Creates a panel to hold two control buttons.
	//------------------------------------------------------
	private JPanel makeButtonPanel()
	{
		JPanel bp = new JPanel();
		bp.setPreferredSize(new Dimension(200, 50));
		bp.setBackground(Color.white);
		start = new JButton("START");
		stop = new JButton("STOP");
		start.addActionListener(new ButtonListener("START"));
		stop.addActionListener(new ButtonListener("STOP"));
		bp.add(start);
		bp.add(stop);
		return bp;
	}

	//----------------------------------------------------------------------
	// Simulates the breathing effect of the bubble.
	//----------------------------------------------------------------------
	public void changeBubble()
	{
		Graphics g = getGraphics();
		drawCircle(g, bubbleValue, Color.white); //"erases" previous bubble

		revalidate();

		bubbleValue = (bubbleValue + 1) % 6; //move to the next position

		drawCircle(g, bubbleValue, bubble.getColor());

		revalidate();
	}

	//-------------------------------------------------------------------------------
	// Draws the bubble at each phase and the white bubble that "erases" the bubble
	// before it is changed.
	//-------------------------------------------------------------------------------
	private void drawCircle(Graphics page, int phase, Color color)
	{
		int min = bubble.getMin();
		int max = bubble.getMax();
		int change = (max - min) / 3; //changes the diameter
		int posChange = change / 2; //changes the upper-left corner of bounding box
		int x = 100 - min / 2; //initial x
		int y = 100 - min / 2; //initial y
		page.setColor(color);

		switch (phase)
		{
			case 0: page.fillOval(x, y, min, min);
					break;
			case 1: case 5: page.fillOval(x - posChange, y - posChange, min + change,
							min + change);
							break;
			case 2: case 4: page.fillOval(x - 2 * posChange, y - 2 * posChange,
							min + 2 * change, min + 2 * change);
							break;
			case 3: page.fillOval(x - 3 * posChange, y - 3 * posChange , max, max);
					break;
		}
	}

	//**********************************************************
	// Represents a listener for button events.
	//**********************************************************
	private class ButtonListener implements ActionListener
	{
		String title;

		//-----------------------------------------------------
		// Constructor initializes this listener's text, which
		// will be matched with its respective button's text.
		//-----------------------------------------------------
		public ButtonListener(String buttonName)
		{
			title = buttonName;
		}

		//-----------------------------------------------------
		// Starts and stops the breathing animation, depending
		// on what button is pushed.
		//-----------------------------------------------------
		public void actionPerformed(ActionEvent ae)
		{
			if (title.equals("START") && !bubble.isRunning())
				bubble.toggle();
			if (title.equals("STOP") && bubble.isRunning())
				bubble.toggle();
		}
	}
}

