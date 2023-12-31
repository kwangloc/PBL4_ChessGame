package GUI_Client;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class moveHistoryPanel extends JPanel {
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public moveHistoryPanel() {
		setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(128, 128, 128));
		textArea.setBounds(36, 52, 528, 246);
		add(textArea);
		
		JLabel lblNewLabel = new JLabel("History moves");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(36, 10, 217, 44);
		add(lblNewLabel);
	}

}
