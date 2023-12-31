package GUI_Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class selectServer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					selectServer frame = new selectServer();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public selectServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1185, 641);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 64, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server IP address:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 32));
		lblNewLabel.setBounds(306, 254, 338, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblServerPort = new JLabel("Server port:");
		lblServerPort.setFont(new Font("Arial", Font.BOLD, 32));
		lblServerPort.setBounds(306, 366, 338, 60);
		contentPane.add(lblServerPort);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD, 32));
		textField.setBounds(598, 255, 476, 60);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.BOLD, 32));
		textField_1.setColumns(10);
		textField_1.setBounds(598, 367, 476, 60);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(textField.getText() + "," + Integer.parseInt(textField_1.getText()));
				dispose();
				new inMatchWithPlayer(textField.getText(), Integer.parseInt(textField_1.getText()));
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 32));
		btnNewButton.setBounds(434, 483, 257, 60);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Arial", Font.BOLD, 32));
		btnCancel.setBounds(740, 483, 257, 60);
		contentPane.add(btnCancel);
		
		this.setVisible(true);
	}
}
