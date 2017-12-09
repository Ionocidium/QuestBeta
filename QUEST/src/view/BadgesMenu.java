package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import model.Badges;
import model.Database;
import model.Exercises;
import model.User;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Handles the Badges Menu to show all the badges the game has and the badges the user has obtained.
 * A black badge denotes a badge that has not been obtained, while the others (bronze, silver, gold
 * and platinum) denote the difficulty of the badge.
 * 
 * @author Ramon Arca
 */

public class BadgesMenu {

	private JFrame frame;
	private User user;
	private Exercises question;
	private Database db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//BadgesMenuAgainAgain window = new BadgesMenuAgainAgain();
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
	public BadgesMenu(User user , Exercises question, Database db) {
		this.user = user;
		this.question = question;
		this.db = db;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Badges");
		frame.setBounds(100, 100, 450, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu mainFrame = new MainMenu(user, question, db);
				mainFrame.setVisible(true);
				frame.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(btnReturn))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReturn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/img/badgestitle.png")));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		ArrayList<Badges> badgeList = new ArrayList<>();
		JButton[] btnArr = new JButton[24];
		JLabel[] ttlArr = new JLabel[24];
		JLabel[] dscArr = new JLabel[24];
		Connection conn = null;
		Statement stmt = null;
		//getting the achievement/badge details from the database
		try {
			System.out.print("test");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(db.getIP(), db.getDbu(), db.getDbp());
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT A_num, A_Ttl, A_Msg, A_Bdg FROM achievements WHERE A_Num != 0 AND A_Bdg != 0");
			while(rs.next()){
				Badges bdg = new Badges();
				bdg.setBadgeNum(rs.getInt("A_num"));
				bdg.setBadgeTitle(rs.getString("A_Ttl"));
				bdg.setBadgeDisc(rs.getString("A_Msg"));
				bdg.setBadgeType(rs.getInt("A_bdg"));
				bdg.setBadgeIcon("res/no-badge.png");
				badgeList.add(bdg);
			}
			
			PreparedStatement st = conn.prepareStatement("SELECT A_Num FROM userachievements WHERE U_num = ?");
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//getting the badges that the user has already acquired from the database
		
		for(int i = 0; i < btnArr.length; i++){
			
			if(badgeList.get(i).isAcquired()){
				String urlString = "";
				switch(badgeList.get(i).getBadgeType()){
				case 1: urlString = "/img/bronze-sm.png"; break;
				case 2: urlString = "/img/silver-sm.png"; break;
				case 3: urlString = "/img/gold-sm.png"; break;
				}
				btnArr[i] = new JButton(new ImageIcon(getClass().getResource(urlString)));
			}
			else{
			btnArr[i] = new JButton(new ImageIcon(getClass().getResource("/img/no-badge.png")));
			}
			btnArr[i].setToolTipText(badgeList.get(i).getBadgeTitle() + ": " + badgeList.get(i).getBadgeDisc());
			btnArr[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_1.add(btnArr[i]);
			
			ttlArr[i] = new JLabel(badgeList.get(i).getBadgeTitle());
			ttlArr[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			//ttlArr[i].setBorder(new EmptyBorder(10, 10, 10, 10));
			panel_1.add(ttlArr[i]);
			panel_1.add(Box.createRigidArea(new Dimension(0, 10)));
			
			//dscArr[i] = new JLabel(badgeList.get(i).getBadgeDisc());
			//dscArr[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			//panel_1.add(dscArr[i]);
		}
		
		
		frame.getContentPane().setLayout(groupLayout);
	}
}
