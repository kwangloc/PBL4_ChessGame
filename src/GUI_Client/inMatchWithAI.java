package GUI_Client;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Board;

public class inMatchWithAI extends JFrame {
	String ipServer;
	int portServer;
	
	public matchFeaturesPanel matchFeaturesPanel;
	Board board;
	private JPanel contentPane;
	JComboBox<String> comboBox = new JComboBox<String>();;
	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					inMatchWithAI frame = new inMatchWithAI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public inMatchWithAI(String ipServer, int portServer) {
		this.ipServer = ipServer;
		this.portServer = portServer;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
