package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;

public class WorldMap {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorldMap window = new WorldMap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WorldMap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 25, 764, 467);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(WorldMap.class.getResource("/img/mainmap-tp.png")));
		label.setBounds(108, 0, 559, 467);
		panel.add(label);
		
		JLabel nodeLabel_1 = new JLabel("");
		nodeLabel_1.setBounds(239, 312, 34, 24);
		panel.add(nodeLabel_1);
		
		JLabel nodeLabel_2 = new JLabel("");
		nodeLabel_2.setBounds(205, 221, 34, 24);
		panel.add(nodeLabel_2);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(294, 202, 34, 24);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(362, 144, 34, 24);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(523, 202, 34, 30);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(431, 301, 34, 30);
		panel.add(label_5);
	}

}
