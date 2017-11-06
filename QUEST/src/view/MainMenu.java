package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import api.component.PCompiler;
import controller.ErrorPolling;
import model.Badges;
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
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.sql.*;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private String fp = "";
	private String quests = "";
	private String[] questMessage = {"", "", "", "", ""};
	private String[] questTitle = {"", "", "", "", ""};
	private boolean newarea = false;
	private String areapass = "";
	private int emblem = 0;
	private int emblemcheck = 0;
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
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
				emblem = 19;
				fp = "./exercises/" + user.getUsername() + "-e1.c";
				if (new File("./exercises/" + user.getUsername() + "-e1.c").exists()) {
					//do nothing
				}
				else {
					newarea = true;
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
				emblem = 24;
				fp = "./exercises/" + user.getUsername() + "-e2.c";
				if (new File("./exercises/" + user.getUsername() + "-e2.c").exists()) {
					//do nothing
				}
				else {
					newarea = true;
					try {
						List<String> lines = Arrays.asList("int main () {", "\tint number = 9", "\tchar character 9;", "\tprintf\"hello world\");" , "}");
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
				emblem = 20;
				fp = "./exercises/" + user.getUsername() + "-e3.c";
				if (new File("./exercises/" + user.getUsername() + "-e3.c").exists()) {
					//do nothing
				}
				else {
					newarea = true;
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
				emblem = 21;
				fp = "./exercises/" + user.getUsername() + "-e4.c";
				if (new File("./exercises/" + user.getUsername() + "-e4.c").exists()) {
					//do nothing
				}
				else {
					newarea = true;
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
				emblem = 22;
				fp = "./exercises/" + user.getUsername() + "-e5.c";
				if (new File("./exercises/" + user.getUsername() + "-e5.c").exists()) {
					//do nothing
				}
				else {
					newarea = true;
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
				emblem = 23;
				fp = "./exercises/" + user.getUsername() + "-e6.c";
				if (new File("./exercises/" + user.getUsername() + "-e6.c").exists()) {
					//do nothing
				}
				else {
					newarea = true;
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
				emblem = 24;
				break;
				
		}
		
		/**
		 * Check the db if the person has a file for some reason, but no entry in the db, create
		 * an entry if that happens
		 */
		
		try {
			Connection conn = null;
			Statement stmt = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
				stmt = (Statement) conn.createStatement();

				String check = "SELECT * FROM userareas " +
							   "WHERE U_Num = " + user.getUserNumber() + " AND E_Num = '" + question.getExercise() + "'";
			
				ResultSet res = stmt.executeQuery(check);
				
				if (res.next()) {
					
				}
				else {
					newarea = true;
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
		
		
		/**
		 * Check the db if the person has already cleared the boss room, if they have change the text
		 * that appears on it
		 */
		
		try {
			Connection conn = null;
			Statement stmt = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
				stmt = (Statement) conn.createStatement();

				String check = "SELECT * FROM userachievements " +
							   "WHERE U_Num = " + user.getUserNumber() + " AND A_Num = '" + emblem + "'";
			
				ResultSet res = stmt.executeQuery(check);
				
				if (res.next()) {
					emblemcheck = res.getInt(1);
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
		
		/**
		 * Fetch a list of quests if there exists any within the db
		 */
		
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
		
		/**
		 * If it's a new area, we generate a boss password for it and save it into the userareas table
		 */
		
		if (newarea == true) {
			//JOptionPane.showMessageDialog(null,  "this is a new area: " + question.getExercise());
			StringBuilder sb = new StringBuilder(10);
			for( int i = 0; i < 10; i++ ) {
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
			}
			areapass = sb.toString();
			question.setAreaPassword(areapass);
			
			try {
				Connection conn = null;
				Statement stmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");	        

					conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
					stmt = (Statement) conn.createStatement();

					//JOptionPane.showMessageDialog(null, user.getArea());
					
					String test = "SELECT * FROM exercises " +
								  "WHERE AR_Num = " + user.getArea() + " ";
					
					ResultSet rs = stmt.executeQuery(test);
					
					if (rs.next()) {
						question.setExercise(rs.getInt("E_Num"));
		
						String check = "INSERT INTO userareas (UA_Pas, E_Num, U_Num)" +
						     	   	   "VALUES ('" + areapass + "', '" + question.getExercise() + "', '" + user.getUserNumber() + "')";
					
						stmt.executeUpdate(check);
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
		
		/**
		 * Changing areas and getting the information about the area from the database
		 */
		
		try {
			Connection conn = null;
			Statement stmt = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
				stmt = (Statement) conn.createStatement();

				String check = "SELECT * FROM exercises " +
				        	   "WHERE AR_Num = " + user.getArea() + " ";
				
				ResultSet d = stmt.executeQuery(check);
				
				int en = 1;
				
				if (d.next()) {
					int rows = d.getInt(1);
					en = d.getInt("E_Num");
				}
				
				String query = "SELECT * FROM userareas " +
				        	   "WHERE U_Num = " + user.getUserNumber() + " AND E_Num = " + en + " ";
				
				ResultSet ex = stmt.executeQuery(query);
				
				int cleared = 0;
				String areapass = "AAAAAAAAAA";
				
				if (ex.next()) {
					int rows = ex.getInt(1);
					cleared = ex.getInt("UA_Clr");
					areapass = ex.getString("UA_Pas");
				}
				
				//System.out.println("clear = " + cleared);
				
				query = "SELECT * FROM exercises " +
				        "WHERE E_Num = " + en + " ";
				
				ResultSet res = stmt.executeQuery(query);
				
				String ins = "";
				
				if (res.next()) {
					int rows = res.getInt(1);
					ins = res.getString("E_Ins");
				}
				
				question.setExercise(en);
				question.setMessage(ins);
				question.setArea(user.getArea());
				question.setCleared(cleared);
				question.setAreaPassword(areapass);
				
				//System.out.println("update! = " + question.getCleared());
				
			} 
			catch(Exception a) {
				System.out.println(a.getMessage());	    	
				JOptionPane.showMessageDialog(null, "A database error occured.");
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
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
					ImageIcon icon = new ImageIcon(MainMenu.class.getResource("/key.png"));
					if (question.getCleared() == 0) {
						
						//JOptionPane.showMessageDialog(null, "The Boss Room is still locked! Complete the main quest to proceed!", "Boss Room", JOptionPane.PLAIN_MESSAGE, icon);
						
						Object[] choices = {"Input Password", "Skip on Password"};
						String input = (String) JOptionPane.showInputDialog(null, "The Boss Room is still locked! Complete the main quest to proceed!\n...you can also input the password if you have it.", "Boss Room", JOptionPane.PLAIN_MESSAGE, icon, null, "");
						
						if (input != null && input.equals(question.getAreaPassword())) {
							
							/**
							 * Change the user to have completed the exercise in the model and db, and have them
							 * challenge the boss
							 */
							
							try {
								Connection conn = null;
								Statement stmt = null;

								try {
									Class.forName("com.mysql.jdbc.Driver");	        

									conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");	
									stmt = (Statement) conn.createStatement();

									String check = "UPDATE userareas " +
												   "SET UA_Clr = 1 " +
										           "WHERE U_Num = '" + user.getUserNumber() + "' AND E_Num = '" + question.getExercise() + "'";
									
									stmt.executeUpdate(check);
								} 
								catch(Exception a) {
									System.out.println(a.getMessage());	    	
									JOptionPane.showMessageDialog(null, "A database error occured.");
								}	
							}
							catch (Exception e) {
								e.printStackTrace();
							}
							
							JOptionPane.showMessageDialog(null, "The Boss Room is unlocked! Proceed to challenge the boss now!", "Boss Room", JOptionPane.PLAIN_MESSAGE, icon);
							
							question.setCleared(1);
							//moving windows
							TestMenu tframe = new TestMenu(user, question);
							//tframe.initialize(user);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "The Boss Door doesn't budge.", "Boss Room", JOptionPane.PLAIN_MESSAGE, icon);
						}
					}
					else if (emblemcheck >= 1) {
						JOptionPane.showMessageDialog(null, "The Boss Room is empty...", "Boss Room", JOptionPane.PLAIN_MESSAGE, icon);
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
					//JOptionPane.showMessageDialog(null, fp);
					String elog = cmp.compileRun(filePath);
					//JOptionPane.showMessageDialog(null, elog);
					ErrorPolling ep = new ErrorPolling(elog, user, fp);
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
		bMap.setIcon(new ImageIcon(MainMenu.class.getResource("/img/mapsmall.png")));
		bMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				WorldMap newView = new WorldMap(user, question);
				dispose();
				
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
		
		JTextArea exerciseTextArea = new JTextArea();
		exerciseTextArea.setLineWrap(true);
		exerciseTextArea.setWrapStyleWord(true);
		exerciseTextArea.setEditable(false);
		exerciseTextArea.setToolTipText("The current mission to unlock the Boss Room!");
		exerciseTextArea.setBounds(311, 113, 277, 121);
		contentPane.add(exerciseTextArea);
		
		//Exercise text area to display the current mission, if not cleared
		
		//System.out.println(question.getCleared());
		
		if (question.getCleared() == 0) {
			exerciseTextArea.setText("Main Quest:\n" + question.getMessage());
		}
		else if (emblemcheck >= 1) {
			exerciseTextArea.setText("The Boss Room has been cleared! Move on to the next area!");
		}
		else {
			exerciseTextArea.setText("The Boss Room has been unlocked! Go for the challenge!");
		}
		
		JTextArea questTextArea = new JTextArea();
		questTextArea.setLineWrap(true);
		questTextArea.setToolTipText("Contains the current quests from a previous report.");
		questTextArea.setWrapStyleWord(true);
		questTextArea.setEditable(false);
		questTextArea.setBounds(311, 254, 277, 335);
		contentPane.add(questTextArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 16, 277, 75);
		contentPane.add(scrollPane);
		
		JPanel bdgPanel = new JPanel();
		scrollPane.setViewportView(bdgPanel);
		bdgPanel.setLayout(new BoxLayout(bdgPanel, BoxLayout.X_AXIS));
		
		JLabel lblTip = new JLabel("<html>Tip: <font face=\"Tahoma\" size=\"3\">Exercises are located in the <b>program folder/exercises</b> folder (ex. QUESTBeta/exercises).</font></html>");
		lblTip.setFont(new Font("3Dventure", Font.PLAIN, 20));
		lblTip.setBounds(311, 589, 289, 51);
		contentPane.add(lblTip);
		//adding badge to scrollpane
		Connection bConn = null;
		Statement bStmt = null;
		ArrayList<Badges> badgeList = new ArrayList<>();
		ArrayList<JButton> btnArr = new ArrayList<>();
		
		try {
			bConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/quest", "root", "");
			bStmt = bConn.createStatement();
			ResultSet bRs = bStmt.executeQuery("SELECT A_num, A_Ttl, A_Msg, A_Bdg FROM achievements WHERE A_Num != 0 AND A_Bdg != 0");
			while(bRs.next()){
				Badges bdg = new Badges();
				bdg.setBadgeNum(bRs.getInt("A_num"));
				bdg.setBadgeTitle(bRs.getString("A_Ttl"));
				bdg.setBadgeDisc(bRs.getString("A_Msg"));
				bdg.setBadgeType(bRs.getInt("A_bdg"));
				//bdg.setBadgeIcon("res/no-badge.png");
				badgeList.add(bdg);
			}
			
			PreparedStatement st = bConn.prepareStatement("SELECT A_Num FROM userachievements WHERE U_num = ?");
			st.setObject(1, user.getUserNumber());
			ResultSet rs2 = st.executeQuery();
			
			while(rs2.next()){
				int achID = rs2.getInt("A_Num");
				for(int i = 0; i < badgeList.size(); i++){
					if(achID == badgeList.get(i).getBadgeNum()){
						badgeList.get(i).setAcquired(true);
						break;
					}
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i = 0; i < badgeList.size(); i++){
			
			if(badgeList.get(i).isAcquired()){
				
				String urlString = "";
				switch(badgeList.get(i).getBadgeType()){
				case 1: urlString = "/img/bronze-tb.png"; break;
				case 2: urlString = "/img/silver-tb.png"; break;
				case 3: urlString = "/img/gold-tb.png"; break;
				}
				//System.out.print("badgetest");
				JButton bdgButton = new JButton(new ImageIcon(getClass().getResource(urlString)));
				bdgButton.setToolTipText(badgeList.get(i).getBadgeTitle() + ": " + badgeList.get(i).getBadgeDisc());
				bdgPanel.add(bdgButton);
				//btnArr.add(new JButton(new ImageIcon(getClass().getResource(urlString))));
				//btnArr.get(i).setToolTipText(badgeList.get(i).getBadgeTitle() + ": " + badgeList.get(i).getBadgeDisc());
				//bdgPanel.add(btnArr.get(i));
			}
		}
		
		
		//end adding badge to scrollpane
		
		
		
		//Quest text area to display the current quests, if not cleared
		if (quests.equals("")) {
			questTextArea.setText("Sub Quests:\n\nThere are no sub-quests to do!");
		}
		else {
			questTextArea.setText("Sub Quests:\n\n" + quests);
		}
	}
}
