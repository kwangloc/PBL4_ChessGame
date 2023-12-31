package GUI_Client;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Board;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inMatchWithPlayer extends JFrame {
	String ipServer;
	int portServer;
	
	public matchFeaturesPanel matchFeaturesPanel;
	Board board;
	
	
	private JPanel contentPane;
	JComboBox<String> comboBox = new JComboBox<String>();;
	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					inMatchWithPlayer frame = new inMatchWithPlayer();
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
	public inMatchWithPlayer(String ipServer, int portServer) {
		this.ipServer = ipServer;
		this.portServer = portServer;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 1200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chess game");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(718, 10, 279, 70);
		contentPane.add(lblNewLabel);
		// Board
		board = new Board(ipServer, portServer, this);
		board.setBounds(162, 98, 680, 680);
//		board.setBounds(162, 98, board.tileSize*board.cols, board.tileSize*board.rows);

		contentPane.add(board);
		// History
//		moveHistoryPanel moveHistoryPanel = new moveHistoryPanel();
//		moveHistoryPanel.setBounds(750, 100, 600, 315);
//		contentPane.add(moveHistoryPanel);
		// Chat
		matchFeaturesPanel = new matchFeaturesPanel();
		matchFeaturesPanel.setBounds(896, 59, 600, 686);
		contentPane.add(matchFeaturesPanel);
		
		JLabel lblNewLabel_1 = new JLabel("Theme:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(931, 761, 61, 17);
		contentPane.add(lblNewLabel_1);
		
		comboBox.setBounds(989, 755, 110, 32);
		contentPane.add(comboBox);
		
		comboBoxModel.addElement("Default");
		comboBoxModel.addElement("Red");
		comboBoxModel.addElement("Green");
		comboBoxModel.addElement("Blue");
		comboBox.setModel(comboBoxModel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(comboBox.getSelectedItem().toString());
				String themeOption = comboBox.getSelectedItem().toString();
				Color whiteSquare = new Color(227, 198, 181);
				Color blackSquare = new Color(157, 105, 53);
				switch (themeOption) {
					case "Red":
//						whiteSquare = new Color(227, 198, 181);
						blackSquare = new Color(153, 51, 51);
						break;
					case "Green":
//						whiteSquare = new Color(227, 198, 181);
						blackSquare = new Color(106, 153, 78);
						break;
					case "Blue":
						whiteSquare = new Color(255, 232, 214);
						blackSquare = new Color(26, 117, 159);
						break;
					default:
						break;
				}
				board.updateTheme(whiteSquare, blackSquare);
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(1109, 758, 45, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quit");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(1454, 17, 76, 32);
		contentPane.add(btnNewButton_1);
		
		
		//
		this.setVisible(true);
	}
	public void updateHistory(String move) {
		matchFeaturesPanel.updateHistory(move);
	}
	public void goToSelectMode() {
		new selectMode();
		dispose();
	}
}
