package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



import model.Exercises;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorldMap {

	private JFrame frame;
	private User user;
	private Exercises question;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//WorldMap window = new WorldMap();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WorldMap(User user, Exercises question) {
		this.user = user;
		this.question = question;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void changeMap(int areaNum){
		user.setArea(areaNum);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quest", "user", "");
			stmt = conn.prepareStatement("UPDATE users SET U_Are = ? WHERE U_Num = ?");
			stmt.setObject(1, areaNum);
			stmt.setObject(2, user.getUserNumber());
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		MainMenu newView = new MainMenu(user, question);
		newView.setVisible(true);
		frame.dispose();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("World Map");
		frame.setResizable(false);
		frame.setBounds(100, 100, 645, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 25, 609, 467);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(WorldMap.class.getResource("/img/mainmap-tp.png")));
		label.setBounds(31, 0, 559, 467);
		panel.add(label);
		
		
		
		JButton areaBtn_1 = new JButton("");
		areaBtn_1.setOpaque(false);
		areaBtn_1.setContentAreaFilled(false);
		areaBtn_1.setBorderPainted(false);
		areaBtn_1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				changeMap(1);
			}
		});
		areaBtn_1.setBounds(131, 221, 25, 23);
		panel.add(areaBtn_1);

		
		JButton areaBtn_2 = new JButton("");
		areaBtn_2.setOpaque(false);
		areaBtn_2.setContentAreaFilled(false);
		areaBtn_2.setBorderPainted(false);
		areaBtn_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JOptionPane.showMessageDialog(null, "testing");
				changeMap(2);
			}
		});
		areaBtn_2.setBounds(167, 312, 25, 23);
		panel.add(areaBtn_2);
		
		JButton areaBtn_3 = new JButton("");
		areaBtn_3.setOpaque(false);
		areaBtn_3.setContentAreaFilled(false);
		areaBtn_3.setBorderPainted(false);
		areaBtn_3.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				changeMap(3);
			}
		});
		areaBtn_3.setBounds(222, 202, 25, 23);
		panel.add(areaBtn_3);
		
		JButton areaBtn_4 = new JButton("");
		areaBtn_4.setOpaque(false);
		areaBtn_4.setContentAreaFilled(false);
		areaBtn_4.setBorderPainted(false);
		areaBtn_4.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				changeMap(4);
			}
		});
		areaBtn_4.setBounds(290, 145, 25, 23);
		panel.add(areaBtn_4);
		
		JButton areaBtn_5 = new JButton("");
		areaBtn_5.setOpaque(false);
		areaBtn_5.setContentAreaFilled(false);
		areaBtn_5.setBorderPainted(false);
		areaBtn_5.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				changeMap(5);
			}
		});
		areaBtn_5.setBounds(450, 202, 25, 31);
		panel.add(areaBtn_5);
		
		JButton areaBtn_6 = new JButton("");
		areaBtn_6.setOpaque(false);
		areaBtn_6.setContentAreaFilled(false);
		areaBtn_6.setBorderPainted(false);
		areaBtn_6.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				changeMap(6);
			}
		});
		areaBtn_6.setBounds(360, 300, 25, 31);
		panel.add(areaBtn_6);
		
		JButton areaBtn_7 = new JButton("");
		areaBtn_7.setOpaque(false);
		areaBtn_7.setContentAreaFilled(false);
		areaBtn_7.setBorderPainted(false);
		areaBtn_7.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0){
				changeMap(7);
			}
		});
		areaBtn_7.setBounds(403, 83, 41, 73);
		areaBtn_7.setEnabled(false); //if area 7 is not unlocked, perform a check to determine if it should be enabled or not(which should be the same for the map)
		panel.add(areaBtn_7);
		
		JButton returnButton = new JButton("Return");
		returnButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		returnButton.setBounds(10, 503, 122, 32);
		frame.getContentPane().add(returnButton);
	}
	
}
