/**
 * 
 */
package com.qna;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author fulintseng
 *
 */
public class YogBot extends JFrame implements KeyListener {

	JPanel p = new JPanel();
	private JTextArea dialog = new JTextArea(20, 50);
	private JTextArea input = new JTextArea(1, 50);
	JScrollPane scroll = new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	String[][] yogBot = {
			//standard greetings
			{"hi", "hello", "Hey"},
			{"HaHa Hi~", "hello", "hey"},
			//question greetings
			{"how are you", "how r u", "how are you"},
			{"good", "doing well"},
			//yes
			{"yes"},
			{"no><"},
			//default
			{"yo","haha", "good", "not available"}
			
	};
	

	
	
	
	 public static void main(String[] args) {
		 new YogBot();
	 }
	
	 public YogBot() {
		 super("Yog Bot!");
		 //Frame Attributes
		 setSize(600,400);
		 setResizable(false);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		 dialog.setEditable(false);
		 input.addKeyListener(this);
		 
		 p.add(scroll);
		 p.add(input);
		 p.setBackground(new Color(255, 200, 0));
		 add(p);
		 
		 setVisible(true);
		 
	 }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(false);
			//grab quote
			String quote = input.getText();
			input.setText("");
			addText("-->YOU: \t" + quote);
			quote = quote.trim();
			while(
				quote.charAt(quote.length() - 1) == '!' ||
				quote.charAt(quote.length() - 1) == ',' ||
				quote.charAt(quote.length() - 1) == '?'
				) {
				quote = quote.substring(0, quote.length() - 1);
			}
			quote = quote.trim();
			byte response =0;
			//check for matches
			int j = 0; //which group we are checking
			while(response == 0) {
				if(inArray(quote.toLowerCase(), yogBot[j*2])) {
					response = 2;
					int r = (int) Math.floor(Math.random()*yogBot[(j*2)+1].length);
					addText("\n -->Yog\t" + yogBot[(j*2)+1][r]);
				}
				
				j++;
				if(j*2 == yogBot.length -1 && response == 0) {
					response = 1;
				}
			}
			
			//default
			if(response == 1) {
				int r = (int) Math.floor(Math.random()*yogBot[yogBot.length -1].length);
				addText("\n-->Yog\t" + yogBot[yogBot.length-1][r]);
			}
			addText("\n");
		}
	}

	private boolean inArray(String in, String[] str) {
		boolean match = false;
		for(int i = 0; i< str.length; i++) {
			if(str[i].equals(in)) {
				match = true;
			}
		}
		return match;
	}

	private void addText(String string) {
		// TODO Auto-generated method stub
		dialog.setText(dialog.getText() + string);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(true);
		}
	}
	
}
