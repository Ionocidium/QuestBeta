package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

import model.Answer;
import model.Database;
import model.Test;
import model.User;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.ComboBoxModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Handles the actions to be done by a teacher. Includes the addition of tests, addition of badges 
 * and addition of exercises. Tests and exercises may be deleted to a certain extent (there has to 
 * be at least one test/exercise per area), but badges may not be deleted. This is to not nullify 
 * a student's achievements and cause bugs within the program.
 * 
 * @author Darren Garcia
 * 
 */

public class TeacherModule {

	private JFrame frmTeacherModule;
	public String des;
	private ArrayList <Integer> testnum;
	Vector<Integer> comboBoxItems;
	private JTextField titleField;
	private JTextField badgeNameField;
	private JTextField dTitleField;
	private JTextField dBadgeField;
	private JTextField dAreaField;
	private JTextField qdelAreaField;
	private JTextField vbName;
	private JTextField vbType;
	private JTable areaTable;
	
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
	
	public TeacherModule(Database db) {
		initialize(db);
	}
	
	public void refresh(Database db) {
		TeacherModule ref = new TeacherModule(db);
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
		
		JLabel lblDescription = new JLabel("Description/Instructions: ");
		lblDescription.setBounds(12, 77, 158, 16);
		testPanel.add(lblDescription);
		
		JScrollPane scrollDescriptionArea = new JScrollPane();
		scrollDescriptionArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDescriptionArea.setBounds(12, 106, 250, 168);
		testPanel.add(scrollDescriptionArea);
		
		JTextArea txtrDescriptionArea = new JTextArea();
		txtrDescriptionArea.setLineWrap(true);
		txtrDescriptionArea.setToolTipText("Description and instructions for the test are inputed here");
		scrollDescriptionArea.setViewportView(txtrDescriptionArea);
		txtrDescriptionArea.setWrapStyleWord(true);
		
		JScrollPane testPane = new JScrollPane();
		testPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		testPane.setBounds(12, 316, 250, 168);
		testPanel.add(testPane);
		
		JTextArea testAnsArea = new JTextArea();
		testAnsArea.setLineWrap(true);
		testAnsArea.setToolTipText("Input the code that will be shown during the boss");
		testPane.setViewportView(testAnsArea);
		testAnsArea.setWrapStyleWord(true);
		
		JLabel lblTestCode = new JLabel("Test Code: ");
		lblTestCode.setBounds(12, 287, 67, 16);
		testPanel.add(lblTestCode);
		
		JLabel lblFinalAnswer = new JLabel("Final Answer Code: ");
		lblFinalAnswer.setBounds(274, 77, 116, 16);
		testPanel.add(lblFinalAnswer);
		
		JScrollPane ansPane = new JScrollPane();
		ansPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ansPane.setBounds(274, 106, 250, 168);
		testPanel.add(ansPane);
		
		JTextArea txtAnsCode = new JTextArea();
		txtAnsCode.setLineWrap(true);
		txtAnsCode.setToolTipText("Input the correct and full code");
		ansPane.setViewportView(txtAnsCode);
		txtAnsCode.setWrapStyleWord(true);
		
		JLabel lblIncorrect = new JLabel("Incorrect Display Message: ");
		lblIncorrect.setBounds(274, 287, 158, 16);
		testPanel.add(lblIncorrect);
		
		JScrollPane incorrectPane = new JScrollPane();
		incorrectPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		incorrectPane.setBounds(274, 316, 250, 168);
		testPanel.add(incorrectPane);
		
		JTextArea incorrectArea = new JTextArea();
		incorrectArea.setLineWrap(true);
		incorrectArea.setToolTipText("Input the message when the user gets the code incorrect");
		incorrectPane.setViewportView(incorrectArea);
		incorrectArea.setWrapStyleWord(true);
		
		JLabel lblCorrect = new JLabel("Correct Display Message: ");
		lblCorrect.setBounds(536, 77, 158, 16);
		testPanel.add(lblCorrect);
		
		JScrollPane correctPane = new JScrollPane();
		correctPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		correctPane.setBounds(536, 106, 250, 168);
		testPanel.add(correctPane);
		
		JTextArea correctArea = new JTextArea();
		correctArea.setLineWrap(true);
		correctArea.setToolTipText("Input the message when the user gets the code correct");
		correctPane.setViewportView(correctArea);
		correctArea.setWrapStyleWord(true);
		
		JLabel lblAnswer = new JLabel("Answer of Test:");
		lblAnswer.setBounds(536, 287, 116, 16);
		testPanel.add(lblAnswer);
		
		JLabel lblBadge = new JLabel("Badge Association:");
		lblBadge.setBounds(536, 369, 116, 16);
		testPanel.add(lblBadge);
		
		JLabel lblAreaNumber = new JLabel("Area Number:");
		lblAreaNumber.setToolTipText("Input the area number for the test to appear upon. If left blank, it will be associated with the first.");
		lblAreaNumber.setBounds(536, 433, 116, 16);
		testPanel.add(lblAreaNumber);
		
		JButton btnSubmit = new JButton("Add");
		btnSubmit.setBounds(697, 42, 89, 23);
		testPanel.add(btnSubmit);
		
		/**
		 * Obtaining the badges from the database for the achievement combo box in add test
		 */
		
		try {
			Connection conn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
				PreparedStatement st = conn.prepareStatement("SELECT A_Num FROM achievements");

				ResultSet rs = st.executeQuery();
				
				comboBoxItems = new Vector<Integer>();
				
				while (rs.next()) {
					if (rs.getInt("A_Num") != 0) {
						comboBoxItems.add(rs.getInt("A_Num"));
					}
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
		
		final DefaultComboBoxModel<Integer> badgemodel = new DefaultComboBoxModel<Integer>(comboBoxItems);
		
		JComboBox<Integer> badgeBox = new JComboBox<Integer>(badgemodel);
		badgeBox.setBounds(536, 398, 250, 22);
		testPanel.add(badgeBox);
		
		/**
		 * Obtaining the areas from the database for the area combo box in add test
		 */
		
		try {
			Connection conn = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
				PreparedStatement st = conn.prepareStatement("SELECT AR_Num FROM areas");

				ResultSet rs = st.executeQuery();
				
				comboBoxItems = new Vector<Integer>();
				
				while (rs.next()) {
					comboBoxItems.add(rs.getInt("AR_Num"));
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
		
		final DefaultComboBoxModel<Integer> areamodel = new DefaultComboBoxModel<Integer>(comboBoxItems);
		
		JComboBox<Integer> areaBox = new JComboBox<Integer>(areamodel);
		areaBox.setBounds(536, 462, 250, 22);
		testPanel.add(areaBox);
		
		JScrollPane ansFieldPane = new JScrollPane();
		ansFieldPane.setBounds(536, 316, 250, 40);
		testPanel.add(ansFieldPane);
		
		JTextArea ansField = new JTextArea();
		ansField.setLineWrap(true);
		ansFieldPane.setViewportView(ansField);
		ansField.setWrapStyleWord(true);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						//Add into database
						try {
							int badgeselect = (int) badgeBox.getSelectedItem();
							int areaselect = (int) areaBox.getSelectedItem();
							
							Connection conn = null;
							Statement stmt = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
								
								PreparedStatement st = conn.prepareStatement("INSERT INTO tests (T_Ttl, T_Msg, T_Cod, T_Ans, T_Fan, T_Pts, T_Cor, T_Inc, A_Num, AR_Num) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
								st.setObject(1, titleField.getText());
								st.setObject(2, txtrDescriptionArea.getText());
								st.setObject(3, testAnsArea.getText());
								st.setObject(4, ansField.getText());
								st.setObject(5, txtAnsCode.getText());
								st.setObject(6, "20");
								st.setObject(7, correctArea.getText());
								st.setObject(8, incorrectArea.getText());
								st.setObject(9, badgeselect);
								st.setObject(10, areaselect);
								
								st.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "You have successfully inserted the test into the database.");
								
								refresh(db);
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
		tabbedPane.addTab("View/Delete Tests", null, deletePanel, null);
		
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
		dDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dDescriptionPane.setBounds(12, 106, 250, 168);
		deletePanel.add(dDescriptionPane);
		
		JTextArea dDescriptionArea = new JTextArea();
		dDescriptionArea.setLineWrap(true);
		dDescriptionArea.setEditable(false);
		dDescriptionPane.setViewportView(dDescriptionArea);
		dDescriptionArea.setWrapStyleWord(true);
		
		JLabel dTestCode = new JLabel("Test Code: ");
		dTestCode.setBounds(12, 287, 67, 16);
		deletePanel.add(dTestCode);
		
		JScrollPane dTestPane = new JScrollPane();
		dTestPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dTestPane.setBounds(12, 316, 250, 168);
		deletePanel.add(dTestPane);
		
		JTextArea dTestArea = new JTextArea();
		dTestArea.setLineWrap(true);
		dTestArea.setEditable(false);
		dTestPane.setViewportView(dTestArea);
		dTestArea.setWrapStyleWord(true);
		
		JLabel dAns = new JLabel("Final Answer Code: ");
		dAns.setBounds(274, 77, 116, 16);
		deletePanel.add(dAns);
		
		JScrollPane dAnsPane = new JScrollPane();
		dAnsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dAnsPane.setBounds(274, 106, 250, 168);
		deletePanel.add(dAnsPane);
		
		JTextArea dAnsArea = new JTextArea();
		dAnsArea.setLineWrap(true);
		dAnsArea.setEditable(false);
		dAnsPane.setViewportView(dAnsArea);
		dAnsArea.setWrapStyleWord(true);
		
		JLabel dIncorrect = new JLabel("Incorrect Display Message: ");
		dIncorrect.setBounds(274, 287, 158, 16);
		deletePanel.add(dIncorrect);
		
		JScrollPane dIncorrectPane = new JScrollPane();
		dIncorrectPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dIncorrectPane.setBounds(274, 316, 250, 168);
		deletePanel.add(dIncorrectPane);
		
		JTextArea dIncorrectArea = new JTextArea();
		dIncorrectArea.setLineWrap(true);
		dIncorrectArea.setEditable(false);
		dIncorrectPane.setViewportView(dIncorrectArea);
		dIncorrectArea.setWrapStyleWord(true);
		
		JLabel dCorrect = new JLabel("Correct Display Message: ");
		dCorrect.setBounds(536, 77, 158, 16);
		deletePanel.add(dCorrect);
		
		JScrollPane dCorrectPane = new JScrollPane();
		dCorrectPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dCorrectPane.setBounds(536, 106, 250, 168);
		deletePanel.add(dCorrectPane);
		
		JTextArea dCorrectArea = new JTextArea();
		dCorrectArea.setLineWrap(true);
		dCorrectArea.setEditable(false);
		dCorrectPane.setViewportView(dCorrectArea);
		dCorrectArea.setWrapStyleWord(true);
		
		JLabel dAnswerofTest = new JLabel("Answer of Test:");
		dAnswerofTest.setBounds(536, 287, 116, 16);
		deletePanel.add(dAnswerofTest);
		
		JLabel dBadge = new JLabel("Badge Association:");
		dBadge.setBounds(536, 369, 116, 16);
		deletePanel.add(dBadge);
		
		JScrollPane dAnsFieldPane = new JScrollPane();
		dAnsFieldPane.setBounds(536, 316, 250, 40);
		deletePanel.add(dAnsFieldPane);
		
		JTextArea dAnswerField = new JTextArea();
		dAnswerField.setLineWrap(true);
		dAnsFieldPane.setViewportView(dAnswerField);
		dAnswerField.setWrapStyleWord(true);
		
		dBadgeField = new JTextField();
		dBadgeField.setEditable(false);
		dBadgeField.setToolTipText("Leave blank if the test is not associated with any badge");
		dBadgeField.setColumns(10);
		dBadgeField.setBounds(536, 398, 250, 22);
		deletePanel.add(dBadgeField);
		
		JLabel dArea = new JLabel("Area Number:");
		dArea.setToolTipText("Input the area number for the test to appear upon. If left blank, it will be associated with the first.");
		dArea.setBounds(536, 433, 116, 16);
		deletePanel.add(dArea);
		
		dAreaField = new JTextField();
		dAreaField.setEditable(false);
		dAreaField.setToolTipText("Leave blank if the test is not associated with any badge");
		dAreaField.setColumns(10);
		dAreaField.setBounds(536, 462, 250, 22);
		deletePanel.add(dAreaField);
		
		JLabel dTestNumber = new JLabel("Test Number:");
		dTestNumber.setBounds(12, 13, 103, 16);
		deletePanel.add(dTestNumber);
		
		/**
		 * Obtaining all the test numbers from the database and the combo box
		 */
		
		try {
			Connection conn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
				
				PreparedStatement st = conn.prepareStatement("SELECT T_Num FROM tests WHERE T_Del = 0");
				ResultSet rs = st.executeQuery();
				
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

						try {
							Class.forName("com.mysql.jdbc.Driver");	        

							conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
							
							PreparedStatement st = conn.prepareStatement("SELECT * FROM tests WHERE T_Num = ?");
							st.setObject(1, selected);

							ResultSet rs = st.executeQuery();
							
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
		tabbedPane.addTab("Add Badges", null, badgePanel, null);
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
		badgeDescriptionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		badgeDescriptionPane.setBounds(272, 180, 250, 168);
		badgePanel.add(badgeDescriptionPane);
		
		JTextArea badgeDescriptionArea = new JTextArea();
		badgeDescriptionArea.setLineWrap(true);
		badgeDescriptionArea.setToolTipText("Badge description");
		badgeDescriptionPane.setViewportView(badgeDescriptionArea);
		badgeDescriptionArea.setWrapStyleWord(true);
		
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
						int selected = (int) testCombo.getSelectedItem();
						int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete test #" + selected + " from the database?");
						
						if (confirm == JOptionPane.YES_OPTION) {
							//deletion of test
							try {
								Connection conn = null;

								try {
									Class.forName("com.mysql.jdbc.Driver");	        

									conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
									
									/**
									 * Check database if there is at least one of the test to prevent errors.
									 * Checks the database for area just in case the boxes weren't populated yet.
									 */
									
									PreparedStatement st = conn.prepareStatement("SELECT AR_Num FROM tests WHERE T_Num = ?");
									st.setObject(1, selected);
									
									ResultSet rs = st.executeQuery();
									int area = 1;
									
									if (rs.next()) {
										area = rs.getInt("AR_Num");
									}
									
									st = conn.prepareStatement("SELECT * FROM tests WHERE AR_Num = ?");
									st.setObject(1, area);
									
									rs = st.executeQuery();
									
									int count = 0;
									
									while (rs.next()) {
										count++;
									}
									
									//Delete if there is at least one left after deletion
									
									if (count >= 2) {
										st = conn.prepareStatement("UPDATE tests SET T_Del = 1 WHERE T_Num  = ?");
										st.setObject(1, selected);

										st.executeUpdate();

										JOptionPane.showMessageDialog(null, "You have successfully deleted the test.");
										refresh(db);
									}
									else {
										JOptionPane.showMessageDialog(null, "Warning: Test deletion failed! You cannot have no tests in an area!");
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
					}
				});
			}
		});
		deleteButton.setBounds(697, 42, 89, 23);
		deletePanel.add(deleteButton);
		
		JLabel viewTestLabel = new JLabel("Can be used to view the tests as well.");
		viewTestLabel.setBounds(569, 13, 217, 16);
		deletePanel.add(viewTestLabel);
		
		JButton badgeSubmit = new JButton("Add");
		badgeSubmit.setBounds(425, 389, 97, 25);
		badgePanel.add(badgeSubmit);
		
		JPanel vbadgePanel = new JPanel();
		vbadgePanel.setLayout(null);
		tabbedPane.addTab("View Badges", null, vbadgePanel, null);
		
		JLabel label = new JLabel("Name of Badge:");
		label.setBounds(272, 123, 116, 16);
		vbadgePanel.add(label);
		
		vbName = new JTextField();
		vbName.setEditable(false);
		vbName.setToolTipText("Name of the badge.");
		vbName.setColumns(10);
		vbName.setBounds(272, 152, 250, 22);
		vbadgePanel.add(vbName);
		
		JLabel label_1 = new JLabel("Description: ");
		label_1.setBounds(272, 187, 72, 16);
		vbadgePanel.add(label_1);
		
		JScrollPane vbPane = new JScrollPane();
		vbPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		vbPane.setBounds(272, 216, 250, 168);
		vbadgePanel.add(vbPane);
		
		JTextArea vbDescription = new JTextArea();
		vbDescription.setEditable(false);
		vbDescription.setLineWrap(true);
		vbPane.setViewportView(vbDescription);
		vbDescription.setWrapStyleWord(true);
		
		JLabel label_2 = new JLabel("Badge Type:");
		label_2.setBounds(272, 397, 72, 16);
		vbadgePanel.add(label_2);
		
		/**
		 * Obtaining the badges from the database for the area combo box in view badges
		 */
		
		try {
			Connection conn = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
				PreparedStatement st = conn.prepareStatement("SELECT A_Num FROM achievements");

				ResultSet rs = st.executeQuery();
				
				comboBoxItems = new Vector<Integer>();
				
				while (rs.next()) {
					comboBoxItems.add(rs.getInt("A_Num"));
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
		
		final DefaultComboBoxModel<Integer> vbmodel = new DefaultComboBoxModel<Integer>(comboBoxItems);
		
		JComboBox<Integer> vbNumberBox = new JComboBox<Integer>(vbmodel);
		vbNumberBox.setToolTipText("Badge number, according to the database.");
		vbNumberBox.setBounds(272, 88, 251, 22);
		vbadgePanel.add(vbNumberBox);
		
		vbNumberBox.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent eventSource) {
				   int selected = (int) vbNumberBox.getSelectedItem();

				   try {
						Connection conn = null;

						try {
							Class.forName("com.mysql.jdbc.Driver");	        

							conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
							
							PreparedStatement st = conn.prepareStatement("SELECT * FROM achievements WHERE A_Num = ? AND A_Num != 0");
							st.setObject(1, selected);

							ResultSet rs = st.executeQuery();
							
							String area = "";
							
							if (rs.next()) {
								vbName.setText(rs.getString("A_Ttl"));
								vbDescription.setText(rs.getString("A_Msg"));
								
								//area translation
								switch (rs.getInt("A_Bdg")) {
									case 1:
										area = "Bronze (1)";
										break;
									case 2:
										area = "Silver (2)";
										break;
									case 3:
										area = "Gold (3)";
										break;
									case 4:
										area = "Platinum (4)";
										break;
								}
								
								vbType.setText(area);
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
		
		JLabel vbNumber = new JLabel("Badge Number:");
		vbNumber.setBounds(273, 60, 116, 16);
		vbadgePanel.add(vbNumber);
		
		vbType = new JTextField();
		vbType.setEditable(false);
		vbType.setBounds(272, 426, 250, 22);
		vbadgePanel.add(vbType);
		vbType.setColumns(10);
		
		JPanel mquestPanel = new JPanel();
		mquestPanel.setLayout(null);
		tabbedPane.addTab("Add Exercise", null, mquestPanel, null);
		
		JLabel qDescription = new JLabel("Main Quest Description/Instructions:");
		qDescription.setBounds(272, 151, 207, 16);
		mquestPanel.add(qDescription);
		
		JScrollPane qPane = new JScrollPane();
		qPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qPane.setBounds(272, 180, 250, 168);
		mquestPanel.add(qPane);
		
		JTextArea qIns = new JTextArea();
		qIns.setLineWrap(true);
		qPane.setViewportView(qIns);
		qIns.setWrapStyleWord(true);
		
		/**
		 * Obtaining the areas from the database for the area combo box in add delete main quest
		 */
		
		try {
			Connection conn = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
				PreparedStatement st = conn.prepareStatement("SELECT AR_Num FROM areas");

				ResultSet rs = st.executeQuery();
				
				comboBoxItems = new Vector<Integer>();
				
				while (rs.next()) {
					comboBoxItems.add(rs.getInt("AR_Num"));
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
		
		final DefaultComboBoxModel<Integer> qareamodel = new DefaultComboBoxModel<Integer>(comboBoxItems);
		
		JComboBox<Integer> qAreaBox = new JComboBox<Integer>(qareamodel);
		qAreaBox.setToolTipText("The area associated with the Main Quest.");
		qAreaBox.setSelectedIndex(0);
		qAreaBox.setBounds(272, 390, 76, 22);
		mquestPanel.add(qAreaBox);
		
		JLabel qArea = new JLabel("Exercise Area:");
		qArea.setBounds(272, 361, 97, 16);
		mquestPanel.add(qArea);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						//Add into database
						try {
							Connection conn = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
								
								PreparedStatement st = conn.prepareStatement("INSERT INTO exercises (E_Ins, AR_Num) VALUES (?, ?)");
								st.setObject(1, qIns.getText());
								st.setObject(2, (int)qAreaBox.getSelectedItem());
								
								st.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "You have successfully inserted the badge into the database.");
								
								refresh(db);
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
		btnAdd.setBounds(425, 389, 97, 25);
		mquestPanel.add(btnAdd);
		
		JPanel mdelPanel = new JPanel();
		mdelPanel.setLayout(null);
		tabbedPane.addTab("View/Delete Exercise", null, mdelPanel, null);
		
		JLabel qdelDesc = new JLabel("Main Quest Description/Instructions:");
		qdelDesc.setBounds(272, 151, 207, 16);
		mdelPanel.add(qdelDesc);
		
		JScrollPane qdelQuestPane = new JScrollPane();
		qdelQuestPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qdelQuestPane.setBounds(272, 180, 250, 168);
		mdelPanel.add(qdelQuestPane);
		
		JTextArea qdelQuestArea = new JTextArea();
		qdelQuestArea.setLineWrap(true);
		qdelQuestArea.setEditable(false);
		qdelQuestPane.setViewportView(qdelQuestArea);
		qdelQuestArea.setWrapStyleWord(true);
		
		JLabel qdelArea = new JLabel("Exercise Area:");
		qdelArea.setBounds(272, 361, 97, 16);
		mdelPanel.add(qdelArea);
		
		qdelAreaField = new JTextField();
		qdelAreaField.setToolTipText("The area the quest takes place in.");
		qdelAreaField.setEditable(false);
		qdelAreaField.setBounds(272, 390, 97, 22);
		mdelPanel.add(qdelAreaField);
		qdelAreaField.setColumns(10);
		
		JButton qdelButton = new JButton("Delete");
		qdelButton.setBounds(425, 389, 97, 25);
		mdelPanel.add(qdelButton);
		
		JLabel qdelMainQuestNum = new JLabel("Main Quest Number");
		qdelMainQuestNum.setBounds(272, 87, 116, 16);
		mdelPanel.add(qdelMainQuestNum);
		
		try {
			Connection conn = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
				PreparedStatement st = conn.prepareStatement("SELECT E_Num FROM exercises");

				ResultSet rs = st.executeQuery();
				
				comboBoxItems = new Vector<Integer>();
				
				while (rs.next()) {
					comboBoxItems.add(rs.getInt("E_Num"));
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
		
		final DefaultComboBoxModel<Integer> qdelareamodel = new DefaultComboBoxModel<Integer>(comboBoxItems);
		
		JComboBox<Integer> qdelNumBox = new JComboBox<Integer>(qdelareamodel);
		qdelNumBox.setToolTipText("The main quest's corresponding number in the database.");
		qdelNumBox.setBounds(272, 116, 250, 22);
		mdelPanel.add(qdelNumBox);
		
		JLabel mdelViewQuest = new JLabel("Can be used to view Main Quests as well.");
		mdelViewQuest.setBounds(282, 425, 250, 16);
		mdelPanel.add(mdelViewQuest);
		
		JPanel areaPanel = new JPanel();
		tabbedPane.addTab("View Areas", null, areaPanel, null);
		areaPanel.setLayout(null);
		
		JScrollPane atablePane = new JScrollPane();
		atablePane.setBounds(183, 104, 432, 310);
		areaPanel.add(atablePane);
		
		/**
		 * Get values from the database about the areas
		 */
		
		areaTable = new JTable();
		
		areaTable.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "Varisland"},
				{2, "Syntown"},
				{3, "Inoutown"},
				{4, "Elsif Village"},
				{5, "Looping Meadows"},
				{6, "City of No Return"},
				{7, "The Tower of Knowledge"},
			},
			new String[] {
				"Area Number", "Area Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		areaTable.getColumnModel().getColumn(0).setResizable(false);
		areaTable.getColumnModel().getColumn(0).setPreferredWidth(90);
		areaTable.getColumnModel().getColumn(1).setResizable(false);
		areaTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		areaTable.setToolTipText("The areas present in the game.");
		atablePane.setViewportView(areaTable);
		
		/**
		 * Handles the action listener for the combo box in the quest delete panel.
		 */
		
		qdelNumBox.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent eventSource) {
				   int selected = (int) qdelNumBox.getSelectedItem();

				   try {
						Connection conn = null;

						try {
							Class.forName("com.mysql.jdbc.Driver");	        

							conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
							
							PreparedStatement st = conn.prepareStatement("SELECT * FROM exercises WHERE E_Num = ?");
							st.setObject(1, selected);

							ResultSet rs = st.executeQuery();
							
							if (rs.next()) {
								qdelQuestArea.setText(rs.getString("E_Ins"));
								qdelAreaField.setText(rs.getString("AR_Num"));
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
		
		qdelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						int selected = (int) qdelNumBox.getSelectedItem();
						int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete main quest #" + selected + " from the database?");
						
						if (confirm == JOptionPane.YES_OPTION) {
							//deletion of main quest
							try {
								Connection conn = null;

								try {
									Class.forName("com.mysql.jdbc.Driver");	        

									conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
									
									/**
									 * Check database if there is at least one of the exercise to prevent errors.
									 * Checks the database for area just in case the boxes weren't populated yet.
									 */
									
									PreparedStatement st = conn.prepareStatement("SELECT AR_Num FROM exercises WHERE E_Num = ?");
									st.setObject(1, selected);
									
									ResultSet rs = st.executeQuery();
									int area = 1;
									
									if (rs.next()) {
										area = rs.getInt("AR_Num");
									}
									
									st = conn.prepareStatement("SELECT * FROM exercises WHERE AR_Num = ?");
									st.setObject(1, area);
									
									rs = st.executeQuery();
									
									int count = 0;
									
									while (rs.next()) {
										count++;
									}
									
									//Delete if there is at least one left after deletion
									
									if (count >= 2) {
										st = conn.prepareStatement("UPDATE exercises SET E_Del = 1 WHERE E_Num  = ?");
										st.setObject(1, selected);

										st.executeUpdate();

										JOptionPane.showMessageDialog(null, "You have successfully deleted the main quest.");
										refresh(db);
									}
									else {
										JOptionPane.showMessageDialog(null, "Warning: Quest deletion failed! You cannot have no quests in an area!");
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
					}
				});
			}
		});
		
		badgeSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						//Add into database
						try {
							Connection conn = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
								
								PreparedStatement st = conn.prepareStatement("INSERT INTO achievements (A_Ttl, A_Msg, A_Bdg) VALUES (?, ?, ?)");
								st.setObject(1, badgeNameField.getText());
								st.setObject(2, badgeDescriptionArea.getText());
								st.setObject(3, (String)badgeTypeCombo.getSelectedItem());
								
								st.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "You have successfully inserted the badge into the database.");
								
								refresh(db);
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
