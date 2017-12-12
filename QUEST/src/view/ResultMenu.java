package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Answer;
import model.Database;
import model.Exercises;
import model.Test;
import model.User;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

/**
 * Handles the results of the test. Coming from the test page, the user's answer is transferred
 * to this page. It is then compared and the user is shown the verdict. If they are correct,
 * they will be congratulated and awarded an achievement if applicable. Otherwise, they will
 * have to try the test again.
 * 
 * @author Darren Garcia
 */

public class ResultMenu {

	private JFrame frame;
	private int emblem = 19;
	private Database db;

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
	public ResultMenu(User user, Test test, Answer answer, String des, int pts, int ach, Exercises question, Database db) {
		initialize(user, test, answer, des, pts, ach, question, db);
	}


	@SuppressWarnings("null")
	public void initialize(User user, Test test, Answer answer, String des, int pts, int ach, Exercises question, Database db) {

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
							MainMenu mm = new MainMenu(user, question, db);
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

		if (user.getArea() == 7) {
			JTextArea txtrVerdictArea = new JTextArea();
			txtrVerdictArea.setText("You've reached the pinnacle of the tower! Congratulations on clearing the game!");
			txtrVerdictArea.setLineWrap(true);
			txtrVerdictArea.setWrapStyleWord(true);
			txtrVerdictArea.setEditable(false);
			scrollPane_1.setViewportView(txtrVerdictArea);

			txtrCodeArea.setText("");
			txtrCodeArea.setBackground(Color.gray);

			frame.setTitle("The Tower of Knowledge Results");
			lblDescription.setText("Location:");
			txtrDescriptionArea.setText("The Tower of Knowledge Challenge: Rooftops");

			emblem = 25;

			/**
			 * Check if all areas have been cleared and inputting it into the db
			 */
			
			try {
				Connection conn = null;
				java.sql.Statement stmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");	        

					conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
					stmt = (Statement) conn.createStatement();
					
					PreparedStatement bossclear = conn.prepareStatement("SELECT * FROM userachievements WHERE U_Num = ? AND A_Num = ?");
					bossclear.setObject(1, user.getUserNumber());
					bossclear.setObject(2, emblem);

					ResultSet testingemblem = bossclear.executeQuery();
					
					if (testingemblem.next()) {
						//do nothing
					}
					else {
						PreparedStatement bossupdate = conn.prepareStatement("INSERT INTO userachievements (U_Num, A_Num) VALUES (?, ?)");
						bossupdate.setObject(1, user.getUserNumber());
						bossupdate.setObject(2, emblem);
						
						bossupdate.executeUpdate();
					}

					/**
					 * This next snippet is to check whether or not they have cleared all the areas
					 * within the game. This is done by checking if the amount of achievements between
					 * and including 19 and 25 is 7.
					 */

					PreparedStatement areatest = conn.prepareStatement("SELECT * FROM userachievements WHERE U_Num = ? AND A_Num > ? AND A_Num < ?");
					areatest.setObject(1, user.getUserNumber());
					areatest.setObject(2, 18);
					areatest.setObject(3, 26);

					ResultSet at = areatest.executeQuery();

					int row = 0;
					int count = 0;
					while (at.next()) {
						row = at.getInt(1);
						count++;
					}

					if (count >= 7) {
						
						//to test if the user has already obtained the clear all areas badge
						PreparedStatement st = conn.prepareStatement("SELECT * FROM userachievements WHERE U_Num = ? AND A_Num = ?");
						st.setObject(1, user.getUserNumber());
						st.setObject(2, 26);

						ResultSet testingall = st.executeQuery();
						
						if (testingall.next()) {
							//do nothing
						}
						else {
							PreparedStatement allupdate = conn.prepareStatement("INSERT INTO userachievements VALUES (?, ?)");
							allupdate.setObject(1, user.getUserNumber());
							allupdate.setObject(2, 26);

							allupdate.executeUpdate();
						}
					}
					ImageIcon icon = new ImageIcon(MainMenu.class.getResource("/no-badge.png"));
					JOptionPane.showMessageDialog(null, "You have obtained an achievement!", "Congratulations!", JOptionPane.PLAIN_MESSAGE, icon);
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
		else if (answer.getAnswer().equals(test.getAnswer())) {
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

					conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
					stmt = (Statement) conn.createStatement();

					/**
					 * Boss cleared badges, if they clear the boss it is inserted into the database.
					 */

					switch (user.getArea()) {
					case 1: emblem = 19; break;
					case 2: emblem = 24; break;
					case 3: emblem = 20; break;
					case 4: emblem = 21; break;
					case 5: emblem = 22; break;
					case 6: emblem = 23; break;
					case 7: emblem = 25; break;
					}

					PreparedStatement bossclear = conn.prepareStatement("SELECT * FROM userachievements WHERE U_Num = ? AND A_Num = ?");
					bossclear.setObject(1, user.getUserNumber());
					bossclear.setObject(2, emblem);

					ResultSet testingemblem = bossclear.executeQuery();
					
					if (testingemblem.next()) {
						//do nothing
					}
					else {
						PreparedStatement bossupdate = conn.prepareStatement("INSERT INTO userachievements (U_Num, A_Num) VALUES (?, ?)");
						bossupdate.setObject(1, user.getUserNumber());
						bossupdate.setObject(2, emblem);
						
						bossupdate.executeUpdate();
					}

					/**
					 * This next snippet is to check whether or not they have cleared all the areas
					 * within the game. This is done by checking if the amount of achievements between
					 * and including 19 and 25 is 7.
					 */

					PreparedStatement areatest = conn.prepareStatement("SELECT * FROM userachievements WHERE U_Num = ? AND A_Num > ? AND A_Num < ?");
					areatest.setObject(1, user.getUserNumber());
					areatest.setObject(2, 18);
					areatest.setObject(3, 26);
					
					ResultSet at = areatest.executeQuery();

					int row = 0;
					int count = 0;
					while (at.next()) {
						row = at.getInt(1);
						count++;
					}

					if (count >= 7) {
						
						PreparedStatement st = conn.prepareStatement("SELECT * FROM userachievements WHERE U_Num = ? AND A_Num = ?");
						st.setObject(1, user.getUserNumber());
						st.setObject(2, 26);

						ResultSet testingall = st.executeQuery();
						
						if (testingall.next()) {
							//do nothing
						}
						else {
							PreparedStatement allupdate = conn.prepareStatement("INSERT INTO userachievements VALUES (?, ?)");
							allupdate.setObject(1, user.getUserNumber());
							allupdate.setObject(2, 26);

							allupdate.executeUpdate();
						}
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
						ImageIcon icon = new ImageIcon(MainMenu.class.getResource("/no-badge.png"));
						JOptionPane.showMessageDialog(null, "You have obtained achievement(s)!", "Congratulations!", JOptionPane.PLAIN_MESSAGE, icon);
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
