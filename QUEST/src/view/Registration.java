package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Database;
import model.User;
import view.MainMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

/**
 * The Register Screen of the program. The user inputs their ID, which serves as their username 
 * for the program, and the accompanying password. These two make up their credentials to be 
 * able to log in and use the program. Clicking on register will save the user and lead them 
 * back to the login page, where they may use the new account.
 * 
 * @author Ramon Arca
 * @author Darren Garcia
 */
public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField fNum;
	private JLabel lRegistration;
	private JButton btnNewButton;
	private Database db;
	private JPasswordField fPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Registration frame = new Registration();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public Registration(Database db) throws FontFormatException, IOException {
		initialize(db);
		
	}
	
	public void initialize(Database db) throws FontFormatException, IOException {
		
		
		try {
			UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.db = db;
		setResizable(false);
		setTitle("Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lNum = new JLabel("ID Number");

		fNum = new JTextField();
		fNum.setToolTipText("Input your ID here!");
		fNum.setColumns(10);

		JLabel lPassword = new JLabel("Password");

		lRegistration = new JLabel("<html>\r\n\t<div align=\"center\">\r\n\t\t<p style=\"font-size:42px\">\r\n\t\t\tQUEST\r\n\t\t</p>\r\n\t\t\tRegistration\r\n\t</div>\r\n</html>");
		lRegistration.setFont(new Font("3Dventure", Font.PLAIN, 30));

		JButton btnConfirm = new JButton("Register");

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							
							String username = fNum.getText();
							String password = fPassword.getText();
							System.out.print(password);

							Connection conn = null;
							Statement stmt = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
								stmt = (Statement) conn.createStatement();

								String query = "INSERT INTO users (U_Usn, U_Pas)" +
											   "VALUES ('" + username + "', '" + password + "')";

								stmt.executeUpdate(query);
								
									//moving windows
									Login frame = new Login();
									frame.setVisible(true);
									dispose();
							} 
							catch(Exception a) {
								System.out.println(a.getMessage());	    	
								JOptionPane.showMessageDialog(null, "A registration error has occured.");
							}		

						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login login = new Login();
					login.setVisible(true);
					dispose();
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		fPassword = new JPasswordField();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(61)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addComponent(btnConfirm)
					.addGap(60))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(78, Short.MAX_VALUE)
					.addComponent(lRegistration)
					.addGap(57))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lPassword)
						.addComponent(lNum))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(fPassword, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
						.addComponent(fNum, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addGap(38))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lRegistration, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lNum)
						.addComponent(fNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lPassword)
						.addComponent(fPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
