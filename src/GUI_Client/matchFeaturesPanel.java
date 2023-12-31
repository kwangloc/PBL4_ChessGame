package GUI_Client;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;

public class matchFeaturesPanel extends JPanel {
	private JTextField textField;
	private JTextArea textArea;
	private JTextArea textArea_1;
	JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public matchFeaturesPanel() {
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(36, 437, 528, 194);
		add(textArea);
		
		textField = new JTextField();
		textField.setBounds(36, 644, 438, 34);
		add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Send");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton.setBounds(479, 646, 85, 32);
		add(btnNewButton);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.BOLD, 20));
		textArea_1.setForeground(new Color(255, 255, 255));
		textArea_1.setBackground(new Color(128, 128, 128));
		textArea_1.setBounds(36, 49, 528, 194);
		add(textArea_1);
		
		JLabel lblNewLabel = new JLabel("History moves");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(36, 10, 193, 29);
		add(lblNewLabel);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setFont(new Font("Arial", Font.BOLD, 24));
		lblChat.setBounds(36, 398, 193, 29);
		add(lblChat);
		
//		this.setVisible(true);
	}
	
	public void updateHistory(String move) {
		String curText = textArea_1.getText();
		curText += "\n" + move;
		textArea_1.setText(curText);
	}
}
