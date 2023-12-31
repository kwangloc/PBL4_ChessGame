package GUI_Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class selectMode extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectMode frame = new selectMode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public selectMode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 1200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlayWithFriend = new JButton("Play with friend");
		btnPlayWithFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormListRoom room = new FormListRoom();
				room.show();
//				new inMatchWithPlayer("localhost", 4444);
			}
		});
		
		JButton btnQuickPlay = new JButton("Quick play");
		btnQuickPlay.setForeground(Color.BLACK);
		btnQuickPlay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnQuickPlay.setBackground(new Color(0, 128, 128));
		btnQuickPlay.setBounds(644, 346, 257, 63);
		contentPane.add(btnQuickPlay);
		btnPlayWithFriend.setForeground(new Color(0, 0, 0));
		btnPlayWithFriend.setBackground(new Color(0, 128, 128));
		btnPlayWithFriend.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPlayWithFriend.setBounds(644, 191, 257, 63);
		contentPane.add(btnPlayWithFriend);
		
		JButton btnPlayWithAi = new JButton("Play with AI");
		btnPlayWithAi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
				new inMatchWithPlayer("localhost", 6666);
			}
		});
		btnPlayWithAi.setForeground(Color.BLACK);
		btnPlayWithAi.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPlayWithAi.setBackground(new Color(0, 128, 128));
		btnPlayWithAi.setBounds(644, 268, 257, 63);
		contentPane.add(btnPlayWithAi);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Truong Quang Loc\\OneDrive\\Hình ảnh\\Get the We Heart It app!.jpg"));
//		lblNewLabel.setBounds(55, 75, 820, 496);
//		contentPane.add(lblNewLabel);
		//bg
//		ImageIcon backgroundIcon = new ImageIcon("C:\\\\Users\\\\Truong Quang Loc\\\\OneDrive\\\\Hình ảnh\\\\Get the We Heart It app!.jpg"); // Replace with the actual path to your image
		ImageIcon backgroundIcon = new ImageIcon(selectMode.class.getResource("/res/bg2.jpg")); // Replace with the actual path to your image
		JLabel backgroundLabel = new JLabel(backgroundIcon);
//        JLabel backgroundLabel = new JLabel(new ImageIcon(selectMode.class.getResource("/res/b.jpg")));
        backgroundLabel.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
        getContentPane().add(backgroundLabel);
        setSize(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(true); // Optional: setResizable to false if you don't want the user to resize the frame
        
		setVisible(true);
	}
}
