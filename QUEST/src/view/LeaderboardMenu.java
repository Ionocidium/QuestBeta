package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.BadgeCount;
import model.Database;
import model.Exercises;
import model.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Handles the leaderboard view. This shows the local leaderboards and is based on the number of 
 * achievements the users have and compares with users who are near the logged in user's number 
 * of achievements.
 * 
 * @author Ramon Arca
 * @author Darren Garcia
 */

public class LeaderboardMenu {
	
	private JFrame frame;
	private JTable table;
	private Database db;
	private int usercount = 0;
	private int userindex = 0;
	private int above = 0;
	private int below = 0;
	private ArrayList<BadgeCount> list = new ArrayList<BadgeCount>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//LeaderboardMenu window = new LeaderboardMenu();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public LeaderboardMenu(User user, Exercises question, Database db) {
		initialize(user, question, db);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user, Exercises question, Database db) {
		int bottom = 0;
		this.db = db;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblLeaderboards = new JLabel(new ImageIcon(getClass().getResource("/img/leaderboardstitle.png")));
		lblLeaderboards.setBounds(30, 15, 394, 35);
		panel.add(lblLeaderboards);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Ranking"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setBounds(10, 61, 414, 100);
		table.setRowHeight(20);
		
		//obtaining from the database
		
		try {
			Connection conn = null;
			Statement stmt = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");	        

				conn = (Connection) DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());	
				stmt = (Statement) conn.createStatement();

				//getting the user list and putting it in the array
				
				PreparedStatement userlist = conn.prepareStatement("SELECT * FROM users");

				ResultSet uresult = userlist.executeQuery();
				
				while (uresult.next()) {
					list.add(new BadgeCount(uresult.getInt("U_Num"), uresult.getString("U_Usn"), 0));
					usercount++;
					if (uresult.getString("U_Usn") == user.getUsername() && uresult.getInt("U_Num") == user.getUserNumber()) {
						userindex = usercount;
					}
				}
				
				//getting the count of the achievements per user and putting it in the array
				
				PreparedStatement countachievement = conn.prepareStatement("SELECT U_Num, COUNT(A_Num) AS A_Cou FROM userachievements GROUP BY U_Num");

				ResultSet aresult = countachievement.executeQuery();
				
				while (aresult.next()) {
					for(BadgeCount b : list) {
						if (b.getUserNumber() == aresult.getInt("U_Num")) {
							b.setAchievementCount(aresult.getInt("A_Cou"));
						}
					}
				}
				
				//arranging the arraylist by achievement count
				list.sort(Comparator.comparing(BadgeCount::getAchievementCount).reversed());
				
				//find out how many users are above and below the user
				below = usercount - userindex;
				above = userindex;
				
				System.out.println("User-index = " + userindex);
				System.out.println("Above = " + above + " | Below = " + below);
				
				//preparation for adding rows
				DefaultTableModel dt = (DefaultTableModel) table.getModel();
				
				if (below >= 2 && above >= 2) {
					//user is in the middle +2, -2					
					dt.addRow(new Object[] {list.get(userindex-2).getUsername(), 1});
					dt.addRow(new Object[] {list.get(userindex-1).getUsername(), 2});
					dt.addRow(new Object[] {list.get(userindex).getUsername(), 3});
					dt.addRow(new Object[] {list.get(userindex+1).getUsername(), 4});
					dt.addRow(new Object[] {list.get(userindex+2).getUsername(), 5});
				}
				else if (below >= 3 && above == 1) {
					//user is second to the top +3, -1
					dt.addRow(new Object[] {list.get(userindex-1).getUsername(), 1});
					dt.addRow(new Object[] {list.get(userindex).getUsername(), 2});
					dt.addRow(new Object[] {list.get(userindex+1).getUsername(), 3});
					dt.addRow(new Object[] {list.get(userindex+2).getUsername(), 4});
					dt.addRow(new Object[] {list.get(userindex+3).getUsername(), 5});
				}
				else if (below >= 4 && above == 0) {
					//user is top +4, -0
					dt.addRow(new Object[] {list.get(userindex).getUsername(), 1});
					dt.addRow(new Object[] {list.get(userindex+1).getUsername(), 2});
					dt.addRow(new Object[] {list.get(userindex+2).getUsername(), 3});
					dt.addRow(new Object[] {list.get(userindex+3).getUsername(), 4});
					dt.addRow(new Object[] {list.get(userindex+4).getUsername(), 5});
				}
				else if (below == 1 && above >= 3) {
					//user is second to the bottom +1, -3
					dt.addRow(new Object[] {list.get(userindex-3).getUsername(), 1});
					dt.addRow(new Object[] {list.get(userindex-2).getUsername(), 2});
					dt.addRow(new Object[] {list.get(userindex-1).getUsername(), 3});
					dt.addRow(new Object[] {list.get(userindex).getUsername(), 4});
					dt.addRow(new Object[] {list.get(userindex+1).getUsername(), 5});
				}
				else if (below == 0 && above >= 4) {
					//user is bottom +0, -4
					dt.addRow(new Object[] {list.get(userindex-4).getUsername(), 1});
					dt.addRow(new Object[] {list.get(userindex-3).getUsername(), 2});
					dt.addRow(new Object[] {list.get(userindex-2).getUsername(), 3});
					dt.addRow(new Object[] {list.get(userindex-1).getUsername(), 4});
					dt.addRow(new Object[] {list.get(userindex).getUsername(), 5});
				}
			} 
			catch(Exception a) {
				System.out.println(a.getMessage());	    	
				JOptionPane.showMessageDialog(null, "A database error has occured.");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		panel.add(table);
		
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
		btnConfirm.setBounds(335, 227, 89, 23);
		panel.add(btnConfirm);
	}
}
