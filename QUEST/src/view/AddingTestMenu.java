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
import model.Test;
import model.User;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * Handles the adding of tests by a teacher.
 * 
 * @author Darren Garcia
 */

public class AddingTestMenu {

	private JFrame frmTeacherModule;
	public String des;
	private JTextField titleField;
	private JTextField ansField;
	private JTextField badgeField;
	private JTextField areaField;
	private JTextField badgeNameField;
	
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

	
	
	public AddingTestMenu(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * 
	 */
	public void initialize(User user) {
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 807, 546);
		panel.add(tabbedPane);
		
		JPanel testPanel = new JPanel();
		tabbedPane.addTab("Tests", null, testPanel, null);
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
		txtrDescriptionArea.setLineWrap(true);
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
