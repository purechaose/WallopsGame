import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game {
	private JFrame window;
	private JPanel top;
	private JPanel bottom;
	private JPanel inputSection;
	private JPanel hud;
	private String[] commandHistory;
	private JTextArea[] commandPanes;
	private JTextField textField;
	private KeyListener commandInput;
	
	public Game() {
		//initialization
		window = new JFrame();
		top = new JPanel();
		bottom = new JPanel();
		inputSection = new JPanel();
		hud = new JPanel();
		commandHistory = new String[20];
		for (int i = 0; i < commandHistory.length; i++)
			commandHistory[i] = "";
		commandPanes = new JTextArea[20];
		for (int i = 0; i < commandPanes.length; i++)
			commandPanes[i] = new JTextArea();
		textField = new JTextField();
		commandInput = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					addCommand(textField.getText().substring(1));
					textField.setText(">");
					for (int i = 0; i < commandPanes.length; i++)
					{
						commandPanes[i].setText(/*">" + */commandHistory[commandHistory.length - 1 - i]);
					}
				}
				
			}
		};
		//window
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setLayout(new GridLayout(2, 1));
		//top half of window
		window.add(top);
		top.setBackground(Color.BLUE);
		//bottom half of window
		window.add(bottom);
		bottom.setLayout(new GridLayout(1, 2));
		//command input
		bottom.add(inputSection);
		inputSection.setLayout(new GridLayout(commandPanes.length + 1, 1));
		Color gray = new Color(170, 170, 170);
		for (int i = 0; i < commandPanes.length; i++)
		{
			inputSection.add(commandPanes[i]);
			commandPanes[i].setEditable(false);
			commandPanes[i].setBackground(gray);
			gray = new Color(gray.getRed() + 3, gray.getGreen() +3, gray.getBlue() + 3);
		}
		inputSection.add(textField);
		textField.addKeyListener(commandInput);
		textField.setText(">");
		textField.setBackground(Color.WHITE);
		//HUD
		bottom.add(hud);
		hud.setBackground(Color.RED);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void addCommand(String command)
	{
		for (int i = commandHistory.length - 1; i > 0; i--)
		{
			commandHistory[i] = commandHistory[i - 1];
		}
		commandHistory[0] = command;
	}
}
