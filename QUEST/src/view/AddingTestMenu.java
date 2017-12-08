package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;

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
import model.Database;
import model.Test;
import model.User;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

/**
 * Handles the adding of tests by a teacher.
 * 
 * @author Darren Garcia
 */

public class AddingTestMenu {

	private JFrame frmTeacherModule;
	public String des;
	private ArrayList <Integer> testnum;
	Vector<Integer> comboBoxItems;
	private JTextField titleField;
	private JTextField ansField;
	private JTextField badgeField;
	private JTextField areaField;
	private JTextField badgeNameField;
	private JTextField dTitleField;
	private JTextField dAnswerField;
	private JTextField dBadgeField;
	private JTextField dAreaField;
	
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

	
	
	public AddingTestMenu(Database db) {
		initialize(db);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void initialize(Database db) {
		String cod = "";
		int pts = 0;
		Test test = new Test();
				
		frmTeacherModule = new JFrame();
		frmTeacherModule.setBounds(100, 100, 850, 650);
		frmTeacherModule.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmTeacherModule.setTitle("Teacher Module");
		frmTeacherModule.setVisible(true);
		JPanel panel = new JPanel();
		frmTeacherModule.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		final Test ptest = test;
		final int pt = pts;
		testnum = new ArrayList<Integer>();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 807, 546);
		panel.add(tabbedPane);
		
		JPanel testPanel = new JPanel();
		tabbedPane.addTab("Add Tests", null, testPanel, null);
		testPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title of Test: ");
		lblTitle.setBounds(12, 13, 116, 16);
		testPanel.add(lblTitle);
		
		titleField = new JTextField();
		titleField.setToolTipText("Title of the test, to be displayed only");
		titleField.setBounds(12, 42, 250, 22);
		testPanel.add(titleField);
		titleField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(12, 77, 72, 16);
		testPanel.add(lblDescription);
		
		JScrollPane scrollDescriptionArea = new JScrollPane();
		scrollDescriptionArea.setBounds(12, 106, 250, 168);
		testPanel.add(scrollDescriptionArea);
		
		JTextArea txtrDescriptionArea = new JTextArea();
		txtrDescriptionArea.setToolTipText("Description and instructions for the test are inputed here");
		scrollDescriptionArea.setViewportView(txtrDescriptionArea);
		txtrDescriptionArea.setWrapStyleWord(true);
		
		JScrollPane testPane = new JScrollPane();
		testPane.setBounds(12, 316, 250, 168);
		testPanel.add(testPane);
		
		JTextArea testAnsArea = new JTextArea();
		testAnsArea.setToolTipText("Input the code that will be shown during the boss");
		testPane.setViewportView(testAnsArea);
		
		JLabel lblTestCode = new JLabel("Test Code: ");
		lblTestCode.setBounds(12, 287, 67, 16);
		testPanel.add(lblTestCode);
		
		JLabel lblFinalAnswer = new JLabel("Final Answer Code: ");
		lblFinalAnswer.setBounds(274, 77, 116, 16);
		testPanel.add(lblFinalAnswer);
		
		JScrollPane ansPane = new JScrollPane();
		ansPane.setBounds(274, 106, 250, 168);
		testPanel.add(ansPane);
		
		JTextArea txtAnsCode = new JTextArea();
		txtAnsCode.setToolTipText("Input the correct and full code");
		ansPane.setViewportView(txtAnsCode);
		
		JLabel lblIncorrect = new JLabel("Incorrect Display Message: ");
		lblIncorrect.setBounds(274, 287, 158, 16);
		testPanel.add(lblIncorrect);
		
		JScrollPane incorrectPane = new JScrollPane();
		incorrectPane.setBounds(274, 316, 250, 168);
		testPanel.add(incorrectPane);
		
		JTextArea incorrectArea = new JTextArea();
		incorrectArea.setToolTipText("Input the message when the user gets the code incorrect");
		incorrectPane.setViewportView(incorrectArea);
		
		JLabel lblCorrect = new JLabel("Correct Display Message: ");
		lblCorrect.setBounds(536, 77, 158, 16);
		testPanel.add(lblCorrect);
		
		JScrollPane correctPane = new JScrollPane();
		correctPane.setBounds(536, 106, 250, 168);
		testPanel.add(correctPane);
		
		JTextArea correctArea = new JTextArea();
		correctArea.setToolTipText("Input the message when the user gets the code correct");
		correctPane.setViewportView(correctArea);
		
		JLabel lblAnswer = new JLabel("Answer of Test:");
		lblAnswer.setBounds(536, 287, 116, 16);
		testPanel.add(lblAnswer);
		
		ansField = new JTextField();
		ansField.setToolTipText("Answer that a user has to input to get the question correctly");
		ansField.setColumns(10);
		ansField.setBounds(536, 316, 250, 22);
		testPanel.add(ansField);
		
		JLabel lblBadge = new JLabel("Badge Association:");
		lblBadge.setBounds(536, 351, 116, 16);
		testPanel.add(lblBadge);
		
		badgeField = new JTextField();
		badgeField.setToolTipText("Leave blank if the test is not associated with any badge");
		badgeField.setColumns(10);
		badgeField.setBounds(536, 380, 250, 22);
		testPanel.add(badgeField);
		
		JLabel lblAreaNumber = new JLabel("Area Number:");
		lblAreaNumber.setToolTipText("Input the area number for the test to appear upon. If left blank, it will be associated with the first.");
		lblAreaNumber.setBounds(536, 415, 116, 16);
		testPanel.add(lblAreaNumber);
		
		areaField = new JTextField();
		areaField.setToolTipText("Leave blank if the test is not associated with any badge");
		areaField.setColumns(10);
		areaField.setBounds(536, 444, 250, 22);
		testPanel.add(areaField);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(697, 42, 89, 23);
		testPanel.add(btnSubmit);
		
		JLabel lblWarning = new JLabel("A test isn't a candidate for deletion until this page is refreshed.");
		lblWarning.setBounds(427, 13, 359, 16);
		testPanel.add(lblWarning);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						//Add into database
						try {
							Connection conn = null;
							Statement stmt = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
								stmt = (Statement) conn.createStatement();

								if (areaField.getText() == null || areaField.getText().equals("")) {
									areaField.setText("1");	
								}
								
								String query = "INSERT INTO tests (T_Ttl, T_Msg, T_Cod, T_Ans, T_Fan, T_Pts, T_Cor, T_Inc, A_Num, AR_Num) " +
											   "VALUES ('" + titleField.getText() + "', '" + txtrDescriptionArea.getText() + "', '" + testAnsArea.getText() + "', '" + ansField.getText() + "', '" + txtAnsCode.getText() + "', 20, '" + correctArea.getText() + "', '" + incorrectArea.getText() + "', '" + badgeField.getText() + "', '" + areaField.getText() + "')";

								stmt.executeUpdate(query);
								
								JOptionPane.showMessageDialog(null, "You have successfully inserted the test into the database.");
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
				});
			}
		});
		
		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(null);
		tabbedPane.addTab("Delete Tests", null, deletePanel, null);
		
		JLabel dTitle = new JLabel("Title of Test: ");
		dTitle.setBounds(274, 13, 116, 16);
		deletePanel.add(dTitle);
		
		dTitleField = new JTextField();
		dTitleField.setEditable(false);
		dTitleField.setToolTipText("Title of the test, to be displayed only");
		dTitleField.setColumns(10);
		dTitleField.setBounds(274, 42, 250, 22);
		deletePanel.add(dTitleField);
		
		JLabel dDescription = new JLabel("Description: ");
		dDescription.setBounds(12, 77, 72, 16);
		deletePanel.add(dDescription);
		
		JScrollPane dDescriptionPane = new JScrollPane();
		dDescriptionPane.setBounds(12, 106, 250, 168);
		deletePanel.add(dDescriptionPane);
		
		JTextArea dDescriptionArea = new JTextArea();
		dDescriptionArea.setEditable(false);
		dDescriptionPane.setViewportView(dDescriptionArea);
		
		JLabel dTestCode = new JLabel("Test Code: ");
		dTestCode.setBounds(12, 287, 67, 16);
		deletePanel.add(dTestCode);
		
		JScrollPane dTestPane = new JScrollPane();
		dTestPane.setBounds(12, 316, 250, 168);
		deletePanel.add(dTestPane);
		
		JTextArea dTestArea = new JTextArea();
		dTestArea.setEditable(false);
		dTestPane.setViewportView(dTestArea);
		
		JLabel dAns = new JLabel("Final Answer Code: ");
		dAns.setBounds(274, 77, 116, 16);
		deletePanel.add(dAns);
		
		JScrollPane dAnsPane = new JScrollPane();
		dAnsPane.setBounds(274, 106, 250, 168);
		deletePanel.add(dAnsPane);
		
		JTextPane dAnsArea = new JTextPane();
		dAnsArea.setEditable(false);
		dAnsPane.setViewportView(dAnsArea);
		
		JLabel dIncorrect = new JLabel("Incorrect Display Message: ");
		dIncorrect.setBounds(274, 287, 158, 16);
		deletePanel.add(dIncorrect);
		
		JScrollPane dIncorrectPane = new JScrollPane();
		dIncorrectPane.setBounds(274, 316, 250, 168);
		deletePanel.add(dIncorrectPane);
		
		JTextPane dIncorrectArea = new JTextPane();
		dIncorrectArea.setEditable(false);
		dIncorrectPane.setViewportView(dIncorrectArea);
		
		JLabel dCorrect = new JLabel("Correct Display Message: ");
		dCorrect.setBounds(536, 77, 158, 16);
		deletePanel.add(dCorrect);
		
		JScrollPane dCorrectPane = new JScrollPane();
		dCorrectPane.setBounds(536, 106, 250, 168);
		deletePanel.add(dCorrectPane);
		
		JTextPane dCorrectArea = new JTextPane();
		dCorrectArea.setEditable(false);
		dCorrectPane.setViewportView(dCorrectArea);
		
		JLabel dAnswerofTest = new JLabel("Answer of Test:");
		dAnswerofTest.setBounds(536, 287, 116, 16);
		deletePanel.add(dAnswerofTest);
		
		dAnswerField = new JTextField();
		dAnswerField.setEditable(false);
		dAnswerField.setToolTipText("Answer that a user has to input to get the question correctly");
		dAnswerField.setColumns(10);
		dAnswerField.setBounds(536, 316, 250, 22);
		deletePanel.add(dAnswerField);
		
		JLabel dBadge = new JLabel("Badge Association:");
		dBadge.setBounds(536, 351, 116, 16);
		deletePanel.add(dBadge);
		
		dBadgeField = new JTextField();
		dBadgeField.setEditable(false);
		dBadgeField.setToolTipText("Leave blank if the test is not associated with any badge");
		dBadgeField.setColumns(10);
		dBadgeField.setBounds(536, 380, 250, 22);
		deletePanel.add(dBadgeField);
		
		JLabel dArea = new JLabel("Area Number:");
		dArea.setToolTipText("Input the area number for the test to appear upon. If left blank, it will be associated with the first.");
		dArea.setBounds(536, 415, 116, 16);
		deletePanel.add(dArea);
		
		dAreaField = new JTextField();
		dAreaField.setEditable(false);
		dAreaField.setToolTipText("Leave blank if the test is not associated with any badge");
		dAreaField.setColumns(10);
		dAreaField.setBounds(536, 444, 250, 22);
		deletePanel.add(dAreaField);
		
		JLabel dTestNumber = new JLabel("Test Number:");
		dTestNumber.setBounds(12, 13, 103, 16);
		deletePanel.add(dTestNumber);
		
		/**
		 * Obtaining all the test numbers from the database and the combo box
		 */
		
		
		try {
			Connection conn = null;
			Statement stmt = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
				stmt = (Statement) conn.createStatement();

				String query = "SELECT T_Num FROM tests " +
							   "WHERE T_Del = 0";

				ResultSet rs = stmt.executeQuery(query);
				
				comboBoxItems = new Vector<Integer>();
				
				while (rs.next()) {
					testnum.add(rs.getInt("T_Num"));
					comboBoxItems.add(rs.getInt("T_Num"));
				}
			} 
			catch(Exception a) {
				System.out.println(a.getMessage());	    	
				JOptionPane.showMessageDialog(null, "A connection error has occured. Your IP, SQL username and/or SQL password may be incorrect.");
			}		

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		final DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>(comboBoxItems);
		
		JComboBox<Integer> testCombo = new JComboBox<Integer>(model);
		testCombo.setBounds(12, 42, 250, 22);
		deletePanel.add(testCombo);
		testCombo.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent eventSource) {
				   //JComboBox combo = testCombo.getSource();
				   int selected = (int) testCombo.getSelectedItem();

				   try {
						Connection conn = null;
						Statement stmt = null;

						try {
							Class.forName("com.mysql.jdbc.Driver");	        

							conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
							stmt = (Statement) conn.createStatement();

							String query = "SELECT * FROM tests " +
										   "WHERE T_Num = " + selected;

							ResultSet rs = stmt.executeQuery(query);
							
							comboBoxItems = new Vector<Integer>();
							
							if (rs.next()) {
								dTitleField.setText(rs.getString("T_Ttl"));
								dDescriptionArea.setText(rs.getString("T_Msg"));
								dTestArea.setText(rs.getString("T_Cod"));
								dAnsArea.setText(rs.getString("T_Fan"));
								dIncorrectArea.setText(rs.getString("T_Inc"));
								dCorrectArea.setText(rs.getString("T_Cor"));
								dAnswerField.setText(rs.getString("T_Ans"));
								dBadgeField.setText(Integer.toString(rs.getInt("A_Num")));
								dAreaField.setText(Integer.toString(rs.getInt("AR_Num")));
							}
						} 
						catch(Exception a) {
							System.out.println(a.getMessage());	    	
							JOptionPane.showMessageDialog(null, "A connection error has occured. Your IP, SQL username and/or SQL password may be incorrect.");
						}		

					} 
					catch (Exception e) {
						e.printStackTrace();
					}
			   }
			}
		);
		 
		JPanel badgePanel = new JPanel();
		tabbedPane.addTab("Badges", null, badgePanel, null);
		badgePanel.setLayout(null);
		
		JLabel lblBadgeName = new JLabel("Name of Badge:");
		lblBadgeName.setBounds(272, 87, 116, 16);
		badgePanel.add(lblBadgeName);
		
		badgeNameField = new JTextField();
		badgeNameField.setToolTipText("Name of the badge.");
		badgeNameField.setColumns(10);
		badgeNameField.setBounds(272, 116, 250, 22);
		badgePanel.add(badgeNameField);
		
		JLabel lblBadgeDescription = new JLabel("Description: ");
		lblBadgeDescription.setBounds(272, 151, 72, 16);
		badgePanel.add(lblBadgeDescription);
		
		JScrollPane badgeDescriptionPane = new JScrollPane();
		badgeDescriptionPane.setBounds(272, 180, 250, 168);
		badgePanel.add(badgeDescriptionPane);
		
		JTextArea badgeDescriptionArea = new JTextArea();
		badgeDescriptionArea.setToolTipText("Badge description");
		badgeDescriptionPane.setViewportView(badgeDescriptionArea);
		
		JComboBox badgeTypeCombo = new JComboBox();
		badgeTypeCombo.setToolTipText("The badge type, 1 = Bronze, 2 = Silver. Gold is reserved for areas and Platinum is reserved for getting all the badges.");
		badgeTypeCombo.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		badgeTypeCombo.setSelectedIndex(0);
		badgeTypeCombo.setBounds(272, 390, 76, 22);
		badgePanel.add(badgeTypeCombo);
		
		JLabel lblBadgeType = new JLabel("Badge Type:");
		lblBadgeType.setBounds(272, 361, 72, 16);
		badgePanel.add(lblBadgeType);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						//Add into database
						try {
							Connection conn = null;
							Statement stmt = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
								stmt = (Statement) conn.createStatement();
								
								int selected = (int) testCombo.getSelectedItem();
								
								String query = "UPDATE tests " +
											   "SET T_Del = 1 " +
											   "WHERE T_Num = " + selected;

								stmt.executeUpdate(query);
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
				});
			}
		});
		deleteButton.setBounds(697, 42, 89, 23);
		deletePanel.add(deleteButton);
		
		JButton badgeSubmit = new JButton("Submit");
		badgeSubmit.setBounds(425, 389, 97, 25);
		badgePanel.add(badgeSubmit);
		badgeSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						//Add into database
						try {
							Connection conn = null;
							Statement stmt = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
								stmt = (Statement) conn.createStatement();
								
								String query = "INSERT INTO achievements (A_Ttl, A_Msg, A_Bdg) " +
											   "VALUES ('" + badgeNameField.getText() + "', '" + badgeDescriptionArea.getText() + "', '" + (String)badgeTypeCombo.getSelectedItem() + "')";

								stmt.executeUpdate(query);
								
								JOptionPane.showMessageDialog(null, "You have successfully inserted the badge into the database.");
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
				});
			}
		});
	}
}
