//*********************************************************************************
// ThreadRunner.java								Author: Evan Jackson
//
// The driver program for the breathing bubble application.
//*********************************************************************************

import java.awt.*;
import javax.swing.*;

public class ThreadRunner
{
	//------------------------------------------------------------
	// Creates a frame and and adds four BubblePanels to it.
	//------------------------------------------------------------
	public static void main (String[] args)
 	{
		JFrame frame = new JFrame("BUBBLES THAT BREATHE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 2));
		BubblePanel panel1 = new BubblePanel();
		BubblePanel panel2 = new BubblePanel();
		BubblePanel panel3 = new BubblePanel();
		BubblePanel panel4 = new BubblePanel();
		panel1.startBubble();
		panel2.startBubble();
		panel3.startBubble();
		panel4.startBubble();
		frame.validate();
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel2);
		frame.getContentPane().add(panel3);
		frame.getContentPane().add(panel4);
		frame.pack();
		frame.setVisible(true);
 	}
}