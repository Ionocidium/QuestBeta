package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Answer;
import model.Exercises;
import model.Test;
import model.User;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ResultMenu {

	private JFrame frame;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ResultMenu window = new ResultMenu();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ResultMenu(User user, Test test, Answer answer, String des, int pts, int ach, Exercises question) {
		initialize(user, test, answer, des, pts, ach, question);
	}


	@SuppressWarnings("null")
	public void initialize(User user, Test test, Answer answer, String des, int pts, int ach, Exercises question) {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle(test.getTitle());
		frame.setVisible(true);

		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(10, 11, 126, 14);
		frame.getContentPane().add(lblDescription);

		JLabel lblVerdict = new JLabel("Verdict");
		lblVerdict.setBounds(10, 309, 46, 14);
		frame.getContentPane().add(lblVerdict);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							//moving windows
							MainMenu mm = new MainMenu(user, question);
							mm.setVisible(true);
							frame.dispose();
						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnConfirm.setBounds(335, 481, 89, 23);
		frame.getContentPane().add(btnConfirm);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 414, 157);
		frame.getContentPane().add(scrollPane);

		JTextArea txtrCodeArea = new JTextArea();
		txtrCodeArea.setText(test.getFullAnswer());
		txtrCodeArea.setEditable(false);
		scrollPane.setViewportView(txtrCodeArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 334, 414, 120);
		frame.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 36, 414, 94);
		frame.getContentPane().add(scrollPane_2);
		
		JTextArea txtrDescriptionArea = new JTextArea();
		txtrDescriptionArea.setText(test.getDescription());
		txtrDescriptionArea.setLineWrap(true);
		txtrDescriptionArea.setWrapStyleWord(true);
		txtrDescriptionArea.setEditable(false);
		scrollPane_2.setViewportView(txtrDescriptionArea);
		System.out.println(answer.getAnswer());
		System.out.println(test.getAnswer());
		
		if (answer.getAnswer().equals(test.getAnswer())) {
			JTextArea txtrVerdictArea = new JTextArea();
			txtrVerdictArea.setText(test.getCorrect());
			txtrVerdictArea.setLineWrap(true);
			txtrVerdictArea.setWrapStyleWord(true);
			txtrVerdictArea.setEditable(false);
			scrollPane_1.setViewportView(txtrVerdictArea);
			
			//inserting into database, adding points
			try {
				Connection conn = null;
				java.sql.Statement stmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");	        

					conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
					stmt = (Statement) conn.createStatement();
					
					/**
					 * Boss cleared badges, if they clear the boss it is inserted into the database.
					 */
					
					int emblem = 19;
					switch (user.getArea()) {
						case 1: emblem = 19; break;
						case 2: emblem = 24; break;
						case 3: emblem = 20; break;
						case 4: emblem = 21; break;
						case 5: emblem = 22; break;
						case 6: emblem = 23; break;
						case 7: emblem = 25; break;
					}
					
					String bossclear = "INSERT INTO userachievements (U_Num, A_Num) " +
									   "VALUES ('" + user.getUserNumber() + "', '" + emblem + "')" +
									   "ON DUPLICATE KEY UPDATE U_Num = U_Num"; 

					stmt.executeUpdate(bossclear);
					
					/**
					 * This next snippet is to check whether or not they have cleared all the areas
					 * within the game. This is done by checking if the amount of achievements between
					 * and including 19 and 25 is 7.
					 */
					
					String areatest = "SELECT * " +
							   		  "FROM userachievements " +
							   		  "WHERE U_Num = " + user.getUserNumber() + " AND A_Num > 18 AND A_Num < 26";
	
					ResultSet at = stmt.executeQuery(areatest);
					
					int row = 0;
					int count = 0;
					while (at.next()) {
						row = at.getInt(1);
						count++;
					}
					
					if (count >= 7) {
						String areaclear = "INSERT INTO userachievements (U_Num, A_Num) " +
								   		   "VALUES ('" + user.getUserNumber() + "', '26')" +
								           "ON DUPLICATE KEY UPDATE U_Num = U_Num"; 

						stmt.executeUpdate(areaclear);
					}
					
					String testquery = "SELECT * " +
									   "FROM userachievements " +
							           "WHERE U_Num = " + user.getUserNumber() + " AND A_Num = " + ach;
			
					ResultSet achievements = stmt.executeQuery(testquery);
					
					if (achievements.next()) {
						String query = "UPDATE users " +
									   "SET U_Pts = U_Pts + " + (pts/5) + " " +
							           "WHERE U_Num = " + user.getUserNumber() + " ";

						stmt.executeUpdate(query);
						
						query = "INSERT INTO usertests (U_Num, T_Num, UT_Num) " +
								"VALUES ('" + user.getUserNumber() + "', '" + test.getNumber() + "', '1')" +
							    "ON DUPLICATE KEY UPDATE UT_Num = UT_Num + 1"; 
						
						stmt.executeUpdate(query);
						
						user.setPoints((int) (user.getPoints() + Math.floor(pts/5)));
					} 
					else {
						String query = "UPDATE users " +
								   	   "SET U_Pts = U_Pts + " + pts + " AND U_Ach = U_Ach + 1 " +
							           "WHERE U_Num = " + user.getUserNumber() + " ";

						stmt.executeUpdate(query);
						
						query = "INSERT INTO userachievements (U_Num, A_Num)" +
								"VALUES ('" + user.getUserNumber() + "', '" + ach + "')";
						
						stmt.executeUpdate(query);
						
						query = "INSERT INTO usertests (U_Num, T_Num, UT_Num) " +
								"VALUES ('" + user.getUserNumber() + "', '" + test.getNumber() + "', '1')" +
							    "ON DUPLICATE KEY UPDATE UT_Num = UT_Num + 1"; 
						
						stmt.executeUpdate(query);
								
						JOptionPane.showMessageDialog(null, "You have obtained an achievement!");
						user.setAchievements(user.getAchievements() + 1);
						user.setPoints(user.getPoints() + pts);
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
		}
		else {
			JTextArea txtrVerdictArea = new JTextArea();
			txtrVerdictArea.setText("You've gotten the question incorrect, try again!");
			txtrVerdictArea.setLineWrap(true);
			txtrVerdictArea.setWrapStyleWord(true);
			txtrVerdictArea.setEditable(false);
			scrollPane_1.setViewportView(txtrVerdictArea);
			
			txtrCodeArea.setText("");
			txtrCodeArea.setBackground(Color.gray);
		}
		
	}
}
