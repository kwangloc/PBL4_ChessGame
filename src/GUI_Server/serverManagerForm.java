package GUI_Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ChessServer.ChessServer;
import ChessServer.ChessServer2;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class serverManagerForm extends JFrame {

	private JPanel contentPane;
	// Server
	ChessServer chessServer;
	ChessServer2 chessServer2;
	//
	static JLabel lblServer1Con;
	JLabel lblServer2Con;
	static JComboBox<String> comboBox;
	static DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
	JLabel lblServer1Noti;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverManagerForm frame = new serverManagerForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public serverManagerForm() {
//		chessServer = new ChessServer(this);
		chessServer = new ChessServer();
		Thread thread = new Thread(() -> {
            chessServer.startServer();
        });
		
		chessServer2 = new ChessServer2();
		Thread thread2 = new Thread(() -> {
            chessServer2.startServer();
        });
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server Manager");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 36));
		lblNewLabel.setBounds(696, 55, 296, 73);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Server 1");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(279, 196, 132, 56);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Status:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(279, 296, 75, 32);
		contentPane.add(lblNewLabel_2);

		
		JLabel lblNewLabel_2_1 = new JLabel("Connection:");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(279, 338, 121, 32);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblServer1Sta = new JLabel("off");
		lblServer1Sta.setForeground(new Color(255, 0, 0));
		lblServer1Sta.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Sta.setBounds(364, 296, 84, 32);
		contentPane.add(lblServer1Sta);
		
		lblServer1Con = new JLabel("0");
		lblServer1Con.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Con.setBounds(410, 338, 84, 32);
		contentPane.add(lblServer1Con);
		
		JLabel lblDes = new JLabel("Description:");
		lblDes.setFont(new Font("Arial", Font.BOLD, 20));
		lblDes.setBounds(279, 254, 121, 32);
		contentPane.add(lblDes);
		
		JLabel lblNewLabel_1_1 = new JLabel("Server 2");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(999, 196, 132, 56);
		contentPane.add(lblNewLabel_1_1);
		
		
		
		JLabel lblNewLabel_2_3 = new JLabel("Status:");
		lblNewLabel_2_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_3.setBounds(999, 296, 75, 32);
		contentPane.add(lblNewLabel_2_3);
		
		JLabel lblServer2Sta = new JLabel("off");
		lblServer2Sta.setForeground(new Color(255, 0, 0));
		lblServer2Sta.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer2Sta.setBounds(1084, 296, 84, 32);
		contentPane.add(lblServer2Sta);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Connection:");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_1.setBounds(999, 338, 121, 32);
		contentPane.add(lblNewLabel_2_1_1);
		
		lblServer2Con = new JLabel("0");
		lblServer2Con.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer2Con.setBounds(1130, 338, 84, 32);
		contentPane.add(lblServer2Con);
		
		JLabel lblServer1Des = new JLabel("player with player mode");
		lblServer1Des.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Des.setBounds(410, 254, 237, 32);
		contentPane.add(lblServer1Des);
		
		JLabel lblDes_1 = new JLabel("Description:");
		lblDes_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblDes_1.setBounds(999, 256, 121, 32);
		contentPane.add(lblDes_1);
		
		JLabel lblServer2Des = new JLabel("chess engine mode");
		lblServer2Des.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer2Des.setBounds(1130, 256, 237, 32);
		contentPane.add(lblServer2Des);
		// btnServer1Start
		JButton btnServer1Start = new JButton("Start");
		btnServer1Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        thread.start();
		        lblServer1Sta.setText("running");
		        lblServer1Sta.setForeground(Color.GREEN);
			}
		});
		
		btnServer1Start.setBackground(new Color(0, 128, 64));
		btnServer1Start.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer1Start.setBounds(421, 210, 84, 32);
		contentPane.add(btnServer1Start);
		
		// btnServer1Stop
		JButton btnServer1Stop = new JButton("Stop");
		btnServer1Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer.stopServer();
			}
		});
		btnServer1Stop.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer1Stop.setBackground(new Color(255, 0, 0));
		btnServer1Stop.setBounds(519, 210, 84, 32);
		contentPane.add(btnServer1Stop);
		
		// btnServer1CloseAllConnections
		JButton btnServer1CloseAllConnections = new JButton("Close all connections");
		btnServer1CloseAllConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer.closeAllConnections();
			}
		});
		btnServer1CloseAllConnections.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer1CloseAllConnections.setBackground(new Color(255, 128, 0));
		btnServer1CloseAllConnections.setBounds(461, 338, 260, 32);
		contentPane.add(btnServer1CloseAllConnections);
		
		// btnServer2Start
		JButton btnServer2Start = new JButton("Start");
		btnServer2Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        thread2.start();
		        lblServer2Sta.setText("running");
		        lblServer2Sta.setForeground(Color.GREEN);
			}
		});
		btnServer2Start.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer2Start.setBackground(new Color(0, 128, 64));
		btnServer2Start.setBounds(1141, 210, 84, 32);
		contentPane.add(btnServer2Start);
		// btnServer2Stop
		JButton btnServer2Stop = new JButton("Stop");
		btnServer2Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer2.stopServer();
			}
		});
		btnServer2Stop.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer2Stop.setBackground(Color.RED);
		btnServer2Stop.setBounds(1239, 210, 84, 32);
		contentPane.add(btnServer2Stop);
		
		// btnServer2CloseAllConnections
		JButton btnServer2CloseAllConnections = new JButton("Close all connections");
		btnServer2CloseAllConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chessServer2.closeAllConnections();
			}
		});
		btnServer2CloseAllConnections.setFont(new Font("Arial", Font.PLAIN, 20));
		btnServer2CloseAllConnections.setBackground(new Color(255, 128, 0));
		btnServer2CloseAllConnections.setBounds(1189, 338, 260, 32);
		contentPane.add(btnServer2CloseAllConnections);
		
		comboBox = new JComboBox();
		comboBox.setBounds(445, 398, 202, 32);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("List connection:");
		lblNewLabel_2_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2_1_2.setBounds(279, 395, 171, 32);
		contentPane.add(lblNewLabel_2_1_2);
		
		JButton btnClose = new JButton("Remove");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(comboBox.getSelectedItem().toString());
				String ipToRemove = comboBox.getSelectedItem().toString();
				System.out.println(ipToRemove);
				if(chessServer.removeClient(ipToRemove)) {
					lblServer1Noti.setText("Removed " + ipToRemove);
				} else {
					System.out.println("False");
				}
			}
		});
		btnClose.setFont(new Font("Arial", Font.PLAIN, 20));
		btnClose.setBackground(new Color(255, 128, 0));
		btnClose.setBounds(657, 398, 109, 32);
		contentPane.add(btnClose);
		
		lblServer1Noti = new JLabel("Notification:");
		lblServer1Noti.setForeground(Color.RED);
		lblServer1Noti.setFont(new Font("Arial", Font.BOLD, 20));
		lblServer1Noti.setBounds(445, 440, 202, 32);
		contentPane.add(lblServer1Noti);
		
		setVisible(true);
	}
//	public static void updateServer1(String connections) {
//		lblServer1Con.setText(connections);
//	}
	public static void updateServer1(ArrayList<String> arrayList) {
		lblServer1Con.setText(arrayList.size()+"");
		comboBoxModel.removeAllElements();
		for (String address : arrayList) {
//			comboBox.
			comboBoxModel.addElement(address);
		}
		comboBox.setModel(comboBoxModel);
	}
	public void updateServer2(String connections) {
		lblServer2Con.setText(connections);
	}
}
