package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Answer;
import model.Exercises;
import model.Test;
import model.User;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TestMenu {

	private JFrame frame;
	private JTextField textField;
	private JLabel lFloor;
	private JLabel lLives;
	private JTextArea txtrDescriptionArea;
	private int floor = 1;
	private int lives = 3;
	private String life = "\u2764\u2764\u2764";
	private String ttl = "";
	private String des;

	private String cod = "";
	private int pts = 0;
	private int nach = 0;
	private Test test = new Test();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//TestMenu window = new TestMenu();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * @wbp.parser.entryPoint
	 * 
	 * 
	 */

	
	
	public TestMenu(User user, Exercises question) {
		initialize(user, question);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * 
	 */
	public void initialize(User user, Exercises question) {
		//String ttl = "";
				
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			Connection conn = null;
			Statement stmt = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
				stmt = (Statement) conn.createStatement();

				String query = "SELECT * FROM tests " +
							   "WHERE A_Num != '0' AND AR_Num = '" + user.getArea() + "' " +
						       "ORDER BY RAND() " +
						       "LIMIT 1";
				
				/**
				 * Temporary, right now area 7 gets all tests, regardless if they are cleared or not.
				 */
				
				if (user.getArea() == 7) {
					query = "SELECT * FROM tests " +
							"WHERE A_Num != '0' " +
						    "ORDER BY RAND() " +
						    "LIMIT 1";
				}
				
				ResultSet rs = stmt.executeQuery(query);
				
				if (rs.next()) {
					int rows = rs.getInt(1);
					int num = rs.getInt("T_Num");
					ttl = rs.getString("T_Ttl");
					des = rs.getString("T_Msg");
					cod = rs.getString("T_Cod");
					String ans = rs.getString("T_Ans");
					String fan = rs.getString("T_Fan");
					pts = rs.getInt("T_Pts");
					String cor = rs.getString("T_Cor");
					String inc = rs.getString("T_Inc");
					nach = rs.getInt("A_Num");
					
					test = new Test(num, ttl, des, cod, ans, fan, pts, cor, inc, nach);
				} 
				else {
					JOptionPane.showMessageDialog(null, "Nothing found!");
					MainMenu mm = new MainMenu(user, question);
					mm.setVisible(true);
					frame.dispose();
				}
			} 
			catch(Exception a) {
				System.out.println(a.getMessage());	    	
				JOptionPane.showMessageDialog(null, "A database error occured.");
			}		

		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		frame.setTitle(ttl + " / Points: " + pts);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		if (user.getArea() == 7) {
			frame.setTitle(ttl);
		}
		
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(10, 184, 89, 14);
		panel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 279, 414, 168);
		panel.add(scrollPane);
		
		JTextArea txtrCodeArea = new JTextArea();
		txtrCodeArea.setText(cod);
		txtrCodeArea.setEditable(false);
		scrollPane.setViewportView(txtrCodeArea);
		
		JLabel lblAnswer = new JLabel("Answer: ");
		lblAnswer.setBounds(10, 462, 67, 14);
		panel.add(lblAnswer);
		
		textField = new JTextField();
		textField.setBounds(87, 455, 337, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		final Test ptest = test;
		final int pt = pts;
		final int ach = nach;
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						try {
							if (user.getArea() == 7) {
								if (textField.getText().equals(test.getAnswer())) {
									ImageIcon icon = new ImageIcon(MainMenu.class.getResource("/key.png"));
									JOptionPane.showMessageDialog(null, "Correct! Going up!", "Successful Attack", JOptionPane.PLAIN_MESSAGE, icon);
									floor = floor + 1;
									if (floor > 5) {
										Answer man = new Answer("");
										//moving windows
										ResultMenu rm = new ResultMenu(user, ptest, man, des, pt, ach, question);
										//rm.initialize(user, ptest, man);
										frame.dispose();
									}
								}
								else {
									ImageIcon icon = new ImageIcon(MainMenu.class.getResource("/img/heart.png"));									
									JOptionPane.showMessageDialog(null, "Incorrect. One life lost.", "Failed Attack", JOptionPane.PLAIN_MESSAGE, icon);
									lives = lives - 1;
									if (lives == 0) {
										JOptionPane.showMessageDialog(null, "You've run out of lives! Returning to town...", "Retire", JOptionPane.PLAIN_MESSAGE, icon);
										MainMenu mm = new MainMenu(user, question);
										mm.setVisible(true);
										frame.dispose();
									}
									else if (lives == 2) {
										life = "\u2764\u2764";
									}
									else if (lives == 1) {
										life = "\u2764";
									}
								}
								try {
									Connection conn = null;
									Statement stmt = null;

									try {
										Class.forName("com.mysql.jdbc.Driver");	        

										conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
										stmt = (Statement) conn.createStatement();

										String query = "SELECT * FROM tests " +
													   "WHERE A_Num != '0' " +
												       "ORDER BY RAND() " +
												       "LIMIT 1";
										
										ResultSet rs = stmt.executeQuery(query);
										
										if (rs.next()) {
											int rows = rs.getInt(1);
											int num = rs.getInt("T_Num");
											ttl = rs.getString("T_Ttl");
											des = rs.getString("T_Msg");
											cod = rs.getString("T_Cod");
											String ans = rs.getString("T_Ans");
											String fan = rs.getString("T_Fan");
											pts = rs.getInt("T_Pts");
											String cor = rs.getString("T_Cor");
											String inc = rs.getString("T_Inc");
											nach = rs.getInt("A_Num");
											
											test = new Test(num, ttl, des, cod, ans, fan, pts, cor, inc, nach);
										} 
										else {
											JOptionPane.showMessageDialog(null, "Nothing found!");
										}
									} 
									catch(Exception a) {
										System.out.println(a.getMessage());	    	
										JOptionPane.showMessageDialog(null, "A database error occured.");
									}		

								} 
								catch (Exception e) {
									e.printStackTrace();
								}
								txtrDescriptionArea.setText(des);
								frame.setTitle(ttl);
								txtrCodeArea.setText(cod);
								lFloor.setText("Floor: " + floor + "F");
								lLives.setText("Lives: " + life);
							}
							else {
								Answer man = new Answer(textField.getText());
								//moving windows
								ResultMenu rm = new ResultMenu(user, ptest, man, des, pt, ach, question);
								//rm.initialize(user, ptest, man);
								frame.dispose();
							}
						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnSubmit.setBounds(335, 497, 89, 23);
		panel.add(btnSubmit);
		
		if (user.getArea() == 7) {
			btnSubmit.setText("Proceed");
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 209, 414, 59);
		panel.add(scrollPane_1);
		
		txtrDescriptionArea = new JTextArea();
		txtrDescriptionArea.setText(des);
		txtrDescriptionArea.setLineWrap(true);
		txtrDescriptionArea.setWrapStyleWord(true);
		txtrDescriptionArea.setEditable(false);
		scrollPane_1.setViewportView(txtrDescriptionArea);
		
		JLabel bossImg = new JLabel("");
		bossImg.setIcon(new ImageIcon(TestMenu.class.getResource("/img/boss.png")));
		bossImg.setBounds(125, 26, 198, 144);
		panel.add(bossImg);
		
		lFloor = new JLabel("Floor: 1F");
		lFloor.setVisible(false);
		lFloor.setBounds(20, 495, 56, 16);
		panel.add(lFloor);
		
		lLives = new JLabel("Lives: \u2764\u2764\u2764");
		lLives.setVisible(false);
		lLives.setBounds(97, 495, 89, 16);
		panel.add(lLives);
		
		if (user.getArea() == 7) {
			lFloor.setVisible(true);
			lLives.setVisible(true);
		}
	}
}
