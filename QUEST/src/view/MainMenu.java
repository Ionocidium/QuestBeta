package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.component.PCompiler;
import controller.ErrorPolling;
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
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private String fp = "";
	
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
	public MainMenu(User user) {
		initialize(user);
	}
	
	public void initialize(User user) {
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
					//do nothing
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
		bRandomTest.setBounds(5, 502, 246, 90);
		bRandomTest.setIcon(new ImageIcon(MainMenu.class.getResource("/boss-transparent-small.png")));
		bRandomTest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bRandomTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//moving windows
					TestMenu tframe = new TestMenu(user);
					//tframe.initialize(user);
					dispose();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton bLeaderboards = new JButton("");
		bLeaderboards.setBounds(5, 300, 246, 90);
		bLeaderboards.setIcon(new ImageIcon(MainMenu.class.getResource("/rank-transparent-small.png")));
		bLeaderboards.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bLeaderboards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//moving windows
					LeaderboardMenu Lframe = new LeaderboardMenu(user);
					//tframe.initialize(user);
					dispose();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JButton bBadge = new JButton("");
		bBadge.setBounds(5, 401, 246, 90);
		bBadge.setIcon(new ImageIcon(MainMenu.class.getResource("/badges-transparent-small.png")));
		bBadge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					BadgesMenuAgain badgeFrame = new BadgesMenuAgain(user);
					dispose();
					
				}
				catch(Exception x){
					x.printStackTrace();
				}
			}
		});
		bBadge.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(5, 199, 246, 90);
		btnNewButton.setIcon(new ImageIcon(MainMenu.class.getResource("/compile-transparent-small.png")));
		btnNewButton.addActionListener(new ActionListener() {
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
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(15, 59, 62, 32);
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JButton bMap = new JButton("");
		bMap.setBounds(5, 102, 246, 90);
		bMap.setIcon(new ImageIcon(MainMenu.class.getResource("/res/mapsmall.png")));
		bMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblName);
		contentPane.add(lblArea);
		contentPane.add(bRandomTest);
		contentPane.add(btnNewButton);
		contentPane.add(bLeaderboards);
		contentPane.add(bBadge);
		contentPane.add(bMap);
		
		JLabel lblBadgesHere = new JLabel("badges here");
		lblBadgesHere.setBounds(430, 31, 72, 14);
		contentPane.add(lblBadgesHere);
		
		JTextArea txtrTest = new JTextArea();
		txtrTest.setText("test");
		txtrTest.setBounds(327, 113, 277, 243);
		contentPane.add(txtrTest);
		
		JTextArea txtrTest_1 = new JTextArea();
		txtrTest_1.setText("test2");
		txtrTest_1.setBounds(327, 390, 277, 243);
		contentPane.add(txtrTest_1);
	}
}
