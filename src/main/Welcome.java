package main;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JProgressBar;

public class Welcome extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JLabel lblNewLabel_3 = new JLabel("Loading");
	public Welcome() {
		setUndecorated(true);
		setResizable(false);
		setTitle(Main.getappname());
		setBounds(100, 100, 765, 387);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(new ImageIcon(this.getClass().getResource("/main/logo/logo.png")).getImage());

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(new Color(255, 204, 153));
		panel_1.setBounds(0, 0, 766, 387);
		getContentPane().add(panel_1);

		JLabel lblNewLabel = new JLabel("By: " + Main.getcontributor());
		lblNewLabel.setBounds(10, 11, 509, 27);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Calibri Light", Font.PLAIN, 17));
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Welcome.class.getResource("/main/logo/background.png")));
		lblNewLabel_4.setBounds(0, 0, 766, 387);
		panel_1.setLayout(null);
				
						JLabel lblNewLabel_2 = new JLabel(Main.getappname());
						lblNewLabel_2.setBounds(272, 69, 518, 114);
						lblNewLabel_2.setBackground(Color.WHITE);
						lblNewLabel_2.setForeground(Color.DARK_GRAY);
						lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 58));
						panel_1.add(lblNewLabel_2);
		
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setBounds(172, 69, 80, 89);
				lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/main/logo/logo.png")));
				panel_1.add(lblNewLabel_1);
		lblNewLabel_3.setBounds(196, 194, 194, 66);
		
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setForeground(Color.BLACK);
		panel_1.add(lblNewLabel_3);
		panel_1.add(lblNewLabel);
		panel_1.add(lblNewLabel_4);
	}
}
