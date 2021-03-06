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
import model.Exercises;
import model.User;
import view.MainMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

/**
 * The first screen of the program. The program asks the user their database credentials, to be able
 * to connect to the database, whether it is a local one or a main database. After this, they are
 * redirected to the login page itself. The user may click on register to register an account to log 
 * in with, or log in with an existing account in the database.
 * 
 * @author Ramon Arca
 * @author Darren Garcia
 */

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField fNum;
	private JLabel lLogin;
	private JPasswordField fPassword;
	private JButton btnNewButton;
	private Database db = new Database();
	private boolean nocheck = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
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
	public Login() throws FontFormatException, IOException {
		initialize();
	}

	public void initialize() throws FontFormatException, IOException {

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
		
		

		setResizable(false);
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lNum = new JLabel("ID Number");

		fNum = new JTextField();
		fNum.setToolTipText("Input your ID here!");
		fNum.setColumns(10);

		JLabel lPassword = new JLabel("Password");

		lLogin = new JLabel("");
		lLogin.setIcon(new ImageIcon(Login.class.getResource("/img/title-transparent-small.png")));
		lLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));


		/**
		 * IP, user and password initialization. If the user does not have any IP stored in the database, or if there
		 * was an error connecting to the database, then this is used.
		 */

		boolean validIP = false;

		while (validIP == false) {
			JTextField ipField = new JTextField();
			JTextField dUserField = new JTextField(15);
			JTextField dUserPassword = new JPasswordField(15);

			JPanel myPanel = new JPanel(new GridLayout(0, 1, 2, 2));
			
			myPanel.add(new JLabel("Enter DB Information. (Clicking CANCEL will use localhost):"));
			myPanel.add(new JLabel("IP Address and Port:"));
			myPanel.add(ipField);
			myPanel.add(new JLabel("DB Username:"));
			myPanel.add(dUserField);
			myPanel.add(new JLabel("DB Password:"));
			myPanel.add(dUserPassword);
			
			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Database Information", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == -1) {
				System.exit(0);
			}
			else if ((ipField.getText() == null || ipField.getText().equals("") || dUserField.getText() == null || dUserField.getText().equals("")) && result != JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Please input something in the IP Address Field and/or the Database Username Field.");
				nocheck = true;
			}
			else if (result == JOptionPane.OK_OPTION) {
				db.setIP("jdbc:mysql://" + ipField.getText() + "/quest");
				db.setDbu(dUserField.getText());
				db.setDbp(dUserPassword.getText());
				
				System.out.println("IP Address and Port: " + ipField.getText());
				System.out.println("DB Username: " + dUserField.getText());
				System.out.println("DB Password: " + dUserPassword.getText());
			}
			else if (result == JOptionPane.CANCEL_OPTION) {
				db.setIP("jdbc:mysql://localhost:3307/quest");
				db.setDbu("root");
				db.setDbp("");
				
				System.out.println("IP Address and Port: " + db.getIP());
				System.out.println("DB Username: " + db.getDbu());
				System.out.println("DB Password: " + db.getDbp());
			}
			
			if (nocheck == false) {
				// Try to connect to the database to see if it works
				try {
					Connection conn = null;
					Statement stmt = null;

					try {
						Class.forName("com.mysql.jdbc.Driver");	        

						conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
						stmt = (Statement) conn.createStatement();

						String query = "SELECT 1";

						stmt.executeQuery(query);

						break;
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
			nocheck = false;
		}
		
		JButton btnConfirm = new JButton("Login");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {

							String username = fNum.getText();
							String password = String.valueOf(fPassword.getPassword());
							System.out.println(password);

							Connection conn = null;
							Statement stmt = null;

							try {
								Class.forName("com.mysql.jdbc.Driver");	        

								conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
								stmt = (Statement) conn.createStatement();

								String query = "SELECT * FROM users " +
										"WHERE U_Usn ='" + username + "' AND U_Pas ='" + password + "'";

								ResultSet rs = stmt.executeQuery(query);

								if (rs.next()) {
									int rows = rs.getInt(1); 
									int num = rs.getInt("U_Num");
									String u = rs.getString("U_Usn");
									String p = rs.getString("U_Pas");
									int ac = rs.getInt("U_Ach");
									int pt = rs.getInt("U_Pts");
									int tp = rs.getInt("U_Typ");
									int ar = rs.getInt("AR_Num");

									User set = new User();
									set.setUserNumber(num);
									set.setUsername(u);
									set.setPassword(p);
									set.setAchievements(ac);
									set.setPoints(pt);
									set.setType(tp);
									set.setArea(ar);

									//obtaining exercises, randomized if it's the first time

									query = "SELECT * FROM userareas " +
											"WHERE U_Num = " + num + " ";

									ResultSet ex = stmt.executeQuery(query);

									int en = 1;
									int cleared = 0;
									String areapass = "AAAAAAAAAA";

									if (ex.next()) {
										rows = ex.getInt(1);
										en = ex.getInt("E_Num");
										cleared = ex.getInt("UA_Clr");
										areapass = ex.getString("UA_Pas");
									}

									query = "SELECT * FROM exercises " +
											"WHERE AR_Num = " + en + " AND E_Del != 1 " +
											"ORDER BY RAND() " +
											"LIMIT 1";

									ResultSet res = stmt.executeQuery(query);

									String ins = "";

									if (res.next()) {
										rows = res.getInt(1);
										ins = res.getString("E_Ins");
									}

									Exercises question = new Exercises();
									question.setExercise(en);
									question.setMessage(ins);
									question.setArea(ar);
									question.setCleared(cleared);
									question.setAreaPassword(areapass);

									//moving windows
									if (tp == 0) {
										MainMenu frame = new MainMenu(set, question, db);
										//MainMenu frame = new MainMenu(set);
										frame.setVisible(true);
										dispose();
									}
									else {
										TeacherModule frame = new TeacherModule(db);
										dispose();
									}
								} 
								else {
									JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password!");
								}
							} 
							catch(Exception a) {
								System.out.println(a.getMessage());	    	
								JOptionPane.showMessageDialog(null, "A login error has occured.");
							}		

						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		fPassword = new JPasswordField();

		btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Registration register = new Registration(db);
					register.setVisible(true);
					dispose();
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(51, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lLogin)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lNum)
												.addComponent(lPassword))
										.addGap(28)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(fPassword, Alignment.LEADING)
												.addComponent(fNum, Alignment.LEADING, 160, 160, Short.MAX_VALUE)
												.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnConfirm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
						.addGap(54))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(22)
						.addComponent(lLogin)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(fNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lNum))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lPassword)
								.addComponent(fPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(btnConfirm)
						.addGap(17)
						.addComponent(btnNewButton)
						.addContainerGap(27, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
	}
}
