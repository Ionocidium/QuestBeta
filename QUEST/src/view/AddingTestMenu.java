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

/**
 * Handles the adding of tests by a teacher.
 * 
 * @author Darren Garcia
 */

public class AddingTestMenu {

	private JFrame frame;
	public String des;
	private JTextField titleField;
	private JTextField ansField;
	
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
				
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 651);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("Adding a new test...");
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		final Test ptest = test;
		final int pt = pts;
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 658, 542);
		panel.add(tabbedPane);
		
		JPanel titlePanel = new JPanel();
		tabbedPane.addTab("Title and Description", null, titlePanel, null);
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title of Test: ");
		lblTitle.setBounds(12, 13, 116, 16);
		titlePanel.add(lblTitle);
		
		titleField = new JTextField();
		titleField.setBounds(12, 42, 629, 22);
		titlePanel.add(titleField);
		titleField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(12, 115, 72, 16);
		titlePanel.add(lblDescription);
		
		JScrollPane scrollDescriptionArea = new JScrollPane();
		scrollDescriptionArea.setBounds(12, 144, 629, 168);
		titlePanel.add(scrollDescriptionArea);
		
		JTextArea txtrDescriptionArea = new JTextArea();
		scrollDescriptionArea.setViewportView(txtrDescriptionArea);
		txtrDescriptionArea.setText("Description here...");
		txtrDescriptionArea.setLineWrap(true);
		txtrDescriptionArea.setWrapStyleWord(true);
		
		JPanel codePanel = new JPanel();
		tabbedPane.addTab("Code", null, codePanel, null);
		codePanel.setLayout(null);
		
		JLabel lblTest = new JLabel("Test Code: ");
		lblTest.setBounds(12, 9, 67, 16);
		codePanel.add(lblTest);
		
		JScrollPane testAnsPane = new JScrollPane();
		testAnsPane.setBounds(12, 38, 629, 200);
		codePanel.add(testAnsPane);
		
		JTextArea testAnsArea = new JTextArea();
		testAnsPane.setViewportView(testAnsArea);
		
		JLabel lblAns = new JLabel("Final Answer Code: ");
		lblAns.setBounds(12, 251, 116, 16);
		codePanel.add(lblAns);
		
		JScrollPane ansPane = new JScrollPane();
		ansPane.setBounds(12, 280, 629, 219);
		codePanel.add(ansPane);
		
		JTextArea txtAnsCode = new JTextArea();
		txtAnsCode.setText("");
		txtAnsCode.setEditable(true);
		ansPane.setViewportView(txtAnsCode);
		
		JPanel messagePanel = new JPanel();
		tabbedPane.addTab("Messages and Answer", null, messagePanel, null);
		messagePanel.setLayout(null);
		
		JLabel lblIncorrect = new JLabel("Incorrect Message:");
		lblIncorrect.setBounds(12, 11, 116, 16);
		messagePanel.add(lblIncorrect);
		
		JLabel lblCorrect = new JLabel("Correct:");
		lblCorrect.setBounds(12, 195, 47, 16);
		messagePanel.add(lblCorrect);
		
		JLabel label = new JLabel("Answer: ");
		label.setBounds(12, 396, 52, 16);
		messagePanel.add(label);
		
		JScrollPane incorrectPane = new JScrollPane();
		incorrectPane.setBounds(12, 36, 629, 146);
		messagePanel.add(incorrectPane);
		
		JTextArea incorrectArea = new JTextArea();
		incorrectPane.setViewportView(incorrectArea);
		
		JScrollPane correctPane = new JScrollPane();
		correctPane.setBounds(12, 224, 629, 146);
		messagePanel.add(correctPane);
		
		JTextArea correctArea = new JTextArea();
		correctPane.setViewportView(correctArea);
		
		ansField = new JTextField();
		ansField.setBounds(12, 425, 629, 22);
		messagePanel.add(ansField);
		ansField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
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

								conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/quest", "root", "");	
								stmt = (Statement) conn.createStatement();

								String query = "INSERT INTO tests " +
											   "(`T_Num`, `T_Ttl`, `T_Msg`, `T_Cod`, `T_Ans`, `T_Fan`, `T_Pts`, `T_Cor`, `T_Inc`, `A_Num`)" +
											   "VALUES (NULL, " + titleField.getText() + ", " + txtrDescriptionArea.getText() + ", " + testAnsArea.getText() + ", " + ansField.getText() + ", " + txtAnsCode.getText() + ", 20, " + correctArea.getText() + ", " + incorrectArea.getText() + "0)";

								ResultSet rs = stmt.executeQuery(query);
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
		btnSubmit.setBounds(581, 568, 89, 23);
		panel.add(btnSubmit);
	}
}
