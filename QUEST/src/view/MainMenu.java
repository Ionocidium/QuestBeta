package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import api.component.PCompiler;
import controller.ErrorPolling;
import model.Exercises;
import model.User;

import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private String fp = "";
	private String quests = "";
	private String[] questMessage = {"", "", "", "", ""};
	private String[] questTitle = {"", "", "", "", ""};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainMenu frame = new MainMenu(User user);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainMenu(User user, Exercises question) {
		initialize(user, question);
	}
	
	public void initialize(User user, Exercises question) {
		setTitle("Main Menu");
		
		/**
		 * Area initialization, the user's area is saved within the database and loaded when they are logged in
		 * to get the area, use user.getArea(); In the following switch, the files are created within the directory
		 * for the exercises.
		 */
		
		switch (user.getArea()) {
			case 1: 
				// Varisland
				fp = "./exercises/" + user.getUsername() + "-e1.c";
				if (new File("./exercises/" + user.getUsername() + "-e1.c").exists()) {
					//fetch quests from the db if there are any
					try {
						Connection conn = null;
						Statement stmt = null;

						try {
							Class.forName("com.mysql.jdbc.Driver");	        

							conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
							stmt = (Statement) conn.createStatement();

							String check = "SELECT * FROM userquests " +
										   "WHERE U_Num = " + user.getUserNumber() + " AND UQ_Pth = '" + fp + "' AND UQ_Clr = 0";
						
							ResultSet res = stmt.executeQuery(check);
							
							int counter = 0;
							
							if (res.next()) {
								int rows = res.getInt(1);
								questTitle[counter] = res.getString("UQ_Qnm");
								questMessage[counter] = res.getString("UQ_Pnm");
								counter++;
							}
							
							for (int i = 0; i < counter; i++) {
								quests = questTitle[i] + ":\n" + questMessage[i] + "\n\n";
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
					try {
						new File("./exercises").mkdir();
						List<String> lines = Arrays.asList("int main () {", "\t", "}");
						Path file = Paths.get("./exercises/" + user.getUsername() + "-e1.c");
						Files.write(file, lines, Charset.forName("UTF-8"));
					}
					catch (IOException e){
						e.printStackTrace();
					}
				}
				break;
			case 2:
				// Syntown
				fp = "./exercises/" + user.getUsername() + "-e2.c";
				if (new File("./exercises/" + user.getUsername() + "-e2.c").exists()) {
					//do nothing
				}
				else {
					try {
						List<String> lines = Arrays.asList("int main () {", "\tint number = 9", "}");
						Path file = Paths.get("./exercises/" + user.getUsername() + "-e2.c");
						Files.write(file, lines, Charset.forName("UTF-8"));
					}
					catch (IOException e){
						e.printStackTrace();
					}
				}
				break;
			case 3:
				// Inoutown
				fp = "./exercises/" + user.getUsername() + "-e3.c";
				if (new File("./exercises/" + user.getUsername() + "-e3.c").exists()) {
					//do nothing
				}
				else {
					try {
						List<String> lines = Arrays.asList("int main () {", "\t", "}");
						Path file = Paths.get("./exercises/" + user.getUsername() + "-e3.c");
						Files.write(file, lines, Charset.forName("UTF-8"));
					}
					catch (IOException e){
						e.printStackTrace();
					}
				}
				break;
			case 4:
				// Elsif Village
				fp = "./exercises/" + user.getUsername() + "-e4.c";
				if (new File("./exercises/" + user.getUsername() + "-e4.c").exists()) {
					//do nothing
				}
				else {
					try {
						List<String> lines = Arrays.asList("int main () {", "\t", "}");
						Path file = Paths.get("./exercises/" + user.getUsername() + "-e4.c");
						Files.write(file, lines, Charset.forName("UTF-8"));
					}
					catch (IOException e){
						e.printStackTrace();
					}
				}
				break;
			case 5:
				// Looping Meadows
				fp = "./exercises/" + user.getUsername() + "-e5.c";
				if (new File("./exercises/" + user.getUsername() + "-e5.c").exists()) {
					//do nothing
				}
				else {
					try {
						List<String> lines = Arrays.asList("int main () {", "\t", "}");
						Path file = Paths.get("./exercises/" + user.getUsername() + "-e5.c");
						Files.write(file, lines, Charset.forName("UTF-8"));
					}
					catch (IOException e){
						e.printStackTrace();
					}
				}
				break;
			case 6:
				// City of No Return
				fp = "./exercises/" + user.getUsername() + "-e6.c";
				if (new File("./exercises/" + user.getUsername() + "-e6.c").exists()) {
					//do nothing
				}
				else {
					try {
						List<String> lines = Arrays.asList("int main () {", "\t", "}");
						Path file = Paths.get("./exercises/" + user.getUsername() + "-e6.c");
						Files.write(file, lines, Charset.forName("UTF-8"));
					}
					catch (IOException e){
						e.printStackTrace();
					}
				}
				break;
			case 7:
				// tower preparations
				break;
				
		}
		
		//JOptionPane.showMessageDialog(null, u + " " + p + " " + ac + " " + pt);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JButton bRandomTest = new JButton("");
		bRandomTest.setBounds(23, 536, 246, 90);
		bRandomTest.setIcon(new ImageIcon(MainMenu.class.getResource("/boss-transparent-small.png")));
		bRandomTest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bRandomTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (question.getCleared() == 0) {
						ImageIcon icon = new ImageIcon(MainMenu.class.getResource("/key.png"));
						JOptionPane.showMessageDialog(null, "The Boss Room is still locked! Complete the main quest to proceed!", "Boss Room", JOptionPane.PLAIN_MESSAGE, icon);
					}
					else {
						//moving windows
						TestMenu tframe = new TestMenu(user, question);
						//tframe.initialize(user);
						dispose();
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton bLeaderboards = new JButton("");
		bLeaderboards.setBounds(23, 330, 246, 90);
		bLeaderboards.setIcon(new ImageIcon(MainMenu.class.getResource("/rank-transparent-small.png")));
		bLeaderboards.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bLeaderboards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//moving windows
					LeaderboardMenu Lframe = new LeaderboardMenu(user, question);
					//tframe.initialize(user);
					dispose();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton bBadge = new JButton("");
		bBadge.setBounds(23, 433, 246, 90);
		bBadge.setIcon(new ImageIcon(MainMenu.class.getResource("/badges-transparent-small.png")));
		bBadge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					BadgesMenuAgainAgain badgeFrame = new BadgesMenuAgainAgain(user, question);
					dispose();
					
				}
				catch(Exception x){
					x.printStackTrace();
				}
			}
		});
		bBadge.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton bCompile = new JButton("");
		bCompile.setBounds(23, 227, 246, 90);
		bCompile.setIcon(new ImageIcon(MainMenu.class.getResource("/compile-transparent-small.png")));
		bCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PCompiler cmp = new PCompiler();
					Path filePath = Paths.get(fp);
					String log = cmp.compileRun(filePath);
					ErrorPolling ep = new ErrorPolling(log, user, fp);
					//System.out.println(log);
				}
				catch(Exception x){
					x.printStackTrace();
				}
			}
		});
		
		String userName = user.getUsername();
		JLabel lblName = new JLabel("Name: " + user.getUsername());
		lblName.setBounds(15, 16, 218, 32);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		String userLvl = Integer.toString(user.getPoints() / 100);
		
		String userPts = Integer.toString(user.getPoints());
		
		String area = "";
		
		switch (user.getArea()) {
			case 1: area = "Varisland"; break;
			case 2: area = "Syntown"; break;
			case 3: area = "Inoutown"; break;
			case 4: area = "Elsif Village"; break;
			case 5: area = "Looping Meadows"; break;
			case 6: area = "City of No Return"; break;
			case 7: area = "The Tower of Knowledge"; break;
		}
		
		JLabel lblArea = new JLabel("Area: " + area);
		lblArea.setBounds(15, 59, 254, 32);
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton bMap = new JButton("");
		bMap.setBounds(25, 124, 244, 90);
		bMap.setIcon(new ImageIcon(MainMenu.class.getResource("/mapsmall.png")));
		bMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblName);
		contentPane.add(lblArea);
		contentPane.add(bRandomTest);
		contentPane.add(bCompile);
		contentPane.add(bLeaderboards);
		contentPane.add(bBadge);
		contentPane.add(bMap);
		
		JLabel lblBadgesHere = new JLabel("badges here");
		lblBadgesHere.setBounds(430, 31, 72, 14);
		contentPane.add(lblBadgesHere);
		
		JTextArea exerciseTextArea = new JTextArea();
		exerciseTextArea.setLineWrap(true);
		exerciseTextArea.setWrapStyleWord(true);
		exerciseTextArea.setEditable(false);
		exerciseTextArea.setToolTipText("The current mission to unlock the Boss Room!");
		exerciseTextArea.setBounds(311, 113, 277, 121);
		contentPane.add(exerciseTextArea);
		
		//Exercise text area to display the current mission, if not cleared
		if (question.getCleared() == 0) {
			exerciseTextArea.setText("Main Quest:\n" + question.getMessage());
		}
		else {
			exerciseTextArea.setText("The Boss Room has been unlocked! Go for the challenge!");
		}
		
		JTextArea questTextArea = new JTextArea();
		questTextArea.setLineWrap(true);
		questTextArea.setToolTipText("Contains the current quests from a previous report.");
		questTextArea.setWrapStyleWord(true);
		questTextArea.setEditable(false);
		questTextArea.setBounds(311, 254, 277, 372);
		contentPane.add(questTextArea);
		
		//Quest text area to display the current quests, if not cleared
		if (quests.equals("")) {
			questTextArea.setText("Sub Quests:\n\nThere are no sub-quests to do!");
		}
		else {
			questTextArea.setText("Sub Quests:\n\n" + quests);
		}
	}
}
